package Panels;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GradientTopPanel extends JPanel {

    public static final Color HEADER_COLOR_START = new Color(0xE2F5FE);
    public static final Color HEADER_COLOR_END = new Color(0x4AA8CC);

    private final Color startColor;
    private final Color endColor;

    public GradientTopPanel() {
        this.startColor = HEADER_COLOR_START;
        this.endColor = HEADER_COLOR_END;

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

        setPreferredSize(new Dimension(300, 90));
        add(iconLogo);
    }

    @Override
    protected void paintComponent( Graphics g ) {
        super.paintComponent( g );
        int panelHeight = getHeight();
        int panelWidth = getWidth();

        int startX = panelWidth / 2;
        int startY = panelWidth / 2;;
        int endX = 0;
        int endY = panelHeight;

        GradientPaint gradientPaint = new GradientPaint(
                startX,
                startY,
                startColor,
                endX,
                endY,
                endColor
        );
        if( g instanceof Graphics2D ) {
            Graphics2D graphics2D = (Graphics2D)g;
            graphics2D.setPaint( gradientPaint );
            graphics2D.fillRect( 0 , 0 , panelWidth , panelHeight );
        }
    }
}