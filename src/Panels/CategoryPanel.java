package Panels;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.awt.BorderLayout.CENTER;

/**
 * Created by Sana Eneroth Boukchana
 * Date: 2020-11-21
 * Time: 15:03
 * Project: JavaQuizkampen
 * Copyright: MIT
 */
public class CategoryPanel extends JPanel {

    private final Map<JButton, String> buttonMap = new HashMap<>();

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
        buttonMap.put(categoryOneButton, "Djur & Natur");

        JButton categoryTwoButton = new JButton(new ImageIcon("resources/sportfritidbutton.png"));
        categoryTwoButton.setOpaque(false);
        categoryTwoButton.setBorderPainted(false);
        buttonMap.put(categoryTwoButton, "Sport & fritid");

        JButton categoryThreeButton = new JButton(new ImageIcon("resources/jordenruntbutton.png"));
        categoryThreeButton.setOpaque(false);
        categoryThreeButton.setBorderPainted(false);
        buttonMap.put(categoryThreeButton, "Jorden runt");

        JButton categoryFourButton = new JButton(new ImageIcon("resources/datatvspelbutton.png"));
        categoryFourButton.setOpaque(false);
        categoryFourButton.setBorderPainted(false);
        buttonMap.put(categoryFourButton, "Data- & TVspel");

        buttonPanel.add(title);
        buttonPanel.add(categoryOneButton);
        buttonPanel.add(categoryTwoButton);
        buttonPanel.add(categoryThreeButton);
        buttonPanel.add(categoryFourButton);
        buttonPanel.setOpaque(false);

        add(buttonPanel,CENTER);
    }

    public Map<JButton, String> getButtonMap() {
        return buttonMap;
    }
}
