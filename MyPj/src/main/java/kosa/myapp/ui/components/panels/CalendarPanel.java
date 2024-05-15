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
    public LocalDate currentDate;
    private CommonButton prevButton, nextButton;
    private JLabel dateLabel;
    private final CalendarType type;
    private Consumer<LocalDate> onDateChange;

    public CalendarPanel(CalendarType type, Consumer<LocalDate> onDateChange) {
        this.type = type;
        this.onDateChange = onDateChange;
        initializeUI();
        setCurrentDate(LocalDate.now()); // 초기 날짜를 현재 날짜로 설정
    }

    private void initializeUI() {
        setLayout(new FlowLayout());
        prevButton = new CommonButton("<", ButtonType.ARROW); // 이전 버튼 초기화
        nextButton = new CommonButton(">", ButtonType.ARROW); // 다음 버튼 초기화

        dateLabel = new JLabel();
        add(prevButton);
        add(dateLabel);
        add(nextButton);

        prevButton.addActionListener(e -> changeMonth(-1)); // 이전 월로 변경하는 리스너 추가
        nextButton.addActionListener(e -> changeMonth(1));  // 다음 월로 변경하는 리스너 추가
    }

    private void changeMonth(int months) {
        setCurrentDate(currentDate.plusMonths(months)); // 현재 날짜를 월 단위로 변경
    }

    protected void setCurrentDate(LocalDate date) {
        currentDate = date;
        dateLabel.setText(currentDate.format(DateTimeFormatter.ofPattern("yyyy년 MM월"))); // 날짜 레이블 업데이트
        onDateChange.accept(currentDate); // 내부 onDateChange 호출
    }

}