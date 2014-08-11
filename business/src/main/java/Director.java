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


public class Director {

    private static AlchemyAlgorithmStrategy alchemy;
    private static NlpAlgorithmStrategy nlp;

//    public static void main(String[] args) throws TwitterException, IOException, ParserConfigurationException, XPathExpressionException, SAXException {
//        Director director = new Director();
//        director.getSentiment("Iraq", "A");
//    }


    public List<TweetWithSentiment> getSentiment(String keyword, String choice) throws TwitterException, IOException, XPathExpressionException, SAXException, ParserConfigurationException
    {
        List<String> allTweets = getAllTweets(keyword);
        System.out.println("Tweets collected and size is : "+allTweets.size());
        if (choice.equalsIgnoreCase("A"))
        {
            System.out.println("A is selected");
             return getSentimentAlchemy(allTweets);
        }
        else
        {
            return getSentimentObjectsNLP(allTweets);
        }

    }

    /*
    This method returns all the Tweets for a specific keyword. It does not contain reTweets
     */
    private List<String> getAllTweets(String keyword) throws TwitterException, IOException {
        Twitter4J twitter4J = Twitter4J.getInstance();
        List<Status> tweets = twitter4J.getAllStatus(keyword);
        List<String> tweetText = twitter4J.getTweetText(tweets);
        List<String> reTweetsRemoved = twitter4J.removeRetweets(tweetText);
        return reTweetsRemoved;
    }

    /*
    This method returns the sentiments using AlchemyAPI
     */
    private List<TweetWithSentiment> getSentimentAlchemy(List<String> allTweets) throws SAXException, ParserConfigurationException, XPathExpressionException, IOException
    {
        AlchemyAlgorithmStrategy alchemy = new AlchemyAlgorithmStrategy();
        List<TweetWithSentiment> tweetWithSentimentList = new ArrayList<>();

        for(String eachTweet: allTweets)
        {
            System.out.println("Tweet = "+ alchemy.calculateSentiment(eachTweet).getLine());
            System.out.println("Sentiment = "+alchemy.calculateSentiment(eachTweet).getSentiment());
            tweetWithSentimentList.add(alchemy.calculateSentiment(eachTweet));
        }
        return tweetWithSentimentList;
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
            System.out.println("Tweet = "+nlp.calculateSentiment(eachText).getLine());
            System.out.println("Sentiment = "+nlp.calculateSentiment(eachText).getSentiment());
            tweetWithSentimentList.add(nlp.calculateSentiment(eachText));
        }
        return tweetWithSentimentList;
    }
}
