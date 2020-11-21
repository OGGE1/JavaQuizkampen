package Panels;

import Server.QA;

import javax.swing.*;
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
public class GamePanel extends JPanel {

    private JPanel centerPanel = new JPanel();
    private JPanel questionPanel = new JPanel();
    private JPanel buttonPanel = new JPanel();
    private JPanel categoryPanel = new JPanel();
    private JLabel categoryLabel = new JLabel("Category",JLabel.CENTER);
    private JTextArea question = new JTextArea("Fråga");
    private JPanel topPanel = new JPanel();
    private JLabel roundNr = new JLabel("0",JLabel.CENTER);
    private JLabel name = new JLabel("Display of name",JLabel.RIGHT);
    private JLabel answerText = new JLabel("Antal rätt: ");
    private JLabel result = new JLabel("0");

    private List<JButton> buttons = new ArrayList<>();

    public GamePanel(){
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(280,460));

        centerPanel.setLayout(new GridLayout(2,1));
        add(centerPanel,BorderLayout.CENTER);

        questionPanel.setLayout(new BorderLayout());
        centerPanel.add(questionPanel);

        categoryPanel.setLayout(new FlowLayout());
        categoryPanel.add(categoryLabel);
        categoryPanel.setBackground(Color.MAGENTA);
        questionPanel.add(categoryPanel,BorderLayout.NORTH);

        question.setBackground(Color.WHITE);
        question.setEditable(false);
        question.setFont(new Font("SansSerif",Font.ITALIC,20));
        question.setLineWrap(true);
        question.setWrapStyleWord(true);
        question.setAlignmentX(JTextArea.CENTER_ALIGNMENT);
        questionPanel.add(question,BorderLayout.CENTER);

        buttonPanel.setLayout(new GridLayout(2,2));

        for(int i = 0; i < 4; i++) {
            buttons.add(new JButton("Svar"+(i+1)));
            buttonPanel.add(buttons.get(i));
            buttons.get(i).setBorder(BorderFactory.createLineBorder(Color.CYAN,10));
        }
        centerPanel.add(buttonPanel);

        topPanel.setBackground(new Color(99, 118, 239));

        answerText.setPreferredSize(new Dimension(70,20));
        topPanel.add(answerText);

        result.setPreferredSize(new Dimension(30,20));
        topPanel.add(result);

        roundNr.setBorder(new EtchedBorder());
        roundNr.setOpaque(true);
        roundNr.setBackground(Color.WHITE);
        roundNr.setPreferredSize(new Dimension(40,20));
        topPanel.add(roundNr);
        name.setForeground(Color.WHITE);
        name.setPreferredSize(new Dimension(110,20));
        topPanel.add(name);
        add(topPanel,BorderLayout.NORTH);
    }

    public void setName(String name) {
        this.name.setText(name);
    }

    public String getName() {
        return name.getText();
    }

    public List<JButton> getGameButtons(){
        return buttons;
    }

    public int getGameResult(){
        return Integer.parseInt(result.getText());
    }

    public void setGameResult(int score){
        this.result.setText(String.valueOf(score));
    }

    public String getCategory(){
        return categoryLabel.getText();
    }

    public void setCategory(String category){
        this.categoryLabel.setText(category);
    }

    public String getQuestion(){
        return question.getText();
    }

    public void setQuestion(String question){
        this.question.setText(question);
    }

    public int getRoundNr(){
        return Integer.parseInt(roundNr.getText());
    }

    public void setRoundNr(int roundNr){
        this.roundNr.setText(String.valueOf(roundNr));
    }


    public void addPoint(){
        int score = Integer.parseInt(result.getText());
        score++;
        result.setText(String.valueOf(score));
    }

    public void setUpQuestion(QA qa){
        question.setText(qa.getQuestion());
        for(int i = 0; i < 4; i++){
            buttons.get(i).setText(qa.getOptions()[i]);
            buttons.get(i).setBackground(null);
        }
    }
}