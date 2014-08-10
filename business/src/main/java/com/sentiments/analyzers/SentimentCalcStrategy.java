package com.sentiments.analyzers;

public interface SentimentCalcStrategy {

    public TweetWithSentiment calculateSentiment(String tweet);
}
