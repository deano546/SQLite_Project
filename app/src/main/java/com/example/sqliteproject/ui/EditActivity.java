package com.example.sqliteproject.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sqliteproject.R;
import com.example.sqliteproject.db.DBHelper;
import com.example.sqliteproject.model.StudentModel;

import java.util.ArrayList;
import java.util.List;

public class EditActivity extends AppCompatActivity {

    Button btnUpdate;
    Button btnDelete;
    Button btnCancel;
    EditText editName;
    EditText editYear;
    TextView tvStudentID;
    SwitchCompat swEditActive;
    DBHelper dbhelper;
    StudentModel student;
    List<StudentModel> studentList = new ArrayList<StudentModel>();
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);
        editName = findViewById(R.id.etEditName);
        editYear = findViewById(R.id.etEditYear);
        tvStudentID = findViewById(R.id.tvstudentID);
        swEditActive = findViewById(R.id.swEditActive);

        //Connect to database, populate list
        dbhelper = new DBHelper(EditActivity.this);
        studentList = dbhelper.getallStudents();


        //Getting the ID that the main activity passed
        Intent intent = getIntent();
        id = intent.getIntExtra("id", -1);

        //For each loop to find the correct student based on ID
        if(id >= 0) {
            for (StudentModel s: studentList
                 ) {if (s.getStudentId() == id) {
                     student = s;
            }}}

        //Setting the values
        editName.setText(student.getName());
        editYear.setText(String.valueOf(student.getEnrolYear()));
        swEditActive.setChecked(student.isEnrolled());
        tvStudentID.setText(String.valueOf(student.getStudentId()));

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editName.getText().toString().matches("") || (editYear.getText().toString().matches("")))  {
                    Toast.makeText(EditActivity.this, "Please ensure all fields are filled", Toast.LENGTH_SHORT).show();
                }

                else {
                    //Update the record based on User Inputs
                    student.setName(editName.getText().toString());
                    student.setEnrolYear(Integer.parseInt(editYear.getText().toString()));
                    student.setEnrolled(swEditActive.isChecked());
                    dbhelper.updateRecord(student);
                    Intent intent = new Intent(EditActivity.this, MainActivity.class);
                    startActivity(intent);
                } }});

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Delete the record
                dbhelper.deleteRecord(student);
                Toast.makeText(EditActivity.this, "Record Deleted", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(EditActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Return to main activity
                Intent intent = new Intent(EditActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}