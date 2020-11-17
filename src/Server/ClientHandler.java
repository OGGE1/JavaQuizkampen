package Server;
import java.io.*;
import java.net.Socket;

/**
 * Created by Oscar Norman <br>
 * Date: 2020-11-13   <br>
 * Time: 09:27   <br>
 * Project: JavaQuizkampen <br>
 */
public class ClientHandler extends Thread implements Serializable {

    Socket p1;
    Socket p2;
    ObjectOutputStream p1oos;
    ObjectInputStream p1ois;
    ObjectOutputStream p2oos;
    ObjectInputStream p2ois;

    boolean p1Turn = true;


    public ClientHandler(Socket p1, Socket p2) {
        this.p1 = p1;
        this.p2 = p2;

    }

    @Override
    public void run() {
        try {
            p1oos = new ObjectOutputStream(p1.getOutputStream());
            p1ois = new ObjectInputStream(p1.getInputStream());
            p2oos = new ObjectOutputStream(p2.getOutputStream());
            p2ois = new ObjectInputStream(p2.getInputStream());

            Object obj;

            p1oos.writeObject(new Initiator());
            p2oos.writeObject(new Initiator());

            while (true) {
                while (p1Turn) {
                    while ((obj = p1ois.readObject()) != null) {
                        System.out.println("Player one");
                        p1oos.writeObject(new Initiator());
                        p1Turn = false;
                        break;
                    }
                }

                while (!p1Turn) {
                    while ((obj = p2ois.readObject()) != null) {
                        System.out.println("Player two");
                        p2oos.writeObject(new Initiator());
                        p1Turn = true;
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

