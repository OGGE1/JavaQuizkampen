package Panels;

import javax.swing.*;
import java.awt.*;
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
    String categoryTitle = "Kategorin";

    int playerOneScore = 0;
    int playerTwoScore = 0;
    //TODO: Hur många ronder ska vara default?
    int amountOfRounds =  6;

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

        JPanel scoreBoard = new JPanel(new GridLayout(amountOfRounds,0));



        newRound(1, "Sport & Fritid");
        newRound(2, "Djur & Natur");
        newRound(3, "Jorden runt");
        newRound(4, "Data & Tvspel");
        newRound(5, "Test & Testing");
        newRound(6, "Skog & träd");




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


        JTextArea ruta = new JTextArea();
        JTextArea ruta2 = new JTextArea();
        JTextArea ruta3 = new JTextArea();
        JTextArea ruta4 = new JTextArea();
        JTextArea ruta5 = new JTextArea();
        JTextArea ruta6 = new JTextArea();
        rond.setText("ROND " + rondNr);
        category.setText(categoryTitle);

        ruta.setEditable(false);
        ruta.setPreferredSize(new Dimension(30,20));
        ruta.setBackground(Color.gray);
        ruta2.setEditable(false);
        ruta2.setPreferredSize(new Dimension(30,20));
        ruta2.setBackground(Color.red);
        ruta3.setEditable(false);
        ruta3.setPreferredSize(new Dimension(30,20));
        ruta3.setBackground(Color.green);

        ruta4.setEditable(false);
        ruta4.setPreferredSize(new Dimension(30,20));
        ruta4.setBackground(Color.green);
        ruta5.setEditable(false);
        ruta5.setPreferredSize(new Dimension(30,20));
        ruta5.setBackground(Color.gray);
        ruta6.setEditable(false);
        ruta6.setPreferredSize(new Dimension(30,20));
        ruta6.setBackground(Color.red);


        JPanel leftColumn = new JPanel(new GridLayout(0,3));

        JPanel centerColumn = new JPanel(new GridLayout(2,0));

        JPanel rightColumn = new JPanel(new GridLayout(0,3));

        backgroundPanel.add(leftColumn);

//        leftColumn.add(new JLabel("HEJ"));
        leftColumn.add(ruta);
        leftColumn.add(ruta2);
        leftColumn.add(ruta3);



        backgroundPanel.add(centerColumn);
        centerColumn.add(rond);
        centerColumn.add(category);


        backgroundPanel.add(rightColumn);
//        rightColumn.add(new JLabel("DÅ"));
        rightColumn.add(ruta4);
        rightColumn.add(ruta5);
        rightColumn.add(ruta6);
    }

    public static void main(String[] args) {
        ResultPanel rp = new ResultPanel();
    }
}
