package excel;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * packageName    : excel
 * fileName       : excelTest
 * author         : Yeong-Huns
 * date           : 2024-04-30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-30        Yeong-Huns       최초 생성
 */
public class ExcelTest {
    public void write(String path, String fileName) throws IOException {
        Workbook workbook = new XSSFWorkbook();

        Sheet sheet0 = workbook.createSheet();
        Sheet sheet1 = workbook.createSheet();
        Sheet sheet2 = workbook.createSheet("sheetName");

        Row row0 = sheet0.createRow(0);
        Row row1 = sheet0.createRow(1);
        Row row2 = sheet0.createRow(2);

        row0.createCell(0).setCellValue("Name");
        row0.createCell(1).setCellValue("TeamName");
        row0.createCell(2).setCellValue("role");
        row0.createCell(3).setCellValue("birthday");
        row0.createCell(4).setCellValue("workStartDay");

        row1.createCell(0).setCellValue("홍길동");
        row1.createCell(1).setCellValue("1팀");
        row1.createCell(2).setCellValue("MANAGER");
        row1.createCell(3).setCellValue("1989-07-15");
        row1.createCell(4).setCellValue("2024-04-30");

        row2.createCell(0).setCellValue("김종현");
        row2.createCell(1).setCellValue("2팀");
        row2.createCell(2).setCellValue("MEMBER");
        row2.createCell(3).setCellValue("1994-04-16");
        row2.createCell(4).setCellValue("2024-04-28");

        FileOutputStream fos = new FileOutputStream(path+fileName);
        workbook.write(fos);
        fos.close();
        workbook.close();
    }
}
