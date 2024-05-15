package main.java.kosa.myapp.ui.views.attendanceDetail;

import main.java.kosa.myapp.Main;
import main.java.kosa.myapp.dto.attendance.Attendance;
import main.java.kosa.myapp.dto.response.ResponseEntity;
import main.java.kosa.myapp.repository.attendance.AttendanceRepository;
import main.java.kosa.myapp.ui.components.button.ButtonType;
import main.java.kosa.myapp.ui.components.button.CommonButton;
import main.java.kosa.myapp.ui.components.panels.TopPanelWithBackBtn;
import main.java.kosa.myapp.util.excel.ExcelExporter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.IntStream;

/**
 * packageName    : main.java.kosa.myapp.ui.views.attendanceDetail
 * fileName       : ExtendTopPanelAttendanceDetail
 * author         : Yeong-Huns
 * date           : 2024-05-14
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-14        Yeong-Huns       최초 생성
 */
public class ExtendTopPanelAttendanceDetail extends TopPanelWithBackBtn {
    private LocalDate localDate;
    private ExcelExporter excelExporter;
    public ExtendTopPanelAttendanceDetail(String titleName) {
        super(titleName);
        excelExporter = new ExcelExporter();
        addButton();
    }

    private void addButton() {
        JPanel outerPanel = new JPanel(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.LIGHT_GRAY);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(Box.createVerticalGlue());

        CommonButton optionsButton = new CommonButton("신청", ButtonType.SMALL);
        optionsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        optionsButton.addActionListener(this::showOptionsDetail);
        buttonPanel.add(optionsButton);

        buttonPanel.add(Box.createVerticalGlue());

        outerPanel.add(buttonPanel, BorderLayout.EAST);
        add(outerPanel, BorderLayout.EAST);
    }

    private void showOptionsDetail(ActionEvent e) {
        JComboBox<Integer> yearComboBox = new JComboBox<>(IntStream.iterate(LocalDate.now().getYear(), i -> i - 1).limit(LocalDate.now().getYear() - 2000 + 1).boxed().toArray(Integer[]::new));
        JComboBox<Integer> monthComboBox = new JComboBox<>(IntStream.rangeClosed(1, 12).boxed().toArray(Integer[]::new));

        JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));
        panel.add(new JLabel("년도를 선택하세요:"));
        panel.add(yearComboBox);
        panel.add(new JLabel("월을 선택하세요:"));
        panel.add(monthComboBox);

        int result = JOptionPane.showConfirmDialog(null, panel, "년월 선택", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            int selectedYear = (int) yearComboBox.getSelectedItem();
            int selectedMonth = (int) monthComboBox.getSelectedItem();
            localDate = LocalDate.of(selectedYear, selectedMonth, 1);
            String selectedDate = localDate.format(DateTimeFormatter.ofPattern("yyyy년-MM월"));
            System.out.println("Selected Date: " + selectedDate);
            executeAdditionalMethod(selectedDate);
        }
    }

    private void executeAdditionalMethod(String selectedDate) {
        ResponseEntity<List<Attendance>> responses = AttendanceRepository.getInstance().getMemberMonthlyAttendance(
                Attendance.builder()
                        .memberId(Main.getSessionKey())
                        .attendanceDate(localDate).
                        build());
        responses.showDialogs();
        excelExporter.exportToExcel(responses.getData());
        System.out.println("선택된 날짜: " + selectedDate);
    }
}