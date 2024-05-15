package main.java.kosa.myapp.ui.views.setting;

import main.java.kosa.myapp.ui.components.panels.BottomPanel;
import main.java.kosa.myapp.ui.components.panels.TopPanel;
import main.java.kosa.myapp.ui.frames.MainCard;
import main.java.kosa.myapp.ui.views.View;
import main.java.kosa.myapp.ui.views.attendanceDetail.ExtendTopPanelAttendanceDetail;

import javax.swing.*;
import java.awt.*;

/**
 * packageName    : main.java.kosa.myapp.ui.views.setting
 * fileName       : SettingView
 * author         : Yeong-Huns
 * date           : 2024-05-14
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-14        Yeong-Huns       최초 생성
 */
public class SettingView extends JPanel {
    public SettingView() {
        super(new BorderLayout());
        MainCard.getInstance().add(this, View.SETTING);
        initialize();
    }
    private void initialize() {
        add(new TopPanel("설정"), BorderLayout.NORTH);
        add(new Setting(), BorderLayout.CENTER);
        add(new BottomPanel(), BorderLayout.SOUTH);
    }
}
