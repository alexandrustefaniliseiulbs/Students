package ro.ulbs.proiectaresoftware.students;

public class Student {
    int numarMatricol;
    String nume;
    String prenume;
    String formațieDeStudiu;

    public Student(int numarMatricol, String nume, String prenume, String formațieDeStudiu) {
        this.numarMatricol = numarMatricol;
        this.nume = nume;
        this.prenume = prenume;
        this.formațieDeStudiu = formațieDeStudiu;
    }

    public int getNumarMatricol() {
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
        return String.format("%05d %10s %10s %10s",numarMatricol,prenume,nume,formațieDeStudiu);
    }
}
