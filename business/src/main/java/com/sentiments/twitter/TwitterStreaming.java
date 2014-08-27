package com.sentiments.twitter;

import com.google.common.collect.Lists;
import com.twitter.hbc.ClientBuilder;
import com.twitter.hbc.core.Client;
import com.twitter.hbc.core.Constants;
import com.twitter.hbc.core.Hosts;
import com.twitter.hbc.core.HttpHosts;
import com.twitter.hbc.core.endpoint.Location;
import com.twitter.hbc.core.endpoint.StatusesFilterEndpoint;
import com.twitter.hbc.core.processor.StringDelimitedProcessor;
import com.twitter.hbc.httpclient.auth.Authentication;
import com.twitter.hbc.httpclient.auth.OAuth1;
import org.json.JSONObject;
import java.util.Collections;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TwitterStreaming {

    private static final String CONSUMER_KEY = "pQ72WX3zCFTw9bcJw6EgqZF6w";
    private static final String CONSUMER_SECRET = "YWsY1i684Nw3NdL27ixUQIwMoIx2SV7i9TwUdQrNKNJB98fVjz";
    private static final String ACCESS_TOKEN = "2618859422-Yof0JYLSEO8reaxLCCUI38lENldnM9NvjHWgpK9";
    private static final String ACCESS_TOKEN_SECRET = "3fdIW91cxFvY80Ci90HWrgC3dfDKxudPsUO9cnvnPeaAh";

    //create a blocking queue to collect messages from the stream
    private static final BlockingQueue<String> queue = new LinkedBlockingQueue<>(100000);
    private static Client client;


    public static Client getInstance() {
        try {
            if (client == null)
            {
                final Hosts hoseBirdHosts = new HttpHosts(Constants.STREAM_HOST);
                final StatusesFilterEndpoint hoseBirdEndpoint = new StatusesFilterEndpoint();
                final Location location = new Location(new Location.Coordinate(-180, -90), new Location.Coordinate(180, 90));
                hoseBirdEndpoint.locations(Collections.singletonList(location));

                hoseBirdEndpoint.languages(Lists.newArrayList("en"));

                final Authentication hoseBirdAuth = new OAuth1(CONSUMER_KEY, CONSUMER_SECRET, ACCESS_TOKEN, ACCESS_TOKEN_SECRET);
                final ClientBuilder builder = new ClientBuilder()
                        .name("tweetStream-client")                              // optional: mainly for the logs
                        .hosts(hoseBirdHosts)
                        .authentication(hoseBirdAuth)
                        .endpoint(hoseBirdEndpoint)
                        .processor(new StringDelimitedProcessor(queue));

                synchronized (TwitterStreaming.class) {
                    client = builder.build();
                }
                client.connect();

            }}catch(Exception e){
                System.out.println("Exception in building twitter client:" + e.getMessage());
                e.printStackTrace();
            }
        return client;
    }

    /*
    This method returns the BlockingQueue
     */
    public static BlockingQueue<String> getQueue() {
        return queue;
    }

    /*
    This method returns the text of the tweet after parsing JSON
     */
    public static String parseJSON(String tweet) {
        JSONObject jsonObject = new JSONObject(tweet);
        return jsonObject.getString("text");

    }

}
