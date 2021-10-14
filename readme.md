#What is this?
Simple Java application, which works on Raspberry Pi with connected DHT-22 or BME-280 sensor and collect data every 5 minutes to SQLite database.

#Requirements
This app requires Java 11 and latest Maven.

#Build
After Java and maven was installed, just type

```sh
mvn install
```

#Start
Just put "dht_database-0.1-jar-with-dependencies.jar" file to ypur Raspberry Pi device and run

```sh
java -jar <location of jar>/dht_database-0.1-jar-with-dependencies.jar
```
