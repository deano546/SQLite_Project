package com.example.sqliteproject.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.sqliteproject.model.StudentModel;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String STUDENT_TABLE = "STUDENT_TABLE";
    public static final String COLUMN_STUDENT_NAME = "STUDENT_NAME";
    public static final String COLUMN_YEAR_ENROLLED = "YEAR_ENROLLED";
    public static final String COLUMN_CURRENT_STUDENT = "CURRENT_STUDENT";

    public DBHelper(@Nullable Context context) {
        super(context, "student.db", null, 1);
    }

    //Generates a new table
    @Override
    public void onCreate(SQLiteDatabase db) {
    String createTableStatement = "CREATE TABLE " + STUDENT_TABLE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_STUDENT_NAME + " TEXT, " + COLUMN_YEAR_ENROLLED + " INT, " + COLUMN_CURRENT_STUDENT + " BOOL )";

    db.execSQL(createTableStatement);

    }

    //When you are changing the database version, such as adding new tables
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addRecord(StudentModel studentModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_STUDENT_NAME, studentModel.getName());
        cv.put(COLUMN_YEAR_ENROLLED, studentModel.getEnrolYear());
        cv.put(COLUMN_CURRENT_STUDENT, studentModel.isEnrolled());

        long insert = db.insert(STUDENT_TABLE, null, cv);
        if(insert == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    public boolean deleteRecord(StudentModel studentModel) {
        //If record is found, delete it and return true
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + STUDENT_TABLE + " Where ID = " + studentModel.getStudentId();

        Cursor cursor = db.rawQuery(queryString, null);


        //If there is a result, return true
        if(cursor.moveToFirst()) {
            return true;
        }
        else {
            return false;
        }


    }

    public boolean updateRecord(StudentModel studentModel) {
        //String active = studentModel.isEnrolled() == true ? "1" : "0";
        //String queryString = "UPDATE " + STUDENT_TABLE + " SET " + COLUMN_STUDENT_NAME + " " + name + " " + COLUMN_YEAR_ENROLLED + " " + year + " " + " " + COLUMN_CURRENT_STUDENT + " " + active +  " Where ID = " + id;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_STUDENT_NAME, studentModel.getName());
        cv.put(COLUMN_YEAR_ENROLLED, studentModel.getEnrolYear());
        cv.put(COLUMN_CURRENT_STUDENT, studentModel.isEnrolled());
        cv.put("ID", studentModel.getStudentId());

        long update = db.update(STUDENT_TABLE,cv,"ID =" + studentModel.getStudentId(),null);
        if(update == -1) {
            return false;
        }
        else {
            return true;
        }
    }



    public List<StudentModel> getallStudents() {
        List<StudentModel> returnList = new ArrayList<>();

        String queryString = "Select * from " + STUDENT_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        //returns a true if there are results
        if(cursor.moveToFirst()) {

            //loop through results, assign each to a new student
            do {

                int studentID = cursor.getInt(0);
                String studentName = cursor.getString(1);
                int yearEnrolled = cursor.getInt(2);
                boolean activeStudent = cursor.getInt(3) == 1 ? true: false;
                StudentModel studentModel = new StudentModel(studentID,studentName,yearEnrolled,activeStudent);
                returnList.add(studentModel);

            } while(cursor.moveToNext());

        }
        else {
            //failure. return an empty list
        }
        cursor.close();
        db.close();
        return returnList;
    }

    public List<StudentModel> getSelectedStudents(String query) {
        List<StudentModel> returnList = new ArrayList<>();

        String queryString = "Select * from " + STUDENT_TABLE + " WHERE " + COLUMN_STUDENT_NAME + " LIKE " + "'%" + query + "%'" ;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        //returns a true if there are results
        if(cursor.moveToFirst()) {

            //loop through results, assign each to a new student
            do {

                int studentID = cursor.getInt(0);
                String studentName = cursor.getString(1);
                int yearEnrolled = cursor.getInt(2);
                boolean activeStudent = cursor.getInt(3) == 1 ? true: false;
                StudentModel studentModel = new StudentModel(studentID,studentName,yearEnrolled,activeStudent);
                returnList.add(studentModel);

            } while(cursor.moveToNext());

        }
        else {
            //failure. return an empty list
        }
        cursor.close();
        db.close();
        return returnList;
    }

    public List<StudentModel> getSortedStudents(String query) {
        List<StudentModel> returnList = new ArrayList<>();

        String queryString;

        switch(query){
            case "yearDesc":
                queryString = "Select * from " + STUDENT_TABLE + " ORDER BY YEAR_ENROLLED DESC" ;
            case "yearAsc":
                queryString = "Select * from " + STUDENT_TABLE + " ORDER BY YEAR_ENROLLED ASC" ;
            case "nameAsc":
                queryString = "Select * from " + STUDENT_TABLE + " ORDER BY STUDENT_NAME ASC" ;
            case "nameDesc" :
                queryString = "Select * from " + STUDENT_TABLE + " ORDER BY STUDENT_NAME DESC" ;

                break;
            default:
                throw new IllegalStateException("Unexpected value: " + query);
        }



        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        //returns a true if there are results
        if(cursor.moveToFirst()) {

            //loop through results, assign each to a new student
            do {

                int studentID = cursor.getInt(0);
                String studentName = cursor.getString(1);
                int yearEnrolled = cursor.getInt(2);
                boolean activeStudent = cursor.getInt(3) == 1 ? true: false;
                StudentModel studentModel = new StudentModel(studentID,studentName,yearEnrolled,activeStudent);
                returnList.add(studentModel);

            } while(cursor.moveToNext());

        }
        else {
            //failure. return an empty list
        }
        cursor.close();
        db.close();
        return returnList;
    }

}
