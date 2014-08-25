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
    private void getStreamOfTweets(String handshake, Session session) throws IOException, InterruptedException
    {
//        TwitterStreaming twitterStreaming = TwitterStreaming.getInstance();
        Client client = TwitterStreaming.getInstance();
        while (session.getOpenSessions().isEmpty() == false) {
            for (Session session1 : session.getOpenSessions()) {
                System.out.println("received");
//                try {
//                    String tweet = twitterStreaming.getQueue().take();
//                System.out.println("twitterStreaming.getQueue()");
//                    String tweetText = twitterStreaming.parseJSON(tweet);
//                    SentimentStrategy sentimentStrategy = new NlpAlgorithmStrategy();
//                    TweetWithSentiment tweetWithSentiment = sentimentStrategy.getTweetWithSentiment(tweetText);
//                    System.out.println(tweetWithSentiment.toString());
                    session1.getAsyncRemote().sendText("received");
//
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        }
    }

    @OnError
    private void logError(final Throwable t) {
        System.out.println("Error in WebSocket API:" + t.getCause());
    }

    @OnClose
    private void closeConnection(Session session, CloseReason closeReason) {
        System.out.println("Session with id " + session.getId() + " is now closing due to " + closeReason);
    }

}
