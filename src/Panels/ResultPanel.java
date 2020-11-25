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
public class ResultPanel extends JPanel implements ActionListener{

    //TODO: Denna information ska importeras från en Properties-fil
    int amountOfRounds =  6;
    int questionsAskedPerRound = 3;

    //TODO: Denna ska bort och ersättas med att importeras från val av kategori
    String[] categoryList = {"Sport & Fritid", "Djur & Natur", "Jorden runt", "Data- & tvspel"};

    int playerOneScore = 0;
    int playerTwoScore = 0;

    JLabel labelPlayerOne = new JLabel();
    JLabel labelPlayerTwo = new JLabel();
    JLabel score = new JLabel(" " + playerOneScore + " - " + playerTwoScore + " ", SwingConstants.CENTER);
    JLabel text = new JLabel("HÄR SKA VINNAREN STÅ", SwingConstants.CENTER);

    JButton button = new JButton("Nästa runda");

    JPanel backgroundPanel = new JPanel(new GridLayout(0,1));

    List<RoundResultPanel> roundResultPanelList = new ArrayList<>();
    
    public ResultPanel(){
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

        createBlankBoard();

        // TEST STARTA NYTT SPEL NÄR MAN KLICKAR PÅ KNAPP
//        button.setText("Starta spelet");
//        button.addActionListener(this);

        backgroundPanel.add(text);
        backgroundPanel.add(button);

        setPreferredSize(new Dimension(300,500));
    }

    public void createBlankBoard(){
        for (int i = 0; i < amountOfRounds; i++) {
            RoundResultPanel runda = new RoundResultPanel(questionsAskedPerRound);
            roundResultPanelList.add(runda);
            runda.setRondNr(i+1);
            backgroundPanel.add(roundResultPanelList.get(i).getRoundPanel());
        }
    }

    public void newRound(String categoryTitle, int rondNr, List<Boolean> listOfAnswers){
        roundResultPanelList.get(rondNr).setCategory(categoryTitle);

        for (int i = 0; i < listOfAnswers.size(); i++) {

            /*
                SÄTTER FÄRGEN PÅ BOXEN BEROENDE PÅ SVARET OCH TILLDELAR POÄNG
             */
            if(i < questionsAskedPerRound){
                if(listOfAnswers.get(i)){
                    roundResultPanelList.get(rondNr).getRuta(i).setBackground(Color.green);
                    playerOneScore++;
                }
                else
                    roundResultPanelList.get(rondNr).getRuta(i).setBackground(Color.red);
            }
            else{
                if(listOfAnswers.get(i)){
                    roundResultPanelList.get(rondNr).getRuta(i).setBackground(Color.green);
                    playerTwoScore++;
                }
                else
                    roundResultPanelList.get(rondNr).getRuta(i).setBackground(Color.red);
            }
        }

        //TODO: Importera antal rätt från SPEL panelen
        score.setText(" " + playerOneScore + " - " + playerTwoScore + " ");
    }

    public void countTheScore(){
        if(playerOneScore > playerTwoScore)
            text.setText(getNamePlayerOne() + " vinner spelet!");
        else if(playerTwoScore > playerOneScore)
            text.setText(getNamePlayerTwo() + " vinner spelet!");
        else
            text.setText("Det blev oavgjort!");
    }

    public JButton getButton(){
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            //Om man klickar på Nytt spel-knappen

//            for (int i = 0; i < 3; i++) {
//                Random r = new Random();
//                int testing = r.nextInt(categoryList.length);
//                newRound(categoryList[testing], i);
//
//                if (i == amountOfRounds - 1) {
//                    countTheScore();
//                    button.setText("Nytt spel");
//                    button.setEnabled(false);
//                    text.setVisible(true);
//                }
//
//                if (i != amountOfRounds - 1) {
//                    button.setText("Nästa rond");
//                    button.setEnabled(true);
//                    text.setVisible(false);
//                }
//            }
        }
    }

    public String getNamePlayerOne() {
        return labelPlayerOne.getText();
    }

    public String getNamePlayerTwo() {
        return labelPlayerTwo.getText();
    }

    public void setNamePlayerOne(String namePlayerOne) {
        this.labelPlayerOne.setText(namePlayerOne);
    }

    public void setNamePlayerTwo(String namePlayerTwo) {
        this.labelPlayerTwo.setText(namePlayerTwo);
    }
}
