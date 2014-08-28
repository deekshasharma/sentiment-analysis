package com.sentiments.analyzers;

import org.json.JSONObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class IdolAlgorithmStrategy implements SentimentStrategy {

    private static final String API_KEY = "c8dd6ad0-524f-46b3-88ce-ee3a1257f16f";
    private static IdolAlgorithmStrategy idolInstance = new IdolAlgorithmStrategy();
    private Client client;

    /*
    Returning Singleton instance of APIStrategy class to avoid creating connection objects repeatedly.
     */
    public static IdolAlgorithmStrategy getInstance()
    {
        return idolInstance;
    }

    /*
    Private constructor to create a singleton for APIStrategy
     */
    private IdolAlgorithmStrategy()
    {
        client = ClientBuilder.newBuilder().build();
    }

    @Override
    public TweetWithSentiment getTweetWithSentiment(String tweet) {
        String encodedTweet = "";
        try {
            encodedTweet = URLEncoder.encode(tweet, "UTF-8").replaceAll(" ", "%20");
        }catch (UnsupportedEncodingException e)
        {
            System.out.println("Encoding exception");
        }
            WebTarget webTarget = client.target("https://api.idolondemand.com/1/api/sync/analyzesentiment/v1?text=" + encodedTweet + "&apikey=" + API_KEY);
            Response response = webTarget.request().get();
            String result = response.readEntity(String.class);
            String sentiment = parseJSON(result);

            TweetWithSentiment tweetWithSentiment = new TweetWithSentiment.TweetBuilder(tweet, sentiment).build();
            return tweetWithSentiment;

    }


    /*
    This method parse the response string in JSOn and returns the sentiment of the tweet
     */
    private String parseJSON(String result)
    {
        JSONObject jsonObject = new JSONObject(result);
        JSONObject aggregate = jsonObject.getJSONObject("aggregate");
        return aggregate.getString("sentiment");
    }
}
