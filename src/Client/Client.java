package Client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
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
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            String message;
            while (true) {
                while ((message = in.readLine()) != null) {
                    System.out.println(message);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
