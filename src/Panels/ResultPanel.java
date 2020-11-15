package Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
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
    int amountOfRounds =  6;
    int questionsAskedPerRound = 3;

    String[] categoryList = {"Sport & Fritid", "Djur & Natur", "Jorden runt", "Data- & tvspel"};

    JLabel labelPlayerOne = new JLabel(namePlayerOne);
    JLabel labelPlayerTwo = new JLabel(namePlayerTwo);
    JLabel score = new JLabel(" " + playerOneScore + " - " + playerTwoScore + " ", SwingConstants.CENTER);

    JLabel text = new JLabel("HÄR SKA VINNAREN STÅ", SwingConstants.CENTER);
    JButton button = new JButton("EN KNAPP");

    JPanel backgroundPanel = new JPanel(new GridLayout(0,1));

    List<JTextArea> rutaList = new ArrayList<>();

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
        for (int i = 1; i <= amountOfRounds ; i++) {
            createBoard(i);
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

    public void createBoard(int rondNr){
        JLabel rond = new JLabel("ROND", SwingConstants.CENTER);
        rond.setFont(new Font("", Font.BOLD, 12));
        JLabel category = new JLabel("", SwingConstants.CENTER);
        category.setFont(new Font("", Font.BOLD, 12));

        rond.setText("ROND " + rondNr);
        category.setText("En kategori");

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
            rutaList.add(ruta);
            ruta.setEditable(false);
            ruta.setPreferredSize(new Dimension((90/questionsAskedPerRound), 10));
            ruta.setBorder(BorderFactory.createLineBorder(Color.white, 2));

            /*
                PLACERAR RUTORNA ANTINGEN TILL HÖGER ELLER VÄNSTER KOLUMN
             */
            if(i < questionsAskedPerRound){
                leftColumn.add(ruta);
                ruta.setBackground(Color.gray);;
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

        backgroundPanel.add(roundBoardPanel);
        roundBoardPanel.add(leftPanel);
        roundBoardPanel.add(centerPanel);
        roundBoardPanel.add(rightPanel);

    }

    public void newRound(String categoryTitle){
        /*
                SKAPAR UPP BOXARNA EFTER HUR MÅNGA FRÅGOR SOM SKA STÄLLAS
         */
        for (int i = 0; i < (questionsAskedPerRound*2); i++) {

            Random r = new Random();
            int testing = r.nextInt(11);

            /*
                SÄTTER FÄRGEN PÅ BOXEN BEROENDE PÅ SVARET OCH TILLDELAR POÄNG
             */
            if(i < questionsAskedPerRound){
                if(testing <= 5){
                    rutaList.get(i).setBackground(Color.green);
                    playerOneScore++;
                }
                else
                    rutaList.get(i).setBackground(Color.red);
            }
            else{
                if(testing <= 5){
                    rutaList.get(i).setBackground(Color.green);
                    playerTwoScore++;
                }
                else
                    rutaList.get(i).setBackground(Color.red);
            }
        }
//        if (!(playerOneScore == 0 && playerTwoScore == 0)){
//            category.setText(categoryTitle);
//        }

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


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button){
            //Om man klickar på Nytt spel-knappen
            if (e.getSource() == button) {
                for (int i = 1; i <= amountOfRounds ; i++) {
                    newRound("TITLE");
                }
                button.setEnabled(false);
                button.setText("Tom knapp");

            }
        }
    }
}
