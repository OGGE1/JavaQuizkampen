package Client;
import Panels.*;
import Server.Initiator;
import Server.Message;
import Server.QA;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.Arrays;
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
    ObjectOutputStream objectOut;
    ObjectInputStream objectIn;

    Utility util = new Utility();
    Message message = new Message();
    Properties p = new Properties();

    private JPanel mainPanel = new JPanel();
    GamePanel gp = new GamePanel();
    ResultPanel rp = new ResultPanel();
    FakeLobby fl = new FakeLobby();
    FakeCategory fc = new FakeCategory();
    FakeWaiting fw = new FakeWaiting();

    String answer = "";
    boolean hasAnswered;
    int rounds;
    int numQuestions;
    int currentQuestion;

    public Client() throws IOException {

        setSettings();
        setUpFakeCategory();

        //util.setPlayerName(JOptionPane.showInputDialog("Enter name")); TODO GLÖM EJ ATT TA BORT KOMMENTAREN (hårdkodade namnet)
        util.setPlayerName("Oscar");
        gp.setName(util.getPlayerName());

        fl.getButton().addActionListener(l -> {
            changePanel(gp);
        });

        mainPanel.add(fl);

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

            while (true) {
                while ((fromServer = objectIn.readObject()) != null) {
                    if (fromServer instanceof Initiator) {
                        System.out.println("connected");
                        fl.getButton().setEnabled(true);
                    }
                    // fromServer instanceof Message &&
                    else if (((Message) fromServer).getPerform().equalsIgnoreCase("CHOOSE CATEGORY")) {
                        changePanel(fc);
                    }

                    else if (((Message) fromServer).getPerform().equalsIgnoreCase("ANSWER QUESTION")) {
                        message = (Message)fromServer;
                        playRound();
                        sendObject(message);
                        changePanel(fw);
                    }

                    else if (((Message) fromServer).getPerform().equalsIgnoreCase("SEE RESULT")) {
                        message = (Message)fromServer;
                        changePanel(rp);
                        util.addEnemyAnswers(message.getResultsFromAnswers());

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

    public void setUpGpListener(){
        for (var e : gp.getGameButtons()) {
            e.addActionListener(l -> {
                answer = e.getText();
                if(e.getText().equalsIgnoreCase(message.getQaList().get(currentQuestion).getCorrectAnswer())){
                    e.setBackground(Color.GREEN);
                    gp.addPoint();
                    util.addAnswers(true);
                }
                else {
                    e.setBackground(Color.RED);
                    util.addAnswers(false);
                }
                currentQuestion++;
                hasAnswered = true;
            });
        }
    }

    public void playRound() throws InterruptedException {
        changePanel(gp);
        currentQuestion = 0;
        setUpGpListener();

        gp.setCategory(message.getCategory());
        for (int i = 0; i < numQuestions; i++) {
            gp.setUpQuestion(message.getQaList().get(i));
            hasAnswered = false;
            while(!hasAnswered){Thread.sleep(10);}
            Thread.sleep(2000);
        }
        message.setResultsFromAnswers(util.getRoundAnswers());
        message.setPlayerID(util.getPlayerID());
    }

    public void sendObject(Object object) {
        try {
            objectOut.writeObject(object);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void setUpFakeCategory() {
        for (var e : fc.getButtonList()) {
            e.addActionListener(l -> {
                String text = e.getText();
                util.setCategory(text);
                message.setCategory(text);
                sendObject(message);
            });
        }
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
