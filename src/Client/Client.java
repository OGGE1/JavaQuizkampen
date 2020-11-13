package Client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by Oscar Norman <br>
 * Date: 2020-11-12   <br>
 * Time: 14:06   <br>
 * Project: JavaQuizkampen <br>
 */
public class Client {

    public static void main(String[] args) {
        InetAddress address = InetAddress.getLoopbackAddress();
        int port = 8818;


        try(Socket clientSocket = new Socket(address, port);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true)) {

            String message;
                while ((message = in.readLine()) != null) {
                    System.out.println(message);
                }


        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}

