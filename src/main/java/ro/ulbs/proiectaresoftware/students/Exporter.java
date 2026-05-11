package ro.ulbs.proiectaresoftware.students;

import java.io.IOException;
import java.util.List;

public interface Exporter {

    void export(List<Student> lista) throws IOException;


}
