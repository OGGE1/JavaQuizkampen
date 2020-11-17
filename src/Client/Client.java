package Client;

import Server.Initiator;
import Server.Response;

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


    int port = 27015;


    public Client() {

        try {
            Socket clientSocket = new Socket(address, port);

            Object fromServer;
            String fromClient;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

