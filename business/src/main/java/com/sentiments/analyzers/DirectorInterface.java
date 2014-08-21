package com.sentiments.analyzers;

import java.util.Iterator;

public interface DirectorInterface {

    public void getSentiment(String keyword, String algorithm);
    public Iterator<TweetWithSentiment> createIterator();
}
