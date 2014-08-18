package com.sentiments.analyzers;

import org.json.JSONObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public class APIStrategy implements SentimentCalcStrategy{

    private static final String API_KEY = "c8dd6ad0-524f-46b3-88ce-ee3a1257f16f";
    private static APIStrategy ourInstance = new APIStrategy();
    private Client client;

    /*
    Returning Singleton instance of APIStrategy class to avoid creating connection objects repeatedly.
     */
    public static APIStrategy getInstance()
    {
        return ourInstance;
    }

    /*
    Private constructor to create a singleton for APIStrategy
     */
    private APIStrategy()
    {
        client = ClientBuilder.newBuilder().build();
    }

    @Override
    public TweetWithSentiment calculateSentiment(String tweet)
    {
        WebTarget webTarget = client.target("https://api.idolondemand.com/1/api/sync/analyzesentiment/v1?text="+tweet+"&apikey="+API_KEY);
        Response response = webTarget.request().get();
        String result = response.readEntity(String.class);
        String sentiment = parseJSON(result);

        TweetWithSentiment tweetWithSentiment = new TweetWithSentiment(tweet,sentiment);
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


    public static void main(String[] args) {
        APIStrategy apiStrategy = APIStrategy.getInstance();
        apiStrategy.calculateSentiment("I love to hear music!");
    }
}
