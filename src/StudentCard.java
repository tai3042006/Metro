import java.io.*;
import java.util.*;

public class StudentCard {

    private String studentId;
    private String studentName;

    /**
     * Default constructor
     */
    public StudentCard() {
    }

    /**
     * Constructor đầy đủ tham số (Nên dùng cái này)
     */
    public StudentCard(String studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;
    }

    // --- Getters & Setters ---

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @Override
    public String toString() {
        return "StudentCard{" +
                "id='" + studentId + '\'' +
                ", name='" + studentName + '\'' +
                '}';
    }
}