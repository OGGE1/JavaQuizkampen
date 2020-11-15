package Panels;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Created by Hanna Edlund
 * Date: 2020-11-12
 * Time: 15:44
 * Project: JavaQuizkampen
 */
public class ResultPanel extends JFrame {
    String namePlayerOne = "Player one";
    String namePlayerTwo = "Player two";

    int playerOneScore = 0;
    int playerTwoScore = 0;
    int amountOfRounds =  6;
    int questionsAskedPerRound = 3;

    String[] categoryList = {"Sport & Fritid", "Djur & Natur", "Jorden runt", "Data- & tvspel"};

    JLabel labelPlayerOne = new JLabel(namePlayerOne);
    JLabel labelPlayerTwo = new JLabel(namePlayerTwo);
    JLabel score = new JLabel("", SwingConstants.CENTER);

    JLabel text = new JLabel("HÄR SKA VINNAREN STÅ", SwingConstants.CENTER);
    JButton button = new JButton("EN KNAPP");


    JPanel backgroundPanel = new JPanel(new GridLayout(0,1));

    ResultPanel(){
        labelPlayerOne.setFont(new Font("", Font.BOLD, 18));
        labelPlayerTwo.setFont(new Font("", Font.BOLD, 18));

        JPanel topPanel = new JPanel(new BorderLayout());

        add(backgroundPanel);
        backgroundPanel.add(topPanel, 0, 0);
        topPanel.add(labelPlayerOne, BorderLayout.WEST);
        topPanel.add(score, BorderLayout.CENTER);
        topPanel.add(labelPlayerTwo, BorderLayout.EAST);

        /*
                SÄTTER KATEGORIN FRÅN ARRAY OCH SÄTTER NUMMER TILL RONDEN
         */
        for (int i = 1; i <= amountOfRounds ; i++) {
            Random r = new Random();
            int y = r.nextInt(4);
            newRound(i, categoryList[y]);
        }

        backgroundPanel.add(text);
        backgroundPanel.add(button);

        //setResizable(false);
        setVisible(true);
        setSize(300,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void newRound(int rondNr, String categoryTitle){
        JLabel rond = new JLabel("ROND", SwingConstants.CENTER);
        JLabel category = new JLabel(categoryTitle, SwingConstants.CENTER);
        rond.setFont(new Font("", Font.BOLD, 12));
        category.setFont(new Font("", Font.BOLD, 12));
        score.setFont(new Font("", Font.BOLD, 22));

        rond.setText("ROND " + rondNr);
        category.setText(categoryTitle);

        JPanel roundBoardPanel = new JPanel(new GridLayout(0,3));

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
            JTextArea ruta = new JTextArea();
            ruta.setEditable(false);
            ruta.setPreferredSize(new Dimension((90/questionsAskedPerRound), 10));
            ruta.setBorder(BorderFactory.createLineBorder(Color.white, 2));

            Random r = new Random();
            int testing = r.nextInt(11);

            /*
                SÄTTER FÄRGEN PÅ BOXEN BEROENDE PÅ SVARET OCH TILLDELAR POÄNG
             */
            if(i < questionsAskedPerRound){
                leftColumn.add(ruta);
                if(testing <= 5){
                    ruta.setBackground(Color.green);
                    playerOneScore++;
                }
                else
                    ruta.setBackground(Color.red);
            }
            else{
                rightColumn.add(ruta);
                if(testing <= 5){
                    ruta.setBackground(Color.green);
                    playerTwoScore++;
                }
                else
                    ruta.setBackground(Color.red);
            }
        }
        leftPanel.add(leftColumn, BorderLayout.WEST);
//        leftColumn.add(new JLabel("HEJ"));

        centerPanel.add(centerColumn, BorderLayout.CENTER);
        centerColumn.add(rond, SwingConstants.CENTER);
        centerColumn.add(category);

        rightPanel.add(rightColumn, BorderLayout.EAST);

        backgroundPanel.add(roundBoardPanel);
        roundBoardPanel.add(leftPanel);
        roundBoardPanel.add(centerPanel);
        roundBoardPanel.add(rightPanel);

        score.setText(" " + playerOneScore + " - " + playerTwoScore + " ");

        if(playerOneScore > playerTwoScore)
            text.setText(namePlayerOne + " vinner spelet!");
        else if(playerTwoScore > playerOneScore)
            text.setText(namePlayerTwo + " vinner spelet!");
        else
            text.setText("Det blev oavgjort!");

    }

    public static void main(String[] args) {
        ResultPanel rp = new ResultPanel();
    }
}
