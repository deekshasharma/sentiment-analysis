package com.sentiments.analyzers;

public interface SentimentStrategy {

    public TweetWithSentiment getTweetWithSentiment(String tweet);
}
