package Panels;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;

/**
 * Created by Hanna Edlund
 * Date: 2020-11-12
 * Time: 15:44
 * Project: JavaQuizkampen
 */
public class ResultPanel extends JFrame {
    String playerOne = "Player one";
    String playerTwo = "Player two";

    int playerOneScore = 0;
    int playerTwoScore = 0;
    //TODO: Hur många ronder ska vara default?
    int amountOfRounds =  6;

    String[] categoryList = {"Sport & Fritid", "Djur & Natur", "Jorden runt", "Data- & tvspel"};

    JLabel namePlayerOne = new JLabel(playerOne);
    JLabel namePlayerTwo = new JLabel(playerTwo);
    JLabel score = new JLabel(" " + playerOneScore + " - " + playerTwoScore + " ");

    JPanel backgroundPanel = new JPanel();

    ResultPanel(){
        score.setFont(new Font("", Font.BOLD, 22));
        namePlayerOne.setFont(new Font("", Font.BOLD, 18));
        namePlayerTwo.setFont(new Font("", Font.BOLD, 18));

        JPanel topPanel = new JPanel(new BorderLayout());

        add(backgroundPanel);
        backgroundPanel.add(topPanel);
        topPanel.add(namePlayerOne, BorderLayout.WEST);
        topPanel.add(score, BorderLayout.CENTER);
        topPanel.add(namePlayerTwo, BorderLayout.EAST);

        for (int i = 1; i <= amountOfRounds ; i++) {
            Random r = new Random();
            int y = r.nextInt(4);
            newRound(i, categoryList[y]);
        }

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


        JTextArea ruta2 = new JTextArea();
        JTextArea ruta3 = new JTextArea();
        JTextArea ruta4 = new JTextArea();
        JTextArea ruta5 = new JTextArea();
        JTextArea ruta6 = new JTextArea();

        rond.setText("ROND " + rondNr);
        category.setText(categoryTitle);


        JPanel leftColumn = new JPanel(new GridLayout(0,3));

        JPanel centerColumn = new JPanel(new GridLayout(2,0));

        JPanel rightColumn = new JPanel(new GridLayout(0,3));


        for (int i = 0; i < amountOfRounds; i++) {
            JTextArea ruta = new JTextArea();
            ruta.setEditable(false);
            ruta.setPreferredSize(new Dimension(30,20));
            ruta.setBorder(BorderFactory.createLineBorder(Color.white, 2));

            Random r = new Random();
            int testing = r.nextInt(10);

            if(testing > 5)
                ruta.setBackground(Color.green);
            else if(testing < 5)
                ruta.setBackground(Color.red);
            else
                ruta.setBackground(Color.gray);

            int questionsAsked = amountOfRounds/2;

            if(i < questionsAsked)
                leftColumn.add(ruta);
            else
                rightColumn.add(ruta);
        }

        backgroundPanel.add(leftColumn);
//        leftColumn.add(new JLabel("HEJ"));


        backgroundPanel.add(centerColumn);
        centerColumn.add(rond);
        centerColumn.add(category);

        backgroundPanel.add(rightColumn);
//        rightColumn.add(new JLabel("DÅ"));

    }

    public static void main(String[] args) {
        ResultPanel rp = new ResultPanel();
    }
}
