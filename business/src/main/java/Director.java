import com.sentiments.analyzers.*;
import com.sentiments.twitter.Twitter4J;
import org.xml.sax.SAXException;
import twitter4j.Status;
import twitter4j.TwitterException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Document;


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
    This method returns all the Tweets for a specific keyword. It does not contain reTweets
     */
    private List<String> getAllTweets(String keyword) throws TwitterException, IOException {
        Twitter4J twitter4J = new Twitter4J();
        List<Status> tweets = twitter4J.connectToTwitter(keyword);
        List<String> tweetText = twitter4J.getTweetText(tweets);
        List<String> reTweetsRemoved = twitter4J.removeRetweets(tweetText);
        return reTweetsRemoved;
    }

    /*
    This method returns the sentiments using AlchemyAPI
     */
    private void getSentimentAlchemy(List<String> allTweets) throws SAXException, ParserConfigurationException, XPathExpressionException, IOException
    {
        AlchemyAlgorithmStrategy alchemy = new AlchemyAlgorithmStrategy();
        List<TweetWithSentiment> tweetWithSentimentList = new ArrayList<>();

        for(String eachTweet: allTweets)
        {
            System.out.println(alchemy.calculateSentiment(eachTweet).getLine());
            System.out.println(alchemy.calculateSentiment(eachTweet).getSentiment());
            tweetWithSentimentList.add(alchemy.calculateSentiment(eachTweet));
        }
    }

    /*
    This method returns the list of tweetSentiment objects received from NLP Sentiment Analyzer
     */
    public List<TweetWithSentiment> getSentimentObjectsNLP(List<String> allTweets)
     {
        NlpAlgorithmStrategy nlp = new NlpAlgorithmStrategy();
        List<TweetWithSentiment> tweetWithSentimentList = new ArrayList<>();

        for(String eachText : allTweets)
        {
            System.out.println(nlp.calculateSentiment(eachText));
            tweetWithSentimentList.add(nlp.calculateSentiment(eachText));
        }
        return tweetWithSentimentList;
    }
}
