package ro.ulbs.proiectaresoftware.students;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.util.Map;
import java.util.TreeMap;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;

public class ExcelExport {

    public static void main(String[] args) {
        // Create a new workbook
        XSSFWorkbook workbook = new XSSFWorkbook();

        // Create a sheet
        XSSFSheet sheet = workbook.createSheet("Student Details");

        // Data to write
        Map<String, Object[]> data = new TreeMap<>();
        data.put("1", new Object[]{"ID", "NAME", "LASTNAME"});
        data.put("2", new Object[]{1, "Pankaj", "Kumar"});
        data.put("3", new Object[]{2, "Prakashni", "Yadav"});
        data.put("4", new Object[]{3, "Ayan", "Mondal"});
        data.put("5", new Object[]{4, "Virat", "Kohli"});

        int rowNum = 0;

        for (String key : data.keySet()) {
            Row row = sheet.createRow(rowNum++);
            Object[] objArr = data.get(key);
            int cellNum = 0;
            for (Object obj : objArr) {
                Cell cell = row.createCell(cellNum++);
                if (obj instanceof String)
                    cell.setCellValue((String) obj);
                else if (obj instanceof Integer)
                    cell.setCellValue((Integer) obj);
            }
        }

        try (FileOutputStream out = new FileOutputStream("StudentData.xlsx")) {
            workbook.write(out);
            System.out.println("StudentData.xlsx written successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
