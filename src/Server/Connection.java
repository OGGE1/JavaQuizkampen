package Server;

import java.io.*;
import java.net.Socket;

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
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            String message, result;
            while ((message = in.readLine()) != null) {
                if (message.equalsIgnoreCase("exit")){
                    break;
                }
                result = "Typed: " + message + "\n";
                out.println(result);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
