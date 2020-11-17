package UserInterface;

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

    private ActionListener newGameListener;

    public LobbyScreen(ActionListener newGameListener) {
        this.newGameListener = newGameListener;
    }

    public void setupLobby(JPanel controlPanel) {
        controlPanel.setLayout(new BorderLayout());
        controlPanel.setOpaque(false);
        createButton(controlPanel);
    }

    private void createButton(JPanel container) {
        JPanel buttonPanel = new JPanel();

        JButton startNewGame = new JButton(new ImageIcon("resources/lobbybutton.png"));
        startNewGame.setOpaque(false);
        startNewGame.setBorderPainted(false);
        startNewGame.addActionListener(newGameListener);

        buttonPanel.add(startNewGame);
        buttonPanel.setOpaque(false);

        container.add(buttonPanel, BorderLayout.SOUTH);
    }
}
