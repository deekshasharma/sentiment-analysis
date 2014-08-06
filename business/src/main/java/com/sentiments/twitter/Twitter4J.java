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


    public static void main(String[] args) throws TwitterException, IOException {

        Twitter4J twitter4J = new Twitter4J();
        List<Status> tweets = twitter4J.connectToTwitter("immigration reform");
        List<String> tweetText = twitter4J.getTweetText(tweets);
        System.out.println();
        twitter4J.removeRetweets(tweetText);


    }
    /*
    public List<Status> connectToTwitter() throws TwitterException, IOException {
        Twitter twitter = TwitterFactory.getSingleton();
        AccessToken accessToken = new AccessToken(aToken, aSecret);
        twitter.setOAuthConsumer(consumerKey, consumerSecret);
        twitter.setOAuthAccessToken(accessToken);

        Query query = new Query("lang:en Israel");
        QueryResult queryResult = twitter.search(query);
        List<Status> tweets = new ArrayList<>();

        while (queryResult.hasNext())
        {
            tweets.addAll(queryResult.getTweets());
            if(tweets.size() > 50)
            {
                break;
            }
        }
        return (tweets);
    }
    */

    public List<Status> connectToTwitter(String searchKeyword) throws TwitterException, IOException {
        Twitter twitter = TwitterFactory.getSingleton();
        AccessToken accessToken = new AccessToken(aToken, aSecret);
        twitter.setOAuthConsumer(consumerKey, consumerSecret);
        twitter.setOAuthAccessToken(accessToken);

        Query query = new Query(searchKeyword);
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
    This method returns the list of text of each tweet given in the status list
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
    This method removes Retweets from the given tweet text
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





