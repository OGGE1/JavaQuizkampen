package Panels;

import javax.swing.*;

/**
 * Created by Oscar Norman <br>
 * Date: 2020-11-12   <br>
 * Time: 15:29   <br>
 * Project: JavaQuizkampen <br>
 */
public class PanelForTestingResultPanel extends JFrame{
    ResultPanel rp = new ResultPanel();
    JPanel panel = new JPanel();

    PanelForTestingResultPanel(){
        add(panel);
        panel.add(rp);
        setSize(300,500);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }


    public static void main(String[] args) {
        PanelForTestingResultPanel p = new PanelForTestingResultPanel();

    }
}
