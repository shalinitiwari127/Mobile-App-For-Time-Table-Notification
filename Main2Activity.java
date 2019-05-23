package com.example.vision.assignment_timetable;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
Button b1,b2,b3;
    DBadapter db = new DBadapter(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main2);
        b1=findViewById(R.id.but1);
        b2=findViewById(R.id.but2);
        b3=findViewById(R.id.but3);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(Main2Activity.this,Timetable.class);
                startActivity(i);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               db.open();
               db.deleteALLTimetable();
               db.close();
               DisplayReset();

            //  Toast.makeText(this,"Previous data is cleared.... TO SET NEW DATA CLICK ON SET BUTTON",Toast.LENGTH_SHORT).show();
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                db.open();
        Cursor c=db.getAllTimetable();
        if(c.moveToFirst()){
            do{
                DisplayTimetable(c);
            }while(c.moveToNext());
        }
        db.close();
    }
    });
    }
    public void DisplayTimetable(Cursor c){

        Toast.makeText(this,"days:"+c.getString(0)+"\n"+"p1:"+c.getString(1)+"\n"+"p2:"+c.getString(2)+"\n"+"p3:"+c.getString(3)+"\n"+"p4:"+c.getString(4)+"\n"+"p5:"+c.getString(5)+"\n"+"p6:"+c.getString(6),Toast.LENGTH_SHORT).show();
    }
    public void DisplayReset() {
        Toast.makeText(this,"Previous data is cleared.... TO SET NEW DATA CLICK ON SET BUTTON",Toast.LENGTH_SHORT).show();
    }

}