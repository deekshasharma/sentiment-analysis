package com.sentiments.analyzers;

import com.sentiments.twitter.Twitter4J;
import org.xml.sax.SAXException;
import twitter4j.Status;
import twitter4j.TwitterException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Director implements DirectorInterface {

    private static SentimentStrategy sentimentCalcStrategy;
    private List<TweetWithSentiment> tweetWithSentimentList = new ArrayList<>();


    /*
    This method returns the list of tweetWithSentiment objects for a given keyword.
     */
    public void getSentiment(String keyword, String algorithm) //throws TwitterException, IOException, XPathExpressionException, SAXException, ParserConfigurationException
    {
        try {
            List<String> allTweets = getAllTweets(keyword);
            System.out.println("Tweets collected and size is : " + allTweets.size());
            if (algorithm.equalsIgnoreCase("A")) {
                sendTweetsToAlchemy(allTweets);
            } else if (algorithm.equalsIgnoreCase("N")) {
                sendTweetsToNLP(allTweets);
            } else {
                sendTweetsToIdol(allTweets);
            }
        } catch (TwitterException t) {
            System.out.println("Error due to TwitterException");
        } catch (IOException i) {
            System.out.println("Error due to IOException");
        } catch (XPathExpressionException e) {
            System.out.println("Error due to XPathExpression");
        } catch (SAXException s) {
            System.out.println("Error to SAXException");
        } catch (ParserConfigurationException p) {
            System.out.println("Error due to ParserConfigurationException");
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
    This method send the tweets to Alchemy and update tweetWithSentimentList.
     */
    private void sendTweetsToAlchemy(List<String> allTweets) throws SAXException, ParserConfigurationException, XPathExpressionException, IOException {
        sentimentCalcStrategy = new AlchemyAlgorithmStrategy();

        for (String eachTweet : allTweets)
        {
            tweetWithSentimentList.add(sentimentCalcStrategy.getTweetWithSentiment(eachTweet));
        }
    }

    /*
    This method send the tweets to NLP and update tweetWithSentimentList.
     */
    private void sendTweetsToNLP(List<String> allTweets) {
        sentimentCalcStrategy = new NlpAlgorithmStrategy();

        for (String eachText : allTweets) {
            tweetWithSentimentList.add(sentimentCalcStrategy.getTweetWithSentiment(eachText));
        }
    }

    /*
        This method send the tweets to Alchemy and update tweetWithSentimentList.
     */
    private void sendTweetsToIdol(List<String> allTweets) {
        sentimentCalcStrategy = IdolAlgorithmStrategy.getInstance();

        for (String eachText : allTweets) {
            tweetWithSentimentList.add(sentimentCalcStrategy.getTweetWithSentiment(eachText));
        }
    }

    /*
    This method returns an iterator over the List<tweetWithSentiment>
     */
    public Iterator<TweetWithSentiment> createIterator() {
        System.out.println("List= " + tweetWithSentimentList);
        return new ListIterator(tweetWithSentimentList);
    }
}