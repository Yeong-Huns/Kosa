package main.java.kosa.myapp.ui.views.approval;

import com.toedter.calendar.JCalendar;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
    private JComboBox<String> dateCombo;
    private JButton prevButton, nextButton, applyButton;
    private LocalDate currentDate;

    public ApprovalDetail() {
        SpringLayout layout = new SpringLayout();
        setLayout(layout);
        initialize();
        alignComponents(layout);
    }

    private void initialize() {
        currentDate = LocalDate.now();
        dateCombo = new JComboBox<>();
        updateDateCombo(currentDate);

        prevButton = new JButton("<");
        nextButton = new JButton(">");
        applyButton = new JButton("신청");

        add(dateCombo);
        add(prevButton);
        add(nextButton);
        add(applyButton);

        prevButton.addActionListener(e -> navigateMonths(-1));
        nextButton.addActionListener(e -> navigateMonths(1));

        applyButton.addActionListener(e -> showOptions());
    }

    private void alignComponents(SpringLayout layout) {
        layout.putConstraint(SpringLayout.WEST, prevButton, -90, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, prevButton, 35, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.WEST, dateCombo, 5, SpringLayout.EAST, prevButton);
        layout.putConstraint(SpringLayout.NORTH, dateCombo, 35, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.WEST, nextButton, 5, SpringLayout.EAST, dateCombo);
        layout.putConstraint(SpringLayout.NORTH, nextButton, 35, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.EAST, applyButton, -20, SpringLayout.EAST, this);
        layout.putConstraint(SpringLayout.NORTH, applyButton, 5, SpringLayout.NORTH, this);
    }

    private void navigateMonths(int direction) {
        int monthIndex = currentDate.getMonthValue() - 1 + direction;
        int year = currentDate.getYear();
        if (monthIndex < 0) {
            year--;
            monthIndex = 11;
        } else if (monthIndex > 11) {
            year++;
            monthIndex = 0;
        }
        currentDate = LocalDate.of(year, monthIndex + 1, 1);
        updateDateCombo(currentDate);
    }

    private void updateDateCombo(LocalDate date) {
        String formattedDate = date.getYear() + "년 " + (date.getMonthValue()) + "월";
        dateCombo.removeAllItems();
        dateCombo.addItem(formattedDate);
    }

    public void showOptions() {
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
