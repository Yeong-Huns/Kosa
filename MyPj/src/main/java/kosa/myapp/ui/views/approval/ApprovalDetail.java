package main.java.kosa.myapp.ui.views.approval;

import main.java.kosa.myapp.Main;
import main.java.kosa.myapp.dto.approval.Approval;
import main.java.kosa.myapp.dto.approval.GetDeptApprovalResponse;
import main.java.kosa.myapp.dto.response.ResponseEntity;
import main.java.kosa.myapp.repository.approval.ApprovalRepository;
import main.java.kosa.myapp.ui.components.label.BoldLabel;
import main.java.kosa.myapp.ui.components.label.PlainLabel;
import main.java.kosa.myapp.ui.components.panels.CalendarPanel;
import main.java.kosa.myapp.ui.components.panels.CalendarType;
import main.java.kosa.myapp.ui.components.panels.DataPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

/**
 * packageName    : main.java.kosa.myapp.ui.views.approval
 * fileName       : ApprovalDetail
 * author         : Yeong-Huns
 * date           : 2024-05-14
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-14        Yeong-Huns       최초 생성
 */
public class ApprovalDetail extends JPanel{
    private ExtendedCalendarPanel calendarPanel1, calendarPanel2;
    private JPanel dataPanel1, dataPanel2;;
    private  JScrollPane scrollPane1, scrollPane2;
    private  JTabbedPane tabbedPane;

    public ApprovalDetail() {
        setPreferredSize(new Dimension(600, 900)); // 패널 사이즈 설정
        setLayout(new BorderLayout());
    }

    public void initUIComponents(){
        tabbedPane = new JTabbedPane();
        dataPanel1 = new JPanel(new GridBagLayout());
        dataPanel2 = new JPanel(new GridBagLayout());
        calendarPanel1 = new ExtendedCalendarPanel(CalendarType.YEAR_MONTH, (date, dropboxValue) -> submittedApprovals(date, dataPanel1, dropboxValue));
        calendarPanel2 = new ExtendedCalendarPanel(CalendarType.YEAR_MONTH, (date, dropboxValue) -> receivedApprovals(date, dataPanel2, dropboxValue));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
        gbc.weighty = 0;


        scrollPane1 = new JScrollPane(dataPanel1);
        scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane1.getViewport().setBackground(Color.WHITE);
        scrollPane2 = new JScrollPane(dataPanel2);
        scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane2.getViewport().setBackground(Color.WHITE);


        JPanel panel1 = new JPanel(new BorderLayout());
        panel1.add(calendarPanel1, BorderLayout.NORTH);
        panel1.add(scrollPane1, BorderLayout.CENTER);
        JPanel panel2 = new JPanel(new BorderLayout());
        panel2.add(calendarPanel2, BorderLayout.NORTH);
        panel2.add(scrollPane2, BorderLayout.CENTER);

        tabbedPane.addTab("상신함", panel1);
        tabbedPane.addTab("수신함", panel2);
        add(tabbedPane, BorderLayout.CENTER);
    }
    public void submittedApprovals(LocalDate date, JPanel dataPanel, String dropBoxValue){
        switch(dropBoxValue){
            case "연차" -> {
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridwidth = GridBagConstraints.REMAINDER;
                gbc.weightx = 1;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                gbc.insets = new Insets(2, 2, 2, 2);

                ResponseEntity<List<Approval>> approvalList = ApprovalRepository.getInstance().getAnnualLeavesApproval(
                        Approval.builder()
                                .memberId(Main.getSessionKey())
                                .approvalType(1)
                                .approvalDate(date)
                                .build());
                //System.out.println("approvalList 넘기는 값 : " + Main.getSessionKey() + " date : " + date);
                //System.out.println("받아오는 에러코드 값 : " + approvalList.getErrorCode() + " 메세지 : " + approvalList.getErrorMessage());
                dataPanel.removeAll();
                if (approvalList.isSuccess()) {
                    approvalList.getData().forEach(approval -> addDataRow(dataPanel, approval, gbc));
                    gbc.weighty = 1;
                    dataPanel.add(Box.createVerticalGlue(), gbc);
                } else if (approvalList.getErrorCode() == 1) {
                    addErrorRow(dataPanel, approvalList.getErrorMessage(), gbc);
                }
                dataPanel.revalidate();
                dataPanel.repaint();
            }
            case "퇴근 누락" -> {
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridwidth = GridBagConstraints.REMAINDER;
                gbc.weightx = 1;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                gbc.insets = new Insets(2, 2, 2, 2);
                ResponseEntity<List<Approval>> approvalList = ApprovalRepository.getInstance().getMemberStatementOfReason(
                        Approval.builder()
                                .memberId(Main.getSessionKey())
                                .approvalType(2)
                                .approvalDate(date)
                                .build());
                //System.out.println("getDeptAnnualLeaves 넘기는 값 : " + Main.getSessionKey() + " date : " + date);
                //System.out.println("받아오는 에러코드 값 : " + approvalList.getErrorCode() + " 메세지 : " + approvalList.getErrorMessage());
                dataPanel.removeAll();
                if (approvalList.isSuccess()) {
                    approvalList.getData().forEach(approval -> addDataRow(dataPanel, approval, gbc));
                    gbc.weighty = 1;
                    dataPanel.add(Box.createVerticalGlue(), gbc);
                } else if (approvalList.getErrorCode() == 1) {
                    addErrorRow(dataPanel, approvalList.getErrorMessage(), gbc);
                }

                dataPanel.revalidate();
                dataPanel.repaint();
            }
        }

    }

    public void receivedApprovals(LocalDate date, JPanel dataPanel, String dropBoxValue){
        switch(dropBoxValue){
            case "연차" -> {
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridwidth = GridBagConstraints.REMAINDER;
                gbc.weightx = 1;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                gbc.insets = new Insets(10, 10, 10, 10);
                ResponseEntity<List<GetDeptApprovalResponse>> approvalList = ApprovalRepository.getInstance().getDeptAnnualLeaves(
                        Approval.builder()
                                .memberId(Main.getSessionKey())
                                .approvalType(1)
                                .approvalDate(date)
                                .build());
                //System.out.println("getDeptAnnualLeaves 넘기는 값 : " + Main.getSessionKey() + " date : " + date);
                //System.out.println("받아오는 에러코드 값 : " + approvalList.getErrorCode() + " 메세지 : " + approvalList.getErrorMessage());
                dataPanel.removeAll();
                if (approvalList.isSuccess()) {
                    approvalList.getData().forEach(approval -> addDataRow(dataPanel, approval, gbc));
                    gbc.weighty = 1;
                    dataPanel.add(Box.createVerticalGlue(), gbc);
                } else if (approvalList.getErrorCode() == 1) {
                    addErrorRow(dataPanel, approvalList.getErrorMessage(), gbc);
                }

                dataPanel.revalidate();
                dataPanel.repaint();
            }
            case "퇴근 누락" -> {
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridwidth = GridBagConstraints.REMAINDER;
                gbc.weightx = 1;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                gbc.insets = new Insets(10, 10, 10, 10);

                ResponseEntity<List<GetDeptApprovalResponse>> approvalList = ApprovalRepository.getInstance().getDeptStatementOfReason(
                        Approval.builder()
                                .memberId(Main.getSessionKey())
                                .approvalType(2)
                                .approvalDate(date)
                                .build());
                //System.out.println("getDeptAnnualLeaves 넘기는 값 : " + Main.getSessionKey() + " date : " + date);
                //System.out.println("받아오는 에러코드 값 : " + approvalList.getErrorCode() + " 메세지 : " + approvalList.getErrorMessage());
                dataPanel.removeAll();
                if (approvalList.isSuccess()) {
                    approvalList.getData().forEach(approval -> addDataRow(dataPanel, approval, gbc));
                    gbc.weighty = 1;
                    dataPanel.add(Box.createVerticalGlue(), gbc);
                } else if (approvalList.getErrorCode() == 1) {
                    addErrorRow(dataPanel, approvalList.getErrorMessage(), gbc);
                }

                dataPanel.revalidate();
                dataPanel.repaint();
            }
        }
    }

    private void addDataRow(JPanel panel, Approval approval, GridBagConstraints gbc) {
        JPanel rowPanel = new JPanel();
        rowPanel.setLayout(new BoxLayout(rowPanel, BoxLayout.Y_AXIS));
        rowPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        rowPanel.setBackground(Color.WHITE);

        PlainLabel dateLabel = new PlainLabel("날짜: " + approval.getApprovalDate().toString(), 16);
        PlainLabel confirmLabel = new PlainLabel("승인여부: " + (approval.getConfirm() == 2 ? "승인 대기중" : approval.getConfirm() == 1 ? "승인됨" : "거절됨"), 16);
        PlainLabel contentLabel = new PlainLabel("내용: " + (approval.getContent()), 14);

        dateLabel.setBorder(new EmptyBorder(5,5,5,5));
        confirmLabel.setBorder(new EmptyBorder(5,5,5,5));
        contentLabel.setBorder(new EmptyBorder(5,5,5,5));

        rowPanel.add(dateLabel);
        rowPanel.add(confirmLabel);
        rowPanel.add(contentLabel);

        panel.add(rowPanel, gbc);
    }
    private void addDataRow(JPanel panel, GetDeptApprovalResponse getDeptApprovalResponse, GridBagConstraints gbc) {
        DataPanel rowPanel = new DataPanel();
        rowPanel.setLayout(new BoxLayout(rowPanel, BoxLayout.Y_AXIS));
        rowPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        rowPanel.setBackground(Color.WHITE);


        rowPanel.setData("approvalId", getDeptApprovalResponse.approvalId());
        rowPanel.setData("memberId", getDeptApprovalResponse.memberId());
        PlainLabel approvalDate = new PlainLabel("날짜: " + getDeptApprovalResponse.approvalDate(), 16);
        PlainLabel memberName = new PlainLabel("사용자 이름 : " + getDeptApprovalResponse.memberName(), 16);
        PlainLabel confirm = new PlainLabel("승인여부: " + (getDeptApprovalResponse.confirm() == 2 ? "승인 대기중" : getDeptApprovalResponse.confirm() == 1 ? "승인됨" : "거절됨"), 16);
        PlainLabel content = new PlainLabel("내용: " + getDeptApprovalResponse.content(), 14);


        approvalDate.setBorder(new EmptyBorder(5,5,5,5));
        memberName.setBorder(new EmptyBorder(5,5,5,5));
        confirm.setBorder(new EmptyBorder(5,5,5,5));
        content.setBorder(new EmptyBorder(5,5,5,5));

        rowPanel.add(approvalDate);
        rowPanel.add(memberName);
        rowPanel.add(confirm);
        rowPanel.add(content);

        panel.add(rowPanel, gbc);
    }
    private void addErrorRow(Container panel, String errorMessage, GridBagConstraints gbc) {
        BoldLabel errorLabel = new BoldLabel(errorMessage, 24);
        errorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel rowPanel = new JPanel();
        rowPanel.setLayout(new BoxLayout(rowPanel, BoxLayout.Y_AXIS));
        rowPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        rowPanel.setBackground(Color.WHITE);


        int verticalSpace = 50;
        rowPanel.add(Box.createVerticalStrut(verticalSpace));
        rowPanel.add(errorLabel);
        rowPanel.add(Box.createVerticalStrut(verticalSpace));

        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;
        panel.add(rowPanel, gbc);


        JPanel emptyPanel = new JPanel();

        gbc.weighty = 1;
        panel.add(emptyPanel, gbc);

        panel.revalidate();
        panel.repaint();
    }

    /*```java

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
```*/
}
