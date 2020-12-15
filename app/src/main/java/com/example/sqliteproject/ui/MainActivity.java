package com.example.sqliteproject.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.sqliteproject.R;
import com.example.sqliteproject.adapters.RecycleViewAdapter;
import com.example.sqliteproject.db.DBHelper;
import com.example.sqliteproject.model.StudentModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnAdd;
    Button btnSearch;
    FloatingActionButton btnPref;
    EditText etName;
    EditText etYear;
    EditText etSearch;
    Switch swEnrolled;
    RecyclerView rvStudents;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager layoutManager;
    DBHelper dbhelper;
    Menu menu;
    List<StudentModel> studentList = new ArrayList<StudentModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btnAdd);
        btnSearch = findViewById(R.id.btnSearch);
        btnPref = findViewById(R.id.btnCamera);
        etName = findViewById(R.id.etName);
        etYear = findViewById(R.id.etYear);
        etSearch = findViewById(R.id.etSearch);
        swEnrolled = findViewById(R.id.swCurrent);
        rvStudents = findViewById(R.id.rvStudents);

        //Connecting to database, populate list
        dbhelper = new DBHelper(MainActivity.this);
        studentList = dbhelper.getallStudents();

        //Displaying the recycler
        rvStudents.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rvStudents.setLayoutManager(layoutManager);
        mAdapter = new RecycleViewAdapter(studentList,this);
        rvStudents.setAdapter(mAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudentModel studentModel;
                //Checking fields are not empty
                if ((etName.getText().toString().matches("")) || (etYear.getText().toString().matches(""))) {
                    Toast.makeText(MainActivity.this, "Please ensure Name & Year are not empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    try {
                        studentModel = new StudentModel(-1,etName.getText().toString(),Integer.parseInt(etYear.getText().toString()),swEnrolled.isChecked() );
                    }
                    catch (Exception e) {
                        Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        studentModel = new StudentModel(-1,"error",0,false );
                    }
                    boolean success = dbhelper.addRecord(studentModel);
                    Toast.makeText(MainActivity.this, "Record Added", Toast.LENGTH_SHORT).show();

                    //Displaying the new list, there are probably more code efficient ways to do it but this was the only way I got it to work
                    studentList = dbhelper.getallStudents();
                    rvStudents.setHasFixedSize(true);
                    layoutManager = new LinearLayoutManager(MainActivity.this);
                    rvStudents.setLayoutManager(layoutManager);
                    mAdapter = new RecycleViewAdapter(studentList,MainActivity.this);
                    rvStudents.setAdapter(mAdapter);

                }
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (etSearch.getText().toString().matches("")) {
                    Toast.makeText(MainActivity.this, "Please enter a search", Toast.LENGTH_SHORT).show();
                }
                else {
                    //Pass query to the search function, then display it
                    String query = etSearch.getText().toString();
                    studentList = dbhelper.getSelectedStudents(query);
                    rvStudents.setHasFixedSize(true);
                    layoutManager = new LinearLayoutManager(MainActivity.this);
                    rvStudents.setLayoutManager(layoutManager);
                    mAdapter = new RecycleViewAdapter(studentList,MainActivity.this);
                    rvStudents.setAdapter(mAdapter);
                }
            }
        });

        btnPref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Open Splash Screen Preference Activity
                Intent intent = new Intent(MainActivity.this,PreferenceActivity.class);
                startActivity(intent);
            }
        });

    }


    //Code for menus and sorting is adapted from https://www.youtube.com/watch?v=FFCpjZkqfb0
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sortmenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //Use comparator to sort the list, then display it

        switch(item.getItemId()) {
            case R.id.menu_aToZ:
                Collections.sort(studentList, StudentModel.StudentAZComparator);


               rvStudents.setHasFixedSize(true);
               layoutManager = new LinearLayoutManager(MainActivity.this);
               rvStudents.setLayoutManager(layoutManager);
               mAdapter = new RecycleViewAdapter(studentList,MainActivity.this);
               rvStudents.setAdapter(mAdapter);
               mAdapter.notifyDataSetChanged();
                return true;
            case R.id.menu_zToA:
                Collections.sort(studentList, StudentModel.StudentZAComparator);

                rvStudents.setHasFixedSize(true);
                layoutManager = new LinearLayoutManager(MainActivity.this);
                rvStudents.setLayoutManager(layoutManager);
                mAdapter = new RecycleViewAdapter(studentList,MainActivity.this);
                rvStudents.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
                return true;
            case R.id.menu_yearAscending:
                Collections.sort(studentList, StudentModel.StudentYearAscComparator);

                rvStudents.setHasFixedSize(true);
                layoutManager = new LinearLayoutManager(MainActivity.this);
                rvStudents.setLayoutManager(layoutManager);
                mAdapter = new RecycleViewAdapter(studentList,MainActivity.this);
                rvStudents.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
                return true;
            case R.id.menu_yearDescending:
                Collections.sort(studentList, StudentModel.StudentYearDescComparator);

                rvStudents.setHasFixedSize(true);
                layoutManager = new LinearLayoutManager(MainActivity.this);
                rvStudents.setLayoutManager(layoutManager);
                mAdapter = new RecycleViewAdapter(studentList,MainActivity.this);
                rvStudents.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
                return true;
                //Added this option to reset the search
            case R.id.menu_resetSearch:
                studentList = dbhelper.getallStudents();
                rvStudents.setHasFixedSize(true);
                layoutManager = new LinearLayoutManager(MainActivity.this);
                rvStudents.setLayoutManager(layoutManager);
                mAdapter = new RecycleViewAdapter(studentList,MainActivity.this);
                rvStudents.setAdapter(mAdapter);


        }




        return super.onOptionsItemSelected(item);
    }
}