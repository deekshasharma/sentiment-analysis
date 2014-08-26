import com.sentiments.analyzers.NlpAlgorithmStrategy;
import com.sentiments.analyzers.SentimentStrategy;
import com.sentiments.analyzers.TweetWithSentiment;
import com.sentiments.twitter.TwitterStreaming;
import com.twitter.hbc.core.Client;
import twitter4j.Twitter;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

@ServerEndpoint("/tweets")
public class WebSocket {

    @OnOpen
    private void openConnection(Session session) {
        System.out.println("Connected to new Session: " + session.getId());
    }

    @OnMessage
    private void getStreamOfTweets(String handshake, Session session) throws IOException, InterruptedException {
//        Client twitterStreaming = TwitterStreaming.getInstance();
        Client client = TwitterStreaming.getInstance();
        while (session.getOpenSessions().isEmpty() == false) {
            for (Session session1 : session.getOpenSessions()) {
                try {
                    String tweet = TwitterStreaming.getQueue().take();
                    String tweetText = TwitterStreaming.parseJSON(tweet);
                    if(tweetText != null){
                    System.out.println(tweetText);
                    session1.getAsyncRemote().sendText(tweetText);}
//                    SentimentStrategy sentimentStrategy = new NlpAlgorithmStrategy();
//                    TweetWithSentiment tweetWithSentiment = sentimentStrategy.getTweetWithSentiment(tweetText);

                } catch (Exception e) {
                    System.out.println(e.getCause());
                }
            }
        }
    }

    @OnError
    public void onError(final Throwable t, final Session session) {
        System.out.println("error: " + t.getMessage());
        System.out.println("closing session: " + session);
        t.printStackTrace();
    }

    @OnClose
    private void closeConnection(Session session, CloseReason closeReason) {
        System.out.println("Session with id " + session.getId() + " is now closing due to " + closeReason);
    }

}
