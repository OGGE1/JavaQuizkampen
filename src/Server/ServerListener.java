package Server;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Oscar Norman <br>
 * Date: 2020-11-12   <br>
 * Time: 14:03   <br>
 * Project: JavaQuizkampen <br>
 */
public class ServerListener {

    public static void main(String[] args) throws Exception {
        int port = 27015;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server running...");

        while (true) {
            try {
                Socket p1 = serverSocket.accept();
                Socket p2 = serverSocket.accept();

                ClientHandler ch = new ClientHandler(p1, p2);
                ch.start();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
