package model;

import java.util.ArrayList;
import java.util.List;


public class Course {

    private String name;
    private List<Registering> registerings;

    public Course() {
        name = "";
        registerings = new ArrayList<>();
    }

    public Course(String name, List<Registering> registerings) {
        this.name = name;
        this.registerings = registerings;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Registering> getRegisterings() {
        return registerings;
    }

    public void setRegisterings(List<Registering> registerings) {
        this.registerings = registerings;
    }

    public void addToCourse(Registering r) {
        if (r != null) {
            registerings.add(r);
        }
    }
}
