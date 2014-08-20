package com.sentiments.analyzers;

public interface SentimentCalcStrategy {

    public TweetWithSentiment getTweetWithSentiment(String tweet);
}
