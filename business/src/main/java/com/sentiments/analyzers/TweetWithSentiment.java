package com.sentiments.analyzers;

public class TweetWithSentiment{

    private String tweetText;
    private String sentiment;
    private String geoTag;
    private String timestamp;
    private String userName;


    private TweetWithSentiment(TweetBuilder tweetBuilder)
    {
        this.tweetText = tweetBuilder.tweetText;
        this.sentiment = tweetBuilder.sentiment;
        this.geoTag = tweetBuilder.geoTag;
        this.timestamp = tweetBuilder.timestamp;
        this.userName = tweetBuilder.userName;
    }

    public String getGeoTag() {
        return geoTag;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getUserName() {
        return userName;
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


    /*
        Inner Builder class to build tweet
     */
    public static class TweetBuilder
    {
        private String tweetText;
        private String sentiment;
        private String geoTag;
        private String timestamp;
        private String userName;

        public TweetBuilder(String tweetText, String sentiment)
        {
            this.tweetText = tweetText;
            this.sentiment = sentiment;
        }

        public TweetBuilder buildGeoTag(String geoTag)
        {
            this.geoTag = geoTag;
            return this;
        }

        public TweetBuilder buildTimeStamp(String timestamp)
        {
            this.timestamp = timestamp;
            return this;
        }

        public TweetBuilder buildUserName(String userName)
        {
            this.userName = userName;
            return this;
        }

        public TweetWithSentiment build()
        {
            TweetWithSentiment tweetWithSentiment = new TweetWithSentiment(this);
            return tweetWithSentiment;
        }
    }

}
