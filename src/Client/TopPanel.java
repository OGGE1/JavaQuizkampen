package Client;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by Sana Eneroth Boukchana
 * Date: 2020-11-17
 * Time: 19:08
 * Project: JavaQuizkampen
 * Copyright: MIT
 */
public class TopPanel extends GradientPanel {

    public TopPanel() {
        super(GradientPanel.HEADER_COLOR_START, GradientPanel.HEADER_COLOR_END, GradientPanel.DIRECTION_LEFT_RIGHT);
        setOpaque(false);
        ImageIcon imageIcon = new ImageIcon();
        Image logo = null;
        try {
            logo = ImageIO.read(new File("resources/logo.png"));
            Image scaledInstance = logo.getScaledInstance(300, 80, Image.SCALE_SMOOTH);
            imageIcon.setImage(scaledInstance);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JLabel iconLogo = new JLabel(imageIcon);

        setPreferredSize(new Dimension(300, 80));
        add(iconLogo);
    }
}
