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
public class Connection extends Thread implements Serializable {

    ClientList clientList;
    Socket clientSocket;
    ObjectOutputStream objectOut;
    ObjectInputStream objectIn;

    public Connection(Socket clientSocket, ClientList clientList) {
        this.clientSocket = clientSocket;
        this.clientList = clientList;
    }

    @Override
    public void run() {
        testConnection();
    }


    public void testConnection() {
        try {
            objectOut = new ObjectOutputStream(clientSocket.getOutputStream());
            objectIn = new ObjectInputStream(clientSocket.getInputStream());

            clientList.addObjectOutPutStream(objectOut);

            objectOut.writeObject(new Initiator());
            Object fromClient;

            while ((fromClient = objectIn.readObject()) != null) {
                for (var e : clientList.getObjectOut()){
                    e.writeObject(fromClient);
                }
            }



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
