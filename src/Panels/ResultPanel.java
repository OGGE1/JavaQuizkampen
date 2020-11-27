package Panels;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ResultPanel extends JPanel{

    int amountOfRounds;
    int questionsAskedPerRound;
    Properties properties = new Properties();

    int playerOneScore = 0;
    int playerTwoScore = 0;

    JLabel labelPlayerOne = new JLabel();
    JLabel labelPlayerTwo = new JLabel();
    JLabel score = new JLabel(" " + playerOneScore + " - " + playerTwoScore + " ", SwingConstants.CENTER);
    JLabel text = new JLabel("", SwingConstants.CENTER);

    JButton button = new JButton("Nästa runda");

    JPanel backgroundPanel = new JPanel(new GridLayout(0,1));

    List<RoundResultPanel> roundResultPanelList = new ArrayList<>();
    
    public ResultPanel(){
        setSettings();
        labelPlayerOne.setFont(new Font("", Font.BOLD, 18));
        labelPlayerTwo.setFont(new Font("", Font.BOLD, 18));
        score.setFont(new Font("", Font.BOLD, 22));
        text.setFont(new Font("", Font.BOLD, 18));
        button.setFont(new Font("", Font.BOLD, 16));

        JPanel topPanel = new JPanel(new BorderLayout());

        add(backgroundPanel);
        backgroundPanel.setBackground(new Color(80, 127, 213));
        this.setBackground(new Color(80, 127, 213));
        backgroundPanel.add(topPanel, 0, 0);
        topPanel.setBackground(new Color(80, 127, 213));
        topPanel.add(labelPlayerOne, BorderLayout.WEST);
        topPanel.add(score, BorderLayout.CENTER);
        topPanel.add(labelPlayerTwo, BorderLayout.EAST);

        createBlankBoard();

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
                    roundResultPanelList.get(rondNr).getRuta(i).setBackground(new Color(101, 255, 96));
                    playerOneScore++;
                }
                else
                    roundResultPanelList.get(rondNr).getRuta(i).setBackground(new Color(255, 64, 64));
            }
            else{
                if(listOfAnswers.get(i)){
                    roundResultPanelList.get(rondNr).getRuta(i).setBackground(new Color(101, 255, 96));
                    playerTwoScore++;
                }
                else
                    roundResultPanelList.get(rondNr).getRuta(i).setBackground(new Color(255, 64, 64));
            }
        }

        score.setText(" " + playerOneScore + " - " + playerTwoScore + " ");

        if (rondNr == (amountOfRounds-1)){
            countTheScore();
            button.setText("Avsluta");
        }
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

    public void setSettings() {
        try {
            properties.load(new FileInputStream("src/Properties.properties"));
        } catch (Exception e) {
            System.out.println("Could not load properties file");
            e.printStackTrace();
        }
        amountOfRounds = Integer.parseInt(properties.getProperty("rounds", "6"));
        questionsAskedPerRound = Integer.parseInt(properties.getProperty("questionsPerRound", "3"));
    }
}
