package main.java.kosa.myapp.ui.views.commuteTime;

import javax.swing.*;
import java.awt.*;

public class DNamePanel extends JPanel {
    public DNamePanel() {
        setBackground(new Color(157, 157, 157));
        setBounds(20, 20, 543, 70);
        setLayout(null);
        initalize();
    }

    private void initalize() {
        JLabel workState = new JLabel("현재 근무지");
        workState.setForeground(new Color(255, 255, 255));
        workState.setFont(new Font("D2coding", Font.PLAIN, 18));
        workState.setBounds(28, 25, 100, 20);
        add(workState);

        JLabel hrDept = new JLabel("HR부서");
        hrDept.setForeground(new Color(255, 255, 255));
        hrDept.setFont(new Font("D2coding", Font.PLAIN, 21));
        hrDept.setHorizontalAlignment(SwingConstants.RIGHT);
        hrDept.setBounds(377, 25, 140, 20);
        add(hrDept);
    }
}
