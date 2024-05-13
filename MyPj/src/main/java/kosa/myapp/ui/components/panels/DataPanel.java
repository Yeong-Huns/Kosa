package main.java.kosa.myapp.ui.components.panels;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

/**
 * packageName    : main.java.kosa.myapp.ui.components.panels
 * fileName       : DataPanel
 * author         : Yeong-Huns
 * date           : 2024-05-14
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-14        Yeong-Huns       최초 생성
 */
public class DataPanel extends JPanel {
    private Map<String, Object> data = new HashMap<String, Object>();
    public void setData(String key, Object value) {
        data.put(key, value);
    }
    public Object getData(String key) {
        return data.get(key);
    }
}
