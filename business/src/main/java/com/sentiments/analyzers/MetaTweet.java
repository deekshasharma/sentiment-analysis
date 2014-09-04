package com.sentiments.analyzers;

public class MetaTweet {

    private String tweetText;
    private String sentiment;
    private String geoTag;
    private String timestamp;
    private String userName;


    private MetaTweet(MetaTweetBuilder metaTweetBuilder)
    {
        this.tweetText = metaTweetBuilder.tweetText;
        this.sentiment = metaTweetBuilder.sentiment;
        this.geoTag = metaTweetBuilder.geoTag;
        this.timestamp = metaTweetBuilder.timestamp;
        this.userName = metaTweetBuilder.userName;
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
    public static class MetaTweetBuilder
    {
        private String tweetText;
        private String sentiment;
        private String geoTag;
        private String timestamp;
        private String userName;

        public MetaTweetBuilder(String tweetText, String sentiment)
        {
            this.tweetText = tweetText;
            this.sentiment = sentiment;
        }

        public MetaTweetBuilder buildGeoTag(String geoTag)
        {
            this.geoTag = geoTag;
            return this;
        }

        public MetaTweetBuilder buildTimeStamp(String timestamp)
        {
            this.timestamp = timestamp;
            return this;
        }

        public MetaTweetBuilder buildUserName(String userName)
        {
            this.userName = userName;
            return this;
        }

        public MetaTweet build()
        {
            MetaTweet metaTweet = new MetaTweet(this);
            return metaTweet;
        }
    }

}
