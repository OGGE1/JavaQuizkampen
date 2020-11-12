package UserInterface;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Benjamin Brankovic
 * Date: 2020-11-12
 * Time: 14:13
 * Project: JavaQuizkampen
 */
public class GameBoard extends JFrame {

    public GameBoard() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 500);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.LIGHT_GRAY);
        setupLobby(getContentPane());
        this.setVisible(true);
    }

    public void setupLobby(Container controlPanel) {
        controlPanel.setLayout(new BorderLayout());

        createTopPanel(controlPanel);

        createButton(controlPanel);
    }

    private void createTopPanel(Container container) {
        ImageIcon imageIcon = new ImageIcon("/Users/sana/Desktop/logo.png");
        Image image = imageIcon.getImage();
        Image scaledInstance = image.getScaledInstance(300, 70, Image.SCALE_SMOOTH);
        imageIcon.setImage(scaledInstance);
        JLabel iconLogo = new JLabel(imageIcon);

        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.decode("#4AA8CC"));
        topPanel.setPreferredSize(new Dimension(300, 80));
        topPanel.add(iconLogo);

        container.add(topPanel, BorderLayout.NORTH);
    }

    private void createButton(Container container) {
        JPanel buttonPanel = new JPanel();

        JButton startNewGame = new JButton("START NEW GAME");
        startNewGame.setPreferredSize(new Dimension(280, 50));
        startNewGame.setBackground(Color.decode("#40DA3F"));
        startNewGame.setOpaque(true);
        startNewGame.setBorderPainted(false);

        buttonPanel.add(startNewGame);
        buttonPanel.setOpaque(false);

        container.add(buttonPanel, BorderLayout.SOUTH);
    }
}
