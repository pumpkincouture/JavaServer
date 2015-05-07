# Java Server
Java Server, written in Java version 1.8.0_25.

## Setting Up
You will need [Maven](http://maven.apache.org/) 4.0.0 to package all dependencies. To get Maven, make sure you have [Homebrew](http://brew.sh/) installed by typing  ```brew --version```.
Type ```brew install maven```. To check if Maven was installed, type ```mvn -version```. For troubleshooting, please refer to the Homebrew documentation.
The server requires Java version 1.8 or higher. To check your version, type ```java -version```.

## Running the Server From Command Line
Clone the repository to the desktop. Navigate to the main project directory.
In command line, type ```java -cp target/solak.com.javaServer-1.0-SNAPSHOT.jar JavaServer.ServerMain``` to start the server. The server will serve from its public directory as the DEFAULT directory.

## Optional Arguments
To configure port and directory, run the start command with optional -p and -d flag arguments, like below. Note: the server only supports valid directories and valid ports. ([List of Taken Ports](http://en.wikipedia.org/wiki/List_of_TCP_and_UDP_port_numbers))
- ```java -cp target/solak.com.javaServer-1.0-SNAPSHOT.jar JavaServer.ServerMain -p 1405 -d /fun```

## Optional Directory
The server supports another directory - to access, please specify the below relative path in your -d argument:
- ```/fun```

## Running the Server From IntelliJ
Clone the repository to the desktop. Open IntelliJ and import the project as a Maven project. Run ```ServerMain```. To stop the server, close the tab running ServerMain or press the stop button.

## Stopping the Server
In your terminal, press Ctrl + C to kill the server process (otherwise, the server will persist).
