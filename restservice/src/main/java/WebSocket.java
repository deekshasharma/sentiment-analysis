import com.sentiments.twitter.TwitterStreaming;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/tweets")
public class WebSocket
{

    @OnOpen
    private void openConnection(Session session)
    {
        System.out.println("Connected to new Session"+session.getId());
    }

    @OnMessage
    private void getStreamOfTweets()
    {
        TwitterStreaming  twitterStreaming = TwitterStreaming.getInstance();

    }

    @OnError
    private void logError()
    {
        System.out.println("Error in WebSocket API");
    }

    @OnClose
    private void closeConnection(Session session, CloseReason closeReason)
    {
        System.out.println("Session with id "+ session.getId()+" is now closing due to "+ closeReason);
    }

}
