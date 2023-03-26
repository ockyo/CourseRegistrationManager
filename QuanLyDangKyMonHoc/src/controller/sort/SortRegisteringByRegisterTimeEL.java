package controller.sort;

import java.util.Comparator;
import model.Registering;


public class SortRegisteringByRegisterTimeEL implements Comparator<Registering> {

    @Override
    public int compare(Registering o1, Registering o2) {
        return (int) (o1.getRegistedDate().getTime()
                - o2.getRegistedDate().getTime());
    }

}
