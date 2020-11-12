package Panels;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2020-11-12 <br>
 * Time: 15:34 <br>
 * Project: JavaQuizkampen <br>
 */
public class GamePanel {

    JPanel groundPanel = new JPanel();
    JPanel topPanel = new JPanel();

    JLabel questions1 = new JLabel("");
    JLabel questions2 = new JLabel("");
    JLabel questions3 = new JLabel("");
    JLabel round = new JLabel();

    public GamePanel(){
        JFrame frame = new JFrame();
        frame.add(groundPanel);
     //   groundPanel.setLayout(new BorderLayout());
        groundPanel.setBackground(Color.CYAN);
        groundPanel.setSize(300,500);

        topPanel.setLayout(new GridLayout(1,7));
        topPanel.setSize(300,50);
        topPanel.setBackground(Color.BLUE);

        questions1.setBackground(Color.RED);
        questions1.setSize(42,50);
        questions1.setOpaque(true);
        questions2.setBackground(Color.RED);
        questions2.setSize(42,50);
        questions2.setOpaque(true);
        questions3.setBackground(Color.RED);
        questions3.setSize(42,50);
        questions3.setOpaque(true);

        topPanel.add(questions1); topPanel.add(questions2); topPanel.add(questions3);


        groundPanel.add(topPanel);



        frame.setVisible(true);
        frame.setSize(300,500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }


    public static void main(String[] args) {
        GamePanel start = new GamePanel();
    }
}
