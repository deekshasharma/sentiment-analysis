package com.sentiments.analyzers;
import java.util.Properties;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;


public class NlpAlgorithmStrategy implements SentimentCalcStrategy{
    @Override
    public TweetWithSentiment calculateSentiment(String tweet)
    {
        //  Create a StanfordCoreNLP object to construct a pipeline using the given annotators
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        int mainSentiment = 0;
        if (tweet != null && tweet.length() > 0) {
            int longest = 0;
            // Runs the entire pipeline on the content of the given text passed and returns an Annotation object
            // Annotation represents text in a document and implement CoreMap
            Annotation annotation = pipeline.process(tweet);
            for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class))
            {
                Tree tree = sentence.get(SentimentCoreAnnotations.AnnotatedTree.class);
                int sentiment = RNNCoreAnnotations.getPredictedClass(tree);
                String partText = sentence.toString();
                if (partText.length() > longest) {
                    mainSentiment = sentiment;
                    longest = partText.length();
                }
            }
        }
        if ( mainSentiment > 4 || mainSentiment < 0) {
            return null;
        }
        TweetWithSentiment tweetWithSentiment = new TweetWithSentiment(tweet, toText(mainSentiment));
        return tweetWithSentiment;

    }

    /*
    This method returns the Text interpretation
     */
    private String toText(int sentiment) {
        switch (sentiment) {

            case 0:
                return "Very - Negative";
            case 1:
                return "Negative";
            case 2:
                return "Neutral";
            case 3:
                return "Positive";
            case 4:
                return "Very - Positive";
            default:
                return "";
        }
    }


//    public static void main(String[] args) {
//        SentimentAnalyzer sentimentAnalyzer = new SentimentAnalyzer();
//        TweetWithSentiment tweetWithSentiment = sentimentAnalyzer
//                .findSentiment("RT @PeterPyke: Picture heroic #Israel soldier. Says all - Israeli soldier pointing gun at 2 year old child.");
//        System.out.println(tweetWithSentiment);
//    }

}