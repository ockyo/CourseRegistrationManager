package controller.sort;

import java.util.Comparator;
import model.Student;

public class SortStudentByIdASC implements Comparator<Student> {

    @Override
    public int compare(Student o1, Student o2) {
        return o1.getStudentId().compareTo(o2.getStudentId());
    }
    
}
