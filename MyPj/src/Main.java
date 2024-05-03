import me.mini.view.ViewConstructor;
import me.mini.view.main.MainFrame;

import java.awt.*;

/**
 * packageName    :
 * fileName       : ${NAME}
 * author         : Yeong-Huns
 * date           : 2024-05-02
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-02        Yeong-Huns       최초 생성
 */
public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ViewConstructor system = new ViewConstructor();
                    MainFrame.getInstance().setVisible(true);
                    System.out.println("Working Directory = " + System.getProperty("user.dir"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}