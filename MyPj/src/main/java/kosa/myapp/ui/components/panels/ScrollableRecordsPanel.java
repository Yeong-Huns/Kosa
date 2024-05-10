package main.java.kosa.myapp.ui.components.panels;

import javax.swing.*;
import java.awt.*;
import java.util.List;

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
    private int numberOfRecords;
    private int cardHeight;
    private final int spacing = 10;

    public ScrollableRecordsPanel() {
        super(null);
        setPreferredSize(new Dimension(550, 503));
        setBorder(BorderFactory.createLineBorder(Color.red));
    }

    private int getTotalHeight() {
        return numberOfRecords * (cardHeight + spacing);
    }

    public void printRecord(List<? extends JPanel> panel){
        //
        // removeAll();
        numberOfRecords = panel.size();
        cardHeight = panel.get(0).getPreferredSize().height;
        System.out.println("ScrollableRecordsPanel: " + numberOfRecords + " : " + cardHeight);
        System.out.println("getTotalHeight: " + getTotalHeight());
        setPreferredSize(new Dimension(550, getTotalHeight()));
        for (int i = 0; i < panel.size(); i++) {
            panel.get(i).setBounds(10, i * (cardHeight + spacing), 545, cardHeight);
            panel.get(i).setBorder(BorderFactory.createLineBorder(Color.BLACK));
            add(panel.get(i));
        }
    }
}
