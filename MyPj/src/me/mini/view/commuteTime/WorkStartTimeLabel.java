package me.mini.view.commuteTime;

import javax.swing.*;
import java.awt.*;

public class WorkStartTimeLabel extends JLabel {
    public WorkStartTimeLabel(String s) {
        super(s);
        // 출근 버튼 누를 때 시간 들어가야함, 테스트용
        initalize();
    }

    private void initalize() {
        setForeground(new Color(255, 255, 255));
        setHorizontalAlignment(SwingConstants.RIGHT);
        setFont(new Font("D2coding", Font.PLAIN, 21));
        setBounds(367, 41, 150, 20);
    }
}
