package com.example.sqliteproject.model;

import java.util.Comparator;

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

    //These comparators allow sorting of the list of students. adapted from https://www.youtube.com/watch?v=FFCpjZkqfb0
public static Comparator<StudentModel> StudentAZComparator = new Comparator<StudentModel>() {
    @Override
    public int compare(StudentModel s1, StudentModel s2) {
        return s1.getName().compareTo(s2.getName());
    }
};

    public static Comparator<StudentModel> StudentZAComparator = new Comparator<StudentModel>() {
        @Override
        public int compare(StudentModel s1, StudentModel s2) {
            return s2.getName().compareTo(s1.getName());
        }
    };

    public static Comparator<StudentModel> StudentYearAscComparator = new Comparator<StudentModel>() {
        @Override
        public int compare(StudentModel s1, StudentModel s2) {
            return s1.getEnrolYear() - s2.getEnrolYear();
        }
    };

    public static Comparator<StudentModel> StudentYearDescComparator = new Comparator<StudentModel>() {
        @Override
        public int compare(StudentModel s1, StudentModel s2) {
            return s2.getEnrolYear() - s1.getEnrolYear();
        }
    };


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
