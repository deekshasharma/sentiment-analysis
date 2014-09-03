package com.sentiments.analyzers;

import java.util.Iterator;

public interface DirectorInterface {

    public void getSentiment(String keyword, SentimentStrategy strategy);
    public Iterator<TweetWithSentiment> createIterator();
}
