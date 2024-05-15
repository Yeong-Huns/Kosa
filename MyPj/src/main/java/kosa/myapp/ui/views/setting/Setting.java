package main.java.kosa.myapp.ui.views.setting;

import main.java.kosa.myapp.ui.components.button.ButtonType;
import main.java.kosa.myapp.ui.components.button.CommonButton;

import javax.swing.*;
import java.awt.*;

/**
 * packageName    : main.java.kosa.myapp.ui.views.setting
 * fileName       : Setting
 * author         : Yeong-Huns
 * date           : 2024-05-14
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-14        Yeong-Huns       최초 생성
 */
public class Setting extends JPanel{
    public Setting() {
        setPreferredSize(new Dimension(900, 600));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        CommonButton button1 = new CommonButton("Button 1", ButtonType.X_LARGE);
        CommonButton button2 = new CommonButton("Button 2", ButtonType.X_LARGE);
        CommonButton button3 = new CommonButton("Button 3", ButtonType.X_LARGE);

        // Button 1
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 50, 0); // Bottom margin
        add(button1, gbc);

        // Button 2
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 50, 0); // Bottom margin
        add(button2, gbc);

        // Button 3
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 0, 0); // No margin
        add(button3, gbc);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Button Panel Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new Setting());
        frame.pack();
        frame.setLocationRelativeTo(null); // 화면 가운데에 배치
        frame.setVisible(true);
    }
}
