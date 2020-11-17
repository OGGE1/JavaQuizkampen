package Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Hanna Edlund
 * Date: 2020-11-12
 * Time: 15:44
 * Project: JavaQuizkampen
 */
public class ResultPanel extends JFrame implements ActionListener{
    String namePlayerOne = "Player one";
    String namePlayerTwo = "Player two";

    int playerOneScore = 0;
    int playerTwoScore = 0;

    //TODO: Denna information ska importeras från en Properties-fil
    int amountOfRounds =  6;
    int questionsAskedPerRound = 3;

    String[] categoryList = {"Sport & Fritid", "Djur & Natur", "Jorden runt", "Data- & tvspel"};

    JLabel labelPlayerOne = new JLabel(namePlayerOne);
    JLabel labelPlayerTwo = new JLabel(namePlayerTwo);
    JLabel score = new JLabel(" " + playerOneScore + " - " + playerTwoScore + " ", SwingConstants.CENTER);

    JLabel text = new JLabel("HÄR SKA VINNAREN STÅ", SwingConstants.CENTER);
    JButton button = new JButton("EN KNAPP");
    JPanel backgroundPanel = new JPanel(new GridLayout(0,1));

    List<Round> roundList = new ArrayList<>();
    
    ResultPanel(){
        labelPlayerOne.setFont(new Font("", Font.BOLD, 18));
        labelPlayerTwo.setFont(new Font("", Font.BOLD, 18));
        score.setFont(new Font("", Font.BOLD, 22));
        text.setFont(new Font("", Font.BOLD, 18));
        button.setFont(new Font("", Font.BOLD, 16));

        JPanel topPanel = new JPanel(new BorderLayout());

        add(backgroundPanel);
        backgroundPanel.add(topPanel, 0, 0);
        topPanel.add(labelPlayerOne, BorderLayout.WEST);
        topPanel.add(score, BorderLayout.CENTER);
        topPanel.add(labelPlayerTwo, BorderLayout.EAST);

        /*
                SKAPAR UPP BOARDEN
         */

        for (int i = 0; i < amountOfRounds; i++) {
            Round runda = new Round(questionsAskedPerRound);
            roundList.add(runda);
            runda.setRondNr(i+1);
            backgroundPanel.add(roundList.get(i).getRoundPanel());
        }

        // TEST STARTA NYTT SPEL NÄR MAN KLICKAR PÅ KNAPP
        button.setText("Starta spelet");
        button.addActionListener(this);

        backgroundPanel.add(text);
        backgroundPanel.add(button);

//        if(text.getText().contains("spelet!"))
//            button.setText("Nytt spel");
//        else
//            button.setText("Väntar...");

//        button.setText("Starta runda");

        setResizable(false);
        setVisible(true);
        setSize(300,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void newRound(String categoryTitle, int rondNr){
        roundList.get(rondNr).setCategory(categoryTitle);

        for (int i = 0; i < (questionsAskedPerRound*2); i++) {

            //TODO: Byt ut mot en boolean, true = rättsvar = grönt, false = felsvar = rött
            Random r = new Random();
            int testing = r.nextInt(11);

            /*
                SÄTTER FÄRGEN PÅ BOXEN BEROENDE PÅ SVARET OCH TILLDELAR POÄNG
             */
            if(i < questionsAskedPerRound){
                if(testing <= 5){
                    roundList.get(rondNr).getRuta(i).setBackground(Color.green);
                    playerOneScore++;
                }
                else
                    roundList.get(rondNr).getRuta(i).setBackground(Color.red);
            }
            else{
                if(testing <= 5){
                    roundList.get(rondNr).getRuta(i).setBackground(Color.green);
                    playerTwoScore++;
                }
                else
                    roundList.get(rondNr).getRuta(i).setBackground(Color.red);
            }
        }

        score.setText(" " + playerOneScore + " - " + playerTwoScore + " ");
    }

    public void countTheScore(){
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


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button){
            //Om man klickar på Nytt spel-knappen
            if (e.getSource() == button) {
                for (int i = 0; i < amountOfRounds ; i++) {
                    Random r = new Random();
                    int testing = r.nextInt(categoryList.length);
                    newRound(categoryList[testing], i);

                    if(i == amountOfRounds-1)
                        countTheScore();
                }
                button.setEnabled(false);
                button.setText("Tom knapp");

            }
        }
    }
}
