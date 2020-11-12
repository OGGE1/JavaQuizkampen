package Server;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Oscar Norman <br>
 * Date: 2020-11-12   <br>
 * Time: 14:03   <br>
 * Project: JavaQuizkampen <br>
 */
public class Server {

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8818);
        int itr = 0;

        while (true) {
            try(Socket clientSocket = serverSocket.accept();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream())) {
                out.println("Yay!");
                itr++;

                if (itr == 2) {
                    //new ServerGame(PlayerOneSocket, PlayerTwoSocket).start;
                    System.out.println("2!");
                    itr = 0;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }


    }

}
