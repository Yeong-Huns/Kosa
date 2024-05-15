package main.java.kosa.myapp.ui.views.attendanceDetail;

import lombok.Getter;
import main.java.kosa.myapp.Main;
import main.java.kosa.myapp.dto.attendance.Attendance;
import main.java.kosa.myapp.dto.attendance.GetDeptMonthlyAttendanceResponse;
import main.java.kosa.myapp.dto.response.ResponseEntity;
import main.java.kosa.myapp.repository.attendance.AttendanceRepository;
import main.java.kosa.myapp.ui.components.label.BoldLabel;
import main.java.kosa.myapp.ui.components.label.PlainLabel;
import main.java.kosa.myapp.ui.components.panels.CalendarPanel;
import main.java.kosa.myapp.ui.components.panels.CalendarType;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * packageName    : main.java.kosa.myapp.ui.frames
 * fileName       : Test
 * author         : Yeong-Huns
 * date           : 2024-05-13
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-13        Yeong-Huns       최초 생성
 */
@Getter
public class AttendanceDetail extends JPanel{
    private CalendarPanel calendarPanel1, calendarPanel2;
    private  JPanel dataPanel1, dataPanel2;;
    private  JScrollPane scrollPane1, scrollPane2;
    private  JTabbedPane tabbedPane;

    public AttendanceDetail() {
        setPreferredSize(new Dimension(600, 900));
        setLayout(new BorderLayout());
        //initUIComponents(); --외부 ActionListener로 호출
    }

    public void initUIComponents(){
        removeAll();
        tabbedPane = new JTabbedPane();
        dataPanel1 = new JPanel(new GridBagLayout());
        dataPanel2 = new JPanel(new GridBagLayout());
        calendarPanel1 = new CalendarPanel(CalendarType.YEAR_MONTH, date -> updateMemberAttendanceDataPanel(date, dataPanel1));
        calendarPanel2 = new CalendarPanel(CalendarType.YEAR_MONTH, date -> updateDeptAttendanceDataPanel(date, dataPanel2));

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

        tabbedPane.addTab("나의 근태", panel1);
        tabbedPane.addTab("부서 근태", panel2);
        add(tabbedPane, BorderLayout.CENTER);
        revalidate();
        repaint();
    }



    public void updateMemberAttendanceDataPanel(LocalDate date, JPanel dataPanel) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(2, 2, 2, 2);

        ResponseEntity<List<Attendance>> attendanceList = AttendanceRepository.getInstance().getMemberMonthlyAttendance(
                Attendance.builder().memberId(Main.getSessionKey()).attendanceDate(date).build());

        dataPanel.removeAll();
        if (attendanceList.isSuccess()) {
            attendanceList.getData().forEach(attendance -> addDataRow(dataPanel, attendance, gbc));
            gbc.weighty = 1;
            dataPanel.add(Box.createVerticalGlue(), gbc);
        } else if (attendanceList.getErrorCode() == 1) {
            addErrorRow(dataPanel, attendanceList.getErrorMessage(), gbc);
        }

        dataPanel.revalidate();
        dataPanel.repaint();
    }

    public void updateDeptAttendanceDataPanel(LocalDate date, JPanel dataPanel) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        ResponseEntity<List<GetDeptMonthlyAttendanceResponse>> attendanceList = AttendanceRepository.getInstance().getDeptMonthlyAttendance(
                Attendance.builder().memberId(Main.getSessionKey()).attendanceDate(date).build());

        System.out.println("updateDept 넘기는 값 : " + Main.getSessionKey() + " date : " + date);
        System.out.println("받아오는 에러코드 값 : " + attendanceList.getErrorCode() + " 메세지 : " + attendanceList.getErrorMessage());
        dataPanel.removeAll();
        if (attendanceList.isSuccess()) {
            attendanceList.getData().forEach(attendance -> addDataRow(dataPanel, attendance, gbc));
            gbc.weighty = 1;
            dataPanel.add(Box.createVerticalGlue(), gbc);
        } else if (attendanceList.getErrorCode() == 1) {
            addErrorRow(dataPanel, attendanceList.getErrorMessage(), gbc);
        }

        dataPanel.revalidate();
        dataPanel.repaint();
    }



    private void addDataRow(JPanel panel, Attendance attendance, GridBagConstraints gbc) {
        JPanel rowPanel = new JPanel();
        rowPanel.setLayout(new BoxLayout(rowPanel, BoxLayout.Y_AXIS));
        rowPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        rowPanel.setBackground(Color.WHITE);

        BoldLabel dateLabel = new BoldLabel("날짜: " + attendance.getAttendanceDate().toString(), 16);
        BoldLabel startLabel = new BoldLabel("출근 시간: " + (attendance.getStartOfWork() == null ? "-- : -- : --" : attendance.getStartOfWork()), 16);
        BoldLabel endLabel = new BoldLabel("퇴근 시간: " + (attendance.getEndOfWork() == null ? "-- : -- : --" : attendance.getEndOfWork()), 16);

        dateLabel.setBorder(new EmptyBorder(5,5,5,5));
        startLabel.setBorder(new EmptyBorder(5,5,5,5));
        endLabel.setBorder(new EmptyBorder(5,5,5,5));

        rowPanel.add(dateLabel);
        rowPanel.add(startLabel);
        rowPanel.add(endLabel);

        panel.add(rowPanel, gbc);
    }
    private void addDataRow(JPanel panel, GetDeptMonthlyAttendanceResponse attendance, GridBagConstraints gbc) {
        JPanel rowPanel = new JPanel();
        rowPanel.setLayout(new BoxLayout(rowPanel, BoxLayout.Y_AXIS));
        rowPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        rowPanel.setBackground(Color.WHITE);

        BoldLabel nameLabel = new BoldLabel("이름: " + attendance.memberName(), 16);
        BoldLabel deptLabel = new BoldLabel("부서 번호: " + attendance.deptNo(), 16);
        BoldLabel dateLabel = new BoldLabel("날짜: " + attendance.date().toString(), 16);
        BoldLabel startLabel = new BoldLabel("출근 시간: " + (attendance.startOfWork() == null ? "-- : -- : --" : attendance.startOfWork().format(DateTimeFormatter.ofPattern("HH:mm:ss"))), 16);
        BoldLabel endLabel = new BoldLabel("퇴근 시간: " + (attendance.endOfWork() == null ? "-- : -- : --" : attendance.endOfWork().format(DateTimeFormatter.ofPattern("HH:mm:ss"))), 16);

        nameLabel.setBorder(new EmptyBorder(5,5,5,5));
        deptLabel.setBorder(new EmptyBorder(5,5,5,5));
        dateLabel.setBorder(new EmptyBorder(5,5,5,5));
        startLabel.setBorder(new EmptyBorder(5,5,5,5));
        endLabel.setBorder(new EmptyBorder(5,5,5,5));

        rowPanel.add(nameLabel);
        rowPanel.add(deptLabel);
        rowPanel.add(dateLabel);
        rowPanel.add(startLabel);
        rowPanel.add(endLabel);

        panel.add(rowPanel, gbc);
    }
    private void addErrorRow(JPanel panel, String errorMessage, GridBagConstraints gbc) {
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
}


