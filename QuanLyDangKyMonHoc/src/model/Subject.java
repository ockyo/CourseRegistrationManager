package model;

import java.util.Objects;

public class Subject {

    private String id;
    private String name;
    private int numOfLesson;
    private String kind;

    public Subject() {
    }

    public Subject(String id) {
        this.id = id;
    }

    public Subject(String name, String kind, int numOfLesson) {
        this();
        this.name = name;
        this.kind = kind;
        this.numOfLesson = numOfLesson;
    }

    public Subject(String id, String name, String kind, int numOfLesson) {
        this.id = id;
        this.name = name;
        this.kind = kind;
        this.numOfLesson = numOfLesson;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public int getNumOfLesson() {
        return numOfLesson;
    }

    public void setNumOfLesson(int numOfLesson) {
        this.numOfLesson = numOfLesson;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Subject other = (Subject) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Subject{" + "id=" + id + ", name=" + name + ", kind=" + kind + ", numOfLesson=" + numOfLesson + '}';
    }
    
}
