package main.java.kosa.myapp.util.test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * packageName    : main.java.kosa.myapp.util.test
 * fileName       : Alarm
 * author         : Yeong-Huns
 * date           : 2024-05-14
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-14        Yeong-Huns       최초 생성
 */
public class AlarmClock extends JFrame {
    private JTextField startTimeField;
    private JTextField endTimeField;
    private java.util.Timer timer;

    public AlarmClock() {
        setTitle("알람 시계");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        startTimeField = new JTextField(5);
        endTimeField = new JTextField(5);
        JButton setAlarmButton = new JButton("알람 설정");

        add(new JLabel("출근 시간:"));
        add(startTimeField);
        add(new JLabel("퇴근 시간:"));
        add(endTimeField);
        add(setAlarmButton);

        setAlarmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setAlarm(startTimeField.getText(), true);
                setAlarm(endTimeField.getText(), false);
            }
        });

        setVisible(true);
    }

    private void setAlarm(String time, boolean isStart) {
        String[] parts = time.split(":");
        int hour = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Calendar cal = Calendar.getInstance();
                int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
                if (dayOfWeek != Calendar.SATURDAY && dayOfWeek != Calendar.SUNDAY) {
                    showAlarmPopup(isStart ? "출근" : "퇴근");
                }
            }
        }, 0, 1000 * 60 * 60 * 24);
    }

    private void showAlarmPopup(String type) {
        JPanel panel = new JPanel();
        JLabel messageLabel = new JLabel(type + "시간입니다.");
        JButton closeButton = new JButton(type + " 체크하기");
        closeButton.addActionListener(e -> dispose());

        panel.add(messageLabel);
        panel.add(closeButton);

        JOptionPane.showMessageDialog(null, panel
                , "알람", JOptionPane.INFORMATION_MESSAGE);
    }
    public static void main(String[] args) {
        new AlarmClock();
    }
}
