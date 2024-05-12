package main.java.kosa.myapp.ui.views.commuteTime;

import main.java.kosa.myapp.ui.components.label.PlainLabel;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class NowDatePanel extends JPanel {
    public NowDatePanel(LocalDate date) {
        setBounds(20, 110, 543, 60);
        setBackground(new Color(157, 157, 157));
        setLayout(null);
        initialize(date);
    }
    private void initialize(LocalDate date) {
        String format = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일").format(date);
        PlainLabel nowDate = new PlainLabel(format, 17);
        nowDate.setForeground(new Color(255, 255, 255));
        nowDate.setHorizontalAlignment(SwingConstants.RIGHT);
        nowDate.setBounds(367, 24, 150, 16);
        add(nowDate);
    }
}
