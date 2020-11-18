package Server;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Formatter;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Oscar Norman <br>
 * Date: 2020-11-12   <br>
 * Time: 15:26   <br>
 * Project: JavaQuizkampen <br>
 */
public class Server extends JFrame {
    private final JTextArea outputArea;
    private final Player[] players;
    private ServerSocket server;

    private final int currentPlayer;
    private final static int PLAYER_1 = 0;
    private final static int PLAYER_2 = 1;

    private final ExecutorService runGame;
    private final Lock gameLock;
    private final Condition otherPlayerConnected;
    private final Condition otherPlayerTurn;

    public Server() {
        super("Quizkampen server"); // titel för fönster

        // skapar ExecutorService med en tråd för varje spelare
        runGame = Executors.newFixedThreadPool(2);
        gameLock = new ReentrantLock(); // skapa trådlås för spelet

        // villkor för att båda spelarna ska vara uppkopplade
        otherPlayerConnected = gameLock.newCondition();

        // villkor för motståndarens tur i spelet
        otherPlayerTurn = gameLock.newCondition();

        players = new Player[2];
        currentPlayer = PLAYER_1;

        try {
            server = new ServerSocket(12345, 2);
        } catch (IOException ioException) {
            ioException.printStackTrace();
            System.exit(1);
        }

        outputArea = new JTextArea();
        add(outputArea, BorderLayout.CENTER);
        outputArea.setText("Server awaiting connections\n");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setVisible(true);
    }

    // väntar på två anslutningar så att spelet kan köras
    public void execute() {
        waitForPlayers();
        gameLock.lock(); // lås-signal till spelare 1s tråd

        try {
            players[PLAYER_1].setSuspended(false); // återuppta spelare 1
            otherPlayerConnected.signal(); // sänd signal till spelare 1 att den kan fortsätta exekvering
        } finally {
            gameLock.unlock(); // låser upp spelet när spelare 1 får signal om motståndare
        }
    }

    private void waitForPlayers() {
        for (int i = 0; i < players.length; i++) {
            try {
                players[i] = new Player(server.accept(), i);
                System.out.println("Player " + i+1 + " connected");
                runGame.execute(players[i]); // starta spelartråden
            }
            catch (IOException ioException) {
                ioException.printStackTrace();
                System.exit(1);
            }
        }
    }

    // display message in outputArea
    private void displayMessage(final String messageToDisplay) {
        // display message from event-dispatch thread of execution
        // visar meddelande från
        // uppdaterar "outputArea"
        SwingUtilities.invokeLater(() -> { outputArea.append(messageToDisplay); });
    }

    /**
     * Check if the answer is correct.
     * INCOMPLETE - Need logic to check the question
     * @param answerIndex The index for the alternative the player clicked
     * @return true if correct, false otherwise
     */
    public boolean isAnswerCorrect(int answerIndex) {
        return true;
    }

    public boolean isGameCompleted() {
        return false; // TODO
    }

    // private inner class Player manages each Player as a runnable
    // Privat inre klass som hanterar varje spelare som "körbar"
    private class Player implements Runnable {
        private final Socket connection;
        private Scanner input;
        private Formatter output;
        private final int playerNumber;
        private boolean suspended = true;

        public Player(Socket socket, int number) {
            playerNumber = number;
            connection = socket;

            try {
                input = new Scanner(connection.getInputStream());
                output = new Formatter(connection.getOutputStream());
            }
            catch (IOException ioException) {
                ioException.printStackTrace();
                System.exit(1);
            }
        }

        public void run() {
            try {
                displayMessage("Player " + playerNumber + " connected\n");
                output.format("%s\n", playerNumber); // skickar spelares "märkning"
                output.flush(); // flush output

                // Om spelare 1 väntar på en motståndare
                if (playerNumber == PLAYER_1) {
                    output.format("%s\n", "Waiting for another player");
                    output.flush();

                    gameLock.lock(); // "låser" spelet i väntan på spelare nr. 2 (motståndare)

                    try {
                        while (suspended) {
                            otherPlayerConnected.await();
                        }
                    } catch (InterruptedException exception) {
                        exception.printStackTrace();
                    } finally {
                        gameLock.unlock();
                    }
                }
                sendStartGameSignal();

                while (!isGameCompleted()) {
                    int answer = 0;

                    if (input.hasNext())
                        answer = input.nextInt();

                    if (isAnswerCorrect(answer)) {
                        // Markera korrekt svar
                    } else {
                        // Markera om svaret är felaktigt
                    }
                }
            } finally {
                try {
                    connection.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                    System.exit(1);
                }
            }
        }

        private void sendStartGameSignal() {
            output.format("%s\n", "Player 2 connected");
            output.flush();

            output.format("%s\n", "Ready to play");
            output.flush();
        }

        public void setSuspended(boolean status) {
            suspended = status;
        }
    }

    public static void main(String[] args) {
        Server s = new Server();
        s.execute();
    }
}
