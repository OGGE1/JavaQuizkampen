package Server;
import java.net.ServerSocket;

/**
 * Created by Oscar Norman <br>
 * Date: 2020-11-12   <br>
 * Time: 14:03   <br>
 * Project: JavaQuizkampen <br>
 */
public class ServerListener {

    private static ClientList clientList = new ClientList();

    public static void main(String[] args) throws Exception {
        int port = 27015;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server running...");

        while (true) {
            try {
                new Connection(serverSocket.accept(), clientList).start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
