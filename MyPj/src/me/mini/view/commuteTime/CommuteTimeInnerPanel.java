package me.mini.view.commuteTime;

import javax.swing.*;

public class CommuteTimeInnerPanel extends JPanel {
    public CommuteTimeInnerPanel() {
        setBounds(0, 80, 586, 683);
        setLayout(null);
        initialize();

    }

    private void initialize() {
        add(new DNamePanel());
        add(new WorkStartPanel());
        add(new WorkEndPanel());
        add(new NowDatePanel());
        add(new CheckBtn("출근 체크하기")); // 테스트용, 출근시에는 퇴근 체크하기로 바뀌어야 함
    }
}
