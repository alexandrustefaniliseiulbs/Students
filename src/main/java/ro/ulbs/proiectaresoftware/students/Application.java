package ro.ulbs.proiectaresoftware.students;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Application {
    public static void main(String[] args) throws IOException {
 //       Student s1 = new Student(null, "KEN", "MARCU", "C22/1");
//        Student s2 = new Student("112", "Maria", "Oprea", "TI21/1");
//        Student s3 = new Student("120", "Alis", "Popa", "TI21/2");
//        Student s4 = new Student("122", "Mihai", "Vecerdea", "TI22/1");
//        Student s5 = new Student("122", "Eugen", "Uritescu", "TI22/2");

//        System.out.println(s1);
//        System.out.println(s2);
//        System.out.println(s3);
//        System.out.println(s4);
//        System.out.println(s5);

        List<Student> studenti = creareLista();

        Set<Student> set = new HashSet<>(studenti);

//        System.out.println("Lista contine "+studenti.size()+" studenti.");

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

}
