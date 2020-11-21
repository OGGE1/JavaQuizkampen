package NewPanels;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Oscar Norman <br>
 * Date: 2020-11-21   <br>
 * Time: 11:31   <br>
 * Project: JavaQuizkampen <br>
 */
public class Lobby extends JPanel {

    private JLabel lobbyText = new JLabel();
    private JPanel lobbyTextPanel = new JPanel();
    

    public Lobby() {



        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(280, 460));
    }
}
