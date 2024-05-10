package main.java.kosa.myapp.ui.views.approval;

import com.toedter.calendar.JCalendar;
import main.java.kosa.myapp.Main;
import main.java.kosa.myapp.controller.approval.ApprovalController;
import main.java.kosa.myapp.dto.approval.response.GetAnnualLeavesApproveResponse;
import main.java.kosa.myapp.entity.approval.Approval;
import main.java.kosa.myapp.entity.response.ResponseEntity;
import main.java.kosa.myapp.ui.components.button.ButtonType;
import main.java.kosa.myapp.ui.components.button.CommonButton;
import main.java.kosa.myapp.ui.components.panels.CalendarPanel;
import main.java.kosa.myapp.ui.components.panels.CalendarType;
import main.java.kosa.myapp.ui.components.panels.ScrollPanel;
import main.java.kosa.myapp.ui.components.panels.ScrollableRecordsPanel;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * packageName    : main.java.kosa.myapp.ui.views.approval
 * fileName       : Approval
 * author         : Yeong-Huns
 * date           : 2024-05-07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-07        Yeong-Huns       최초 생성
 */
public class ApprovalDetail extends JPanel {
    private CalendarPanel calendarPanel;
    private CommonButton applyButton;
    private ScrollableRecordsPanel scrollableRecordsPanel;
    private ScrollPanel scrollPanel;
    private final int approvalType;

    public ApprovalDetail(int approvalType) {
        this.approvalType = approvalType;
        SpringLayout layout = new SpringLayout();
        setLayout(layout);
        initialize();
        alignComponents(layout);
        List<AnnualRecordJPanel> defaultPan = new ArrayList<>();
        defaultPan.add(new AnnualRecordJPanel());
        scrollableRecordsPanel.printRecord(defaultPan);
    }

    public void printRecord(int sessionKey){
        Approval approval = new Approval();
        approval.setMemberId(sessionKey);
        approval.setApprovalType(1);
        approval.setApprovalDate(calendarPanel.getCurrentDate());
        showDialog(ApprovalController.getInstance().getAnnualLeavesApproval(approval));
    }

    private void showDialog(ResponseEntity<List<GetAnnualLeavesApproveResponse>> response){
        if(response.getErrorCode() == 0){ // DB에서 반환할 데이터를 찾았습니다
            List<AnnualRecordJPanel> panelList = response.getData().stream()
                    .map(GetAnnualLeavesApproveResponse::toAnnualRecordJPanel)
                    .toList();
            // DB 조회가 성공(errorCode = 0) 하면 GetAnnualLeavesApproveResponse를 JPanel로 바꿉니다.
            scrollableRecordsPanel.printRecord(panelList);
        }else{
            List<AnnualRecordJPanel> defaultPanel = new ArrayList<>();
            defaultPanel.add(new AnnualRecordJPanel());
            scrollableRecordsPanel.printRecord(defaultPanel);
        }
    }

    private void initialize() {
        applyButton = new CommonButton("신청", ButtonType.SMALL);
        calendarPanel = new CalendarPanel(CalendarType.YEAR_MONTH);
        scrollableRecordsPanel = new ScrollableRecordsPanel();
        scrollPanel = new ScrollPanel(scrollableRecordsPanel);
        calendarPanel.setButtonAction(e->printRecord(Main.getSessionKey()) );
        add(applyButton);
        add(calendarPanel);
        add(scrollPanel);
        applyButton.addActionListener(e -> showOptions());
    }

    private void alignComponents(SpringLayout layout) {
        layout.putConstraint(SpringLayout.EAST, applyButton, -20, SpringLayout.EAST, this);
        layout.putConstraint(SpringLayout.NORTH, applyButton, 5, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.WEST, calendarPanel, 0, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, calendarPanel, 5, SpringLayout.SOUTH, applyButton);

        layout.putConstraint(SpringLayout.WEST, scrollPanel, 0, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, scrollPanel, 5, SpringLayout.SOUTH, calendarPanel);
    }



    private void showOptions() {
        String[] options = {"퇴근 누락", "휴가"};
        String choice = (String) JOptionPane.showInputDialog(this, "구분 선택", "결재 신청",
                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (choice != null) {
            showOptionsDetail(choice);
        }
    }

    private void showOptionsDetail(String choice) {
        JCalendar calendar = new JCalendar();
        JTextArea reasonTextArea = new JTextArea(5, 30);
        reasonTextArea.setLineWrap(true);
        reasonTextArea.setWrapStyleWord(true);

        JLabel dateLabel = new JLabel("날짜를 선택하세요");
        dateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        dateLabel.setPreferredSize(new Dimension(400, 20));

        calendar.getDayChooser().addPropertyChangeListener("day", e -> {
            LocalDate selectedDate = LocalDate.of(calendar.getYearChooser().getYear(),
                    calendar.getMonthChooser().getMonth() + 1,
                    calendar.getDayChooser().getDay());
            dateLabel.setText(selectedDate.format(DateTimeFormatter.ofPattern("신청 날짜 : yyyy년-MM월-dd일")));
        });

        JScrollPane scrollPane = new JScrollPane(reasonTextArea);

        JPanel datePanel = new JPanel(new BorderLayout());
        datePanel.add(dateLabel, BorderLayout.NORTH);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(new JLabel("사유:"), BorderLayout.WEST);
        bottomPanel.add(scrollPane, BorderLayout.CENTER);

        datePanel.add(bottomPanel, BorderLayout.SOUTH);

        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.add(calendar, BorderLayout.CENTER);
        panel.add(datePanel, BorderLayout.SOUTH);

        int result = JOptionPane.showConfirmDialog(null, panel, choice,
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            System.out.println("Selected Date: " + dateLabel.getText());
            System.out.println("Reason: " + reasonTextArea.getText());
        }
    }
}
