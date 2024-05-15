package main.java.kosa.myapp.ui.views.approval;

import com.toedter.calendar.JCalendar;
import lombok.Getter;
import main.java.kosa.myapp.Main;
import main.java.kosa.myapp.controller.UIController;
import main.java.kosa.myapp.dto.approval.Approval;
import main.java.kosa.myapp.dto.response.ResponseEntity;
import main.java.kosa.myapp.repository.approval.ApprovalRepository;
import main.java.kosa.myapp.repository.attendance.AttendanceRepository;
import main.java.kosa.myapp.ui.components.button.ButtonType;
import main.java.kosa.myapp.ui.components.button.CommonButton;
import main.java.kosa.myapp.ui.components.panels.TopPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * packageName    : main.java.kosa.myapp.ui.views.approval
 * fileName       : ExtendedTopPanel
 * author         : Yeong-Huns
 * date           : 2024-05-14
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-14        Yeong-Huns       최초 생성
 */
@Getter
public class ExtendedTopPanel extends TopPanel {
    private int approvalType;
    private LocalDate localDate;

    public ExtendedTopPanel() {
        super("결재함");
        initialize();
        addButton();
    }

    private void initialize() {
        setBackground(Color.LIGHT_GRAY);
        setPreferredSize(new Dimension(580, 100));
    }

    private void addButton() {
        JPanel outerPanel = new JPanel(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.LIGHT_GRAY);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(Box.createVerticalGlue());

        CommonButton optionsButton = new CommonButton("신청", ButtonType.SMALL);
        optionsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        optionsButton.addActionListener(this::showOptions);
        buttonPanel.add(optionsButton);

        buttonPanel.add(Box.createVerticalGlue());

        outerPanel.add(buttonPanel, BorderLayout.EAST);
        add(outerPanel, BorderLayout.EAST);
    }

    private void showOptions(ActionEvent e) {
        String[] options = {"퇴근 누락", "휴가"};
        String choice = (String) JOptionPane.showInputDialog(this, "구분 선택", "결재 신청",
                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (choice != null) {
            approvalType = choice.equals("휴가") ? 1 : 2;
            System.out.println("Before showOptionsDetail:");
            System.out.println("Choice: " + choice);
            System.out.println("ApprovalType: " + approvalType);

            showOptionsDetail(choice, approvalType);
        }
    }

    private void showOptionsDetail(String choice, int approvalType) {
        JCalendar calendar = new JCalendar();
        JTextArea reasonTextArea = new JTextArea(5, 30);
        reasonTextArea.setLineWrap(true);
        reasonTextArea.setWrapStyleWord(true);

        JLabel dateLabel = new JLabel("날짜를 선택하세요");
        dateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        dateLabel.setPreferredSize(new Dimension(400, 20));

        calendar.getDayChooser().addPropertyChangeListener("day", e -> {
            localDate = LocalDate.of(calendar.getYearChooser().getYear(),
                    calendar.getMonthChooser().getMonth() + 1,
                    calendar.getDayChooser().getDay());
            dateLabel.setText(localDate.format(DateTimeFormatter.ofPattern("신청 날짜 : yyyy년-MM월-dd일")));
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

            System.out.println("Before switch:");
            System.out.println("ApprovalType: " + approvalType);
            try{
            switch (approvalType) {
                case 1 -> {
                    ResponseEntity<Void> response = ApprovalRepository.getInstance().resisterApproval(
                            Approval.builder()
                                    .memberId(Main.getSessionKey())
                                    .content(reasonTextArea.getText())
                                    .approvalDate(localDate)
                                    .build());

                    response.showDialogs();
                }
                case 2 -> {
                    ResponseEntity<Void> response = ApprovalRepository.getInstance().insertStatementReason(
                            Approval.builder()
                                    .memberId(Main.getSessionKey())
                                    .content(reasonTextArea.getText())
                                    .approvalDate(localDate)
                                    .build());

                    response.showDialogs();
                }
            }
            } finally {
                UIController.getInstance().getApprovalView().getApprovalDetail().removeAll();
                UIController.getInstance().getApprovalView().getApprovalDetail().initUIComponents();
                UIController.getInstance().getApprovalView().getApprovalDetail().revalidate();
                UIController.getInstance().getApprovalView().getApprovalDetail().repaint();
                System.out.println("After switch:");
                System.out.println("ApprovalType: " + approvalType);
            }

        }
    }
}