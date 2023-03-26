package model;

import java.util.Date;
import java.util.Objects;


public class Registering {

    private Student student;
    private Subject subject;
    private Date registedDate;

    public Registering() {
    }

    public Registering(Student student, Subject subject, Date registedDate) {
        this.student = student;
        this.subject = subject;
        this.registedDate = registedDate;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Date getRegistedDate() {
        return registedDate;
    }

    public void setRegistedDate(Date registedDate) {
        this.registedDate = registedDate;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.student);
        hash = 47 * hash + Objects.hashCode(this.subject);
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
        final Registering other = (Registering) obj;
        if (!Objects.equals(this.student, other.student)) {
            return false;
        }
        return Objects.equals(this.subject, other.subject);
    }

    

    

}
