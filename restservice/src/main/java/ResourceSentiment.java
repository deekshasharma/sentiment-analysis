import twitter4j.TwitterException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.io.IOException;

@Path("/twitter")
public class ResourceSentiment {

    public ResourceSentiment()
    {

    }

    @GET
//    @Produces("text/plain")
    @Path("{keyword}")
    public void sendSentiment(@PathParam("keyword") String keyword) throws TwitterException, IOException
    {
             Director director = new Director();
             director.getSentiment(keyword);
    }


}
