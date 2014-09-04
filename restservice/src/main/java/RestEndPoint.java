import com.sentiments.analyzers.*;
import com.sentiments.facade.Director;
import com.sentiments.facade.DirectorInterface;

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
        List<MetaTweet> tweetWithSentimentList = new ArrayList<>();
        Iterator<MetaTweet> iterator = getIterator(keyword, algorithm);

        while (iterator.hasNext())
        {
            tweetWithSentimentList.add(iterator.next());
        }
            return Response.ok(tweetWithSentimentList).build();
    }


    /*
    This method returns the iterator over the data structure
     */
    private Iterator<MetaTweet> getIterator(String keyword,String algorithm)
    {
        DirectorInterface director = new Director();
        if(algorithm.equalsIgnoreCase("A"))
        {
            director.getSentiment(keyword,new AlchemyAlgorithmStrategy());
        }else if(algorithm.equalsIgnoreCase("N"))
        {
            director.getSentiment(keyword, new NlpAlgorithmStrategy());
        }else
        {
            IdolAlgorithmStrategy idolAlgorithmStrategy = IdolAlgorithmStrategy.getInstance();
            director.getSentiment(keyword, idolAlgorithmStrategy);
        }

        Iterator<MetaTweet> iterator = director.createIterator();
        return iterator;
    }

}
