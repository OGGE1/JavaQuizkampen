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

    public GameBoard(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(400, 600);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.cyan);

    }
}
