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
        super("Quizkampen server"); // set title of window

        // create ExecutorService with a thread for each player
        runGame = Executors.newFixedThreadPool(2);
        gameLock = new ReentrantLock(); // create lock for game

        // condition variable for both players being connected
        otherPlayerConnected = gameLock.newCondition();

        // condition variable for the other player's turn
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

    // wait for two connections so game can be played
    public void execute() {
        waitForPlayers();
        gameLock.lock(); // lock game to signal player 1's thread

        try {
            players[PLAYER_1].setSuspended(false); // resume player 1
            otherPlayerConnected.signal(); // wake up player 1's thread
        } finally {
            gameLock.unlock(); // unlock game after signalling player 1
        }
    }

    private void waitForPlayers() {
        for (int i = 0; i < players.length; i++) {
            try {
                players[i] = new Player(server.accept(), i);
                System.out.println("New player connected");
                runGame.execute(players[i]); // execute player runnable
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
        // updates outputArea
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
                output.format("%s\n", playerNumber); // send player's mark
                output.flush(); // flush output

                // if player 1, wait for another player to arrive
                if (playerNumber == PLAYER_1) {
                    output.format("%s\n", "Waiting for another player");
                    output.flush();

                    gameLock.lock(); // lock game to  wait for second player

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
                        // Mark as correct answer
                    } else {
                        // Mark as wrong answer
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

        // set whether or not thread is suspended
        public void setSuspended(boolean status) {
            suspended = status;
        }
    }

    public static void main(String[] args) {
        Server s = new Server();
        s.execute();
    }
}
