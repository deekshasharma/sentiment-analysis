//package com.sentiments.analyzers;
//
//import com.alchemyapi.api.AlchemyAPI;
//import com.alchemyapi.api.*;
//import org.apache.xml.dtm.ref.DTMNodeList;
//import org.w3c.dom.NodeList;
//import org.xml.sax.InputSource;
//import org.xml.sax.SAXException;
//import org.w3c.dom.Document;
//import java.io.*;
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.ParserConfigurationException;
//import javax.xml.xpath.*;
//import javax.xml.transform.Transformer;
//import javax.xml.transform.TransformerException;
//import javax.xml.transform.TransformerFactory;
//import javax.xml.transform.dom.DOMSource;
//import javax.xml.transform.stream.StreamResult;
//
//
//public class SentimentAnalyzerAlchemy {
//
//    private static final String API_KEY= "4d162d16826398e23c0d1eb17287bf1b06ca7492";
//
//
//    public static void main(String[] args) throws SAXException, ParserConfigurationException, XPathExpressionException, IOException
//    {
//        SentimentAnalyzerAlchemy alchemy = new SentimentAnalyzerAlchemy();
//        alchemy.getSentiment("Its hard to get the result");
//    }
//
//    /*
//
//     */
//    public void getSentiment(String tweet) throws SAXException, ParserConfigurationException, XPathExpressionException, IOException
//    {
//        AlchemyAPI alchemyAPI = AlchemyAPI.GetInstanceFromString(API_KEY);
//        Document document =  alchemyAPI.TextGetTextSentiment(tweet);
//        System.out.println("Getting the sentiment for: "+tweet);
//        String xmlString = getStringFromDocument(document);
//        convertToDocumentObject(xmlString);
//    }
//
//    // utility method
//    public String getStringFromDocument(Document doc) {
//        try {
//            DOMSource domSource = new DOMSource(doc);
//            StringWriter writer = new StringWriter();
//            StreamResult result = new StreamResult(writer);
//
//            TransformerFactory tf = TransformerFactory.newInstance();
//            Transformer transformer = tf.newTransformer();
//            transformer.transform(domSource, result);
//
//            return writer.toString();
//        } catch (TransformerException ex) {
//            ex.printStackTrace();
//            return null;
//        }
//    }
//
//    private void convertToDocumentObject(String xmlSource)
//    {
//        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//        DocumentBuilder builder;
//        try
//        {
//            builder = factory.newDocumentBuilder();
//            Document document = builder.parse(new InputSource( new StringReader( xmlSource ) ) );
//            XPath xPath =  XPathFactory.newInstance().newXPath();
//            XPathExpression expression = xPath.compile("//docSentiment/type");
//            NodeList nodeList = (NodeList) expression.evaluate(document,XPathConstants.NODESET);
//            System.out.println(((DTMNodeList)nodeList).getDTMIterator().toString());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//}
