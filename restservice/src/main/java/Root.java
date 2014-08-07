import javax.ws.rs.*;
import java.io.IOException;

@Path("/root")
public class Root
{
    public Root()
    {

    }

    @GET
    @Produces("text/plain")
    @Path("{name}")
    public String get(@PathParam("name") String name) throws IOException {
        if(name.equals("Deeksha"))
        {
            return FirstRestHello.sayHello(name);
        }
        else
        {
            return FirstRestHello.sayHi(name);
        }
    }

    @GET
    @Produces("text/plain")
    public String getMessage()
    {
         return FirstRestHello.conveyMessage();
    }
}
