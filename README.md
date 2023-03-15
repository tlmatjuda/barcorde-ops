# README

A showcase of a simple Spring Boot Micro Service.

This service is a Barcode Operations Service.At the moment we are doing QR Code Generation and responding with a QR Code PNG file in the form of a Base64 ByteArray.
To get the started, let's first look at the tech stack.

## Tech Stack

* Kotlin 1.7.21 *( for JVM Target )*
* Java 17
* Maven 3.8.x
* [Mockk](https://mockk.io/) : Testing Framework

## Setting Up The Codebase

* Close the repo
* Change to that directory and build the project with tests : `mvn clean install`
* Or build without tests : `mvn clean install -DskipTests=true`


## Running The App

You can run the app via the jar after building the app.

This .jar file should be in your /target folder, so you : 

```bash
java -jar target/barcorde-ops-0.0.1-SNAPSHOT.jar
```

<br/>

OR you can use the Maven Spring Boot plugin as follows 

```bash
mvn spring-boot:run
```


## Testing The REST API via Postman

The folder : ***/src/test/resources*** contains the Postman Collection to get you started on testing this API. Otherwise there's nothing else major to play around thea app.



Once you have encoded the data you want to encode then you can use [Code Beautify](https://codebeautify.org/base64-to-image-converter) to do the following :

* Copy the encoded field from the response json.
* Paste it in the left text area on the Code Beautify element
* Click the **"Generate"** button


This will use your pasted Base64 ByteArray to generate a QR code file and when you scan this file on the site you should be able to see the original text you wanted to encode.



## Conclusion



That's it for a Kotlin for JVM based Spring Boot Web App.
