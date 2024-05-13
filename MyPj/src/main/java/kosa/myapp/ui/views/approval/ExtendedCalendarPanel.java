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
        selectedDropBoxValue = "연차";
        this.onDateChangeExternal = onDateChange;
        initializeUI();
    }

    private void initializeUI() {
        initializeDropDown();
        setupListeners();
        triggerInitialDataLoad();
    }

    private void initializeDropDown() {
        dropBox = new JComboBox<>(new String[]{"연차", "퇴근 누락"});
        add(dropBox);
        dropBox.addActionListener(e -> {selectedDropBoxValue = (String) dropBox.getSelectedItem();
                    onDateChangeExternal.accept(getCurrentDate(), selectedDropBoxValue);});
                 // 드롭박스 변경 시 콜백 실행);
    }

    private void setupListeners() {
        ActionListener changeListener = e -> onDateChangeExternal.accept(getCurrentDate(), selectedDropBoxValue);

        getPrevButton().addActionListener(changeListener);
        getNextButton().addActionListener(changeListener);
    }

    private void triggerInitialDataLoad() {
        // 객체가 완전히 초기화된 후 콜백 함수를 호출하여 초기 데이터를 로드합니다.
        onDateChangeExternal.accept(getCurrentDate(), selectedDropBoxValue);
    }
}

