package com.sentiments;
import com.sentiments.analyzers.TweetWithSentiment;
import java.util.Iterator;
import java.util.List;


public class ListIterator implements Iterator<TweetWithSentiment>
{
    private List<TweetWithSentiment> tweetWithSentimentList;
    int currentIndex = 0;

    /*
        Constructor of ListIterator class
    */
    public ListIterator(List<TweetWithSentiment> tweetWithSentimentList)
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
    public TweetWithSentiment next()
    {
          TweetWithSentiment tweetWithSentiment = tweetWithSentimentList.get(currentIndex);
        currentIndex += 1;
        return tweetWithSentiment;
    }

    @Override
    public void remove()
    {

    }
}
