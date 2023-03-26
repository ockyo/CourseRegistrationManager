package controller.sort;

import java.util.Comparator;
import model.Registering;


public class SortRegisteringByRegisterTimeLE implements Comparator<Registering> {

    @Override
    public int compare(Registering o1, Registering o2) {
        return (int) (o2.getRegistedDate().getTime()
                - o1.getRegistedDate().getTime());
    }

}
