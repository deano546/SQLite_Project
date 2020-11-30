package com.example.sqliteproject.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnAdd;
    Button btnSearch;
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
        etName = findViewById(R.id.etName);
        etYear = findViewById(R.id.etYear);
        etSearch = findViewById(R.id.etSearch);
        swEnrolled = findViewById(R.id.swCurrent);
        rvStudents = findViewById(R.id.rvStudents);

        dbhelper = new DBHelper(MainActivity.this);
        studentList = dbhelper.getallStudents();

        rvStudents.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        rvStudents.setLayoutManager(layoutManager);

        mAdapter = new RecycleViewAdapter(studentList,this);
        rvStudents.setAdapter(mAdapter);


        
        

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudentModel studentModel;

                try {
                 studentModel = new StudentModel(-1,etName.getText().toString(),Integer.parseInt(etYear.getText().toString()),swEnrolled.isChecked() );

                }
                catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    studentModel = new StudentModel(-1,"error",0,false );


                }



                boolean success = dbhelper.addRecord(studentModel);
                studentList = dbhelper.getallStudents();


                rvStudents.setHasFixedSize(true);

                layoutManager = new LinearLayoutManager(MainActivity.this);
                rvStudents.setLayoutManager(layoutManager);

                mAdapter = new RecycleViewAdapter(studentList,MainActivity.this);
                rvStudents.setAdapter(mAdapter);

                Toast.makeText(MainActivity.this, "" + success, Toast.LENGTH_SHORT).show();
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = etSearch.getText().toString();

                studentList = dbhelper.getSelectedStudents(query);

                rvStudents.setHasFixedSize(true);

                layoutManager = new LinearLayoutManager(MainActivity.this);
                rvStudents.setLayoutManager(layoutManager);

                mAdapter = new RecycleViewAdapter(studentList,MainActivity.this);
                rvStudents.setAdapter(mAdapter);




            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sortmenu,menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()) {
            case R.id.menu_aToZ:
                studentList =  dbhelper.getSortedStudents("nameAsc");
                rvStudents.setHasFixedSize(true);

                layoutManager = new LinearLayoutManager(MainActivity.this);
                rvStudents.setLayoutManager(layoutManager);

                mAdapter = new RecycleViewAdapter(studentList,MainActivity.this);
                rvStudents.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
                return true;
            case R.id.menu_zToA:
                studentList =  dbhelper.getSortedStudents("nameDesc");
                rvStudents.setHasFixedSize(true);

                layoutManager = new LinearLayoutManager(MainActivity.this);
                rvStudents.setLayoutManager(layoutManager);

                mAdapter = new RecycleViewAdapter(studentList,MainActivity.this);
                rvStudents.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
                return true;
            case R.id.menu_yearAscending:
                studentList =  dbhelper.getSortedStudents("yearAsc");
                rvStudents.setHasFixedSize(true);

                layoutManager = new LinearLayoutManager(MainActivity.this);
                rvStudents.setLayoutManager(layoutManager);

                mAdapter = new RecycleViewAdapter(studentList,MainActivity.this);
                rvStudents.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
                return true;
            case R.id.menu_yearDescending:
                studentList =  dbhelper.getSortedStudents("yearDesc");
                rvStudents.setHasFixedSize(true);

                layoutManager = new LinearLayoutManager(MainActivity.this);
                rvStudents.setLayoutManager(layoutManager);

                mAdapter = new RecycleViewAdapter(studentList,MainActivity.this);
                rvStudents.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
                return true;

        }




        return super.onOptionsItemSelected(item);
    }
}