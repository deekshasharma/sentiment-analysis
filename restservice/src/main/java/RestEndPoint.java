import com.sentiments.analyzers.Director;
import com.sentiments.analyzers.DirectorInterface;
import com.sentiments.analyzers.TweetWithSentiment;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Path("/twitter")
public class RestEndPoint {


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/query")
    public Response Sentiment(@QueryParam("keyword") String keyword,
                              @QueryParam("algorithm") String algorithm)
    {
        List<TweetWithSentiment> tweetWithSentimentList = new ArrayList<>();
        Iterator<TweetWithSentiment> iterator = getIterator(keyword, algorithm);

        while (iterator.hasNext())
        {
            tweetWithSentimentList.add(iterator.next());
        }
            return Response.ok(tweetWithSentimentList).build();
    }


    /*
    This method returns the iterator over the data structure
     */
    private Iterator<TweetWithSentiment> getIterator(String keyword,String algorithm)
    {
        DirectorInterface director = new Director();
        director.getSentiment(keyword,algorithm);
        Iterator<TweetWithSentiment> iterator = director.createIterator();
        return iterator;
    }

}
