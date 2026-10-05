package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

    private static String resolveWorkbookPath() {
        String[] candidatePaths = new String[] {
                System.getProperty("user.dir")
                        + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "testdata.xlsx",
                System.getProperty("user.dir") + File.separator + "Sprint Case Study_Unacdamy (1).xlsx",
                System.getProperty("user.dir") + File.separator + "Sprint Case Study_Unacdamy.xlsx"
        };

        for (String path : candidatePaths) {
            if (new File(path).exists()) {
                return path;
            }
        }
        return candidatePaths[0];
    }

    private static final String FILE_PATH = resolveWorkbookPath();

    public static Object[][] getexceldata() throws IOException {
        return getexceldata("Test Case Template ");
    }

    public static Object[][] getexceldata(String sheetName) throws IOException {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            throw new IOException("Excel file not found at path: " + FILE_PATH);
        }

        FileInputStream fis = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet(sheetName);

        if (sheet == null) {
            sheet = workbook.getSheetAt(0);
        }

        int rows = sheet.getPhysicalNumberOfRows();
        int columns = sheet.getRow(0).getPhysicalNumberOfCells();

        DataFormatter formatter = new DataFormatter();
        Object[][] data = new Object[rows - 1][columns];

        for (int i = 1; i < rows; i++) {
            XSSFRow row = sheet.getRow(i);
            for (int j = 0; j < columns; j++) {
                if (row != null) {
                    XSSFCell cell = row.getCell(j);
                    data[i - 1][j] = (cell != null) ? formatter.formatCellValue(cell).trim() : "";
                } else {
                    data[i - 1][j] = "";
                }
            }
        }

        workbook.close();
        fis.close();
        return data;
    }

    public static Map<String, String> getTestCaseData(String testCaseId) {
        Map<String, String> testDataMap = new HashMap<>();
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                return testDataMap;
            }

            FileInputStream fis = new FileInputStream(file);
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheet("Test Case Template ");
            if (sheet == null) {
                sheet = workbook.getSheetAt(0);
            }

            DataFormatter formatter = new DataFormatter();
            int rows = sheet.getPhysicalNumberOfRows();
            XSSFRow headerRow = sheet.getRow(0);

            for (int i = 1; i < rows; i++) {
                XSSFRow row = sheet.getRow(i);
                if (row != null) {
                    XSSFCell idCell = row.getCell(0);
                    String id = (idCell != null) ? formatter.formatCellValue(idCell).trim() : "";
                    if (id.equalsIgnoreCase(testCaseId)) {
                        int cells = row.getPhysicalNumberOfCells();
                        for (int j = 0; j < cells; j++) {
                            String header = formatter.formatCellValue(headerRow.getCell(j)).trim();
                            String value = formatter.formatCellValue(row.getCell(j)).trim();
                            testDataMap.put(header, value);
                        }
                        break;
                    }
                }
            }

            workbook.close();
            fis.close();
        } catch (Exception e) {
            System.err.println("Error reading test case data for: " + testCaseId + " - " + e.getMessage());
        }
        return testDataMap;
    }
}
