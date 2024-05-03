package me.mini.view.commuteTime;

import javax.swing.*;
import java.awt.*;

public class DNameLable2 extends DNameLable1 {
    public DNameLable2(String s) {
        super(s); // 자기 부서 정보 받아와서 나타내야 함
        initalize();

    }

    private void initalize() {
        setForeground(new Color(255, 255, 255));
        setFont(new Font("D2coding", Font.PLAIN, 21));
        setHorizontalAlignment(SwingConstants.RIGHT);
        setBounds(377, 25, 140, 20);
    }
}
