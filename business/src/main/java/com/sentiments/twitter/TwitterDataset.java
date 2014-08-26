package com.sentiments.twitter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TwitterDataSet {


    private static final String TWITTER_DATA_PATH = "/Users/deeksha/IdeaProjects/sentiment-analysis-project/business/src/main/resources/tweets.txt";


    /*
  This method returns the list containing texts of all tweets in the status list
   */
    public List<String> getTweets(String keyword)
    {
        List<String> tweetText = new ArrayList<>();
        try
        {
            String eachLine;
            File file = new File(TWITTER_DATA_PATH);
            BufferedReader br = new BufferedReader(new FileReader(file));
            while((eachLine = br.readLine()) != null)
            {
                if(eachLine.toLowerCase().matches(".*\\b"+keyword.toLowerCase()+"\\b.*") && tweetText.size() <= 10)
                {tweetText.add(eachLine);}

            }

        }catch (IOException e)
        {
            System.out.println("Error due to IOException");
        }
        return removeRetweets(tweetText);
    }

     /*
    This method removes reTweets from the given tweet text
     */
    protected List<String> removeRetweets(List<String> tweetText)
{
    Iterator<String> iterator = tweetText.iterator();

    while (iterator.hasNext())
    {
        String text = iterator.next();
        if(text.contains("RT @"))
        {
            iterator.remove();
        }
    }
    return tweetText;
}
}
