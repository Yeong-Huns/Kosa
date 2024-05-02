package me.mini;

import javax.swing.*;
import java.awt.*;

/**
 * packageName    : me.mini
 * fileName       : ViewMain
 * author         : Yeong-Huns
 * date           : 2024-05-02
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-02        Yeong-Huns       최초 생성
 */
public class ViewMain {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    //Login window = new Login();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private static void createAndShowGUI() {
        // Create and set up the window.
        JFrame frame = new JFrame("Image Processing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        CardLayoutDemo cardDemo = new CardLayoutDemo();
        cardDemo.setOpaque(true);
        frame.setContentPane(cardDemo);

        // Display the window.
        frame.pack();
        frame.setVisible(true);
    }

}
