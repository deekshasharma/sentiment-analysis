package com.sentiments.twitter;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class TwitterConnection {

    private static String aToken = "171730389-nssFtwPcTzQvAAf63obf3YR8QYO8KnahffduJgto";
    private static String aSecret = "DasPJdxxtzJICVyyLjScOBWvEPfPWGihzlgYpLIOddnU8";

    private static String consumerKey = "I1gFVs8hhlckQY85PWHpAZhkB";
    private static String consumerSecret = "zRVeRPjCyGuJxMpNRsvKtQeA23xN5l5ijoKAwzTk3UI3mLFW8W";

    private static TwitterConnection ourInstance = new TwitterConnection();

    public static TwitterConnection getInstance()
    {
        return ourInstance;
    }

    private TwitterConnection()
    {
        Twitter twitter = TwitterFactory.getSingleton();
        AccessToken accessToken = new AccessToken(aToken, aSecret);
        twitter.setOAuthConsumer(consumerKey, consumerSecret);
        twitter.setOAuthAccessToken(accessToken);
    }
}
