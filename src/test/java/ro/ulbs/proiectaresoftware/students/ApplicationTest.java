package ro.ulbs.proiectaresoftware.students;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static ro.ulbs.proiectaresoftware.students.Application.*;

class ApplicationTest {

    @Test
    void getNota_cuNrMatricol() throws IOException {
        //Arrange
        Student student=new Student("120", "Alis", "Popa", "TI21/2");
        Map<String,Integer> note= new HashMap<>();

        note.put(student.numarMatricol, 9);

        //Act
        int nota=getNota(student,note);

        //Assert
        assertEquals(9,nota);
    }

    @Test
    void getNota_faraNrMatricol() throws IOException {
        //Arrange
        Student student=new Student("120", "Alis", "Popa", "TI21/2");
        Student student2=new Student(null, "Ion", "Ionut-Ionel", "IT51/3");
        Map<String,Integer> note= new HashMap<>();

        note.put(student2.numarMatricol, 9);

        //Act+Assert
        assertThrows(IllegalArgumentException.class,()->{
            getNota(student2,note);
        });
    }

    @Test
    void prezent_test() {
        //Arrange
        Student student=new Student("120", "Alis", "Popa", "TI21/2");
        Set<Student> studenti=new HashSet<>();
        studenti.add(student);

        //Act
        prezent(student, studenti);
        //Assert
        assertEquals(true,prezent(student, studenti));
    }

    @Test
    void sortareDupaGrupaNumeSiPrenume_test() {

        //Arrange
        List<Student> studenti = new ArrayList<>();
        studenti.add(new Student("1", "Ion", "Popa", "TI22/2"));
        studenti.add(new Student("2", "Ana", "Popa", "TI21/1"));
        studenti.add(new Student("3", "Mihai", "Ionescu", "TI21/1"));
        studenti.add(new Student("4", "Andrei", "Ionescu", "TI21/1"));
        studenti.add(new Student("5", "Zoe", "Aaa", "C22/1"));

        //Act
        sortareDupaGrupaNumeSiPrenume(studenti);

        //Assert
        assertEquals("C22/1", studenti.get(0).getFormatieDeStudiu());
        assertEquals("Aaa", studenti.get(0).getNume());

        assertEquals("TI21/1", studenti.get(1).getFormatieDeStudiu());
        assertEquals("Ionescu", studenti.get(1).getNume());
        assertEquals("Andrei", studenti.get(1).getPrenume());

        assertEquals("TI21/1", studenti.get(2).getFormatieDeStudiu());
        assertEquals("Ionescu", studenti.get(2).getNume());
        assertEquals("Mihai", studenti.get(2).getPrenume());

        assertEquals("TI21/1", studenti.get(3).getFormatieDeStudiu());
        assertEquals("Popa", studenti.get(3).getNume());
        assertEquals("Ana", studenti.get(3).getPrenume());

        assertEquals("TI22/2", studenti.get(4).getFormatieDeStudiu());
        assertEquals("Popa", studenti.get(4).getNume());
        assertEquals("Ion", studenti.get(4).getPrenume());

    }



}