package main.java.kosa.myapp.ui.views.annualLeaves;

import main.java.kosa.myapp.ui.components.panels.ScrollPanel;
import main.java.kosa.myapp.ui.components.panels.ScrollableRecordsPanel;

import javax.swing.*;
import java.awt.*;

/**
 * packageName    : main.java.kosa.myapp.ui.views.annualLeaves
 * fileName       : AnnualLeavesDetail
 * author         : Yeong-Huns
 * date           : 2024-05-08
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-08        Yeong-Huns       최초 생성
 */
public class AnnualLeavesDetail extends JTabbedPane {
    private final ScrollableRecordsPanel scrollableRecordsPanel;


    public AnnualLeavesDetail() {
        this.scrollableRecordsPanel = new ScrollableRecordsPanel();
    }

    public void print(){
        //scrollableRecordsPanel.printRecord();
    }

    //add(new ScrollPanel(new ScrollableRecordsPanel(new ArrayList<>())), BorderLayout.CENTER);
}
