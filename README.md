# Poker hands comparator

Please refer to the [rules.md](rules.md) file for a description of the task and also Poker rules.

This project was made using [Gradle](https://gradle.org/) with its standard tasks. In short, 
in order to build the project, a simple **gradle clean build** would suffice. An addition task 
named **fatJar** is also available as to generate the output application jar file with all its
dependencies combined, so as the application can be ran without requiring dependencies to be 
placed in the same folder as where it is running.

The application entry point is defined by the *Poker.main* method, so to run the application one
would typically type in the command line **java -jar poker-<version>.jar** where <version>
is the version of the application (currently 1.0.0-SNAPSHOT). Note that if the **fatJar** task is 
executed the application output jar file will be called **poker-all-<version>.jar**.

When ran, the application accepts input from the stdin (no arguments passed on the command line) or 
a file as input (if a file path is passed as argument on the command line). For example, 
**java -jar poker-all-1.0.0-SNAPSHOT.jar** will run the application and wait for user input as to 
enter the two Poker hands (each one followed by the ENTER key) as to process the hands comparison.
If using a file path on the command like, it would look like 
**java -jar poker-all-1.0.0-SNAPSHOT.jar sample_input.txt** so the application will run an look for 
the *sample_input.txt* file in the current directory.     
