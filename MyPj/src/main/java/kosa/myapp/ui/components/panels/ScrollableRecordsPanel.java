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

    public void printRecord(List<? extends JPanel> panels){
        //
        removeAll();  // Remove all existing components
        numberOfRecords = panels.size();
        cardHeight = panels.get(0).getPreferredSize().height;

        int totalHeight = getTotalHeight();
        setPreferredSize(new Dimension(550, totalHeight));  // Update preferred size based on content

        for (int i = 0; i < panels.size(); i++) {
            JPanel panel = panels.get(i);
            panel.setBounds(10, i * (cardHeight + spacing), 545, cardHeight);
            panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            add(panel);
        }

        revalidate();  // Validate the layout again after modifications
        repaint();  // Repaint the panel to reflect changes
    }
}
