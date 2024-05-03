package me.mini.component.panel;

import javax.swing.*;
import java.awt.*;

/**
 * packageName    : me.mini.view.common
 * fileName       : ScrollableRecordsPanel
 * author         : Yeong-Huns
 * date           : 2024-05-02
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-02        Yeong-Huns       최초 생성
 */
public class ScrollableRecordsPanel extends JPanel {
    private int numberOfRecords = 50;
    private int cardHeight = 60;
    private final int spacing = 5;

    public ScrollableRecordsPanel() {
        super(null);
        setPreferredSize(new Dimension(560, getTotalHeight()));
        printRecord();
    }

    public int getTotalHeight() {
        return numberOfRecords * (cardHeight + spacing);
    }

    public void printRecord(){
        for (int i = 0; i < numberOfRecords; i++) {
            JPanel recordPanel = new JPanel();
            recordPanel.setLayout(new BorderLayout());
            recordPanel.setBounds(10, i * (cardHeight + spacing), 560, cardHeight);
            recordPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            JLabel recordLabel = new JLabel("근무 기록 " + (i + 1), JLabel.CENTER);
            recordPanel.add(recordLabel);
            add(recordPanel);
        }
    }
}
