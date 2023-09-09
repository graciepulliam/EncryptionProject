import java.io.FileWriter;
import java.lang.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
public class Logger {
    public static void main(String[] args) throws IOException {

        SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date current = new Date();

        FileWriter file = new FileWriter("LoggerFile.txt");

        Scanner scan = new Scanner(System.in);

        file.write(format.format(current)+" [START] Logging Started.\n");

        String input = scan.nextLine();


        while(!input.equals("QUIT")){
            Date currentTime = new Date();
            file.write(format.format(currentTime)+" ["+input+"] ");
            input = scan.nextLine();
            file.write(input+"\n");
            input = scan.nextLine();
        }


        Date endTime = new Date();
        file.write(format.format(endTime)+" [STOP] Logging Stopped.\n");
        file.close();


    }

}
