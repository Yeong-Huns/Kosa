package main.java.kosa.myapp.ui.components.panels;

import lombok.Getter;
import main.java.kosa.myapp.Main;
import main.java.kosa.myapp.controller.UIController;
import main.java.kosa.myapp.ui.components.button.ButtonType;
import main.java.kosa.myapp.ui.components.button.CommonButton;
import main.java.kosa.myapp.ui.views.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

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
@Getter
public class BottomPanel extends JPanel {
    CommonButton attendanceBtn, commuteTimeBtn, approvalBtn, settingBtn;
    public BottomPanel() {
        super(new FlowLayout(FlowLayout.CENTER));
        setPreferredSize(new Dimension(580, 100));
        attendanceBtn = new CommonButton("근태관리", ButtonType.DEFAULT);
        attendanceBtn.changeViewTo(View.ATTENDANCE);

        commuteTimeBtn = new CommonButton("출퇴근체크", ButtonType.DEFAULT);
        commuteTimeBtn.changeViewTo(View.COMMUTE);

        approvalBtn = new CommonButton("결재함", ButtonType.DEFAULT);
        approvalBtn.changeViewTo(View.APPROVAL);
        //approvalBtn.addActionListener(e->UIController.getInstance().getApprovalView().getApprovalDetail().initUIComponents()); // 순환참조 발생!!

        settingBtn = new CommonButton("구현예정 ", ButtonType.DEFAULT);
        settingBtn.changeViewTo(View.LOGIN);

        add(attendanceBtn);
        add(commuteTimeBtn);
        add(approvalBtn);
        add(settingBtn);
    }
}
