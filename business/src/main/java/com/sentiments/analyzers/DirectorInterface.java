package com.sentiments.analyzers;

import com.sentiments.analyzers.TweetWithSentiment;

import java.util.Iterator;

public interface DirectorInterface {

    public void getSentiment(String keyword, String algorithm);
    public Iterator<TweetWithSentiment> createIterator();
}
