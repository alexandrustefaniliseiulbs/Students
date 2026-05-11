package ro.ulbs.proiectaresoftware.students;

import java.io.IOException;
import java.util.List;

public interface Importer {
    List<Student> studenti_importati() throws IOException;
}
