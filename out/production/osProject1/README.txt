Explanation of each file:
    Encrypt.java -
    Log.java -
    Driver.java - This file creates two processes, sending data to the standard input of each process and
    reading from the standard output of both processes. The driver is the file that will be executed from the
    terminal for the user to communicate with.

To compile and run, open terminal and enter the following commands:
    cd src
    javac Log.java
    javac Encrypt.java
    javac Driver.java
    java Driver

    after the first run, you must delete the logger file before running again
    rm LoggerFile.txt

Extra notes:
When compiling the driver there may be a note that Driver.java uses/overrides a deprecated API
because we use .exec() to create a new process and newer JDKs prefer the processBuilder class
for creating new processes. You can ignore this and run the driver program.



