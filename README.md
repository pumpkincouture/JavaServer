# Java Server
Java Server, written in Java version 1.8.0_25.

## Setting Up
You will need [Maven](http://maven.apache.org/) 4.0.0 to package all dependencies. To get Maven, make sure you have [Homebrew](http://brew.sh/) installed by typing  ```brew --version```.
Type ```brew install maven```. To check if Maven was installed, type ```mvn -version```. For troubleshooting, please refer to the Homebrew documentation.
The server requires Java version 1.8 or higher. To check your version, type ```java -version```.

## Running the Server From Command Line
Clone the repository to the desktop. Navigate to the main project directory.
In command line, type ```java -cp target/solak.com.javaServer-1.0-SNAPSHOT.jar JavaServer.ServerMain``` to start the server. The server will serve from its public directory.
To configure port and files, run the above command with optional -p and -d flag arguments, like so : ```java -cp target/solak.com.javaServer-1.0-SNAPSHOT.jar JavaServer.ServerMain -p 1405 -d /Users/test/code/JavaServer/fun```.
The Server supports two directories, ```/Users/test/code/JavaServer/fun``` and ```/Users/test/code/JavaServer/public```. You may point it to a valid directory if you wish.

## Running the Server From IntelliJ
Clone the repository to the desktop. Open IntelliJ and import the project as a Maven project. Run ```ServerMain```.

## Stopping the Server
In your terminal, press Ctrl + C to kill the server process (otherwise, the server will persist).
