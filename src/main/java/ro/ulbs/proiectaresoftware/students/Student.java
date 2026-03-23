package ro.ulbs.proiectaresoftware.students;

import java.util.List;
import java.util.Objects;

public class Student implements Comparable<Student>{
    String numarMatricol;
    String nume;
    String prenume;
    String formatieDeStudiu;

    public Student(String numarMatricol, String prenume, String nume, String formatieDeStudiu) {
        this.numarMatricol = numarMatricol;
        this.nume = nume;
        this.prenume = prenume;
        this.formatieDeStudiu = formatieDeStudiu;
    }

    public String getNumarMatricol() {
        return numarMatricol;
    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public String getFormatieDeStudiu() {
        return formatieDeStudiu;
    }


    @Override
    public String toString() {
        return String.format("%s %10s %10s %10s", numarMatricol, prenume, nume, formatieDeStudiu);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(nume, student.nume) && Objects.equals(prenume, student.prenume) && Objects.equals(formatieDeStudiu, student.formatieDeStudiu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nume, prenume, formatieDeStudiu);
    }
    @Override
    public int compareTo(Student student) {
        return this.nume.compareTo(student.nume);
    }
}