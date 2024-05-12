package main.java.kosa.myapp.ui.views.attendance;

import main.java.kosa.myapp.Main;
import main.java.kosa.myapp.config.Image;
import main.java.kosa.myapp.dto.approval.Approval;
import main.java.kosa.myapp.dto.attendance.Attendance;
import main.java.kosa.myapp.repository.approval.ApprovalRepository;
import main.java.kosa.myapp.repository.attendance.AttendanceRepository;
import main.java.kosa.myapp.ui.components.button.ButtonType;
import main.java.kosa.myapp.ui.components.button.CommonButton;
import main.java.kosa.myapp.ui.views.View;

import javax.swing.JPanel;
import java.time.LocalDate;

public class AttendanceAndManagementDetailPanel extends JPanel {
    public AttendanceAndManagementDetailPanel() {
        setBounds(0, 80, 586, 250);
        setLayout(null);
        initialize();

    }
    private void initialize() {
        CommonButton annualLeavesBtn = new CommonButton(ButtonType.IMAGE_BUTTON)
                .setImage(Image.ANNUAL)
                .setPosition(0, 125);
        annualLeavesBtn.addActionListener(i->{
            ApprovalRepository.getInstance().getAnnualLeavesApproval(
                    Approval.builder()
                            .memberId(Main.getSessionKey())
                            .approvalType(1)
                            .approvalDate(LocalDate.now())
                            .build());
        });
        annualLeavesBtn.changeViewTo(View.COMMUTE);
        add(annualLeavesBtn);
        CommonButton myAttendanceBtn = new CommonButton(ButtonType.IMAGE_BUTTON)
                .setImage(Image.ATTENDANCE)
                .setPosition(0, 0);
        myAttendanceBtn.addActionListener(i->{
            AttendanceRepository.getInstance().getMemberMonthlyAttendance(
                    Attendance.builder()
                            .memberId(Main.getSessionKey())
                            .attendanceDate(LocalDate.now())
                            .build());
        });
        myAttendanceBtn.changeViewTo(View.MY_ATTENDANCE);
        add(myAttendanceBtn);
    }
    private void redirectAnnualLeaves(){

    }
    private void redirectMyAttendance(){

    }
}
