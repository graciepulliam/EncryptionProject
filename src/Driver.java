import java.lang.*;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
public class Driver {


    //method to check history
    static String checkHistory(ArrayList<String> history){

            String exit = "exit";
            String input;
            Scanner histScan = new Scanner(System.in);
            System.out.println("=================================================\n                    HISTORY\n=================================================\n");

            //condition that history is empty
            if (history.isEmpty()){
                System.out.println("1 exit");
                 input = histScan.nextLine();
                while(!input.equals("1")){
                    System.out.println("ERROR invalid option.\nEnter new option: ");
                    input = histScan.next();
                }
                return exit;
            }
            //if there are more than 4 entries in the history
            else if(history.size() >= 4){
                int size = history.size();
                for(int i = 1; i < 5; i++){
                    System.out.println(i+" "+history.get(size - i));
                }
                System.out.println("5 exit");
                input = histScan.nextLine();
                //if input is not 1-5 then ask for them to enter a different option
                while(!input.equals("1") && !input.equals("2") && !input.equals("3") && !input.equals("4") && !input.equals("5")){
                    System.out.println("ERROR invalid option.\nEnter new option: ");
                    input = histScan.next();
                }
                if(input.equals("5")){
                    return exit;
                }
                else{
                    int num = size - Integer.parseInt(input);
                    return history.get(num);
                }
            }
            else{
                int size = history.size();
                for(int i = 1; i<= size;i++){
                    System.out.println(i+" "+history.get(size - i));
                }
                int exitSize = size +1;
                System.out.println(exitSize+" exit");

                input = histScan.nextLine();
                //if input is not 1-5 then ask for them to enter a different option
                while(!input.equals("1") && !input.equals("2")){
                    System.out.println("ERROR invalid option.\nEnter new option: ");
                    input = histScan.next();
                }
                if(input.equals(exitSize)){
                    return exit;
                }
                else{
                    int num = size - Integer.parseInt(input);
                    return history.get(num);
                }
            }
    }

    public static void main(String[] args) throws IOException {

        Scanner s = new Scanner(System.in);
        String menu = ("=================================================\n                      MENU\n=================================================\npassword - set password for encryption/decryption\nencrypt - encrypt string\ndecrypt - decrypt string\nhistory - show recent commands\nquit - exit program\n=================================================\n");
        //creating an arraylist to store recent commands for history
        ArrayList<String> hist = new ArrayList<>();
        System.out.print(menu);
        System.out.println("Enter command: ");

        String command = s.next();
        String result;
        String choice;



        //the creation of both of the  processes are derived from the CPU.java file Professor Salazar provided
        //create process to run Log.java and communicate with its standard input/output
        Process logger = Runtime.getRuntime().exec("java Logger"); //creates a reference to an instance of a process
        OutputStream outStream01 = logger.getOutputStream();
        PrintStream toLogger = new PrintStream(outStream01);

        //create process to run Encrypt.java and communicate with its standard input/output
        Process encrypt = Runtime.getRuntime().exec("java Encrypt"); //creates a reference to an instance of a process
        InputStream inStream02 = encrypt.getInputStream();
        OutputStream outStream02 = encrypt.getOutputStream();
        Scanner fromEncrypt = new Scanner(inStream02);
        PrintStream toEncrypt = new PrintStream(outStream02);




        while(!command.equals("quit")){

            //handling the password command
            if (command.equals("password")){
                //send command to encrypt
                toEncrypt.println(command.toUpperCase());
                toEncrypt.flush();
                //send command to logger
                toLogger.println(command.toUpperCase());
                toLogger.flush();


                //prompt user to enter new password or access history
                System.out.println("Access the history? y/n");
                choice = s.next();

                if(!choice.equalsIgnoreCase("y") && !choice.equalsIgnoreCase("n")){
                    System.out.println("ERROR please enter y/n");
                    choice = s.next();
                }


                if(choice.equalsIgnoreCase("y")){
                    String option = checkHistory(hist);
                    if(!option.equals("exit")){
                        //send password to encrypt and logger
                        toEncrypt.println(option);
                        toEncrypt.flush();
                        toLogger.println(option);

                    }
                    else{
                        //send command to encrypt
                        System.out.println("Enter password: ");
                        //store user response in result
                        result = s.next().toUpperCase();
                        //send password to encrypt and logger
                        toEncrypt.println(result);
                        toEncrypt.flush();
                        toLogger.println(result);


                    }
                    //store result from encrypt in result
                    //print result to user
                    //send original command to logger and the result
                    toLogger.flush();
                    result = fromEncrypt.nextLine();
                    System.out.println(result);
                    toLogger.println(command.toUpperCase());
                    toLogger.flush();
                    toLogger.println(result);
                    toLogger.flush();

                }
                else if(choice.equalsIgnoreCase("n")){
                    //send command to encrypt
                    System.out.println("Enter password: ");
                    //store user response in result
                    result = s.next().toUpperCase();
                    //send password to encrypt and logger
                    toEncrypt.println(result);
                    toEncrypt.flush();
                    toLogger.println(result);
                    toLogger.flush();

                    //store result from encrypt in result
                    result = fromEncrypt.nextLine();
                    //print result to user
                    System.out.println(result);
                    //send original command to logger and the result
                    toLogger.println(command.toUpperCase());
                    toLogger.flush();
                    toLogger.println(result);
                    toLogger.flush();
                }
            }



            //handling encrypt command
            else if (command.equals("encrypt")){
                //send command to Encrypt.java
                toEncrypt.println(command.toUpperCase());
                toEncrypt.flush();

                //send command to logger
                toLogger.println(command.toUpperCase());
                toLogger.flush();



                System.out.println("Access the history? y/n");
                choice = s.next();

                if(!choice.equalsIgnoreCase("y") && !choice.equalsIgnoreCase("n")){
                    System.out.println("ERROR please enter y/n");
                    choice = s.next();
                }

                if(choice.equalsIgnoreCase("y")){
                    String option = checkHistory(hist);
                    if(!option.equals("exit")){
                        //send password to encrypt and logger
                        toEncrypt.println(option);
                        toEncrypt.flush();
                        toLogger.println(option);
                        toLogger.flush();
                        hist.add(option);


                    }
                    else{
                        System.out.println("Enter string for encryption: ");
                        //send user string to be encrypted to Encrypt.java
                        result = s.next().toUpperCase();
                        toEncrypt.println(result);
                        toEncrypt.flush();
                        toLogger.println(result);
                        toLogger.flush();
                        hist.add(result);


                    }
                    result = fromEncrypt.nextLine();
                    if (result.equals("ERROR password not set")){
                        System.out.println(result);
                    }
                    else{
                        System.out.println("SUCCESS "+result);
                    }
                    toLogger.println(command.toUpperCase());
                    toLogger.flush();
                    toLogger.println(result);
                    toLogger.flush();
                    hist.add(result);
                }
                else if(choice.equalsIgnoreCase("n")){

                    System.out.println("Enter string for encryption: ");
                    //send user string to be encrypted to Encrypt.java
                    result = s.next().toUpperCase();
                    toEncrypt.println(result);
                    toEncrypt.flush();
                    toLogger.println(result);
                    toLogger.flush();
                    hist.add(result);

                    //store result from encrypt in result
                    result = fromEncrypt.nextLine();
                    //print result to user
                    if (result.equals("ERROR password not set")){
                        System.out.println(result);
                    }
                    else{
                        System.out.println("SUCCESS "+result);
                    }
                    //send original command to logger and the result
                    toLogger.println(command.toUpperCase());
                    toLogger.flush();
                    toLogger.println(result);
                    toLogger.flush();
                    hist.add(result);

                }


            }



            //handling decrypt command
            else if (command.equals("decrypt")){
                //send command to encrypt
                toEncrypt.println(command.toUpperCase());
                toEncrypt.flush();

                //send command to logger
                toLogger.println(command.toUpperCase());
                toLogger.flush();


                //allow user to access history
                System.out.println("Access the history? y/n");
                choice = s.next();

                if(!choice.equalsIgnoreCase("y") && !choice.equalsIgnoreCase("n")){
                    System.out.println("ERROR please enter y/n");
                    choice = s.next();
                }

                if(choice.equalsIgnoreCase("y")){
                    String option = checkHistory(hist);
                    if(!option.equals("exit")){
                        //send password to encrypt and logger
                        toEncrypt.println(option);
                        toEncrypt.flush();
                        toLogger.println(option);
                        toLogger.flush();
                        hist.add(option);
                        }
                    else{
                        System.out.println("Enter string for decryption: ");
                        //send user string to be decrypted to Encrypt.java
                        result = s.next().toUpperCase();
                        toEncrypt.println(result);
                        toEncrypt.flush();
                        toLogger.println(result);
                        toLogger.flush();
                        hist.add(result);

                        //store result from encrypt in result
                            //print result to user
                            //send original command to logger and the result
                        }
                        result = fromEncrypt.nextLine();

                        if (result.equals("ERROR password not set")){
                            System.out.println(result);
                         }
                        else{
                            System.out.println("SUCCESS "+result);
                        }

                        toLogger.println(command.toUpperCase());
                        toLogger.flush();
                        toLogger.println(result);
                        toLogger.flush();
                        hist.add(result);
                    }
                else if(choice.equalsIgnoreCase("n")){

                    System.out.println("Enter string for decryption: ");
                    //send user string to be decrypted to Encrypt.java
                    result = s.next().toUpperCase();
                    toEncrypt.println(result);
                    toEncrypt.flush();
                    toLogger.println(result);
                    toLogger.flush();
                    hist.add(result);

                    //store result from encrypt in result
                    result = fromEncrypt.nextLine();
                    //print result to user
                    if (result.equals("ERROR password not set")){
                        System.out.println(result);
                    }
                    else{
                        System.out.println("SUCCESS "+result);
                    }
                    //send original command to logger and the result
                    toLogger.println(command.toUpperCase());
                    toLogger.flush();
                    toLogger.println(result);
                    toLogger.flush();
                    hist.add(result);
                }

            }




            //handling history command
            else if (command.equals("history")){
                String option = checkHistory(hist);
                if(!option.equals("exit")){

                    System.out.println("ERROR enter a command");
                }
                else{
                    System.out.println("Returning to menu...");
                }
                toLogger.println("HISTORY");
                toLogger.flush();
                toLogger.println("History checked");
                toLogger.flush();

            }
            else{
                System.out.println("ERROR command not found");
            }


            System.out.print(menu);
            System.out.println("Enter command: ");
            command = s.next();
        }




        //after exiting the while loop, send QUIT to encrypt.java and logger.java
        toLogger.print(command.toUpperCase());
        toLogger.flush();
        toEncrypt.print(command.toUpperCase());
        toEncrypt.flush();

    }

}
