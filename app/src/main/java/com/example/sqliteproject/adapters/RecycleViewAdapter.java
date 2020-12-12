package com.example.sqliteproject.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqliteproject.R;
import com.example.sqliteproject.model.StudentModel;
import com.example.sqliteproject.ui.EditActivity;

import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder> {


    //code adapted from https://www.youtube.com/watch?v=FFCpjZkqfb0
    List<StudentModel> studentList;
    Context context;

    public RecycleViewAdapter(List<StudentModel> studentList, Context context) {
        this.studentList = studentList;
        this.context = context;
    }

    //Declare parent layout so we can use a click listener
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvIDDetail;
        TextView tvNameDetail;
        TextView tvYearDetail;
        TextView tvActiveDetail;
        ConstraintLayout parentLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvIDDetail = itemView.findViewById(R.id.tvIDDetail);
            tvNameDetail = itemView.findViewById(R.id.tvNameDetail);
            tvYearDetail = itemView.findViewById(R.id.tvYearDetail);
            tvActiveDetail = itemView.findViewById(R.id.tvActiveDetail);
            parentLayout = itemView.findViewById(R.id.onerow);
        }
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }


    //Setting values based on the position in the list
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tvIDDetail.setText(String.valueOf(studentList.get(position).getStudentId()));
        holder.tvNameDetail.setText(studentList.get(position).getName());
        holder.tvYearDetail.setText(String.valueOf(studentList.get(position).getEnrolYear()));
        holder.tvActiveDetail.setText(studentList.get(position).isEnrolled() == true ? "Yes":"No");
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send user to EditActivity, pass the ID so the appropriate record can be retrieved and displayed
                Intent intent = new Intent(context, EditActivity.class);
                intent.putExtra("id", studentList.get(position).getStudentId());
                context.startActivity(intent);
            }
        });




    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }
}
