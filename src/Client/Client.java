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
    ObjectOutputStream objectOut;
    ObjectInputStream objectIn;

    Utility util = new Utility();
    Message message = new Message();
    Properties p = new Properties();

    private JPanel mainPanel = new JPanel();
    GamePanel gp = new GamePanel();
    ResultPanel rp = new ResultPanel();
    LobbyPanel lp = new LobbyPanel();
    CategoryPanel cp = new CategoryPanel();

    String answer = "";
    boolean hasAnswered;
    int rounds;
    int numQuestions;
    int currentQuestion;
    int currentRound = 0;
    boolean turn = false;

    public Client() throws IOException {

        setSettings();
        setUpCategoryButtonListener();
        setUpResultButtonListener();
        setUpLobbyButtonListener();
        setUpGpButtonListener();

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
//                    System.out.println(((Message)fromServer).getPerform());
                    if (((Message) fromServer).getPerform().equalsIgnoreCase("CHOOSE CATEGORY")) {
                        turn = true;
                        enableButtons(true);
                        changePanel(cp);
                    }

                    else if (((Message) fromServer).getPerform().equalsIgnoreCase("ANSWER QUESTION")) {
                        message = (Message)fromServer;
                        util.clearAnswersList();
                        playRound();
                        changePanel(rp);
                        sendObject(message);
                    }

                    else if (((Message) fromServer).getPerform().equalsIgnoreCase("SEE RESULT")) {
                        message = (Message)fromServer;
                        changePanel(rp);
                        if (!turn){
                            enableButtons(true);
                        } else turn = false;

                        util.addEnemyAnswers(message.getResultsFromAnswers()); // Här blir det fel. Får gamla svaren för motståndaren
                        rp.newRound(message.getCategory(), currentRound, util.getRoundAnswers());
                        currentRound++;
                        if(currentRound == rounds){
                            enableButtons(true);
                        }
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void enableButtons(Boolean setTo){
        rp.getButton().setEnabled(setTo);
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

    public void setUpGpButtonListener(){
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

    public void setUpResultButtonListener(){
        rp.getButton().addActionListener(l -> {
            if(rp.getButton().getText().equalsIgnoreCase("Avsluta")){
                System.exit(0);
            }
            sendObject(message);
        });
    }

    public void setUpLobbyButtonListener(){
        lp.getButton().addActionListener(l -> {
            sendObject(message);
        });
    }

    public void setUpCategoryButtonListener() {
        cp.getButtonMap().forEach((button, category) -> {
            button.addActionListener(l -> {
                        Message temp = new Message();
                        temp.setCategory(category);
                        sendObject(temp);
                    }
            );
        });
    }

    public void playRound() throws InterruptedException {
        changePanel(gp);
        gp.setGameResult(0);
        gp.setRoundNr(currentRound+1);
        currentQuestion = 0;

        gp.setCategory(message.getCategory());
        for (int i = 0; i < numQuestions; i++) {
            gp.setUpQuestion(message.getQaList().get(i));
            hasAnswered = false;
            while(!hasAnswered){Thread.sleep(10);}
            Thread.sleep(500);
        }
        message.setResultsFromAnswers(util.getRoundAnswers());

        System.out.println("Mina sparade svar till util: " +util.getRoundAnswers().toString());
        System.out.println("Skickade svar till servern:  " +message.getResultsFromAnswers().toString());

        message.setPlayerID(util.getPlayerID());
    }

    public void sendObject(Message out) {
        try {
            objectOut.writeObject(out);
            objectOut.reset();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        enableButtons(false);
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
        if(util.getPlayerID() == 2) lp.getButton().setEnabled(false);
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

