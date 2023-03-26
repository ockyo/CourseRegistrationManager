package controller.sort;

import java.util.Comparator;
import model.Registering;


public class SortRegisteringByStudentNameZA implements Comparator<Registering> {

    @Override
    public int compare(Registering o1, Registering o2) {
        return o2.getStudent().getFirstName()
                .compareTo(o1.getStudent().getFirstName());
    }

}
