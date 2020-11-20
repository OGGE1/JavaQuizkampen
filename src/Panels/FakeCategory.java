package Panels;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oscar Norman <br>
 * Date: 2020-11-18   <br>
 * Time: 11:54   <br>
 * Project: JavaQuizkampen <br>
 */
public class FakeCategory extends JPanel {
    private JButton button1 = new JButton("Djur & natur");
    private JButton button2 = new JButton("Jorden runt");
    private JButton button3 = new JButton("Sport & fritid");
    private JButton button4 = new JButton("Data- & TVspel");
    private List<JButton> buttonList = new ArrayList<>();

    public FakeCategory() {
        buttonList.add(button1);
        buttonList.add(button2);
        buttonList.add(button3);
        buttonList.add(button4);

        this.add(button1);
        this.add(button2);
        this.add(button3);
        this.add(button4);

        setPreferredSize(new Dimension(280, 460));
    }

    public List<JButton> getButtonList() {
        return buttonList;
    }

    public JButton getButton1() {
        return button1;
    }

    public JButton getButton2() {
        return button2;
    }

    public JButton getButton3() {
        return button3;
    }

    public JButton getButton4() {
        return button4;
    }
}
