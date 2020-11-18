package Client;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Sana Eneroth Boukchana
 * Date: 2020-11-15
 * Time: 15:16
 * Project: JavaQuizkampen
 * Copyright: MIT
 */
public class GradientPanel extends JPanel {

    public static final int DIRECTION_TOP_DOWN = 0;
    public static final int DIRECTION_LEFT_RIGHT = 1;

    public static final Color HEADER_COLOR_START = new Color(0xE2F5FE);
    public static final Color HEADER_COLOR_END = new Color(0x4AA8CC);
    public static final Color WIZARD_COLOR_START = new Color(0x000080);
    public static final Color WIZARD_COLOR_END = new Color(0x2179DA);
    public static final Color WARNING_COLOR_START = new Color(0xE80000);
    public static final Color WARNING_COLOR_END = new Color(0x000000);

    public static final Color SELECTED_GRID_CELL_BG_COLOR = new Color(0xE2F5FE);

    /** Starting Gradient Color. */
    private Color startColor;

    /** Ending Gradient Color. */
    private Color endColor;

    private int direction = 0;

    /**
     * Constructor supplying a color.
     *
     * @param startColor
     * @param endColor
     */
    public GradientPanel(Color startColor, Color endColor, int direction) {
        super();
        this.startColor = startColor;
        this.endColor = endColor;
        this.direction = direction;
    }

    @Override protected void paintComponent( Graphics g ) {
        super.paintComponent( g );
        int panelHeight = getHeight();
        int panelWidth = getWidth();

        int startX;
        int startY;
        int endX;
        int endY;

        if (direction == DIRECTION_TOP_DOWN) {
            startX = panelWidth / 2;
            endX = panelWidth / 2;
            startY = 0;
            endY = panelHeight;
        } else {
            startX = 0;
            endX = panelWidth;
            startY = panelHeight / 2;
            endY = panelHeight / 2;
        }

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
