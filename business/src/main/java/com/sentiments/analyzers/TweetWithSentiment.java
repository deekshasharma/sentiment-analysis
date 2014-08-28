package com.sentiments.analyzers;

public class TweetWithSentiment {

    private String tweetText;
    private String sentiment;


    public TweetWithSentiment(String tweetText, String sentiment) {
        super();
        this.tweetText = tweetText;
        this.sentiment = sentiment;
    }

    public String getTweet() {
        return tweetText;
    }

    public String getSentiment() {
        return sentiment;
    }


    @Override
    public String toString() {
        return "[tweetText=" + tweetText + ", sentiment=" + sentiment + "]";
    }

}
