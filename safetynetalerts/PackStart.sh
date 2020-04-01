Echo
Echo ****************************Clean****************************
Echo
mvn clean --quiet

Echo
Echo ********* Package  **************
Echo
mvn package --quiet

Echo
Echo ********* Start **************
Echo
java -jar target/safetynetalerts-0.0.1-SNAPSHOT.jar
