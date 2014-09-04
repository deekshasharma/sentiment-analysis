package com.sentiments.facade;

import com.sentiments.analyzers.MetaTweet;
import com.sentiments.analyzers.SentimentStrategy;

import java.util.Iterator;

public interface DirectorInterface {

    public void getSentiment(String keyword, SentimentStrategy strategy);
    public Iterator<MetaTweet> createIterator();

}
