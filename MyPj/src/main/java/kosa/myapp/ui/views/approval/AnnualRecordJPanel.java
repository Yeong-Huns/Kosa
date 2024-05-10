package main.java.kosa.myapp.ui.views.approval;

import lombok.Builder;
import main.java.kosa.myapp.ui.components.label.BoldLabel;
import main.java.kosa.myapp.ui.components.label.PlainLabel;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

/**
 * packageName    : main.java.kosa.myapp.ui.views.approval
 * fileName       : ApprovalCard
 * author         : Yeong-Huns
 * date           : 2024-05-09
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-09        Yeong-Huns       최초 생성
 */
public class AnnualRecordJPanel extends JPanel {
    private int confirm;
    private String contentText;
    private LocalDate approvalDate;
    private final SpringLayout layout = new SpringLayout();
    public AnnualRecordJPanel() {
        initialize();
        BoldLabel title = new BoldLabel("진행 중인 결재가 없습니다.", 24);
        add(title);
        layout.putConstraint(SpringLayout.WEST, title, 10, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, title, 30, SpringLayout.NORTH, this);
    }
    @Builder
    public AnnualRecordJPanel(int confirm, String content, LocalDate approvalDate) {
        this.confirm = confirm;
        this.contentText = content;
        this.approvalDate = approvalDate;
        initialize();
        alignComponents();
    }
    private void initialize(){
        setPreferredSize(new Dimension(530, 200));
        setLayout(layout);
        System.out.println("AnnualRecordJPanel initialize : " + this.getHeight());
    }

    private void alignComponents() {
        BoldLabel date = new BoldLabel(approvalDate.getYear() + "년 " + approvalDate.getMonthValue() + "월 " + approvalDate.getDayOfMonth() + "일", 14);
        PlainLabel approval = new PlainLabel("진행 상태 : 대기중", 14);
        switch (confirm){
            case 0 -> approval.setText("진행 상태 : 거절됨");
            case 1 -> approval.setText("진행 상태 : 승인됨");
            case 2 -> approval.setText("진행 상태 : 대기중");
        }
        PlainLabel content = new PlainLabel("<html>"+contentText+"</html>", 12);

        layout.putConstraint(SpringLayout.WEST, date, 10, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, date, 10, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.EAST, approval, -10, SpringLayout.EAST, this);
        layout.putConstraint(SpringLayout.NORTH, approval, 10, SpringLayout.SOUTH, date);

        layout.putConstraint(SpringLayout.WEST, content, 10, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, content, 10, SpringLayout.SOUTH, approval);
    }
}
