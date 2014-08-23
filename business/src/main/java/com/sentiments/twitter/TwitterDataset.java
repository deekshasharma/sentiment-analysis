package com.sentiments.twitter;

import twitter4j.Status;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TwitterDataset {


    private static final String TWITTER_DATA_PATH = "/Users/deeksha/IdeaProjects/sentiment-analysis-project/business/src/main/resources/tweets.txt";


    public static void main(String[] args)
    {
        TwitterDataset twitterDataset = new TwitterDataset();
        List<String> tweetText = twitterDataset.getTweetText();
        System.out.println(tweetText);
//        System.out.println(twitterDataset.removeRetweets(tweetText));

    }

    /*
  This method returns the list containing texts of all tweets in the status list
   */
    protected List<String> getTweetText()
    {
        List<String> tweetText = new ArrayList<>();
        try
        {
            String eachLine;
            File file = new File(TWITTER_DATA_PATH);
            BufferedReader br = new BufferedReader(new FileReader(file));
            while((eachLine = br.readLine()) != null)
            {
                   tweetText.add(eachLine);
            }

        }catch (IOException e)
        {
            System.out.println("Error due to IOException");
        }



        return tweetText;
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
