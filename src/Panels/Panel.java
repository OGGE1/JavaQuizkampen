package Panels;

import javax.swing.*;

/**
 * Created by Oscar Norman <br>
 * Date: 2020-11-12   <br>
 * Time: 15:29   <br>
 * Project: JavaQuizkampen <br>
 */
public class Panel extends JFrame {

    public Panel(){
        GamePanel game = new GamePanel();
        add(game.getGamePanel());

        setResizable(false);
        setVisible(true);
        setSize(300,500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        Panel start = new Panel();
    }
}
