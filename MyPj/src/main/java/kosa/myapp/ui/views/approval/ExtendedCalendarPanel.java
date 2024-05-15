package main.java.kosa.myapp.ui.views.approval;

import main.java.kosa.myapp.ui.components.panels.CalendarPanel;
import main.java.kosa.myapp.ui.components.panels.CalendarType;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.function.BiConsumer;

public class ExtendedCalendarPanel extends CalendarPanel {
    private JComboBox<String> dropBox;
    private String selectedDropBoxValue;
    private BiConsumer<LocalDate, String> onDateChangeExternal;

    public ExtendedCalendarPanel(CalendarType type, BiConsumer<LocalDate, String> onDateChange) {
        super(type, date -> {}); // 상위 생성자 빈 람다 전달
        this.selectedDropBoxValue = "연차";
        this.onDateChangeExternal = onDateChange;
        initializeUI();
        setupListeners(); // initializeUI 호출 후 setupListeners 호출
        triggerInitialDataLoad(); // 리스너 설정 후 초기 데이터 로드
    }

    private void initializeUI() {
        initializeDropDown(); // 드롭다운 메뉴 초기화
    }

    private void initializeDropDown() {
        dropBox = new JComboBox<>(new String[]{"연차", "퇴근 누락"});
        add(dropBox);
        dropBox.addActionListener(e -> {
            selectedDropBoxValue = (String) dropBox.getSelectedItem();
            onDateChangeExternal.accept(getCurrentDate(), selectedDropBoxValue); // 외부 onDateChange 호출
        });
    }

    private void setupListeners() {
        ActionListener changeListener = e -> {
            // 버튼 클릭 시 현재 날짜 업데이트 후 getCurrentDate 호출
            SwingUtilities.invokeLater(() -> {
                LocalDate currentDate = getCurrentDate();
                onDateChangeExternal.accept(currentDate, selectedDropBoxValue); // 현재 날짜와 선택된 값으로 외부 onDateChange 호출
            });
        };

        getPrevButton().addActionListener(changeListener); // 이전 버튼에 리스너 추가
        getNextButton().addActionListener(changeListener); // 다음 버튼에 리스너 추가
    }

    private void triggerInitialDataLoad() {
        onDateChangeExternal.accept(getCurrentDate(), selectedDropBoxValue); // 현재 날짜와 선택된 값으로 초기 데이터 로드
    }
}