package Server;
import Client.Utility;

import java.io.*;
import java.net.Socket;
import java.util.Properties;

/**
 * Created by Oscar Norman <br>
 * Date: 2020-11-13   <br>
 * Time: 09:27   <br>
 * Project: JavaQuizkampen <br>
 */
public class ClientHandler extends Thread implements Serializable {

    Socket p1;
    ObjectOutputStream p1oos;
    ObjectInputStream p1ois;

    Socket p2;
    ObjectOutputStream p2oos;
    ObjectInputStream p2ois;

    Message message = new Message();
    Utility util = new Utility();
    Properties properties = new Properties();
    Protocol p = new Protocol();

//    boolean p1Turn = true;
    int participant = 1;

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

            String player1Name = (String) p1ois.readObject();
            String player2Name = (String) p2ois.readObject();

            p1oos.writeObject(new Message(player2Name, 1));
            p2oos.writeObject(new Message(player1Name, 2));

            p1oos.writeObject(new Initiator());
            p2oos.writeObject(new Initiator());

            while (true) {
                if (p.getPlayer() == 1) {
//                    p1Turn = true;
                    message = p.getResponse(message);
                    p1oos.writeObject(message);
                }
                while (participant == 1) {
                    while ((obj = p1ois.readObject()) != null) {
                        message = (Message) obj;
                        message = p.getResponse(message);
                        if(message.getSwitchToPlayer() == 2){
                            p2oos.writeObject(message);
                            participant = 2;
                            break;
                        }
                        if(message.getSwitchToPlayer() == 3){
                            participant = 3;
                            break;
                        }
                        p1oos.writeObject(message);
                    }
                }

                while (participant == 2) {
                    while ((obj = p2ois.readObject()) != null) {
                        message = (Message) obj;
                        message = p.getResponse(message);
                        if(message.getSwitchToPlayer() == 1){
                            p2oos.writeObject(message);
                            participant = 1;
                            break;
                        }
                        if(message.getSwitchToPlayer() == 3){
                            participant = 3;
                            break;
                        }
                        p1oos.writeObject(message);
                    }
                }

                if(participant == 3){
                    p1oos.writeObject(message);
                    message = p.getResponse(message);
                    p2oos.writeObject(message);
                    participant = message.getSwitchToPlayer();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

