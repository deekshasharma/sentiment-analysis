package com.sentiments.analyzers;
import java.util.Iterator;
import java.util.List;

public class ListIterator implements Iterator<MetaTweet>
{

    private List<MetaTweet> metaTweetList;
    int currentIndex = 0;

    /*
        Constructor of ListIterator class
    */
    public ListIterator(List<MetaTweet> metaTweetList1)
    {
        this.metaTweetList = metaTweetList1;
    }

    @Override
    public boolean hasNext()
    {
        if(currentIndex >= metaTweetList.size())
        {
            return false;
        }
        else
            return true;
    }

    @Override
    public MetaTweet next()
    {
        MetaTweet tweetWithSentiment = metaTweetList.get(currentIndex);
        currentIndex += 1;
        return tweetWithSentiment;
    }

    @Override
    public void remove()
    {
        // do nothing
    }
}
