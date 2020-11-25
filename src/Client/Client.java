package Client;

import Panels.*;
import Server.Message;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Properties;


/**
 * Created by Oscar Norman <br>
 * Date: 2020-11-12   <br>
 * Time: 14:06   <br>
 * Project: JavaQuizkampen <br>
 */
public class Client extends JFrame implements Serializable {

    InetAddress address = InetAddress.getLoopbackAddress();
    int port = 27015;
    private JPanel mainPanel = new JPanel();
    ObjectOutputStream objectOut;
    ObjectInputStream objectIn;
    GamePanel gp = new GamePanel();
    ResultPanel rp = new ResultPanel();
    LobbyPanel lp = new LobbyPanel();
    Utility util = new Utility();
    CategoryPanel cp = new CategoryPanel();
    FakeWaiting wp = new FakeWaiting();
    Message message = new Message();
    Properties p = new Properties();
    String answer = "";
    boolean hasAnswered;
    int rounds;
    int numQuestions;
    int currentQuestion;
    int currentRound = 0;

    public Client() throws IOException {

        setSettings();
        setUpFakeCategory();
        setUpResultButtonListener();
        setUpLobbyButtonListener();

        //util.setPlayerName(JOptionPane.showInputDialog("Enter name")); TODO GLÖM EJ ATT TA BORT KOMMENTAREN (hårdkodade namnet)
        util.setPlayerName("Oscar");
        gp.setName(util.getPlayerName());

        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(lp);

        this.add(mainPanel);
        this.setSize(300, 500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        try {
            Socket clientSocket = new Socket(address, port);
            objectOut = new ObjectOutputStream(clientSocket.getOutputStream());
            objectIn = new ObjectInputStream(clientSocket.getInputStream());

            Object fromServer;

            startUp();

            System.out.println("Ditt namn: " + util.getPlayerName());
            System.out.println("Du är spelare #" + util.getPlayerID());
            System.out.println("Motståndare: " + util.getEnemyName());

            rp.setNamePlayerOne(util.getPlayerName());
            rp.setNamePlayerTwo(util.getEnemyName());

            while (true) {
                while ((fromServer = objectIn.readObject()) != null) {
                    if (((Message) fromServer).getPerform().equalsIgnoreCase("CHOOSE CATEGORY")) {
                        changePanel(cp);
                    } else if (((Message) fromServer).getPerform().equalsIgnoreCase("ANSWER QUESTION")) {
                        message = (Message) fromServer;
                        playRound();
                        sendObject(message);
                        changePanel(wp);
                    } else if (((Message) fromServer).getPerform().equalsIgnoreCase("SEE RESULT")) {
                        message = (Message) fromServer;
                        changePanel(rp);
                        util.addEnemyAnswers(message.getResultsFromAnswers());
                        rp.newRound(message.getCategory(), currentRound, util.getRoundAnswers());
                        currentRound++;
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setSettings() {
        try {
            p.load(new FileInputStream("src/Properties.properties"));
        } catch (Exception e) {
            System.out.println("Could not load properties file");
            e.printStackTrace();
        }
        rounds = Integer.parseInt(p.getProperty("rounds", "6"));
        numQuestions = Integer.parseInt(p.getProperty("questionsPerRound", "3"));
    }

    public void setUpGpListener() {
        for (var e : gp.getGameButtons()) {
            e.addActionListener(l -> {
                answer = e.getText();
                if (e.getText().equalsIgnoreCase(message.getQaList().get(currentQuestion).getCorrectAnswer())) {
                    e.setBackground(Color.GREEN);
                    gp.addPoint();
                    util.addAnswers(true);
                } else {
                    e.setBackground(Color.RED);
                    util.addAnswers(false);
                }
                currentQuestion++;
                hasAnswered = true;
            });
        }
    }

    public void setUpResultButtonListener() {
        rp.getButton().addActionListener(l -> {
            sendObject(message);
        });
    }

    public void setUpLobbyButtonListener() {
        lp.getButton().addActionListener(l -> {
            sendObject(message);
        });
    }

    public void playRound() throws InterruptedException {
        changePanel(gp);
        currentQuestion = 0;
        setUpGpListener();

        gp.setCategory(message.getCategory());
        for (int i = 0; i < numQuestions; i++) {
            gp.setUpQuestion(message.getQaList().get(i));
            hasAnswered = false;
            while (!hasAnswered) {
                Thread.sleep(10);
            }
            Thread.sleep(2000);
        }
        message.setResultsFromAnswers(util.getRoundAnswers());
        message.setPlayerID(util.getPlayerID());
    }

    public void sendObject(Message out) {
        try {
            objectOut.writeObject(out);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void setUpFakeCategory() {
        cp.getButtonMap().forEach((button, category) -> {
            button.addActionListener(l -> {
                        Message temp = new Message();
                        temp.setCategory(category);
                        sendObject(temp);
                    }
            );
        });
    }

    public void startUp() {
        try {
            objectOut.writeObject(util.getPlayerName());
            message = (Message) objectIn.readObject();
            util.setEnemyName(message.getName());
            util.setPlayerID(message.getPlayerID());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changePanel(JPanel panel) {
        mainPanel.removeAll();
        mainPanel.add(panel);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public static void main(String[] args) throws IOException {
        new Client();
    }
}

