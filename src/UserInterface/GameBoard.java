//package UserInterface;
//
//import javax.imageio.ImageIO;
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionListener;
//import java.io.File;
//import java.io.IOException;
//
//import static java.awt.BorderLayout.CENTER;
//
///**
// * Created by Benjamin Brankovic
// * Date: 2020-11-12
// * Time: 14:13
// * Project: JavaQuizkampen
// */
//public class GameBoard extends JFrame {
//
//    private LobbyScreen lobbyScreen;
//    private CategoryScreen categoryScreen;
//    private JPanel contentPanel;
//
//    public GameBoard() {
//        ActionListener newGameListener = e -> {
//            contentPanel.removeAll();
//            categoryScreen.setupCategoryPanel(contentPanel);
//            contentPanel.revalidate();
//            repaint();
//        };
//        lobbyScreen = new LobbyScreen(newGameListener);
//        categoryScreen = new CategoryScreen();
//
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setSize(300, 500);
//        this.setResizable(false);
//        this.setLocationRelativeTo(null);
//        this.getContentPane().setBackground(Color.LIGHT_GRAY);
//        this.getContentPane().setLayout(new BorderLayout());
//        this.setTitle("\u00A9 All rights reserved");
//        try {
//            createTopPanel(getContentPane());
//            contentPanel = new JPanel();
//            this.getContentPane().add(contentPanel, CENTER);
//            lobbyScreen.setupLobby(contentPanel);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        this.setVisible(true);
//    }
//
//    private void createTopPanel(Container container) throws IOException {
//        Image logo = ImageIO.read(new File("resources/logo.png"));
//        ImageIcon imageIcon = new ImageIcon();
//        Image scaledInstance = logo.getScaledInstance(300, 70, Image.SCALE_SMOOTH);
//        imageIcon.setImage(scaledInstance);
//        JLabel iconLogo = new JLabel(imageIcon);
//
//        GradientPanel topPanel = new GradientPanel(GradientPanel.HEADER_COLOR_START, GradientPanel.HEADER_COLOR_END, GradientPanel.DIRECTION_LEFT_RIGHT);
//        topPanel.setPreferredSize(new Dimension(300, 80));
//        topPanel.add(iconLogo);
//
//        container.add(topPanel, BorderLayout.NORTH);
//    }
//}
