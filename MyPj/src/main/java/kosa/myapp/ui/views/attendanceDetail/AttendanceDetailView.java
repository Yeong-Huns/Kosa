package main.java.kosa.myapp.ui.views.attendanceDetail;

import main.java.kosa.myapp.ui.components.panels.BottomPanel;
import main.java.kosa.myapp.ui.components.panels.ScrollPanel;
import main.java.kosa.myapp.ui.components.panels.ScrollableRecordsPanel;
import main.java.kosa.myapp.ui.components.panels.TopPanelWithBackBtn;
import main.java.kosa.myapp.ui.frames.MainCard;
import main.java.kosa.myapp.ui.views.View;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * packageName    : me.mini.view.commuteLIst
 * fileName       : CommutePanel
 * author         : Yeong-Huns
 * date           : 2024-05-02
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-02        Yeong-Huns       최초 생성
 */
public class AttendanceDetailView extends JPanel{
    public AttendanceDetailView(String string) {
        super(new BorderLayout());
        MainCard.getInstance().add(this, View.MY_ATTENDANCE);
        initialize(string);
    }
    private void initialize(String string){
        add(new TopPanelWithBackBtn(string), BorderLayout.NORTH);
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("나의 근태", new ScrollPanel(new ScrollableRecordsPanel()));

        add(tabbedPane, BorderLayout.CENTER);
        add(new BottomPanel(), BorderLayout.SOUTH);
    }
}
