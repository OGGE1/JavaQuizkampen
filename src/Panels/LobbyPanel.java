package Panels;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Sana Eneroth Boukchana
 * Date: 2020-11-21
 * Time: 15:03
 * Project: JavaQuizkampen
 * Copyright: MIT
 */
public class LobbyPanel extends JPanel {
    private final JButton startNewGame;

    public LobbyPanel() {
        setLayout(new BorderLayout());
        setOpaque(false);

        add(new GradientTopPanel(), BorderLayout.NORTH);

        JLabel messageLabel = new JLabel("Waiting for player");
        messageLabel.setOpaque(false);
        messageLabel.setHorizontalAlignment(SwingConstants.HORIZONTAL);
        Font titleFont = new Font("Andale Mono", Font.BOLD, 16);
        messageLabel.setFont(titleFont);
        add(messageLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();

        startNewGame = new JButton(new ImageIcon("resources/lobbybutton.png"));
        startNewGame.setOpaque(false);
        startNewGame.setBorderPainted(false);

        buttonPanel.add(startNewGame);
        buttonPanel.setOpaque(false);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    public JButton getButton() {
        return startNewGame;
    }
}
