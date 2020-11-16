package UserInterface;

import javax.swing.*;
import java.awt.*;

import static java.awt.BorderLayout.CENTER;

/**
 * Created by Sana Eneroth Boukchana
 * Date: 2020-11-14
 * Time: 00:31
 * Project: JavaQuizkampen
 */
public class CategoryScreen {

    public CategoryScreen() {

    }

    public void setupCategoryPanel(Container container) {
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

        JButton categoryTwoButton = new JButton(new ImageIcon("resources/sportfritidbutton.png"));
        categoryTwoButton.setOpaque(false);
        categoryTwoButton.setBorderPainted(false);

        JButton categoryThreeButton = new JButton(new ImageIcon("resources/jordenruntbutton.png"));
        categoryThreeButton.setOpaque(false);
        categoryThreeButton.setBorderPainted(false);

        JButton categoryFourButton = new JButton(new ImageIcon("resources/datatvspelbutton.png"));
        categoryFourButton.setOpaque(false);
        categoryFourButton.setBorderPainted(false);

        buttonPanel.add(title);
        buttonPanel.add(categoryOneButton);
        buttonPanel.add(categoryTwoButton);
        buttonPanel.add(categoryThreeButton);
        buttonPanel.add(categoryFourButton);
        buttonPanel.setOpaque(false);

        container.add(buttonPanel,CENTER);
    }

}




