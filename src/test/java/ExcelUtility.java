import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtility {

    public FileInputStream fi;
    public FileOutputStream fo;
    public XSSFWorkbook workbook;
    public XSSFSheet sheet;
    public XSSFRow row;
    public XSSFCell cell;
    public String filePath;


    public ExcelUtility(String path){
        filePath = path;

    }

    public int getNumberOfRow(String sheetName) throws IOException {

        fi = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);
        int rowCount = sheet.getLastRowNum();
        workbook.close();
        fi.close();

        return rowCount;
    }

    public String getCellData(String sheetName, int rowNumber, int cellNumber) throws IOException {

        fi = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);

        String cellData;

        try {
            cell = sheet.getRow(rowNumber).getCell(cellNumber);
            cellData = cell.toString();
        } catch (Exception e) {
            cellData = "";
        }


        workbook.close();
        fi.close();

        return cellData;
    }

    public void setCellData(String sheetName, int rowNumber, int cellNumber, String data) throws IOException {

        fi = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);

        cell = sheet.getRow(rowNumber).createCell(cellNumber);
        cell.setCellValue(data);

        fo = new FileOutputStream(filePath);
        workbook.write(fo);

        fo.close();
        workbook.close();
        fi.close();


    }



}
