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
public class RestEndPoint
{
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/query")
    public Response Sentiment(@QueryParam("keyword") String keyword,
                              @QueryParam("algorithm") String algorithm)
    {
        List<MetaTweet> metaTweetList = new ArrayList<>();
        Iterator<MetaTweet> iterator = getIterator(keyword, algorithm);

        while (iterator.hasNext())
        {
            metaTweetList.add(iterator.next());
        }
            return Response.ok(metaTweetList).build();
    }


    /*
    This method returns the iterator over the list of MetaTweet objects
     */
    private Iterator<MetaTweet> getIterator(String keyword,String algorithm)
    {
        DirectorInterface director = new Director();
        if(algorithm.equalsIgnoreCase("A"))
        {
            director.calculateSentiment(keyword, new AlchemyAlgorithmStrategy());
        }else if(algorithm.equalsIgnoreCase("N"))
        {
            director.calculateSentiment(keyword, new NlpAlgorithmStrategy());
        }else
        {
            IdolAlgorithmStrategy idolAlgorithmStrategy = IdolAlgorithmStrategy.getInstance();
            director.calculateSentiment(keyword, idolAlgorithmStrategy);
        }

        Iterator<MetaTweet> iterator = director.createIterator();
        return iterator;
    }

}
