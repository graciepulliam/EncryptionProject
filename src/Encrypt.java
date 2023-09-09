import java.lang.*;
import java.lang.Math;
import java.util.Scanner;
public class Encrypt {
    static String passkey = "";

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        String in = s.next();
        String str ="";

        while (!in.equals("QUIT")) {
            //PASSWORD
            if (in.equals("PASSWORD")) {
                passkey = s.next();
                System.out.println("SUCCESS password set");
            }
            //ENCRYPT
            else if (in.equals("ENCRYPT") && !passkey.equals("")) {
                //save the password so the length can be changed for encryption without changing the password currently typed in
                String pw = passkey;

                String res = "";
                str = s.next();

                /*if length of password is less than the string to be encrypted, repeat string pattern of password
                 * until the length of the password is equal to that of the desired string*/

                if (str.length() > pw.length()) {
                    int diff = str.length() - pw.length();
                    for (int i = 0; i < diff; i++) {
                        if (i == pw.length()) {
                            i = 0;
                        }
                        pw += pw.charAt(i);
                    }
                }
                int pwCnt = 0;
                for (int cnt = 0; cnt < str.length(); cnt++) {

                    //convert char at each index to ASCII value
                    int strIntVal = str.charAt(cnt);
                    int pwIntVal = pw.charAt(pwCnt);

                    //find cipher char value and convert it to ASCII
                    int cipherIntVal = (((strIntVal - 65) + (pwIntVal - 65)) % 26) + 65;

                    //convert ASCII value to char value
                    char cipherChar = (char) cipherIntVal;
                    //append char to result string
                    res += cipherChar;
                    pwCnt++;

                }

                System.out.println(res);

            } else if (in.equals("ENCRYPT") && passkey.equals("")) {
                str = s.next();
                System.out.println("ERROR password not set");
            }
            //DECRYPT
            else if (in.equals("DECRYPT") && !passkey.equals("")) {
                //save the password so the length can be changed for decryption without changing the password currently typed in
                String pw = passkey;

                String res = "";
                str = s.next();

                /*if length of password is less than the string to be encrypted, repeat string pattern of password
                 * until the length of the password is equal to that of the desired string*/

                if (str.length() > pw.length()) {
                    int diff = str.length() - pw.length();
                    for (int i = 0; i < diff; i++) {
                        if (i == pw.length()) {
                            i = 0;
                        }
                        pw += pw.charAt(i);
                    }
                }
                int pwCnt = 0;
                for (int cnt = 0; cnt < str.length(); cnt++) {

                    //convert char at each index to ASCII value
                    int strIntVal = str.charAt(cnt);

                    int pwIntVal = pw.charAt(pwCnt);

                    //find cipher char value and convert it to ASCII
                    int cipherIntVal = (Math.floorMod(((strIntVal - 65) - (pwIntVal - 65)),26)) + 65;

                    //convert ASCII value to char value
                    char cipherChar = (char) cipherIntVal;
                    //append char to result string
                    res += cipherChar;
                    pwCnt++;

                }
                System.out.println(res);

            } else if (in.equals("DECRYPT") && passkey.equals("")) {
                str = s.next();
                System.out.println("ERROR password not set");
            }
            in = s.next();

        }


    }

}