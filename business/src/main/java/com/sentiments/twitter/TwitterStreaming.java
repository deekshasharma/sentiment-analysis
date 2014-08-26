package com.sentiments.twitter;

import com.google.common.collect.Lists;
import com.twitter.hbc.ClientBuilder;
import com.twitter.hbc.core.Client;
import com.twitter.hbc.core.Constants;
import com.twitter.hbc.core.endpoint.Location;
import com.twitter.hbc.core.endpoint.StatusesSampleEndpoint;
import com.twitter.hbc.core.processor.StringDelimitedProcessor;
import com.twitter.hbc.httpclient.BasicClient;
import com.twitter.hbc.httpclient.auth.Authentication;
import com.twitter.hbc.httpclient.auth.OAuth1;
import org.json.JSONObject;
import java.util.Collections;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TwitterStreaming
{
    private static TwitterStreaming ourInstance = new TwitterStreaming();
    private static final String CONSUMER_KEY = "pQ72WX3zCFTw9bcJw6EgqZF6w";
    private static final String CONSUMER_SECRET = "YWsY1i684Nw3NdL27ixUQIwMoIx2SV7i9TwUdQrNKNJB98fVjz";
    private static final String ACCESS_TOKEN = "2618859422-Yof0JYLSEO8reaxLCCUI38lENldnM9NvjHWgpK9";
    private static final String ACCESS_TOKEN_SECRET = "3fdIW91cxFvY80Ci90HWrgC3dfDKxudPsUO9cnvnPeaAh";

    //create a blocking queue to collect messages from the stream
    private static final BlockingQueue<String> queue = new LinkedBlockingQueue<>(100000);
    private static Client client;


//    public static TwitterStreaming getInstance()
//    {
//
//        return ourInstance;
//    }

    public static Client getInstance()
    {
        try {

            //create an endpoint
            StatusesSampleEndpoint sampleEndpoint = new StatusesSampleEndpoint();
            sampleEndpoint.stallWarnings(false);
            sampleEndpoint.languages(Lists.newArrayList("language = en"));
//            Location location = new Location(new Location.Coordinate(-122.75, -36.8), new Location.Coordinate(-121.75, 37.8));
//            sampleEndpoint.locations(Collections.singletonList(location));

            //Authenticate via OAuth to connect to Twitter
            Authentication auth = new OAuth1(CONSUMER_KEY, CONSUMER_SECRET, ACCESS_TOKEN, ACCESS_TOKEN_SECRET);

            // create a new basic client
            client = new ClientBuilder()
                    .name("tweets")
                    .hosts(Constants.STREAM_HOST)
                    .endpoint(sampleEndpoint)
                    .authentication(auth)
                    .processor(new StringDelimitedProcessor(queue))
                    .build();

            System.out.println("connecting client");
            // Create connection
            client.connect();

        }catch (Exception e)
        {
            System.out.println("Exception in building twitter client:" + e.getMessage());
            e.printStackTrace();
        }
        return client;
    }

//    private TwitterStreaming()
//    {
//
//        try {
//            //Authenticate via OAuth to connect to Twitter
//            Authentication auth = new OAuth1(CONSUMER_KEY, CONSUMER_SECRET, ACCESS_TOKEN, ACCESS_TOKEN_SECRET);
//
//            //create an endpoint
//            StatusesSampleEndpoint sampleEndpoint = new StatusesSampleEndpoint();
//            sampleEndpoint.stallWarnings(false);
//            sampleEndpoint.languages(Lists.newArrayList("en"));
//
//            // create a new basic client
//                client = new ClientBuilder()
//                    .name("tweets")
//                    .hosts(Constants.STREAM_HOST)
//                    .endpoint(sampleEndpoint)
//                    .authentication(auth)
//                    .processor(new StringDelimitedProcessor(queue))
//                    .build();
//
//            // Create connection
//            client.connect();
//
//        }catch (Exception e)
//        {
//            System.out.println(e.getCause());
//        }
//    }


    public static BlockingQueue<String> getQueue()
    {
        return queue;
    }

    public static String parseJSON(String tweet)
    {
        JSONObject jsonObject = new JSONObject(tweet);
       return jsonObject.getString("text");

    }

    public static void ifEnglishTweet(String tweet)
    {

    }

    public static void main(String[] args) throws InterruptedException {
        Client twitterStreaming = TwitterStreaming.getInstance();
        while (true){
        System.out.println(getQueue().take());}
//        String tweet = "{\"created_at\":\"Sun Aug 24 21:20:31 +0000 2014\",\"id\":503653102290227201,\"id_str\":\"503653102290227201\",\"text\":\"Sonra dedim ki;\\n-\\u00c7ay var m\\u0131 \\u00e7ay ?\",\"source\":\"\\u003ca href=\\\"http:\\/\\/twitter.com\\/download\\/android\\\" rel=\\\"nofollow\\\"\\u003eTwitter for Android\\u003c\\/a\\u003e\",\"truncated\":false,\"in_reply_to_status_id\":null,\"in_reply_to_status_id_str\":null,\"in_reply_to_user_id\":null,\"in_reply_to_user_id_str\":null,\"in_reply_to_screen_name\":null,\"user\":{\"id\":2729532218,\"id_str\":\"2729532218\",\"name\":\"Faruk \\u265b \\u262f \",\"screen_name\":\"ThePalavra\",\"location\":\"\",\"url\":null,\"description\":\"Kar\\u015f\\u0131yaka.\",\"protected\":false,\"verified\":false,\"followers_count\":503,\"friends_count\":8,\"listed_count\":0,\"favourites_count\":11,\"statuses_count\":34,\"created_at\":\"Wed Aug 13 14:05:11 +0000 2014\",\"utc_offset\":null,\"time_zone\":null,\"geo_enabled\":false,\"lang\":\"tr\",\"contributors_enabled\":false,\"is_translator\":false,\"profile_background_color\":\"000000\",\"profile_background_image_url\":\"http:\\/\\/abs.twimg.com\\/images\\/themes\\/theme1\\/bg.png\",\"profile_background_image_url_https\":\"https:\\/\\/abs.twimg.com\\/images\\/themes\\/theme1\\/bg.png\",\"profile_background_tile\":false,\"profile_link_color\":\"FF0000\",\"profile_sidebar_border_color\":\"000000\",\"profile_sidebar_fill_color\":\"000000\",\"profile_text_color\":\"000000\",\"profile_use_background_image\":false,\"profile_image_url\":\"http:\\/\\/pbs.twimg.com\\/profile_images\\/499584078388019200\\/6Ot-f4xI_normal.jpeg\",\"profile_image_url_https\":\"https:\\/\\/pbs.twimg.com\\/profile_images\\/499584078388019200\\/6Ot-f4xI_normal.jpeg\",\"profile_banner_url\":\"https:\\/\\/pbs.twimg.com\\/profile_banners\\/2729532218\\/1407941137\",\"default_profile\":false,\"default_profile_image\":false,\"following\":null,\"follow_request_sent\":null,\"notifications\":null},\"geo\":null,\"coordinates\":null,\"place\":null,\"contributors\":null,\"retweet_count\":0,\"favorite_count\":0,\"entities\":{\"hashtags\":[],\"trends\":[],\"urls\":[],\"user_mentions\":[],\"symbols\":[]},\"favorited\":false,\"retweeted\":false,\"possibly_sensitive\":false,\"filter_level\":\"medium\",\"lang\":\"tr\"}\n";
//        System.out.println(twitterStreaming.parseJSON(tweet));
    }
}
