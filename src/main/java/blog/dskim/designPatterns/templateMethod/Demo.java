package blog.dskim.designPatterns.templateMethod;

import blog.dskim.designPatterns.templateMethod.networks.Facebook;
import blog.dskim.designPatterns.templateMethod.networks.Network;
import blog.dskim.designPatterns.templateMethod.networks.Twitter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// Demo class. a client code
// Everything comes together here.
public class Demo {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Network network = null;
        System.out.print("Input user name: ");
        String userName = reader.readLine();
        System.out.print("Input password: ");
        String password = reader.readLine();

        // Enter the message
        System.out.print("Input message: ");
        String message = reader.readLine();

        System.out.println("\nChoose social network for posting message.\n" +
                "1 - Facebook\n" +
                "2 - Twitter");
        int choice = Integer.parseInt(reader.readLine());

        // Create proper network object and send the message
        if (choice == 1) {
            network = new Facebook(userName, password);
        } else if (choice == 2 ) {
            network = new Twitter(userName, password);
        }
        network.post(message);
    }
}
