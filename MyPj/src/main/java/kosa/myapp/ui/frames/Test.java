package main.java.kosa.myapp.ui.frames;

import lombok.Getter;
import main.java.kosa.myapp.dto.attendance.Attendance;
import main.java.kosa.myapp.dto.attendance.GetDeptMonthlyAttendanceResponse;
import main.java.kosa.myapp.dto.response.ResponseEntity;
import main.java.kosa.myapp.repository.attendance.AttendanceRepository;
import main.java.kosa.myapp.ui.components.panels.CalendarType;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Consumer;

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
public class Test {
    CalendarPanel_imp calendarPanel1;
    CalendarPanel_imp calendarPanel2;

    private final JPanel dataPanel1;
    private final JPanel dataPanel2;
    private final JScrollPane scrollPane1;
    private final JScrollPane scrollPane2;

    public Test() {
        this.dataPanel1 = new JPanel(new GridBagLayout());
        this.dataPanel2 = new JPanel(new GridBagLayout());

        scrollPane1 = new JScrollPane(dataPanel1);
        scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane2 = new JScrollPane(dataPanel2);
        scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        calendarPanel1 = new CalendarPanel_imp(CalendarType.YEAR_MONTH, date -> updateMemberAttendanceDataPanel(date, dataPanel1));
        calendarPanel2 = new CalendarPanel_imp(CalendarType.YEAR_MONTH, date -> updateDeptAttendanceDataPanel(date, dataPanel2));

        updateMemberAttendanceDataPanel(LocalDate.now(), dataPanel1);
        updateDeptAttendanceDataPanel(LocalDate.now(), dataPanel2);
    }

    public void updateMemberAttendanceDataPanel(LocalDate date, JPanel dataPanel) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(2, 2, 2, 2);

        ResponseEntity<List<Attendance>> attendanceList = AttendanceRepository.getInstance().getMemberMonthlyAttendance(
                Attendance.builder().memberId(3).attendanceDate(date).build());

        dataPanel.removeAll(); // 기존 데이터 제거
        if (attendanceList.isSuccess()) {
            attendanceList.getData().forEach(attendance -> addDataRow(dataPanel, attendance, gbc));
        }
        dataPanel.revalidate();
        dataPanel.repaint();
    }

    public void updateDeptAttendanceDataPanel(LocalDate date, JPanel dataPanel) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10); // 여백 조정
        ResponseEntity<List<GetDeptMonthlyAttendanceResponse>> attendanceList = AttendanceRepository.getInstance().getDeptMonthlyAttendance(
                Attendance.builder().memberId(3).attendanceDate(date).build());

        dataPanel.removeAll();
        if (attendanceList.isSuccess()) {
            attendanceList.getData().forEach(attendance -> addDataRow(dataPanel, attendance, gbc));
        }
        dataPanel.revalidate();
        dataPanel.repaint();
    }


    // 데이터 행 추가 메서드
    private void addDataRow(JPanel panel, Attendance attendance, GridBagConstraints gbc) {
        JPanel rowPanel = new JPanel();
        rowPanel.setLayout(new BoxLayout(rowPanel, BoxLayout.Y_AXIS));
        rowPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1)); // 테두리 추가
        rowPanel.setBackground(Color.WHITE); // 배경색 설정

        JLabel dateLabel = new JLabel("날짜: " + attendance.getAttendanceDate().toString());
        JLabel startLabel = new JLabel("출근 시간: " + (attendance.getStartOfWork() == null ? "--:--:--" : attendance.getStartOfWork()));
        JLabel endLabel = new JLabel("퇴근 시간: " + (attendance.getEndOfWork() == null ? "--:--:--" : attendance.getEndOfWork()));

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
        rowPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1)); // 테두리 추가
        rowPanel.setBackground(Color.WHITE); // 배경색 설정

        JLabel nameLabel = new JLabel("이름: " + attendance.memberName());
        JLabel deptLabel = new JLabel("부서 번호: " + attendance.deptNo());
        JLabel dateLabel = new JLabel("날짜: " + attendance.date().toString());
        JLabel startLabel = new JLabel("출근 시간: " + (attendance.startOfWork() == null ? "--:--:--" : attendance.startOfWork()));
        JLabel endLabel = new JLabel("퇴근 시간: " + (attendance.endOfWork() == null ? "--:--:--" : attendance.endOfWork()));

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

    // 메인 메서드 - GUI 생성
    public static void main(String[] args) {
        JFrame frame = new JFrame("JTabbedPane Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 900);

        Test test = new Test();

        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel panel1 = new JPanel(new BorderLayout());
        panel1.add(test.calendarPanel1, BorderLayout.NORTH);
        panel1.add(test.dataPanel1, BorderLayout.CENTER);

        JPanel panel2 = new JPanel(new BorderLayout());
        panel2.add(test.calendarPanel2, BorderLayout.NORTH);
        panel2.add(test.dataPanel2, BorderLayout.CENTER);

        tabbedPane.addTab("나의 근태", panel1);
        tabbedPane.addTab("부서 근태", panel2);

        frame.add(tabbedPane);
        frame.setVisible(true);
    }

    @Getter
    class CalendarPanel_imp extends JPanel {
        private LocalDate currentDate;
        private JButton prevButton, nextButton;
        private JLabel dateLabel;
        private final CalendarType type;
        private Consumer<LocalDate> onDateChange;

        public CalendarPanel_imp(CalendarType type, Consumer<LocalDate> onDateChange) {
            this.type = type;
            this.onDateChange = onDateChange;
            initializeUI();
            setCurrentDate(LocalDate.now());
        }

        private void initializeUI() {
            setLayout(new FlowLayout());
            prevButton = new JButton("<");
            nextButton = new JButton(">");

            dateLabel = new JLabel();
            add(prevButton);
            add(dateLabel);
            add(nextButton);

            prevButton.addActionListener(e -> changeMonth(-1));
            nextButton.addActionListener(e -> changeMonth(1));
        }

        private void changeMonth(int months) {
            setCurrentDate(currentDate.plusMonths(months));
        }

        private void setCurrentDate(LocalDate date) {
            currentDate = date;
            dateLabel.setText(currentDate.format(DateTimeFormatter.ofPattern("yyyy년 MM월")));
            onDateChange.accept(currentDate);
        }
    }
}


