package main.java.kosa.myapp.ui.components.panels;

import lombok.Getter;
import main.java.kosa.myapp.ui.components.button.ButtonType;
import main.java.kosa.myapp.ui.components.button.CommonButton;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.function.Consumer;

/**
 * packageName    : main.java.kosa.myapp.ui.views.attendanceDetail
 * fileName       : AttendanceCalendarPanel
 * author         : Yeong-Huns
 * date           : 2024-05-14
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-14        Yeong-Huns       최초 생성
 */
@Getter
public class CalendarPanel extends JPanel {
    private LocalDate currentDate;
    private CommonButton prevButton, nextButton;
    private JLabel dateLabel;
    private final CalendarType type;
    private Consumer<LocalDate> onDateChange;

    public CalendarPanel(CalendarType type, Consumer<LocalDate> onDateChange) {
        this.type = type;
        this.onDateChange = onDateChange;
        initializeUI();
        setCurrentDate(LocalDate.now());
    }

    private void initializeUI() {
        setLayout(new FlowLayout());
        prevButton = new CommonButton("<", ButtonType.ARROW);
        nextButton = new CommonButton(">", ButtonType.ARROW);

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

    protected void setCurrentDate(LocalDate date) {
        currentDate = date;
        dateLabel.setText(currentDate.format(DateTimeFormatter.ofPattern("yyyy년 MM월")));
        onDateChange.accept(currentDate);
    }
}
