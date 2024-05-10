package main.java.kosa.myapp.ui.views.approval;

import main.java.kosa.myapp.ui.components.panels.*;
import main.java.kosa.myapp.ui.frames.MainCard;
import main.java.kosa.myapp.ui.views.View;

import javax.swing.*;
import java.awt.*;

/**
 * packageName    : main.java.kosa.myapp.ui.views.approval
 * fileName       : ApprovalView
 * author         : Yeong-Huns
 * date           : 2024-05-07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-07        Yeong-Huns       최초 생성
 */
public class ApprovalView extends JPanel {
    public ApprovalView() {
        setLayout(new BorderLayout());
        MainCard.getInstance().add(this, View.APPROVAL);
        initialize();
    }
    private void initialize() {
        add(new TopPanel("결재함"), BorderLayout.NORTH);
        add(new ApprovalDetail(1), BorderLayout.CENTER);
        add(new BottomPanel(), BorderLayout.SOUTH);
    }
}