package main.java.kosa.myapp.ui.views.approval;

import main.java.kosa.myapp.Main;
import main.java.kosa.myapp.dto.approval.Approval;
import main.java.kosa.myapp.dto.approval.GetDeptApprovalResponse;
import main.java.kosa.myapp.dto.response.ResponseEntity;
import main.java.kosa.myapp.repository.approval.ApprovalRepository;
import main.java.kosa.myapp.ui.components.button.ButtonType;
import main.java.kosa.myapp.ui.components.button.CommonButton;
import main.java.kosa.myapp.ui.components.label.BoldLabel;
import main.java.kosa.myapp.ui.components.label.PlainLabel;
import main.java.kosa.myapp.ui.components.panels.CalendarType;
import main.java.kosa.myapp.ui.components.panels.DataPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
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
    private int confirmType;

    public ApprovalDetail() {
        setPreferredSize(new Dimension(600, 900)); // 패널 사이즈 설정
        setLayout(new BorderLayout());
        //initUIComponents();
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
                System.out.println("approvalList 넘기는 값 : " + Main.getSessionKey() + " date : " + date);
                System.out.println("받아오는 에러코드 값 : " + approvalList.getErrorCode() + " 메세지 : " + approvalList.getErrorMessage());
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
                System.out.println("getDeptAnnualLeaves 넘기는 값 : " + Main.getSessionKey() +" date : " + date);
                System.out.println("받아오는 에러코드 값 : " + approvalList.getErrorCode() + " 메세지 : " + approvalList.getErrorMessage());
                dataPanel.removeAll();
                if (approvalList.isSuccess()) {
                    System.out.println("Approval list size: " + approvalList.getData().size());
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
        //if(dropBoxValue == null) dropBoxValue = "연차";
        switch(dropBoxValue){
            case "연차" -> {
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridwidth = GridBagConstraints.REMAINDER;
                gbc.weightx = 1;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                gbc.insets = new Insets(10, 10, 10, 10);
                ResponseEntity<List<GetDeptApprovalResponse>> approvalList = ApprovalRepository.getInstance().getDeptApprovedAnnualLeaves(
                        Approval.builder()
                                .memberId(Main.getSessionKey())
                                .approvalType(1)
                                .approvalDate(date)
                                .build());
                System.out.println("getDeptApprovedAnnualLeaves 넘기는 값 : " + Main.getSessionKey() + " date : " + date);
                System.out.println("받아오는 에러코드 값 : " + approvalList.getErrorCode() + " 메세지 : " + approvalList.getErrorMessage());
                dataPanel.removeAll();
                if (approvalList.isSuccess()) {
                    approvalList.getData().forEach(approval -> addDataRow(dataPanel, approval, gbc));
                    gbc.weighty = 1;
                    dataPanel.add(Box.createVerticalGlue(), gbc);
                } else  {
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
                System.out.println("getDeptAnnualLeaves 넘기는 값 : " + Main.getSessionKey() + " date : " + date);
                System.out.println("받아오는 에러코드 값 : " + approvalList.getErrorCode() + " 메세지 : " + approvalList.getErrorMessage());
                dataPanel.removeAll();
                try{
                if (approvalList.isSuccess()) {
                    System.out.println("Approval list size: " + approvalList.getData().size());
                    approvalList.getData().forEach(approval -> addDataRow(dataPanel, approval, gbc));
                    gbc.weighty = 1;
                    dataPanel.add(Box.createVerticalGlue(), gbc);
                } else {
                    addErrorRow(dataPanel, approvalList.getErrorMessage(), gbc);
                }
                } finally {
                    dataPanel.revalidate();
                    dataPanel.repaint();
                }

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
        rowPanel.setLayout(new GridBagLayout()); // GridBagLayout 사용
        rowPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        rowPanel.setBackground(Color.WHITE);

        rowPanel.setData("approvalId", getDeptApprovalResponse.approvalId());
        rowPanel.setData("memberId", getDeptApprovalResponse.memberId());
        PlainLabel approvalDate = new PlainLabel("날짜: " + getDeptApprovalResponse.approvalDate(), 16);
        PlainLabel memberName = new PlainLabel("사용자 이름 : " + getDeptApprovalResponse.memberName(), 16);
        PlainLabel confirm = new PlainLabel("승인여부: " + (getDeptApprovalResponse.confirm() == 2 ? "승인 대기중" : getDeptApprovalResponse.confirm() == 1 ? "승인됨" : "거절됨"), 16);
        PlainLabel content = new PlainLabel("내용: " + getDeptApprovalResponse.content(), 14);

        approvalDate.setBorder(new EmptyBorder(5, 5, 5, 5));
        memberName.setBorder(new EmptyBorder(5, 5, 5, 5));
        confirm.setBorder(new EmptyBorder(5, 5, 5, 5));
        content.setBorder(new EmptyBorder(5, 5, 5, 5));

        GridBagConstraints gbcInner = new GridBagConstraints();
        gbcInner.insets = new Insets(5, 5, 5, 5);
        gbcInner.fill = GridBagConstraints.HORIZONTAL;
        gbcInner.gridx = 0;
        gbcInner.gridy = 0;
        gbcInner.weightx = 1.0;
        rowPanel.add(approvalDate, gbcInner);

        gbcInner.gridy = 1;
        rowPanel.add(memberName, gbcInner);

        gbcInner.gridy = 2;
        rowPanel.add(confirm, gbcInner);

        gbcInner.gridy = 3;
        rowPanel.add(content, gbcInner);


        CommonButton button = new CommonButton("승인", ButtonType.SMALL);
        gbcInner.gridx = 1;
        gbcInner.gridy = 0;
        gbcInner.gridheight = GridBagConstraints.REMAINDER;
        gbcInner.weightx = 0;
        gbcInner.fill = GridBagConstraints.NONE;
        gbcInner.anchor = GridBagConstraints.NORTHEAST;
        rowPanel.add(button, gbcInner);
        button.addActionListener(e->{showOptions(e);
            System.out.println(Main.getSessionKey());
            System.out.println(" approvalId " + (int)rowPanel.getData("approvalId"));
            ResponseEntity<Void> approveOrRejectAnnualLeave = ApprovalRepository.getInstance().approveOrRejectAnnualLeave(
                Approval.builder()
                        .memberId(Main.getSessionKey())
                        .approvalId((int)rowPanel.getData("approvalId"))
                        .confirm(confirmType)
                        .build());
            approveOrRejectAnnualLeave.showDialogs();
            panel.removeAll();
            panel.revalidate();
            panel.repaint();
        });
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
    private void showOptions(ActionEvent e) {
        String[] options = {"승인", "거절"};
        String choice = (String) JOptionPane.showInputDialog(this, "결재 옵션", "결재 승인",
                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (choice != null) {
            confirmType = choice.equals("승인") ? 1 : 0;
            System.out.println("Choice: " + choice);
            System.out.println("confirm : " + confirmType);
        }
    }
}
