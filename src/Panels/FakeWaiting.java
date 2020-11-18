package Panels;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Oscar Norman <br>
 * Date: 2020-11-18   <br>
 * Time: 11:55   <br>
 * Project: JavaQuizkampen <br>
 */
public class FakeWaiting extends JPanel {

    private JLabel label = new JLabel("Waiting for opponent to choose category");

    public FakeWaiting() {
        this.add(label);
        setPreferredSize(new Dimension(280, 460));
    }
}
