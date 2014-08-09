import com.sentiments.analyzers.SentimentAnalyzer;
import com.sentiments.analyzers.SentimentAnalyzerAlchemy;
import com.sentiments.analyzers.TweetWithSentiment;
import com.sentiments.twitter.Twitter4J;
import org.xml.sax.SAXException;
import twitter4j.Status;
import twitter4j.TwitterException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Director {

    public static void main(String[] args) throws TwitterException, IOException, ParserConfigurationException, XPathExpressionException, SAXException {
         Director director = new Director();
        director.getSentiment("Ukraine", "A");
    }


    private void getSentiment(String keyword, String choice) throws TwitterException, IOException, XPathExpressionException, SAXException, ParserConfigurationException
    {
        List<String> allTweets = getAllTweets(keyword);
        if (choice.equalsIgnoreCase("A"))
        {
             getSentimentAlchemy(allTweets);
        }
        else if(choice.equalsIgnoreCase("N"))
        {
            getSentimentObjectsNLP(allTweets);
        }
        else  //throw Invalid choice exception
        {
            System.out.printf("Invalid choice of algorithm");
        }
    }

    /*
    This method returns the sentiments using AlchemyAPI
     */
    private void getSentimentAlchemy(List<String> allTweets) throws SAXException, ParserConfigurationException, XPathExpressionException, IOException {
        SentimentAnalyzerAlchemy alchemy = new SentimentAnalyzerAlchemy();


//        for(String eachTweet: allTweets)
//        {
//
//        }

//        alchemy.getSentimentAlchemy("");
    }

    /*
    This method returns the list of tweetSentiment objects received from NLP Sentiment Analyzer
     */
    public List<TweetWithSentiment> getSentimentObjectsNLP(List<String> allTweets)
     {
        SentimentAnalyzer sentimentAnalyzer = new SentimentAnalyzer();
        List<TweetWithSentiment> tweetWithSentimentList = new ArrayList<>();

        for(String eachText : allTweets)
        {
            System.out.println(sentimentAnalyzer.findSentiment(eachText));
            tweetWithSentimentList.add(sentimentAnalyzer.findSentiment(eachText));
        }
        return tweetWithSentimentList;
    }

    /*
    This method returns all the Tweets for a specific keyword. It does not contain reTweets
     */
    private List<String> getAllTweets(String keyword) throws TwitterException, IOException {
        Twitter4J twitter4J = new Twitter4J();
        List<Status> tweets = twitter4J.connectToTwitter(keyword);
        List<String> tweetText = twitter4J.getTweetText(tweets);
        List<String> reTweetsRemoved = twitter4J.removeRetweets(tweetText);
        return reTweetsRemoved;
    }




}
