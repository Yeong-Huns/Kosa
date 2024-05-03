package me.mini.view.attendanceDetail;

import me.mini.component.panel.BottomPanel;
import me.mini.component.panel.ScrollPanel;
import me.mini.component.panel.ScrollableRecordsPanel;
import me.mini.view.main.MainCard;

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
public class CommutePanel extends JPanel{
    public CommutePanel(String string) {
        super(new BorderLayout());
        MainCard.getInstance().add(this, "Records");
        initialize(string);
    }
    private void initialize(String string){
        add(new Header(string), BorderLayout.NORTH);
        add(new ScrollPanel(new ScrollableRecordsPanel()), BorderLayout.CENTER);
        add(new BottomPanel(), BorderLayout.SOUTH);
    }
}
