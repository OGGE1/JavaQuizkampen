package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by Sana Eneroth Boukchana
 * Date: 2020-11-16
 * Time: 19:47
 * Project: JavaQuizkampen
 * Copyright: MIT
 */
public class LobbyScreen {

    private JButton startNewGame;
    private JLabel messageLabel;
    private ActionListener newGameListener;

    public LobbyScreen(ActionListener newGameListener) {
        this.newGameListener = newGameListener;
    }

    public void setupLobby(JPanel controlPanel) {
        controlPanel.setLayout(new BorderLayout());
        controlPanel.setOpaque(false);
        createMessageLabel(controlPanel);
        createButton(controlPanel);
    }

    public void setMessage(String message) {
        messageLabel.setText(message);
    }

    private void createMessageLabel(JPanel container) {
        messageLabel = new JLabel();
        messageLabel.setOpaque(false);
        messageLabel.setHorizontalAlignment(SwingConstants.HORIZONTAL);
        Font titleFont = new Font("Andale Mono", Font.BOLD, 16);
        messageLabel.setFont(titleFont);
        container.add(messageLabel, BorderLayout.CENTER);
    }

    public void setButtonEnabled(boolean enabled) {
        startNewGame.setEnabled(enabled);
    }

    private void createButton(JPanel container) {
        JPanel buttonPanel = new JPanel();

        startNewGame = new JButton(new ImageIcon("resources/lobbybutton.png"));
        startNewGame.setOpaque(false);
        startNewGame.setEnabled(false);
        startNewGame.setBorderPainted(false);
        startNewGame.addActionListener(newGameListener);

        buttonPanel.add(startNewGame);
        buttonPanel.setOpaque(false);

        container.add(buttonPanel, BorderLayout.SOUTH);
    }
}
