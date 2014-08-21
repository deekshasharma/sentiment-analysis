package com.sentiments.analyzers;

public class ApiTest
{
    public static void main(String[] args) {
        IdolAlgorithmStrategy idol = IdolAlgorithmStrategy.getInstance();
        TweetWithSentiment tweetWithSentiment  = idol.getTweetWithSentiment("@GovJayNixon you have Failed Us. You Never know how Weak Your a Leader is until You NEED Him 2 Lead. Thanks for Nothing! @BarackObama #STL");

        System.out.println(tweetWithSentiment.getLine());
        System.out.println(tweetWithSentiment.getSentiment());

    }
}
                                                                                  //Eager to know why you're not going anything about this @BarackObama this is kind of a MAJOR PROBLEM HERE!