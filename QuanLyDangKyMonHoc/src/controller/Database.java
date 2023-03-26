package controller;

import exception.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import model.Registering;
import model.Student;
import model.Subject;
import java.sql.Timestamp;
import java.util.Date;

public class Database {

    private Connection conn;

    public Database() {
        String url = "jdbc:sqlserver://;databasename=FinalProject";
        String user = "sa";
        String pass = "1904";

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Subject
    public ArrayList<Subject> readDataSubject() {
        ArrayList<Subject> subjects = new ArrayList<>();
        String sql = "SELECT * FROM SUBJECT";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Subject subject = new Subject();
                subject.setId(rs.getString("ID"));
                subject.setName(rs.getString("NAME"));
                subject.setNumOfLesson(rs.getInt("NUMOFLESSONS"));
                subject.setKind(rs.getString("KIND"));

                subjects.add(subject);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return subjects;
    }

    public int addSubject(Subject subject) {
        String sql = "INSERT INTO SUBJECT(ID, NAME, NUMOFLESSONS, KIND) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, subject.getId());
            ps.setString(2, subject.getName());
            ps.setInt(3, subject.getNumOfLesson());
            ps.setString(4, subject.getKind());

            return ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    public int removeSubject(Subject subject) {
        String sql = "DELETE SUBJECT WHERE ID = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, subject.getId());
            return ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    // Student
    public ArrayList<Student> readDataStudent() {
        ArrayList<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM STUDENT";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Student student = new Student();
                student.setStudentId(rs.getString("STUDENTID"));
                student.setId(rs.getString("CCCD"));
                student.setAddress(rs.getString("ADDRESS"));
                student.setEmail(rs.getString("EMAIL"));
                student.setPhoneNumber(rs.getString("PHONENUMBER"));
                student.setFullName(rs.getString("FULLNAME"));
                student.setDob(rs.getString("DATEOFBIRTH"));
                student.setStudentClass(rs.getString("CLASS"));
                student.setMajor(rs.getString("MAJOR"));
                student.setSchoolYear(rs.getString("SCHOOLYEAR"));

                students.add(student);

            }
        } catch (SQLException | InvalidEmailException | InvalidPhoneNumberException
                | InvalidNameException | InvalidDateOfBirthException | InvalidPersonIdException
                | InvalidStudentIdException ex) {
            ex.printStackTrace();
        }

        return students;
    }

    public int addStudent(Student student) {
        String sql = "INSERT INTO STUDENT(STUDENTID, CCCD, FULLNAME, DATEOFBIRTH, ADDRESS, "
                + "EMAIL, PHONENUMBER, CLASS, MAJOR, SCHOOLYEAR) "
                + "VALUES(?,?,?,?,?,?,?,?,?,?)";

        var format = "dd/MM/yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, student.getStudentId());
            ps.setString(2, student.getId());
            ps.setString(3, student.getFullName());
            ps.setString(4, dateFormat.format(student.getDob()));
            ps.setString(5, student.getAddress());
            ps.setString(6, student.getEmail());
            ps.setString(7, student.getPhoneNumber());
            ps.setString(8, student.getStudentClass());
            ps.setString(9, student.getMajor());
            ps.setString(10, student.getSchoolYear());

            return ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    public int removeStudent(Student student) {
        String sql = "DELETE STUDENT WHERE STUDENTID = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, student.getStudentId());
            return ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    // Registering
    public ArrayList<Registering> readDataRegistering() {
        ArrayList<Student> students = this.readDataStudent();
        ArrayList<Subject> subjects = this.readDataSubject();

        ArrayList<Registering> registerings = new ArrayList<>();
        String sql = "SELECT * FROM REGISTERING";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                // Chuyển đổi từ dạng datetime trong sql sang dạng date trong java
                Date date = new Date(rs.getTimestamp("REGISTER_DATE").getTime());

                Registering registering = new Registering();
                Student student = new Student();
                Subject subject = new Subject();

                for (var item : students) {
                    if (item.getStudentId().compareTo(rs.getString("STUDENT_ID")) == 0) {
                        student = item;
                        break;
                    }
                }

                for (var item : subjects) {
                    if (item.getId().compareTo(rs.getString("SUBJECT_ID")) == 0) {
                        subject = item;
                        break;
                    }
                }

                registering.setStudent(student);
                registering.setSubject(subject);
                registering.setRegistedDate(date);

                registerings.add(registering);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return registerings;
    }

    public int addRegistering(Registering r) {
        String sql = "INSERT INTO REGISTERING(STUDENT_ID, SUBJECT_ID, REGISTER_DATE) VALUES (?, ?, ?)";

        var studentID = r.getStudent().getStudentId();
        var subjectID = r.getSubject().getId();
        // Chuyển đổi từ dạng date trong java sang datetime trong sql
        Timestamp timestamp = new Timestamp(r.getRegistedDate().getTime());

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, studentID);
            ps.setString(2, subjectID);
            ps.setTimestamp(3, timestamp);

            return ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    public int removeRegistering(Registering r) {
        String sql = "DELETE REGISTERING WHERE STUDENT_ID = ? AND SUBJECT_ID = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, r.getStudent().getStudentId());
            ps.setString(2, r.getSubject().getId());
            return ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }
}
