package main.java.kosa.myapp.ui.views.commuteTime;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class NowDateLabel extends JLabel {
    public NowDateLabel() {
        super();
        initalize();
    }

    private void initalize() {
        setForeground(new Color(255, 255, 255));
        setFont(new Font("D2coding", Font.PLAIN, 17));
        setHorizontalAlignment(SwingConstants.RIGHT);
        setBounds(367, 24, 150, 16);
        LocalDate now = LocalDate.now();  // 현재 날짜를 가져옵니다.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
        String formattedDate = now.format(formatter);
        setText(formattedDate);
    }
}
