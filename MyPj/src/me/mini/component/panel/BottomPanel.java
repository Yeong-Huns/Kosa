package me.mini.component.panel;

import me.mini.component.button.CommonButton;
import me.mini.viewManager.View;

import javax.swing.*;
import java.awt.*;

/**
 * packageName    : me.mini.view.common
 * fileName       : ButtonPanel
 * author         : Yeong-Huns
 * date           : 2024-05-02
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-02        Yeong-Huns       최초 생성
 */
public class BottomPanel extends JPanel {
    public BottomPanel() {
        super(new FlowLayout(FlowLayout.CENTER));
        setPreferredSize(new Dimension(580, 100));
        CommonButton attendanceManagement = new CommonButton("근태관리", View.ATTENDANCE.toString());
        CommonButton commuteTime = new CommonButton("출퇴근체크", View.COMMUTE.toString());
        add(attendanceManagement);
        add(commuteTime);
        for (int i = 1; i <= 2; i++) {
            CommonButton button = new CommonButton("구현예정 " + i, View.LOGIN.toString());
            add(button);
        }
    }
}
