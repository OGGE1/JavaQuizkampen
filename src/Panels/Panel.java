package Panels;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Oscar Norman <br>
 * Date: 2020-11-12   <br>
 * Time: 15:29   <br>
 * Project: JavaQuizkampen <br>
 */
public class Panel extends JFrame {

    int score = 0;

    public Panel(String name){
        GamePanel gamePanel = new GamePanel(name);

        gamePanel.setRoundNr(3);
        gamePanel.setCategory("Sport och fritid");
        gamePanel.setQuestion("Vad hade NHL spelaren Peter Forsberg för tröjnummer?");
        gamePanel.getGameButtons().get(0).setText("21");
        gamePanel.getGameButtons().get(1).setText("27");
        gamePanel.getGameButtons().get(2).setText("12");
        gamePanel.getGameButtons().get(3).setText("72");

        add(gamePanel);

        ActionListener answer = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = e.getActionCommand();
                if(text.equalsIgnoreCase("21")){
                    score++;
                    gamePanel.setGameResult(score);
                }
            }
        };
        for(int i = 0; i < 4; i++){
            gamePanel.getGameButtons().get(i).addActionListener(answer);
        }

        setResizable(false);
        setVisible(true);
        setSize(300,500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        String name = "Nicke Boi";
        Panel start = new Panel(name);
    }
}
