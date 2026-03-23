package ro.ulbs.proiectaresoftware.students;

import java.util.List;
import java.util.Objects;

public class Student {
    String numarMatricol;
    String nume;
    String prenume;
    String formațieDeStudiu;

    public Student(String numarMatricol, String prenume, String nume, String formațieDeStudiu) {
        this.numarMatricol = numarMatricol;
        this.nume = nume;
        this.prenume = prenume;
        this.formațieDeStudiu = formațieDeStudiu;
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

    public String getFormațieDeStudiu() {
        return formațieDeStudiu;
    }


    @Override
    public String toString() {
        return String.format("%s %10s %10s %10s", numarMatricol, prenume, nume, formațieDeStudiu);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(nume, student.nume) && Objects.equals(prenume, student.prenume) && Objects.equals(formațieDeStudiu, student.formațieDeStudiu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nume, prenume, formațieDeStudiu);
    }
}