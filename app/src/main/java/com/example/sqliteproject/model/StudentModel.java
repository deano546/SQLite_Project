package com.example.sqliteproject.model;

public class StudentModel {



    private int studentId;
    private String name;
    private int enrolYear;
    private boolean isEnrolled;


    public StudentModel(int studentId, String name, int enrolYear, boolean isEnrolled) {
        this.setStudentId(studentId);
        this.setName(name);
        this.setEnrolYear(enrolYear);
        this.setEnrolled(isEnrolled);
    }

    public StudentModel() {

    }

    @Override
    public String toString() {
        return "StudentModel{" +
                "studentId=" + studentId +
                ", name='" + name + '\'' +
                ", enrolYear=" + enrolYear +
                ", isEnrolled=" + isEnrolled +
                '}';
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEnrolYear() {
        return enrolYear;
    }

    public void setEnrolYear(int enrolYear) {
        this.enrolYear = enrolYear;
    }

    public boolean isEnrolled() {
        return isEnrolled;
    }

    public void setEnrolled(boolean enrolled) {
        isEnrolled = enrolled;
    }
}
