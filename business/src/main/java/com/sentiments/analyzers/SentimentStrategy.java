package com.sentiments.analyzers;

public interface SentimentStrategy {

    public MetaTweet getMetaTweet(String tweetText);
}
