package me.mini.view.attendanceDetail;

import me.mini.component.panel.BottomPanel;
import me.mini.component.panel.ScrollPanel;
import me.mini.component.panel.ScrollableRecordsPanel;
import me.mini.component.panel.TopPanelWithBackBtn;
import me.mini.viewManager.MainCard;
import me.mini.viewManager.View;

import javax.swing.*;
import java.awt.*;

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
public class AttendanceDetailPanel extends JPanel{
    public AttendanceDetailPanel(String string) {
        super(new BorderLayout());
        MainCard.getInstance().add(this, View.ATTENDANCE.toString());
        initialize(string);
    }
    private void initialize(String string){
        add(new TopPanelWithBackBtn(string), BorderLayout.NORTH);
        add(new ScrollPanel(new ScrollableRecordsPanel()), BorderLayout.CENTER);
        add(new BottomPanel(), BorderLayout.SOUTH);
    }
}
