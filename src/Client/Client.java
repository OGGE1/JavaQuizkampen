package Client;

import Server.Initiator;
import static Client.Utility.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;


/**
 * Created by Oscar Norman <br>
 * Date: 2020-11-12   <br>
 * Time: 14:06   <br>
 * Project: JavaQuizkampen <br>
 */
public class Client extends JFrame implements Serializable {

    String[] questions = {"Vad är 1+1?"};
    String[] answers = {"1", "2", "5", "10", "Nej", "Ja", "999", "1024", "1337"};
    JLabel questionLabel = new JLabel(questions[0]);
    JPanel mainPanel = new JPanel();
    JPanel gamePanel = new JPanel();
    JLabel connection = new JLabel();
    InetAddress address = InetAddress.getLoopbackAddress();
    JButton[][] buttons;
    int port = 27015;
    ObjectOutputStream objectOut;
    ObjectInputStream objectIn;

    public Client() {
        gamePanel.setLayout(new GridLayout(ROWS, COLUMNS));
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(connection);
        mainPanel.add(questionLabel, BorderLayout.NORTH);
        mainPanel.add(gamePanel, BorderLayout.CENTER);

        this.add(mainPanel);
        this.setSize(new Dimension(400, 400));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        buttons = fillGrid();
        updateGUI();
        addActionListenerToArray(buttons);

        try {
            Socket clientSocket = new Socket(address, port);
            objectOut = new ObjectOutputStream(clientSocket.getOutputStream());
            objectIn = new ObjectInputStream(clientSocket.getInputStream());

            Object fromServer;
            String fromClient;


            while ((fromServer = objectIn.readObject()) != null) {
                if (fromServer instanceof Initiator) {
                    connection.setText("Connected to server");
                } else if (fromServer instanceof JButton) {

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public JButton[][] fillGrid() {
        JButton[][] out = new JButton[ROWS][COLUMNS];
        int itr = 0;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                out[i][j] = new JButton(answers[itr++]);
            }
        }
        return out;
    }

    public void addActionListenerToArray(JButton[][] buttons) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                int r = i;
                int c = j;
                buttons[i][j].addActionListener(l -> {

                    if (isCorrect(buttons, r, c)){
                            buttons[r][c].setBackground(Color.green);
                            disableButtons(this.buttons);
                    } else {
                        buttons[r][c].setBackground(Color.red);
                        disableButtons(this.buttons);
                    }
                });
            }
        }
    }

    public void disableButtons(JButton[][] buttons) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }

    public void updateGUI() {
        gamePanel.removeAll();
        int itr = 0;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                gamePanel.add(buttons[i][j]);
            }
        }
        gamePanel.revalidate();
        gamePanel.repaint();
    }

    public boolean isCorrect(JButton[][] buttons, int x, int y) {
        return buttons[x][y].getText().equalsIgnoreCase(answers[1]);
    }


    public void sendObject(Object object) {
        try {
            objectOut.writeObject(object);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public JButton[][] getButtons() {
        return buttons;
    }

    public static void main(String[] args) {
        new Client();
    }
}

