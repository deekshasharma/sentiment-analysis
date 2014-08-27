package com.sentiments.twitter;

import twitter4j.*;
import twitter4j.auth.AccessToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Twitter4J {
    private static String aToken = "171730389-nssFtwPcTzQvAAf63obf3YR8QYO8KnahffduJgto";
    private static String aSecret = "DasPJdxxtzJICVyyLjScOBWvEPfPWGihzlgYpLIOddnU8";
    private static String consumerKey = "I1gFVs8hhlckQY85PWHpAZhkB";
    private static String consumerSecret = "zRVeRPjCyGuJxMpNRsvKtQeA23xN5l5ijoKAwzTk3UI3mLFW8W";
    private static Twitter twitter = TwitterFactory.getSingleton();

    private static Twitter4J ourInstance = new Twitter4J();

    public static Twitter4J getInstance()
    {
        return ourInstance;
    }

    private Twitter4J()
    {
        AccessToken accessToken = new AccessToken(aToken, aSecret);
        twitter.setOAuthConsumer(consumerKey, consumerSecret);
        twitter.setOAuthAccessToken(accessToken);
    }

    /*
    This method returns the List<Status> for a specific keyword
     */
//    public List<Status> getAllStatus(String searchKeyword) throws TwitterException, IOException {
//
//        Query query = new Query("lang:en "+searchKeyword);
//        QueryResult queryResult = twitter.search(query);
//        List<Status> tweets = new ArrayList<>();
//
//        while (queryResult.hasNext())
//        {
//            if(tweets.size() > 10)
//            {
//                break;
//            }
//            tweets.addAll(queryResult.getTweets());
//        }
//        return (tweets);
//    }

    public List<String> getData(String searchKeyword) throws TwitterException, IOException {

        Query query = new Query("lang:en "+searchKeyword);
        QueryResult queryResult = twitter.search(query);
        List<Status> tweets = new ArrayList<>();

        while (queryResult.hasNext())
        {
            if(tweets.size() > 10)
            {
                break;
            }
            tweets.addAll(queryResult.getTweets());
        }
        List<String> tweetText = getTweetText(tweets);
        return (removeRetweets(tweetText));
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
