package Client;

import Server.Initiator;

import static Client.Utility.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;


/**
 * Created by Oscar Norman <br>
 * Date: 2020-11-12   <br>
 * Time: 14:06   <br>
 * Project: JavaQuizkampen <br>
 */
public class Client extends JFrame implements Serializable {
    InetAddress address = InetAddress.getLoopbackAddress();
    int port = 27015;

    public Client() {

        try {
            Socket clientSocket = new Socket(address, port);
            ObjectOutputStream objectOut = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream objectIn = new ObjectInputStream(clientSocket.getInputStream());

            Object fromServer;
            String message;
            BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                while ((fromServer = objectIn.readObject()) != null) {
                    if (fromServer instanceof Initiator) {
                        System.out.println("connected");
                    }

                    message = stdin.readLine();
                    if (message != null) {
                        objectOut.writeObject(message);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Client();
    }
}

