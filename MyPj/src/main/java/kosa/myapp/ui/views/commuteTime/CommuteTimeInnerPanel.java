package main.java.kosa.myapp.ui.views.commuteTime;

import main.java.kosa.myapp.Main;
import main.java.kosa.myapp.dto.attendance.GetCommuteInfoResponse;
import main.java.kosa.myapp.dto.response.ResponseEntity;
import main.java.kosa.myapp.repository.attendance.AttendanceRepository;
import main.java.kosa.myapp.ui.components.button.ButtonType;
import main.java.kosa.myapp.ui.components.button.CommonButton;
import main.java.kosa.myapp.ui.components.label.PlainLabel;

import javax.swing.*;
import java.awt.*;

public class CommuteTimeInnerPanel extends JPanel {
    public CommuteTimeInnerPanel() {
        setBounds(0, 80, 586, 683);
        setLayout(null);
    }
    public void initialize() {
        removeAll();
        ResponseEntity<GetCommuteInfoResponse> response = AttendanceRepository.getInstance().getCommuteInfo(Main.getSessionKey());
        if(response.isSuccess()){
            GetCommuteInfoResponse responseBody = response.getData();
            add(new DNamePanel(responseBody.deptName()));
            add(new WorkStartPanel(responseBody.startOfWork()));
            add(new WorkEndPanel(responseBody.endOfWork()));
            add(new NowDatePanel(responseBody.attendanceDate()));
            CommonButton commonButton = new CommonButton("퇴근 체크하기",ButtonType.LARGE);
            commonButton.setPosition(196, 450);
            commonButton.addActionListener(i-> {
                ResponseEntity<Void> registerAttendance = AttendanceRepository.getInstance().resisterAttendance(Main.getSessionKey());
                registerAttendance.showDialogs();
                registerAttendance.runIfSuccess(a->refreshPanel());
            });
            add(commonButton);
        } else {
            JPanel jPanel = new JPanel(new BorderLayout());
            PlainLabel plainLabel = new PlainLabel(response.getErrorMessage(), 14);
            jPanel.add(plainLabel, BorderLayout.CENTER);
            jPanel.setBackground(new Color(157, 157, 157));
            jPanel.setBounds(20, 20, 543, 70);
            add(jPanel);
            CommonButton commonButton = new CommonButton("출근 체크하기",ButtonType.LARGE);
            commonButton.setPosition(196, 450);
            commonButton.addActionListener(i-> {
                ResponseEntity<Void> registerAttendance = AttendanceRepository.getInstance().resisterAttendance(Main.getSessionKey());
                registerAttendance.showDialogs();
                registerAttendance.runIfSuccess(a->refreshPanel());
            });
            add(commonButton);
        }
        revalidate();
        repaint();
    }
    private void refreshPanel(){
        removeAll();        // 패널에서 모든 컴포넌트 제거
        initialize();       // 컴포넌트 재추가
        revalidate();       // 레이아웃 관리자에게 레이아웃을 다시 계산하도록 지시
        repaint();          // 패널을 다시 그리도록 요청
    }
}
