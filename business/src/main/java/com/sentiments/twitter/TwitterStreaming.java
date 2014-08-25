package com.sentiments.twitter;

import com.google.common.collect.Lists;
import com.twitter.hbc.ClientBuilder;
import com.twitter.hbc.core.Constants;
import com.twitter.hbc.core.endpoint.StatusesSampleEndpoint;
import com.twitter.hbc.core.processor.StringDelimitedProcessor;
import com.twitter.hbc.httpclient.BasicClient;
import com.twitter.hbc.httpclient.auth.Authentication;
import com.twitter.hbc.httpclient.auth.OAuth1;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TwitterStreaming
{
    private static TwitterStreaming ourInstance = new TwitterStreaming();
    private static final String CONSUMER_KEY = "pQ72WX3zCFTw9bcJw6EgqZF6w";
    private static final String CONSUMER_SECRET = "YWsY1i684Nw3NdL27ixUQIwMoIx2SV7i9TwUdQrNKNJB98fVjz";
    private static final String ACCESS_TOKEN = "2618859422-Yof0JYLSEO8reaxLCCUI38lENldnM9NvjHWgpK9";
    private static final String ACCESS_TOKEN_SECRET = "3fdIW91cxFvY80Ci90HWrgC3dfDKxudPsUO9cnvnPeaAh";
    private final BlockingQueue<String> queue;


    public static TwitterStreaming getInstance()
    {

        return ourInstance;
    }

    private TwitterStreaming()
    {
        //create a blocking queue to collect messages from the stream
        queue = new LinkedBlockingQueue<String>(100000);

        //Authenticate via OAuth to connect to Twitter
        Authentication auth = new OAuth1(CONSUMER_KEY,CONSUMER_SECRET,ACCESS_TOKEN,ACCESS_TOKEN_SECRET);

        //create an endpoint
        StatusesSampleEndpoint sampleEndpoint = new StatusesSampleEndpoint();
        sampleEndpoint.stallWarnings(false);
        sampleEndpoint.languages(Lists.newArrayList("en"));

        // create a new basic client
        BasicClient client = new ClientBuilder()
                .name("tweets")
                .hosts(Constants.STREAM_HOST)
                .endpoint(sampleEndpoint)
                .authentication(auth)
                .processor(new StringDelimitedProcessor(queue))
                .build();

        // Create connection
        client.connect();
    }

    protected BlockingQueue<String> getQueue()
    {
        return queue;
    }

    protected void parseJSON(String tweet)
    {

    }
}
