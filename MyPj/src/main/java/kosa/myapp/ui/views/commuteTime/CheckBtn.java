package main.java.kosa.myapp.ui.views.commuteTime;

import javax.swing.*;
import java.awt.*;

public class CheckBtn extends JButton {
    public CheckBtn(String s) {
        super(s);
        initalize();
    }

    private void initalize() {
        setBackground(new Color(255, 255, 255));
        setForeground(new Color(0, 0, 0));
        setFont(new Font("D2coding", Font.PLAIN, 21));
        setBounds(196, 450, 200, 60);
    }
}
