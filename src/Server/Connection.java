package Server;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Dictionary;

/**
 * Created by Oscar Norman <br>
 * Date: 2020-11-13   <br>
 * Time: 09:27   <br>
 * Project: JavaQuizkampen <br>
 */
public class Connection extends Thread {

    private final Socket clientSocket;

    public Connection(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        testConnection();
    }


    public void testConnection() {
        try {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            for (int i = 0; i <= 3; i++) {
                out.println(i);
                Thread.sleep(500);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
