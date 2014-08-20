package com.sentiments.analyzers;

public class ApiTest
{
    public static void main(String[] args) {
        IdolAlgorithmStrategy idol = IdolAlgorithmStrategy.getInstance();
        TweetWithSentiment tweetWithSentiment  = idol.getTweetWithSentiment("Eager to know why you're not going anything about this @BarackObama this is kind of a MAJOR PROBLEM HERE! ");

        System.out.println(tweetWithSentiment.getLine());
        System.out.println(tweetWithSentiment.getSentiment());

    }
}
