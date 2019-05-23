package com.example.vision.assignment_timetable;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
Button b;

    public static int progress;
    public ProgressBar progressBar;
    public int progressStatus = 0;
    public Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        progressBar=findViewById(R.id.progressbar);
        progress=0;
        progressBar.setMax(500);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progressStatus<500)
                {
                    progressStatus=dosomework();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(progressStatus);
                        }
                    });
                }
                if(progressStatus==500)
                {
                    Intent i= new Intent(MainActivity.this,Main2Activity.class);
                    startActivity(i);
                }

                handler.post(new Runnable() {
                    @SuppressLint("WrongConstant")
                    @Override
                    public void run() {

                        progressBar.setVisibility(50);
                    }
                });

            }
            int dosomework(){
                try{
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return ++progress;
            }
        }).start();
      /*  b=findViewById(R.id.but);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(MainActivity.this,Main2Activity.class);
                startActivity(i);
            }
        });
*/
    }

}
