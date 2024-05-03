package me.mini.view.commuteTime;

import me.mini.view.main.MainCard;

import javax.swing.*;

public class CommuteTimePanel extends JPanel {
    public CommuteTimePanel() {
        setBounds(0, 80, 586, 683);
        setLayout(null);
        initialize();
        MainCard.getInstance().add(this, "CommuteTime");
    }

    private void initialize() {
        add(new DNamePanel());
        add(new WorkStartPanel());
        add(new WorkEndPanel());
        add(new NowDatePanel());
        add(new CheckBtn("출근 체크하기")); // 테스트용, 출근시에는 퇴근 체크하기로 바뀌어야 함
    }
}
