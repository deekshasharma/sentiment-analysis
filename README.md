README

Following are the steps to run this project:

- Download the zip file of the project and unzip it.
- business module contains 3 packages (analyzers, facade, twitter)
- restservice module contains the end points to which server delegates the request.
- Create a java project in Eclipse or Intellij.
- Install maven from here for the external dependencies used in the project: http://maven.apache.org/download.cgi
- Place the JBoss(Wildfly) in a separate folder. Wildfly folder with all the startup files is zipped with the project package.
- Go inside the project folder and run the following command. It is assumed that wildfly is located in home folder.
mvn clean install; cp ./restservice/target/restservice-1.0.war ~/wildfly-8.1.0.Final/standalone/deployments/	
- Check once again if the war file is deployed inside ~/wildfly-8.1.0.Final/standalone/deployments/	
- Start the wildly server using the following command
~/wildfly-8.1.0.Final/bin/standalone.sh
- Click on the following link 
http://localhost:8080/restservice-1.0/
- Enter a search keyword and click on either of the algorithms. It may take around 8 seconds to load the tweets first time and after that it will be quick.
- To get the sentiments and tweets for the Twitter Stream , click on Twitter Stream and it will start to give the live stream and its sentiments in around 3 seconds.
- The color resolution and emoticons will be best rendered on Safari browser.
