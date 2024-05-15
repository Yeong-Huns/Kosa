package main.java.kosa.myapp.ui.views.approval;

import lombok.Getter;
import main.java.kosa.myapp.ui.components.panels.BottomPanel;
import main.java.kosa.myapp.ui.components.panels.TopPanel;
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
@Getter
public class ApprovalView extends JPanel {
    ApprovalDetail approvalDetail;
    public ApprovalView() {
        setLayout(new BorderLayout());
        approvalDetail = new ApprovalDetail();
        MainCard.getInstance().add(this, View.APPROVAL);
        initialize();
    }
    private void initialize() {
        add(new ExtendedTopPanel(), BorderLayout.NORTH);
        add(approvalDetail, BorderLayout.CENTER);
        add(new BottomPanel(), BorderLayout.SOUTH);
    }
}
