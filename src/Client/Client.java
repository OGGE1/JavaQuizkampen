package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Formatter;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.awt.BorderLayout.CENTER;

/**
 * Created by Benjamin Brankovic
 * Date: 2020-11-12
 * Time: 14:13
 * Project: JavaQuizkampen
 */
public class Client extends JFrame implements Runnable {

    private final String host;
    private Scanner input; // input från server
    private Formatter output; // output till server
    private Socket connection; // connection till server

    private LobbyScreen lobbyScreen;
    private CategoryScreen categoryScreen;
    private JPanel contentPanel;

    public Client(String host) {
        this.host = host;

        ActionListener newGameListener = e -> {
            contentPanel.removeAll();
            categoryScreen.setupCategoryPanel(contentPanel);
            contentPanel.revalidate();
            repaint();
        };
        lobbyScreen = new LobbyScreen(newGameListener);
        categoryScreen = new CategoryScreen();

        setupFrame();

        startClient();
    }

    private void setupFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 500);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.LIGHT_GRAY);
        this.getContentPane().setLayout(new BorderLayout());
        this.setTitle("\u00A9 All rights reserved");
        getContentPane().add(new TopPanel(), BorderLayout.NORTH);

        contentPanel = new JPanel();
        this.getContentPane().add(contentPanel, CENTER);
        lobbyScreen.setupLobby(contentPanel);
        this.setVisible(true);
    }

    private void startClient() {
        try {
            connection = new Socket(InetAddress.getByName(host), 12345 );
            input = new Scanner( connection.getInputStream() );
            output = new Formatter( connection.getOutputStream() );
        }
        catch ( IOException ioException ) {
            ioException.printStackTrace();
        }

        // skapar och startar en arbetartråd för den här klienten
        ExecutorService worker = Executors.newFixedThreadPool( 1 );
        worker.execute( this );
    }

    @Override
    public void run() {
        while (true) {
            if (input.hasNextLine()) {
                String fromServer = input.nextLine();
                System.out.println("Received: \"" + fromServer + "\" from server");

                // Temporär start-signal. Vi behöver hitta en bättre lösning för det här.
                if (fromServer.startsWith("Player")) {
                    startGame();
                } else {
                    lobbyScreen.setMessage(fromServer);
                }
            }
        }
    }

    private void startGame() {
        // TEMPORÄRT
        lobbyScreen.setButtonEnabled(true);
    }

    public static void main(String[] args) {
        new Client("127.0.0.1");
    }
}
