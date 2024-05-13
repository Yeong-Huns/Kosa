package main.java.kosa.myapp.ui.frames;

import main.java.kosa.myapp.ui.components.label.PlainLabel;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

/**
 * packageName    : main.java.kosa.myapp.ui.frames
 * fileName       : DataDisPlay
 * author         : Yeong-Huns
 * date           : 2024-05-13
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-13        Yeong-Huns       최초 생성
 */
public class AttendancePanel extends JFrame {
    public AttendancePanel() {
        setTitle("Attendance Details");
        setSize(600, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel contentPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        gbc.insets = new Insets(5, 5, 5, 5);

        // 예시 데이터
        String[] headers = {"IN", "OUT", "근무", "인정 시간"};
        String[] values = {"08:33:02", "-", "없음", "-"};
        String[] descriptions = {"출근 상태", "퇴근 상태", "없음", "비인정"};

        contentPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        for (int i = 0; i < headers.length; i++) {
            addLabeledData(contentPanel, headers[i], values[i], descriptions[i], gbc, i);
        }

        add(contentPanel);
        setVisible(true);
    }

    private void addLabeledData(JPanel panel, String header, String value, String description, GridBagConstraints gbc, int gridx) {
        gbc.gridx = gridx;

        JPanel dataPanel = new JPanel(new BorderLayout());
        dataPanel.setBorder(new MatteBorder(0, 0, 0, 1, Color.GRAY));

        PlainLabel headerLabel = new PlainLabel(header, 14);
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        dataPanel.add(headerLabel, BorderLayout.NORTH);

        PlainLabel valueLabel = new PlainLabel(value, 14);
        valueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        dataPanel.add(valueLabel, BorderLayout.CENTER);

        PlainLabel descriptionLabel = new PlainLabel(description, 14);
        descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        dataPanel.add(descriptionLabel, BorderLayout.SOUTH);

        panel.add(dataPanel, gbc);
    }

    public static void main(String[] args) {
        new AttendancePanel();
    }
}