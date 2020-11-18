package Panels;

import javax.swing.*;

/**
 * Created by Oscar Norman <br>
 * Date: 2020-11-12   <br>
 * Time: 15:29   <br>
 * Project: JavaQuizkampen <br>
 */
public class Panel extends JFrame{
    ResultPanel rp = new ResultPanel();
    JPanel panel = new JPanel();

    Panel(){
        add(panel);
        panel.add(rp);
        setSize(300,500);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }


    public static void main(String[] args) {
        Panel p = new Panel();

    }
}
