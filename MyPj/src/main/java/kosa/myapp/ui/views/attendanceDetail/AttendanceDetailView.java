package main.java.kosa.myapp.ui.views.attendanceDetail;

import lombok.Getter;
import main.java.kosa.myapp.ui.components.panels.*;
import main.java.kosa.myapp.ui.frames.MainCard;
import main.java.kosa.myapp.ui.views.View;

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
@Getter
public class AttendanceDetailView extends JPanel{
    private final AttendanceDetail attendanceDetail;
    public AttendanceDetailView(String string) {
        super(new BorderLayout());
        attendanceDetail = new AttendanceDetail();
        MainCard.getInstance().add(this, View.MY_ATTENDANCE);
        initialize(string);
    }
    private void initialize(String string){
        add(new TopPanelWithBackBtn(string), BorderLayout.NORTH);
        add(attendanceDetail , BorderLayout.CENTER);
        add(new BottomPanel(), BorderLayout.SOUTH);
    }
}
