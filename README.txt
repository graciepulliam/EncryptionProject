Explanation of each file:
    Encrypt.java - This program allows the user to enter the commands password, encrypt, and decrypt which will
    set the passkey, encrypt plaintext and return the cipher text, and decrypt ciphertext and return the plaintext
    When the driver program is ran, it creates a new process which enters java Encrypt onto the command line, and
    pipes input from the driver. On the command QUIT, the program is closed.

    Logger.java - This program creates and writes to a file. It is passed input from System.in and formats it
    according to the specifications provided. When the driver program is ran, it creates a new process which
    enters java Logger onto the command line, and pipes input from the driver.On the command QUIT, the program
    writes that the logging has stopped, the file closes, and the program is closed.

    Driver.java - This program creates two processes, sending data to the standard input of each process and
    reading from the standard output of both processes. The driver is the file that will be executed from the
    terminal for the user to communicate with. On the command QUIT, the driver passes the command to the encrypt
    and logger programs before terminating.

To compile and run, open terminal and enter the following commands:
    cd src (make sure you are currently in osProject1 folder then cd into src)
    javac Logger.java
    javac Encrypt.java
    javac Driver.java
    java Driver

    after the first run, you must delete the logger file before running again
    rm LoggerFile.txt

Extra notes:
When compiling the driver there may be a note that Driver.java uses/overrides a deprecated API
because we use .exec() to create a new process and newer JDKs prefer the processBuilder class
for creating new processes. You can ignore this and run the driver program.



