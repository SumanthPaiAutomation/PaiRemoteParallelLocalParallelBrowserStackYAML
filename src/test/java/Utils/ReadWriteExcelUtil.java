package Utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ReadWriteExcelUtil {

    private static final String SAMPLE_XLSX_FILE_PATH = "path/to/your/excel/file.xlsx";

    public static String getCellData(String sheetName, int rowNum, int colNum) throws IOException {
        FileInputStream fis = new FileInputStream(SAMPLE_XLSX_FILE_PATH);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet(sheetName);
        Row row = sheet.getRow(rowNum);

        if (row != null) {
            Cell cell = row.getCell(colNum);
            if (cell != null) {
                return cell.getStringCellValue();
            }
        }

        return null;
    }

    public static void setCellData(String sheetName, int rowNum, int colNum, String value) throws IOException {
        FileInputStream fis = new FileInputStream(SAMPLE_XLSX_FILE_PATH);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet(sheetName);
        Row row = sheet.getRow(rowNum);

        if (row == null) {
            row = sheet.createRow(rowNum);
        }

        Cell cell = row.getCell(colNum);
        if (cell == null) {
            cell = row.createCell(colNum);
        }

        cell.setCellValue(value);

        FileOutputStream fos = new FileOutputStream(SAMPLE_XLSX_FILE_PATH);
        workbook.write(fos);
        fos.close();
        workbook.close();
        fis.close();
    }
}

//usage
// Reading a cell value
//String username = ExcelReaderWriterUtil.getCellData("LoginData", 1, 0);
//
//// Writing a cell value
//ExcelReaderWriterUtil.setCellData("TestResults", 5, 2, "Pass");