package com.example.sqliteproject.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

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
    Switch swEditActive;
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

        dbhelper = new DBHelper(EditActivity.this);
        studentList = dbhelper.getallStudents();

        Intent intent = getIntent();
        id = intent.getIntExtra("id", -1);

        if(id >= 0) {
            for (StudentModel s: studentList
                 ) {if (s.getStudentId() == id) {
                     student = s;
            }

            }
        }

        editName.setText(student.getName());
        editYear.setText(String.valueOf(student.getEnrolYear()));
        swEditActive.setChecked(student.isEnrolled());
        tvStudentID.setText(String.valueOf(student.getStudentId()));

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                student.setName(editName.getText().toString());
                student.setEnrolYear(Integer.parseInt(editYear.getText().toString()));
                student.setEnrolled(swEditActive.isChecked());

                dbhelper.updateRecord(student);

                Intent intent = new Intent(EditActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbhelper.deleteRecord(student);

                Intent intent = new Intent(EditActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}