package main.java.kosa.myapp.util.excel;
import main.java.kosa.myapp.dto.attendance.Attendance;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


/**
 * packageName    : main.java.kosa.myapp.util.excel
 * fileName       : ExcelExporter
 * author         : Yeong-Huns
 * date           : 2024-05-14
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-14        Yeong-Huns       최초 생성
 */

public class ExcelExporter {

    public void exportToExcel(List<Attendance> responses) {
        String fileName = LocalTime.now().format(DateTimeFormatter.ofPattern("HHmmssSSS")) + "근무기록.xlsx";
        try (Workbook workbook = new XSSFWorkbook(); FileOutputStream fileOut = new FileOutputStream(fileName)) {
            Sheet sheet = workbook.createSheet("Attendance");

            CellStyle leftAlignedStyle = workbook.createCellStyle();
            leftAlignedStyle.setAlignment(HorizontalAlignment.LEFT);

            CellStyle rightAlignedStyle = workbook.createCellStyle();
            rightAlignedStyle.setAlignment(HorizontalAlignment.RIGHT);

            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setAlignment(HorizontalAlignment.CENTER);

            // 헤더 행 생성
            String[] headers = {"출석일", "출근 시간", "퇴근 시간"};
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);  // 중앙 정렬 스타일 적용
            }

            // 데이터 행 생성
            int rowNum = 1;
            for (Attendance response : responses) {
                Row row = sheet.createRow(rowNum++);
                createCell(row, 0, response.getAttendanceDate(), leftAlignedStyle);
                createCell(row, 1, response.getStartOfWork() == null ? "-- : -- : --" : response.getStartOfWork().format(DateTimeFormatter.ofPattern("HH:mm:ss")), leftAlignedStyle);
                createCell(row, 2, response.getEndOfWork() == null ? "-- : -- : --" : response.getEndOfWork().format(DateTimeFormatter.ofPattern("HH:mm:ss")), leftAlignedStyle);
            }

            // 열 너비 자동 조절
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // 파일로 쓰기
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createCell(Row row, int column, Object value, CellStyle style) {
        Cell cell = row.createCell(column);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof String) {
            cell.setCellValue((String) value);
        } else if (value instanceof LocalDate) {
            cell.setCellValue(value.toString());
        } else if (value instanceof LocalTime) {
            cell.setCellValue(value.toString());
        }
        cell.setCellStyle(style);
    }

    private String getConfirmStatus(int status) {
        return switch (status) {
            case 1 -> "Approved";
            case 2 -> "Pending";
            default -> "Rejected";
        };
    }
}