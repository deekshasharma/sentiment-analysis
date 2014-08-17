import com.sentiments.analyzers.TweetWithSentiment;
import org.xml.sax.SAXException;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Path("/twitter")
public class ResourceSentiment {

    public ResourceSentiment()
    {

    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/query")
    public Response Sentiment(@QueryParam("keyword") String keyword,
                              @QueryParam("algorithm") String algorithm)
    {
        Director director = new Director();
        List<TweetWithSentiment> tweetWithSentimentList = new ArrayList<>();
        try
        {
            tweetWithSentimentList = director.getSentiment(keyword,algorithm);

        } catch (XPathExpressionException e) {
            System.out.println("Due to XPath Exception");

        }catch(SAXException e1)
        {
            System.out.println("Due to SAXException");
        }catch(ParserConfigurationException e2)
        {
            System.out.println("Due to ParserConfig Exception");
        } catch(TwitterException e3)
        {
            System.out.println("Error due to TwitterException");
        }catch(IOException e4)
        {
            System.out.println("Error due to IOException");
        }
        return Response.ok(tweetWithSentimentList).build();

    }

}
