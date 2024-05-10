package main.java.kosa.myapp.ui.views.annualLeaves;

import main.java.kosa.myapp.ui.views.View;
import main.java.kosa.myapp.ui.components.panels.*;
import main.java.kosa.myapp.ui.frames.MainCard;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * packageName    : main.java.kosa.myapp.ui.views.AnnualLeaves
 * fileName       : AnnualLeavesView
 * author         : Yeong-Huns
 * date           : 2024-05-04
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-04        Yeong-Huns       최초 생성
 */
public class AnnualLeavesView extends JPanel {
    public AnnualLeavesView() {
        super.setLayout(new BorderLayout());
        MainCard.getInstance().add(this, View.ANNUAL_LEAVES);
        initialize();
    }
    private void initialize() {
        add(new TopPanelWithBackBtn("휴가", View.ATTENDANCE), BorderLayout.NORTH);
        add(new ScrollPanel(new ScrollableRecordsPanel()), BorderLayout.CENTER);


        add(new BottomPanel(), BorderLayout.SOUTH);
    }
}
