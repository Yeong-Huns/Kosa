package me.mini.view.commuteTime;

import me.mini.component.panel.BottomPanel;
import me.mini.component.panel.TopPanel;
import me.mini.viewManager.MainCard;
import me.mini.viewManager.View;

import javax.swing.*;
import java.awt.*;

/**
 * packageName    : me.mini.view.commuteTime
 * fileName       : CommutePanel
 * author         : Yeong-Huns
 * date           : 2024-05-03
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-03        Yeong-Huns       최초 생성
 */
public class CommutePanel extends JPanel {
    public CommutePanel() {
        super(new BorderLayout());
        initialize();
        MainCard.getInstance().add(this, View.COMMUTE.toString());
    }
    private void initialize() {
        add(new TopPanel(), BorderLayout.NORTH);
        add(new CommuteTimeInnerPanel(), BorderLayout.CENTER);
        add(new BottomPanel(), BorderLayout.SOUTH);
    }
}
