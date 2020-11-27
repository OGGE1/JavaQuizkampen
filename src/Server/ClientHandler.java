package Server;

import java.io.*;
import java.net.Socket;

public class ClientHandler extends Thread {

    Socket p1;
    ObjectOutputStream p1oos;
    ObjectInputStream p1ois;

    Socket p2;
    ObjectOutputStream p2oos;
    ObjectInputStream p2ois;

    Message message;
    Protocol p = new Protocol();

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

            while (true) {
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
                            p1oos.writeObject(message);
                            participant = 1;
                            break;
                        }
                        if(message.getSwitchToPlayer() == 3){
                            participant = 3;
                            break;
                        }
                        p2oos.writeObject(message);
                    }
                }

                if(participant == 3){
                    message.setSwitchToPlayer(p.getPlayer());
                    p1oos.writeObject(message);
                    p1oos.reset();
                    message = p.getResponse(message);
                    p2oos.writeObject(message);
                    p2oos.reset();
                    participant = message.getSwitchToPlayer();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

