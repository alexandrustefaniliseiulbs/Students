package ro.ulbs.proiectaresoftware.students;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ExportToExcel implements Exporter {

    final String filename;

    public ExportToExcel(String filename) {
        this.filename = filename;
    }

    @Override
    public void export(List<Student> lista) throws IOException {
        Path outFile = Paths.get(filename);  // Convertește String la Path

        try(XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet sheet = workbook.createSheet("Studenti");

            int rowNum = 0;
            Row header = sheet.createRow(rowNum++);
            header.createCell(0).setCellValue("Numar matricol");
            header.createCell(1).setCellValue("Prenume");
            header.createCell(2).setCellValue("Nume");
            header.createCell(3).setCellValue("Formatie de studiu");

            for(Student student : lista) {
                Row row = sheet.createRow(rowNum++);
                Cell c0 = row.createCell(0);
                c0.setCellValue(nullToEmpty(student.getNumarMatricol()));
                row.createCell(1).setCellValue(nullToEmpty(student.getPrenume()));
                row.createCell(2).setCellValue(nullToEmpty(student.getNume()));
                row.createCell(3).setCellValue(nullToEmpty(student.getFormatieDeStudiu()));
            }

            try (OutputStream os = Files.newOutputStream(outFile)) {
                workbook.write(os);
            }
        }
    }

    private static String nullToEmpty(String s) {
        return s == null ? "" : s;
    }
}
