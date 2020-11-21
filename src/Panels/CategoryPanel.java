package Panels;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static java.awt.BorderLayout.CENTER;

/**
 * Created by Sana Eneroth Boukchana
 * Date: 2020-11-21
 * Time: 15:03
 * Project: JavaQuizkampen
 * Copyright: MIT
 */
public class CategoryPanel extends JPanel {

    private final List<JButton> buttonList = new ArrayList<>();

    public CategoryPanel() {
        setLayout(new BorderLayout());
        add(new GradientTopPanel(), BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();

        JLabel title = new JLabel("Välj kategori nedan");
        title.setPreferredSize(new Dimension(300, 50));
        title.setOpaque(false);
        title.setHorizontalAlignment(SwingConstants.HORIZONTAL);
        Font titleFont = new Font("Andale Mono", Font.BOLD, 20);
        title.setFont(titleFont);

        JButton categoryOneButton = new JButton(new ImageIcon("resources/djurnaturbutton.png"));
        categoryOneButton.setOpaque(false);
        categoryOneButton.setBorderPainted(false);
        buttonList.add(categoryOneButton);

        JButton categoryTwoButton = new JButton(new ImageIcon("resources/sportfritidbutton.png"));
        categoryTwoButton.setOpaque(false);
        categoryTwoButton.setBorderPainted(false);
        buttonList.add(categoryTwoButton);

        JButton categoryThreeButton = new JButton(new ImageIcon("resources/jordenruntbutton.png"));
        categoryThreeButton.setOpaque(false);
        categoryThreeButton.setBorderPainted(false);
        buttonList.add(categoryThreeButton);

        JButton categoryFourButton = new JButton(new ImageIcon("resources/datatvspelbutton.png"));
        categoryFourButton.setOpaque(false);
        categoryFourButton.setBorderPainted(false);
        buttonList.add(categoryFourButton);

        buttonPanel.add(title);
        buttonPanel.add(categoryOneButton);
        buttonPanel.add(categoryTwoButton);
        buttonPanel.add(categoryThreeButton);
        buttonPanel.add(categoryFourButton);
        buttonPanel.setOpaque(false);

        add(buttonPanel,CENTER);
    }

    public List<JButton> getButtonList() {
        return buttonList;
    }
}
