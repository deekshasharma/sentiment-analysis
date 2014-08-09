package com.sentiments.analyzers;

/**
 * Per line sentiment
 * 
 * @author shekhargulati
 * 
 */
public class TweetWithSentiment {

    private String line;
    private String sentiment;

    public TweetWithSentiment()
    {

    }

    public TweetWithSentiment(String line, String sentiment) {
        super();
        this.line = line;
        this.sentiment = sentiment;
    }

    public String getLine() {
        return line;
    }

    public String getSentiment() {
        return sentiment;
    }


    @Override
    public String toString() {
        return "TweetWithSentiment [line=" + line + ", sentiment=" + sentiment + "]";
    }

}
