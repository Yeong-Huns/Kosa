package main.java.kosa.myapp.ui.components.panels;

import lombok.Getter;
import lombok.Setter;
import main.java.kosa.myapp.ui.components.button.ButtonType;
import main.java.kosa.myapp.ui.components.button.CommonButton;
import main.java.kosa.myapp.ui.components.label.BoldLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;

/**
 * packageName    : main.java.kosa.myapp.ui.components.panels
 * fileName       : CalendarPanel
 * author         : Yeong-Huns
 * date           : 2024-05-08
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-08        Yeong-Huns       최초 생성
 */
@Getter
public class CalendarPanel extends JPanel {
    private LocalDate currentDate;
    private CommonButton prevButton, nextButton;
    private BoldLabel dateLabel;
    private final CalendarType type;

    public CalendarPanel(CalendarType type) {
        SpringLayout layout = new SpringLayout();
        this.type = type;
        setPreferredSize(new Dimension(560, 50));
        setBorder(BorderFactory.createLineBorder(Color.red));
        setLayout(layout);
        initialize();
        alignComponents(layout);
    }

    private void initialize() {
        currentDate = LocalDate.now();
        prevButton = new CommonButton("<", ButtonType.ARROW);
        nextButton = new CommonButton(">", ButtonType.ARROW);

        switch (type) {
            case YEAR_MONTH -> {
                dateLabel = new BoldLabel(currentDate.getYear() + "년 " + (currentDate.getMonthValue()) + "월", 18);
                prevButton.addActionListener(e -> navigateMonths(-1));
                nextButton.addActionListener(e -> navigateMonths(1));
            }
            case YEAR_MONTH_DAY -> {
                dateLabel = new BoldLabel(currentDate.getYear() + "년 " + currentDate.getMonthValue() + "월 " + currentDate.getDayOfMonth() + "일", 18);
                prevButton.addActionListener(e -> navigateDays(-1));
                nextButton.addActionListener(e -> navigateDays(1));
            }
        }
        add(dateLabel);
        add(prevButton);
        add(nextButton);
    }

    private void navigateDays(int daysToAdd){
        currentDate = currentDate.plusDays(daysToAdd);
        dateLabel.setText(currentDate.getYear() + "년 " + currentDate.getMonthValue() + "월 " + currentDate.getDayOfMonth() + "일");
    }

    private void navigateMonths(int monthToAdd) {
        currentDate = currentDate.plusMonths(monthToAdd);
        dateLabel.setText(currentDate.getYear() + "년 " + (currentDate.getMonthValue()) + "월");
    }

    private void alignComponents(SpringLayout layout) {
        layout.putConstraint(SpringLayout.WEST, prevButton, -90, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, prevButton, 0, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.WEST, dateLabel, 10, SpringLayout.EAST, prevButton);
        layout.putConstraint(SpringLayout.NORTH, dateLabel, 15, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.WEST, nextButton, 10, SpringLayout.EAST, dateLabel);
        layout.putConstraint(SpringLayout.NORTH, nextButton, 0, SpringLayout.NORTH, this);
    }

    private void updateLabel(){
        switch (type){
            case YEAR_MONTH -> {
                dateLabel.setText(currentDate.getYear() + "년 " + (currentDate.getMonthValue()) + "월");
            }
            case YEAR_MONTH_DAY -> {
                dateLabel.setText(currentDate.getYear() + "년 " + currentDate.getMonthValue() + "월 " + currentDate.getDayOfMonth() + "일");
            }
        }
    }
    public void resetDate(){
        currentDate = LocalDate.now();
        updateLabel();
    }
    public void setPrevButtonAction(ActionListener actionListener){
        prevButton.addActionListener(actionListener);
    }
    public void setNextButtonAction(ActionListener actionListener){
        nextButton.addActionListener(actionListener);
    }
    public void setButtonAction(ActionListener actionListener){
        prevButton.addActionListener(actionListener);
        nextButton.addActionListener(actionListener);
    }
}
