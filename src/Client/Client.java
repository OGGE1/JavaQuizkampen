package Client;

import Panels.CategoryPanel;
import Panels.GamePanel;
import Panels.LobbyPanel;
import Panels.WaitingPanel;
import Server.Initiator;
import Server.Message;
import Server.QA;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;
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
    LobbyPanel lp = new LobbyPanel();
    Utility util = new Utility();
    CategoryPanel cp = new CategoryPanel();
    WaitingPanel wp = new WaitingPanel();
    Message message = new Message();
    Properties p = new Properties();
    String answer = "";
    boolean hasAnswered;
    int rounds;
    int numQuestions;
    int currentQuestion;

    public Client() throws IOException {

        setSettings();
        setUpFakeCategory();

        //util.setPlayerName(JOptionPane.showInputDialog("Enter name")); TODO GLÖM EJ ATT TA BORT KOMMENTAREN
        util.setPlayerName("Oscar");
        gp.setName(util.getPlayerName());

        lp.getButton().addActionListener(l -> {
            changePanel(gp);
        });

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

            while (true) {
                while ((fromServer = objectIn.readObject()) != null) {
                    if (fromServer instanceof Initiator) {
                        System.out.println("connected");
                        lp.getButton().setEnabled(true);
                    }
                    // fromServer instanceof Message &&
                    else if (((Message) fromServer).getPerform().equalsIgnoreCase("CHOOSE CATEGORY")) {
                        changePanel(cp);
                    }

                    else if (((Message) fromServer).getPerform().equalsIgnoreCase("ANSWER QUESTION")) {
                        message = (Message)fromServer;
                        playRound();
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
                handleAnsweredQuestion();
            });
        }
    }

    private void handleAnsweredQuestion() {
        currentQuestion++;
        QA nextQuestion = getNextQuestion();
        if (nextQuestion != null) {
            gp.setUpQuestion(nextQuestion);
        } else {
            message.setResultsFromAnswers(util.getRoundAnswers());
            message.setPlayerID(util.getPlayerID());
            sendObject(message);
            changePanel(wp);
        }
    }

    private QA getNextQuestion() {
        List<QA> qaList = message.getQaList();

        if (currentQuestion < qaList.size()) {
            return qaList.get(currentQuestion);
        }
        return null;
    }

    public void playRound() {
        changePanel(gp);
        currentQuestion = 0;
        setUpGpListener();

        gp.setCategory(message.getCategory());

        QA nextQuestion = getNextQuestion();
        if (nextQuestion != null) {
            gp.setUpQuestion(nextQuestion);
        }
    }

    public void sendObject(Object object) {
        try {
            objectOut.writeObject(object);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void setUpFakeCategory() {
        for (var e : cp.getButtonList()) {
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

