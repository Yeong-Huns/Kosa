package main.java.kosa.myapp.ui.views.commuteTime;

import main.java.kosa.myapp.ui.components.panels.BottomPanel;
import main.java.kosa.myapp.ui.components.panels.TopPanel;
import main.java.kosa.myapp.ui.frames.MainCard;
import main.java.kosa.myapp.ui.views.View;

import javax.swing.*;
import java.awt.*;

/**
 * packageName    : main.java.kosa.myapp.ui.views.commuteTime
 * fileName       : CommutePanel
 * author         : Yeong-Huns
 * date           : 2024-05-03
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-03        Yeong-Huns       최초 생성
 */
public class CommuteTimeView extends JPanel {
    public CommuteTimeView() {
        super(new BorderLayout());
        initialize();
        MainCard.getInstance().add(this, View.COMMUTE);
    }
    private void initialize() {
        add(new TopPanel(), BorderLayout.NORTH);
        add(new CommuteTimeInnerPanel(), BorderLayout.CENTER);
        add(new BottomPanel(), BorderLayout.SOUTH);
    }
}
