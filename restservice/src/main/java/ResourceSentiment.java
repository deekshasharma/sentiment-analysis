import com.sentiments.analyzers.TweetWithSentiment;
import twitter4j.TwitterException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Path("/twitter")
public class ResourceSentiment {

    public ResourceSentiment()
    {

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{keyword}")
    public Response sendSentiment(@PathParam("keyword") String keyword) throws TwitterException, IOException
    {
        Director director = new Director();
        List<TweetWithSentiment> tweetWithSentimentList = director.getSentimentObjectsNLP(keyword);
        return Response.ok(tweetWithSentimentList).build();

    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTweets() {
        TweetWithSentiment tweetWithSentiment = new TweetWithSentiment("tweet", "positive");
        List<TweetWithSentiment> responseObject = Arrays.asList(tweetWithSentiment, tweetWithSentiment);
        return Response.ok(responseObject).build();
    }

}
