package com.sentiments.facade;

import com.sentiments.analyzers.MetaTweet;
import com.sentiments.analyzers.SentimentStrategy;

import java.util.Iterator;

public interface DirectorInterface {

    public void calculateSentiment(String keyword, SentimentStrategy strategy);
    public Iterator<MetaTweet> createIterator();

}
