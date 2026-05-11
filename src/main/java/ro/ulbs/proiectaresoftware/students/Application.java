package ro.ulbs.proiectaresoftware.students;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Application {
    public static void main(String[] args) throws IOException {


        List<Student> studenti = creareLista();
        export(studenti,getExporter("studenti_.xlsx"));
        export(studenti,getExporter("studenti_.csv"));

        Set<Student> set = new HashSet<>(studenti);

        afisareLista(studenti);
        System.out.println(prezent(new Student("1752", "MIHAI", "IONESCU",
                "C22/2"), set));

        System.out.println("Dupa sortare dupa nume:");
       sortareDupaNume(studenti);
       afisareLista(studenti);

       System.out.println("Dupa sortare dupa grupa, nume, prenume:");
       sortareDupaGrupaNumeSiPrenume(studenti);
       afisareLista(studenti);

       Map<String,Integer> note=citireNote();
       System.out.println("Nota studentului cautat este: "+getNota(studenti.get(2), citireNote()));

    }

    private static List<Student> creareLista() throws IOException {
        Student s1 = new Student("111", "Ion Ionel", "Ionut", "C22/1");
        Student s2 = new Student("112", "Maria", "Oprea", "TI21/1");
        Student s3 = new Student("120", "Alis", "Popa", "TI21/2");
        Student s4 = new Student("122", "Mihai", "Vecerdea", "TI22/1");
        Student s5 = new Student("122", "Eugen", "Uritescu", "TI22/2");

        List<Student> studenti = new ArrayList<>();
        studenti.add(s1);
        studenti.add(s2);
        studenti.add(s3);
        studenti.add(s4);
        studenti.add(s5);

        studenti.addAll(citireFisier());

        return studenti;
    }

    public static boolean prezent(Student student, Set<Student> studenti) {
//        for (Student student_it : studenti) {
//            if ((student_it.getNume().equals(student.getNume()) && student_it.getPrenume().equals(student.getPrenume())
//                    && student_it.getFormațieDeStudiu().equals(student.getFormațieDeStudiu())) ||
//                    student_it.getNumarMatricol().equals(student.getNumarMatricol())) {
//                return true;
//            }
//        }
//        return false;

        return studenti.contains(student);
    }

    public static void afisareLista(List<Student> studenti) {
        for (Student s : studenti) {
            System.out.println(s);
        }
    }

    public static void sortareDupaNume(List<Student> studenti) {
        Collections.sort(studenti, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return s1.nume.compareTo(s2.nume);
            }});
    }
    
    public static void sortareDupaGrupaNumeSiPrenume(List<Student> studenti) {
        Collections.sort(studenti, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
               if(s1.formatieDeStudiu.equals(s2.formatieDeStudiu)) {
                   if(s1.nume.equals(s2.nume)) {
                       return s1.prenume.compareTo(s2.prenume);
                   }
                   else {
                       return s1.nume.compareTo(s2.nume);
                   }
               }
               else {
                   return s1.formatieDeStudiu.compareTo(s2.formatieDeStudiu);
               }
            }
        });
    }

    public static List<Student> citireFisier() throws IOException {
        List<Student> studenti2 = new ArrayList<>();
        List<String> lines = Files.readAllLines(Paths.get("studenti.csv"));
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length == 4) {
                studenti2.add(new Student(parts[0], parts[1], parts[2], parts[3]));
            }
        }
        return studenti2;
    }

    public static Map<String,Integer> citireNote() throws IOException{
        Map<String,Integer> note2 = new HashMap<>();
        List<String>lines=Files.readAllLines(Paths.get("note.csv"));
        for(String line:lines) {
            String[] parts = line.split(",");
            if(parts.length==2) {
                note2.put(parts[0], Integer.parseInt(parts[1]));
            }
        }

        return note2;
    }

    public static int getNota(Student student, Map<String,Integer>note) throws IOException {
        note = citireNote();

        if (note.containsKey(student.getNumarMatricol())) {
            return note.get(student.getNumarMatricol());
        }
        else {
            throw new IllegalArgumentException("Studentul nu are nota sau nu este in lista");
        }
    }

    public static Map<Student,Integer> mapareNote(Map<String,Integer>note, List<Student>lista){
        Map<Student,Integer>noteMapate=new HashMap<Student,Integer>();
        for(Student student:lista) {
            if(note.containsKey(student.getNumarMatricol())) {
                noteMapate.put(student, note.get(student.getNumarMatricol()));
            }
        }
        return noteMapate;

    }

    public static void exportToExcel(List<Student> lista, Path outFile) throws IOException {
        try(XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet sheet = workbook.createSheet("Studenti");

            int rowNum = 0;
            Row header = sheet.createRow(rowNum++);
            header.createCell(0).setCellValue("Numar matricol");
            header.createCell(1).setCellValue("Prenume");
            header.createCell(2).setCellValue("Nume");
            header.createCell(3).setCellValue("Formatie de studiu");

            for(Student student:lista) {
            Row row = sheet.createRow(rowNum++);
            Cell c0 =row.createCell(0);
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

    public static void exportToCsv(List<Student> studenti, Path outFile) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(outFile, StandardCharsets.UTF_8)) {
            // antet
            writer.write("numarMatricol,prenume,nume,formatieDeStudiu");
            writer.newLine();
            for (Student s : studenti) {
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
        String escaped = field.replace("\"", "\"\""); // dublăm ghilimelele
        if (needQuotes) {
            return "\"" + escaped + "\"";
        } else {
            return escaped;
        }
    }

    private static String nullToEmpty(String s) {
        return s == null ? "" : s;
    }

    public static Exporter getExporter(String filename){
    String fileExtension= filename.substring(filename.lastIndexOf(".")+1);

    switch (fileExtension){
        case "csv":
            return new ExportToCSV(filename);
        case "xlsx":
            return new ExportToExcel(filename);
        default:
            throw new IllegalArgumentException("Unkown file extension: "+fileExtension);
    }
    }

    public static void export(List<Student> list,Exporter exporter) throws IOException {
        exporter.export(list);
    }


//    public static void/*a se schimba din void in Importer*/getImporter(String... args){
//       String fileExtension= args[0].substring(args[0].lastIndexOf(".")+1);
//        switch (args[0]){
//            case "csv":
//                //return new ImportFromCSV(args[1]);
//            case "xlsx":
//                //return new ImportFromExcel(args[1]);
//        }
//    };


    public static void commentsMain() {
        //linia 19       Student s1 = new Student(null, "KEN", "MARCU", "C22/1");
//        Student s2 = new Student("112", "Maria", "Oprea", "TI21/1");
//        Student s3 = new Student("120", "Alis", "Popa", "TI21/2");
//        Student s4 = new Student("122", "Mihai", "Vecerdea", "TI22/1");
//        Student s5 = new Student("122", "Eugen", "Uritescu", "TI22/2");

//        System.out.println(s1);
//        System.out.println(s2);
//        System.out.println(s3);
//        System.out.println(s4);
// 29       System.out.println(s5);

        //25        try {
//            exportToCsv(studenti, Paths.get("studenti_export.csv"));
//            exportToExcel(studenti, Paths.get("studenti_export.xlsx"));
//        } catch (IOException e) {
//            e.printStackTrace(); // sau logging, sau aruncă mai departe
//  30        }
    }
}
