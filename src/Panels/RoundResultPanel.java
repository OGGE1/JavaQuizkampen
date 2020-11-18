package Panels;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hanna Edlund
 * Date: 2020-11-17
 * Time: 11:19
 * Project: JavaQuizkampen
 */
public class RoundResultPanel {
    JLabel rond = new JLabel("ROND", SwingConstants.CENTER);
    JLabel category;

    JPanel roundBoardPanel = new JPanel(new GridLayout(0,3));

    List<JTextArea> rutaList = new ArrayList<>();

    JTextArea ruta;

    public RoundResultPanel(int questionsAskedPerRound){
        category = new JLabel("", SwingConstants.CENTER);
        category.setFont(new Font("", Font.BOLD, 12));
        rond.setFont(new Font("", Font.BOLD, 12));

        JPanel leftPanel = new JPanel(new BorderLayout());
        JPanel leftColumn = new JPanel(new GridLayout(0, questionsAskedPerRound));

        JPanel centerPanel = new JPanel(new BorderLayout());
        JPanel centerColumn = new JPanel(new GridLayout(2,0));

        JPanel rightPanel = new JPanel(new BorderLayout());
        JPanel rightColumn = new JPanel(new GridLayout(0, questionsAskedPerRound));


        /*
                SKAPAR UPP BOXARNA EFTER HUR MÅNGA FRÅGOR SOM SKA STÄLLAS
         */
        for (int i = 0; i < (questionsAskedPerRound*2); i++) {
            ruta = new JTextArea();
            rutaList.add(ruta);
            ruta.setEditable(false);
            ruta.setPreferredSize(new Dimension((90/questionsAskedPerRound), 10));
            ruta.setBorder(BorderFactory.createLineBorder(Color.white, 2));

            /*
                PLACERAR RUTORNA ANTINGEN TILL HÖGER ELLER VÄNSTER
             */
            if(i < questionsAskedPerRound){
                leftColumn.add(ruta);
                ruta.setBackground(Color.gray);
            }
            else{
                rightColumn.add(ruta);
                ruta.setBackground(Color.gray);
            }
        }

        leftPanel.add(leftColumn, BorderLayout.WEST);

        centerPanel.add(centerColumn, BorderLayout.CENTER);
        centerColumn.add(rond, SwingConstants.CENTER);
        centerColumn.add(category);

        rightPanel.add(rightColumn, BorderLayout.EAST);

        roundBoardPanel.add(leftPanel);
        roundBoardPanel.add(centerPanel);
        roundBoardPanel.add(rightPanel);
    }

    public void setRondNr(int rondNr){
        rond.setText("ROND " + rondNr);
    }

    public JPanel getRoundPanel(){
        return roundBoardPanel;
    }

    public JTextArea getRuta(int place){
        return rutaList.get(place);
    }

    public void setCategory(String title){
        category.setText(title);
    }
}
