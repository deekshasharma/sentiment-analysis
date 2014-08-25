import com.sentiments.analyzers.NlpAlgorithmStrategy;
import com.sentiments.analyzers.SentimentStrategy;
import com.sentiments.analyzers.TweetWithSentiment;
import com.sentiments.twitter.TwitterStreaming;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/tweets")
public class WebSocket {

    @OnOpen
    private void openConnection(Session session) {
        System.out.println("Connected to new Session: " + session.getId());
    }

    @OnMessage
    private void getStreamOfTweets(String handshake, Session session) {
//        TwitterStreaming twitterStreaming = TwitterStreaming.getInstance();
        while (session.getOpenSessions().isEmpty() == false) {
            for (Session session1 : session.getOpenSessions()) {
                System.out.println("received");
                session1.getAsyncRemote().sendText("received");
/*
                try {
                    final String tweet = twitterStreaming.getQueue().take();
                    String tweetText = twitterStreaming.parseJSON(tweet);
                    SentimentStrategy sentimentStrategy = new NlpAlgorithmStrategy();
                    TweetWithSentiment tweetWithSentiment = sentimentStrategy.getTweetWithSentiment(tweetText);
                    System.out.println(tweetWithSentiment.toString());

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
*/
            }
        }

    }

    @OnError
    private void logError(final Throwable t) {
        System.out.println("Error in WebSocket API:" + t.getMessage());
    }

    @OnClose
    private void closeConnection(Session session, CloseReason closeReason) {
        System.out.println("Session with id " + session.getId() + " is now closing due to " + closeReason);
    }

}
