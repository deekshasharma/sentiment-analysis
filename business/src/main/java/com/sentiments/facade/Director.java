package com.sentiments.facade;

import com.sentiments.analyzers.*;
import com.sentiments.twitter.DataSource;
import com.sentiments.twitter.TwitterDataSet;
import twitter4j.TwitterException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Director implements DirectorInterface
{
        private static SentimentStrategy strategy;
        private List<MetaTweet> metaTweetList = new ArrayList<>();
        private DataSource dataSet =  new TwitterDataSet();


        /*
        This method calculate the sentiment for all tweets and save each MetaTweet object
        in the metaTweetList
        */
        public void calculateSentiment(String keyword, SentimentStrategy strategy)
        {
            try {
                List<String> allTweets = getAllTweets(keyword);
                for(String tweetText: allTweets)
                {
                    metaTweetList.add(strategy.getMetaTweet(tweetText));
                }
            } catch (TwitterException t) {
                System.out.println("Error due to TwitterException");
            } catch (IOException i) {
                System.out.println("Error due to IOException");
            }
        }

        /*
        This method returns a list of Tweets from the Twitter data set for a specific keyword
         */
        private List<String> getAllTweets(String keyword) throws TwitterException, IOException {
            return dataSet.getTweets(keyword);
        }

        /*
        This method returns an iterator over the List<MetaTweet>
         */
        public Iterator<MetaTweet> createIterator() {
            return new ListIterator(metaTweetList);
        }

}
