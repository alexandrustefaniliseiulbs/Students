package ro.ulbs.proiectaresoftware.students;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Application {
    public static void main(String[] args) {
        Student s1 = new Student("112", "Ioan", "Popa", "C22/1");
        Student s2 = new Student("112", "Maria", "Oprea", "TI21/1");
        Student s3 = new Student("120", "Alis", "Popa", "TI21/2");
        Student s4 = new Student("122", "Mihai", "Vecerdea", "TI22/1");
        Student s5 = new Student("122", "Eugen", "Uritescu", "TI22/2");

//        System.out.println(s1);
//        System.out.println(s2);
//        System.out.println(s3);
//        System.out.println(s4);
//        System.out.println(s5);

        List<Student> studenti = new ArrayList<>();
//        System.out.println("Lista contine " + studenti.size() + " studenti.");
        studenti.add(s1);
        studenti.add(s2);
        studenti.add(s3);
        studenti.add(s4);
        studenti.add(s5);

        Set<Student> set = new HashSet<>(studenti);

//        System.out.println("Lista contine "+studenti.size()+" studenti.");

        Afisare(studenti);
        System.out.println(prezent(new Student(null, "Ioan", "Popa", "C22/1"), set));


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

    private static void Afisare(List<Student> studenti) {
        for (Student s : studenti) {
            System.out.println(s);
        }
    }

}
