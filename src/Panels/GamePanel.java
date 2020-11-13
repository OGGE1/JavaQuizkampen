package Panels;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2020-11-12 <br>
 * Time: 15:34 <br>
 * Project: JavaQuizkampen <br>
 */
public class GamePanel {

    JPanel groundPanel = new JPanel();

    JPanel centerPanel = new JPanel();
    JPanel questionPanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    JPanel categoryPanel = new JPanel();
    JLabel categoryLabel = new JLabel("Category",JLabel.CENTER);
    JPanel questionLabelPanel = new JPanel();
    JLabel questionLabel = new JLabel("Fråga",JLabel.CENTER);
    JPanel topPanel = new JPanel();
    JLabel roundNr = new JLabel("0",JLabel.CENTER);
    JLabel name = new JLabel("Display of name",JLabel.RIGHT);

    List<JButton> buttons = new ArrayList<>();
    List<JLabel> result = new ArrayList<>();

    public GamePanel(){
//        JFrame frame = new JFrame();
        
        groundPanel.setLayout(new BorderLayout());
        groundPanel.setPreferredSize(new Dimension(300,500));
//        frame.add(groundPanel);

        centerPanel.setLayout(new GridLayout(2,1));
        groundPanel.add(centerPanel,BorderLayout.CENTER);

        questionPanel.setLayout(new BorderLayout());
        centerPanel.add(questionPanel);

        categoryPanel.setLayout(new FlowLayout());
        categoryPanel.add(categoryLabel);
        categoryPanel.setBackground(Color.MAGENTA);
        questionPanel.add(categoryPanel,BorderLayout.NORTH);

        questionLabelPanel.setBackground(Color.WHITE);
        questionLabelPanel.add(questionLabel);

        questionPanel.add(questionLabelPanel,BorderLayout.CENTER);

        buttonPanel.setLayout(new GridLayout(2,2));

        for(int i = 0; i < 4; i++) {
            buttons.add(new JButton("Svar"));
            buttonPanel.add(buttons.get(i));
        //    buttons.get(i).setMargin(new Insets(20, 20, 20, 20));
        }
        centerPanel.add(buttonPanel);

        topPanel.setBackground(new Color(99, 118, 239));

        for(int i = 0; i < 4; i++){
            result.add(new JLabel("Q "+(i+1),JLabel.CENTER));
            result.get(i).setBorder(new EtchedBorder());
            result.get(i).setBackground(Color.red);
            result.get(i).setOpaque(true);
            topPanel.add(result.get(i));
        }

        roundNr.setBorder(new EtchedBorder());
        roundNr.setOpaque(true);
        roundNr.setBackground(Color.WHITE);
        roundNr.setPreferredSize(new Dimension(40,20));
        topPanel.add(roundNr);
        name.setForeground(Color.WHITE);
        name.setPreferredSize(new Dimension(110,20));
        topPanel.add(name);
        groundPanel.add(topPanel,BorderLayout.NORTH);

//        frame.setVisible(true);
//        frame.setSize(300,500);
//        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public JPanel getGamePanel(){
        return groundPanel;
    }

    public List<JButton> getGameButtons(){
        return buttons;
    }

    public List<JLabel> getGameResults(){
        return result;
    }

    public JLabel getRoundNr(){
        return roundNr;
    }

    public static void main(String[] args) {
        GamePanel start = new GamePanel();
    }
}
