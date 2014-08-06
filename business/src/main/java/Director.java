import com.sentiments.analyzers.SentimentAnalyzer;
import com.sentiments.analyzers.TweetWithSentiment;
import com.sentiments.twitter.Twitter4J;
import twitter4j.Status;
import twitter4j.TwitterException;

import java.io.IOException;
import java.util.List;

public class Director {

    public static void main(String[] args) throws TwitterException, IOException
    {
         Director director = new Director();
        director.getSentiment("lang:en Ukraine");
    }

    public void getSentiment(String keyword) throws TwitterException, IOException {
        Twitter4J twitter4J = new Twitter4J();
        List<Status> tweets = twitter4J.connectToTwitter(keyword);
        List<String> tweetText = twitter4J.getTweetText(tweets);
        List<String> reTweetsRemoved = twitter4J.removeRetweets(tweetText);

        SentimentAnalyzer sentimentAnalyzer = new SentimentAnalyzer();
        for(String eachText : reTweetsRemoved)
        {
            System.out.println(sentimentAnalyzer.findSentiment(eachText));
//           sentimentAnalyzer.findSentiment(eachText).toString();
        }

    }



}
