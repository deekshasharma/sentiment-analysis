package com.sentiments.facade;

import com.sentiments.analyzers.*;
import com.sentiments.twitter.Twitter4J;
import com.sentiments.twitter.TwitterDataSet;
import twitter4j.TwitterException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Director implements DirectorInterface
{
        private static SentimentStrategy strategy;
        private List<MetaTweet> tweetWithSentimentList = new ArrayList<>();


        /*
        This method returns the list of tweetWithSentiment objects for a given keyword.
        */
        public void getSentiment(String keyword, SentimentStrategy strategy)
        {
            try {
                List<String> allTweets = getAllTweets(keyword);
                for(String tweetText: allTweets)
                {
                    tweetWithSentimentList.add(strategy.getMetaTweet(tweetText));
                }
            } catch (TwitterException t) {
                System.out.println("Error due to TwitterException");
            } catch (IOException i) {
                System.out.println("Error due to IOException");
            }
        }

        /*
        This method returns all the Tweets for a specific keyword. It does not contain reTweets
         */
        private List<String> getTweetsFromTwitter(String keyword) throws TwitterException, IOException {
            Twitter4J twitter4J = Twitter4J.getInstance();
//        List<Status> tweets = twitter4J.getAllStatus(keyword);
//        List<String> tweetText = twitter4J.getTweets(tweets);
//        List<String> reTweetsRemoved = twitter4J.removeRetweets(tweetText);
            List<String> tweetText = twitter4J.getData(keyword);
            return tweetText;
        }

        /*
        This method returns a list of Tweets from the Twitter data set for a specific keyword
         */
        private List<String> getAllTweets(String keyword) throws TwitterException, IOException {
            TwitterDataSet dataSet = new TwitterDataSet();
            return dataSet.getTweets(keyword);
        }

        /*
        This method returns an iterator over the List<tweetWithSentiment>
         */
        public Iterator<MetaTweet> createIterator() {
            return new ListIterator(tweetWithSentimentList);
        }
    }