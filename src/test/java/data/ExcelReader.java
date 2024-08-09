package data;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelReader {
    static FileInputStream fis = null;
    public FileInputStream getFileInputStream(){
        String filepath = System.getProperty("user.dir") + "/src/test/java/data/userData.xlsx";
        File srcFile = new File(filepath);
        try {
            fis = new FileInputStream(filepath);
        } catch (FileNotFoundException e) {
            System.out.println("Test data file not found. terminating Process !!! : Check file path of TestData " + e.getMessage());
            System.exit(0);
        }
        return fis;
    }

    public Object[][] getExcelData() throws IOException {
        fis = getFileInputStream();
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheetAt(0);

        int TotalNumberOfRows = sheet.getLastRowNum() + 1;
        int TotalNumberOfColumns = 4;

        String[][] arrayExcelData = new String[TotalNumberOfRows][TotalNumberOfColumns];
        for(int i = 0; i < TotalNumberOfRows; i++){
            for (int j = 0; j < TotalNumberOfColumns; j++){
                XSSFRow row = sheet.getRow(i);
                arrayExcelData[i][j] = row.getCell(j).toString();
            }
        }
        wb.close();
        return arrayExcelData;
    }
}
