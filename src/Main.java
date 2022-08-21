import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

public class Main {
    static Scanner SC = new Scanner(System.in);
    static String username, password;
    public static void main(String[] args) {
        System.out.print("""
                \t\t==[ Login/Registration DEMO ]==
                
                \t\t[1] Login
                \t\t[2] Register
                \t\t[3] Exit
                
                >>\040""");
        String choice = SC.next();
        if (choice.matches("1")) {
            Login();
        } else if (choice.matches("2")) {
            Register();
        } else if (choice.matches(("3"))) {
            System.exit(0);
        }
    }
    static void Login(){
        try {
            String[] dbIndex = null; // string array to read user/pass from users.txt
            File read = new File("users.txt"); // target file to read
            BufferedReader BR = new BufferedReader((new FileReader(read))); // more efficient reader

            // for loop to iterate through the text file
            for (String readFile = BR.readLine(); readFile != null; readFile = BR.readLine()) {
                dbIndex = readFile.split(",");
            }

            BR.close();

            while(true) {
                System.out.print("Enter Username: ");
                username = SC.next();
                System.out.print("Enter Password: ");
                password = SC.next();
                if (dbIndex != null) { // if array is not empty
                    if (username.matches(dbIndex[0]) && password.matches(dbIndex[1])) {
                        System.out.println("\nSuccessfully Logged in!");
                        System.exit(0);
                    } else {
                        System.out.println("Incorrect Username/Password. Try again.");
                    }
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    static void Register() {
        try {
            FileWriter file = new FileWriter("users.txt", true); // create file
            String[] dbIndex = null;
            File read = new File("users.txt");
            BufferedReader BR = new BufferedReader((new FileReader(read)));

            for (String readFile = BR.readLine(); readFile != null; readFile = BR.readLine()) {
                dbIndex = readFile.split(",");
            }

            BR.close();

            while(true) {
                System.out.print("Enter Username: ");
                username = SC.next();
                if (username.matches("[a-zA-Z0-9]*$")) {
                    if (dbIndex != null) {
                        if (username.matches(dbIndex[0])) { // check if username is taken by matching the array
                            System.out.println("Username is taken. Choose a different username.");
                        } else {
                            file.write(username + ",");
                            System.out.print("Enter Password: ");
                            password = SC.next();
                            file.write(password + "\n");
                            System.out.println("\nSuccessfully Registered!");
                            file.close();
                            break;
                        }
                    }
                } else {
                    System.out.println("Only Alphanumeric characters are allowed. Try again.");
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}