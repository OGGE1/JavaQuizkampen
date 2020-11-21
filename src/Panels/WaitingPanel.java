package Panels;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Sana Eneroth Boukchana
 * Date: 2020-11-21
 * Time: 15:05
 * Project: JavaQuizkampen
 * Copyright: MIT
 */
public class WaitingPanel extends JPanel {

    private JLabel label = new JLabel("Waiting for opponent to choose category");

    public WaitingPanel() {
        label.setFont(new Font("Sans serif", Font.BOLD, 12));
        this.add(label);
        setPreferredSize(new Dimension(280, 460));
    }
}
