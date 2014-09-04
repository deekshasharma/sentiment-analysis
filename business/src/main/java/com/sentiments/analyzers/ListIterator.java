package com.sentiments.analyzers;
import java.util.Iterator;
import java.util.List;

public class ListIterator implements Iterator<MetaTweet>
{

    private List<MetaTweet> tweetWithSentimentList;
    int currentIndex = 0;

    /*
        Constructor of ListIterator class
    */
    public ListIterator(List<MetaTweet> tweetWithSentimentList)
    {
        this.tweetWithSentimentList = tweetWithSentimentList;
    }

    @Override
    public boolean hasNext()
    {
        if(currentIndex >= tweetWithSentimentList.size())
        {
            return false;
        }
        else
            return true;
    }

    @Override
    public MetaTweet next()
    {
        MetaTweet tweetWithSentiment = tweetWithSentimentList.get(currentIndex);
        currentIndex += 1;
        return tweetWithSentiment;
    }

    @Override
    public void remove()
    {
        // do nothing
    }
}
