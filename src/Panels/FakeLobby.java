package Panels;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Oscar Norman <br>
 * Date: 2020-11-18   <br>
 * Time: 11:14   <br>
 * Project: JavaQuizkampen <br>
 */
public class FakeLobby extends JPanel {

    private JButton button = new JButton("Start");
    private JLabel label = new JLabel("Waiting for player");

    public FakeLobby() {
        button.setEnabled(false);
        this.add(label);
        this.add(button);
        this.setBackground(Color.BLUE);
        this.setPreferredSize(new Dimension(280, 460));
    }

    public JButton getButton() {
        return button;
    }
}
