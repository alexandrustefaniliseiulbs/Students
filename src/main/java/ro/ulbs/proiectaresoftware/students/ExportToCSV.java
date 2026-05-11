package ro.ulbs.proiectaresoftware.students;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ExportToCSV implements Exporter {

    final String filename;

    public ExportToCSV(String filename) {
        this.filename = filename;
    }

    @Override
    public void export(List<Student> lista) throws IOException {
        Path outFile = Paths.get(filename);

        try (BufferedWriter writer = Files.newBufferedWriter(outFile, StandardCharsets.UTF_8)) {
            // antet
            writer.write("numarMatricol,prenume,nume,formatieDeStudiu");
            writer.newLine();
            for (Student s : lista) {
                writer.write(csvEscape(s.getNumarMatricol()));
                writer.write(',');
                writer.write(csvEscape(s.getPrenume()));
                writer.write(',');
                writer.write(csvEscape(s.getNume()));
                writer.write(',');
                writer.write(csvEscape(s.getFormatieDeStudiu()));
                writer.newLine();
            }
        }
    }

    private static String csvEscape(String field) {
        if (field == null) return "";
        boolean needQuotes = field.contains(",") || field.contains("\"") || field.contains("\n") || field.contains("\r");
        String escaped = field.replace("\"", "\"\"");
        if (needQuotes) {
            return "\"" + escaped + "\"";
        } else {
            return escaped;
        }
    }
}
