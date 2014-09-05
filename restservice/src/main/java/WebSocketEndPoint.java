import com.sentiments.analyzers.MetaTweet;
import com.sentiments.analyzers.NlpAlgorithmStrategy;
import com.sentiments.analyzers.SentimentStrategy;
import com.sentiments.twitter.TwitterStreaming;
import org.json.JSONObject;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint("/tweets")
public class WebSocketEndPoint {

    @OnOpen
    private void openConnection(Session session)
    {
        System.out.println("Connected to new Session: " + session.getId());
    }

    @OnMessage
    private void notifyObserver(String handshake, Session session) throws IOException, InterruptedException {
        TwitterStreaming.getInstance();
        while (session.getOpenSessions().isEmpty() == false) {
            for (Session session1 : session.getOpenSessions()) {
                try {
                        String tweet = TwitterStreaming.getQueue().take();
                        String tweetText = TwitterStreaming.parseJSON(tweet);
                        if(tweetText != null)
                        {
                            MetaTweet metaTweet = getSentiment(tweetText);
                            JSONObject jsonObject = new JSONObject(metaTweet);
                            session1.getAsyncRemote().sendText(jsonObject.toString());
                        }
                } catch (Exception e) {
                    System.out.println(e.getCause());
                }
            }
        }
    }


    /*
    This method gets the Sentiment using the NLP Algorithm
     */
    private MetaTweet getSentiment(String tweetText)
    {
        SentimentStrategy sentimentStrategy = new NlpAlgorithmStrategy();
        return sentimentStrategy.getMetaTweet(tweetText);
    }

    @OnError
    private void onError(final Throwable t, final Session session) {
        System.out.println("error: " + t.getMessage());
        System.out.println("closing session: " + session);
        t.printStackTrace();
    }

    @OnClose
    private void closeConnection(Session session, CloseReason closeReason) {
        System.out.println("Session with id " + session.getId() + " is now closing due to " + closeReason);
    }

}
