package com.sentiments.twitter;

import twitter4j.*;
import twitter4j.auth.AccessToken;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Twitter4J {


    public List<Status> getAllStatus(String searchKeyword) throws TwitterException, IOException {
        Twitter twitter = TwitterFactory.getSingleton();
        TwitterConnection connection = TwitterConnection.getInstance();
        Query query = new Query("lang:en "+searchKeyword);
        QueryResult queryResult = twitter.search(query);
        List<Status> tweets = new ArrayList<>();

        while (queryResult.hasNext())
        {
            if(tweets.size() > 100)
            {
                break;
            }
            tweets.addAll(queryResult.getTweets());
        }
        return (tweets);
    }

    /*
    This method returns the list containing texts of all tweets in the status list
     */
    public List<String> getTweetText(List<Status> tweets)
    {
        List<String> tweetText = new ArrayList<>();

        for (Status eachStatus : tweets)
        {
            tweetText.add(eachStatus.getText());
        }
        return tweetText;
    }


    /*
    This method removes reTweets from the given tweet text
     */
    public List<String> removeRetweets(List<String> tweetText)
    {
        Iterator<String> iterator = tweetText.iterator();

        while (iterator.hasNext())
        {
            String text = iterator.next();
            if(text.contains("RT @"))
            {
                iterator.remove();
            }
        }
        return tweetText;
    }
}





