package view.GUI;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;

import controller.PlayController;
import view.GUI.Action;
import view.console.PlayService;


/**
 * @author RMNurgalieva
 */
public class WindowService {

    private static JFrame window;
    private static final int BOTTON_WIDTH = 100;
    private static final int HEIGHT = 40;
    private static final int LABEL_WIDTH = 200;
    private static final ActionListener actionListener = new Action();
    private static final String WINDOW_NAME = "SWINGY";


    public static void createWelcomePage(){
        WindowService windowService = new WindowService();
        windowService.getWindow();
    }
    public static JFrame getWindow(){
        if (window == null){
            window = new JFrame(WINDOW_NAME);
            window.setSize(700, 700);
            return createWelcomeWindow();
        }
        return window;
    }

    public static JButton createBotton(String nameBotton, int x, int y){
        JButton button = new JButton(nameBotton);
        button.setBounds(x, y, BOTTON_WIDTH, HEIGHT);
        button.addActionListener(actionListener);
        window.add(button);
        return button;
    }

    public static JFrame createWelcomeWindow(){
        createBackGround("/Users/regina/Desktop/swingy/src/main/resources/mainPage.png");
        window.setLayout(null);
        window.setVisible(true);
        createText("Welcome to swingy!", 350, 100);
        createBotton("create hero", 150, 300);
        createBotton("load hero", 350, 300);
        createBotton("exit", 550, 300);
        createBotton("go to console", 350, 600);
        return window;
    }

    public static JLabel createText(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, LABEL_WIDTH, HEIGHT);
        label.setFont(new Font("Verdana", Font.PLAIN, 18));
        window.add(label);
        return label;
    }

    public static void createBackGround(String path){
        JLabel content = new JLabel();
        content.setIcon( new ImageIcon(path) );
        content.setLayout( new BorderLayout() );
        window.setContentPane(content);
        window.setLayout(new GridBagLayout());
        window.setVisible(true);
    }

    public static void clearWindow(){
        window.getContentPane().removeAll();
        window.getContentPane().repaint();
    }

    public void add_empty_text(int gridy, int gridx) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = gridy;
        c.gridx = gridx;
        c.fill = GridBagConstraints.VERTICAL;
        JLabel text = createText(" ", 16, 100);
        window.add(text, c);
    }
}
