package com.example.vision.assignment_timetable;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import android.os.Handler;

public class Timetable extends AppCompatActivity {
    Button b;
    static int p = 0;
    static String dayy;
    static String days;

    EditText et1, et2, et3, et4, et5, et6, dy1, et7, et8, et9, et10, et11, et12, dy2, et13, et14, et15, et16, et17, et18, dy3, et19, et20, et21, et22, et23, et24, dy4, et25, et26, et27, et28, et29, et30, dy5, et31, et32, et33, et34, et35, et36, dy6;
    DBadapter db = new DBadapter(this);
    static int tim;
    static Timer timer;
    static     Timer timer1;
    TimerTask timerTask;
    TimerTask timerTask1;
    String strDate;
    int notificationID = 1;
    //we are going to use a handler to be able to run in our TimerTask
    final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_timetable);
        et1 = findViewById(R.id.pr1);
        et2 = findViewById(R.id.pr2);
        et3 = findViewById(R.id.pr3);
        et4 = findViewById(R.id.pr4);
        et5 = findViewById(R.id.pr5);
        et6 = findViewById(R.id.pr6);
        dy1 = findViewById(R.id.d1);
        b = findViewById(R.id.bt);
        et7 = findViewById(R.id.pr7);
        et8 = findViewById(R.id.pr8);
        et9 = findViewById(R.id.pr9);
        et10 = findViewById(R.id.pr10);
        et11 = findViewById(R.id.pr11);
        et12 = findViewById(R.id.pr12);
        dy2 = findViewById(R.id.d2);
        et13 = findViewById(R.id.pr13);
        et14 = findViewById(R.id.pr14);
        et15 = findViewById(R.id.pr15);
        et16 = findViewById(R.id.pr16);
        et17 = findViewById(R.id.pr17);
        et18 = findViewById(R.id.pr18);
        dy3 = findViewById(R.id.d3);
        et19 = findViewById(R.id.pr19);
        et20 = findViewById(R.id.pr20);
        et21 = findViewById(R.id.pr21);
        et22 = findViewById(R.id.pr22);
        et23 = findViewById(R.id.pr23);
        et24 = findViewById(R.id.pr24);
        dy4 = findViewById(R.id.d4);
        et25 = findViewById(R.id.pr25);
        et26 = findViewById(R.id.pr26);
        et27 = findViewById(R.id.pr27);
        et28 = findViewById(R.id.pr28);
        et29 = findViewById(R.id.pr29);
        et30 = findViewById(R.id.pr30);
        dy5 = findViewById(R.id.d5);
        et31 = findViewById(R.id.pr31);
        et32 = findViewById(R.id.pr32);
        et33 = findViewById(R.id.pr33);
        et34 = findViewById(R.id.pr34);
        et35 = findViewById(R.id.pr35);
        et36 = findViewById(R.id.pr36);
        dy6 = findViewById(R.id.d6);
        dayy = dy2.getText().toString();

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.open();
                long id = db.insertTimetable(dy1.getText().toString(), et1.getText().toString(), et2.getText().toString(), et3.getText().toString(), et4.getText().toString(), et5.getText().toString(), et6.getText().toString());
                id = db.insertTimetable(dy2.getText().toString(), et7.getText().toString(), et8.getText().toString(), et9.getText().toString(), et10.getText().toString(), et11.getText().toString(), et12.getText().toString());
                id = db.insertTimetable(dy3.getText().toString(), et13.getText().toString(), et14.getText().toString(), et15.getText().toString(), et16.getText().toString(), et17.getText().toString(), et18.getText().toString());
                id = db.insertTimetable(dy4.getText().toString(), et19.getText().toString(), et20.getText().toString(), et21.getText().toString(), et22.getText().toString(), et23.getText().toString(), et24.getText().toString());
                id = db.insertTimetable(dy5.getText().toString(), et25.getText().toString(), et26.getText().toString(), et27.getText().toString(), et28.getText().toString(), et29.getText().toString(), et30.getText().toString());
                id = db.insertTimetable(dy6.getText().toString(), et31.getText().toString(), et32.getText().toString(), et33.getText().toString(), et34.getText().toString(), et35.getText().toString(), et36.getText().toString());
                //db.deleteALLTimetable();
                db.close();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        startTimer();
    }

    public void startTimer() {
        //set a new Timer
        timer = new Timer();
        timer1 = new Timer();
//timer1=new Timer();
        //initialize the TimerTask's job

        //schedule the timer, after the first 5000ms the TimerTask will run every 10000ms
        if (p == 7 || p == 12 || p == 18) {
            initializeTimerTask1();
            timer1.schedule(timerTask1, 60000, 60000);
            (Toast.makeText(this, "times 60000", Toast.LENGTH_SHORT)).show();
        } else {
            initializeTimerTask();
            tim = 3000;
            timer.schedule(timerTask, 1000, 12000);
            (Toast.makeText(this, "times 3000", Toast.LENGTH_SHORT)).show();
        }
    }
    public void stoptimertask(View v) {
        //stop the timer, if it's not already null
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }
    public void initializeTimerTask() {
        timerTask = new TimerTask() {
            public void run() {
                //use a handler to run a toast that shows the current timestamp
                handler.post(new Runnable() {
                    public void run() {
                        //get the current timeStamp
                        Calendar calendar = Calendar.getInstance();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd:MMMM:yyyy HH:mm:ss a");
                        strDate = simpleDateFormat.format(calendar.getTime());
                        //show the toast
                        //    for(int k=0;k<3;k++)
                        createnotification();
                        int duration = 10;
                        //Toast toast = Toast.makeText(getApplicationContext(), strDate, duration);
                        //toast.show();
                    }
                });
            }
        };
    }
    public void createnotification() {
        p++;
        //  String d=s;
        NotificationManager nm = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder mbuilder = new NotificationCompat.Builder(this);
        mbuilder.setSmallIcon(R.drawable.timetable_icon);
        mbuilder.setContentTitle("TODAY'S TIMETABLE:");
        if (dayy.equals("MON")) {
            if (p == 1) {

                //  timer.schedule(timerTask, 500, 60000);
                mbuilder.setContentText(et1.getText().toString());
            }
            if (p == 2) {
                //  timer.schedule(timerTask, 500, 60000);
                mbuilder.setContentText(et2.getText().toString());
            }

            if (p == 3)
                mbuilder.setContentText(et3.getText().toString());

            if (p == 4)
                mbuilder.setContentText(et4.getText().toString());

            if (p == 5)
                mbuilder.setContentText(et5.getText().toString());

            if (p == 6) {
                p=0;

                mbuilder.setContentText(et6.getText().toString());
                dayy = dy2.getText().toString();
                // timer1.cancel();
                Intent i1 = new Intent(Timetable.this, Timetable.class);
                startActivity(i1);
            }
        }
        if (dayy.equals("TUE")) {
            if (p == 1) {
                mbuilder.setContentText(et7.getText().toString());
            }
            if (p == 2)
                mbuilder.setContentText(et8.getText().toString());

            if (p == 3)
                mbuilder.setContentText(et9.getText().toString());

            if (p == 4)
                mbuilder.setContentText(et10.getText().toString());

            if (p == 5)
                mbuilder.setContentText(et11.getText().toString());

            if (p == 6) {
                mbuilder.setContentText(et12.getText().toString());
                dayy = dy3.getText().toString();
                timer.cancel();
            }

        }
        if (dayy.equals("WED")) {
            if (p == 13)
                mbuilder.setContentText(et13.getText().toString());
            if (p == 14)
                mbuilder.setContentText(et14.getText().toString());

            if (p == 15)
                mbuilder.setContentText(et15.getText().toString());

            if (p == 16)
                mbuilder.setContentText(et16.getText().toString());

            if (p == 17)
                mbuilder.setContentText(et17.getText().toString());

            if (p == 18) {
                mbuilder.setContentText(et18.getText().toString());
                dayy = dy4.getText().toString();
            }
        }
        if (dayy.equals("THU")) {
            if (p == 19)
                mbuilder.setContentText(et19.getText().toString());
            if (p == 20)
                mbuilder.setContentText(et20.getText().toString());

            if (p == 21)
                mbuilder.setContentText(et21.getText().toString());

            if (p == 22)
                mbuilder.setContentText(et22.getText().toString());

            if (p == 23)
                mbuilder.setContentText(et23.getText().toString());

            if (p == 24) {
                mbuilder.setContentText(et24.getText().toString());
                dayy = dy5.getText().toString();
            }

        }
        if (dayy.equals("fri")) {
            if (p == 25)
                mbuilder.setContentText(et25.getText().toString());
            if (p == 26)
                mbuilder.setContentText(et26.getText().toString());

            if (p == 27)
                mbuilder.setContentText(et27.getText().toString());

            if (p == 28)
                mbuilder.setContentText(et28.getText().toString());

            if (p == 29)
                mbuilder.setContentText(et29.getText().toString());

            if (p == 30) {
                mbuilder.setContentText(et30.getText().toString());
                dayy = dy6.getText().toString();
            }
        }
        if (dayy.equals("sat")) {
            if (p == 31)
                mbuilder.setContentText(et31.getText().toString());
            if (p == 32)
                mbuilder.setContentText(et32.getText().toString());

            if (p == 33)
                mbuilder.setContentText(et33.getText().toString());

            if (p == 34)
                mbuilder.setContentText(et34.getText().toString());

            if (p == 35)
                mbuilder.setContentText(et35.getText().toString());

            if (p == 36)
                mbuilder.setContentText(et36.getText().toString());

        }


        //       mbuilder.setContentText(et2.getText().toString());
        mbuilder.setAutoCancel(true);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        if (alarmSound == null) {
            alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
            if (alarmSound == null) {
                alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            }
        }
        mbuilder.setSound(alarmSound);
        //Intent nw = new Intent(MainActivity.this, Activity2.class);
        //startActivity(nw);
        //PendingIntent pd= PendingIntent.getActivity(this,0,nw,PendingIntent.FLAG_UPDATE_CURRENT);
        // mbuilder.setContentIntent(pd);
        nm.notify(0, mbuilder.build());


    }

    public void initializeTimerTask1() {
        timerTask1 = new TimerTask() {
            public void run() {
                //use a handler to run a toast that shows the current timestamp
                handler.post(new Runnable() {
                    public void run() {
                        //get the current timeStamp
                        Calendar calendar = Calendar.getInstance();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd:MMMM:yyyy HH:mm:ss a");
                        strDate = simpleDateFormat.format(calendar.getTime());
                        //show the toast
                        //    for(int k=0;k<3;k++)
                        createnotification();
                        int duration = 10;
                        //Toast toast = Toast.makeText(getApplicationContext(), strDate, duration);
                        //toast.show();
                    }
                });
            }
        };
    }

    public void createnotification1() {
        p++;
        //  String d=s;
        NotificationManager nm = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder mbuilder = new NotificationCompat.Builder(this);
        mbuilder.setSmallIcon(R.drawable.timetable_icon);
        mbuilder.setContentTitle("TODAY'S TIMETABLE:");
        if (dayy.equals("mon")) {
            if (p == 1) {

                //  timer.schedule(timerTask, 500, 60000);
                mbuilder.setContentText(et1.getText().toString());
            }
            if (p == 2) {
                //  timer.schedule(timerTask, 500, 60000);
                mbuilder.setContentText(et2.getText().toString());
            }

            if (p == 3)
                mbuilder.setContentText(et3.getText().toString());

            if (p == 4)
                mbuilder.setContentText(et4.getText().toString());

            if (p == 5)
                mbuilder.setContentText(et5.getText().toString());

            if (p == 6) {
                //  p=0;

                mbuilder.setContentText(et6.getText().toString());
                dayy = dy2.getText().toString();
                Intent i1 = new Intent(Timetable.this, Timetable.class);
                startActivity(i1);


            }
        }

        if (dayy.equals("tue")) {
            if (p == 7) {
                mbuilder.setContentText(et7.getText().toString());
            }
            if (p == 8)
                mbuilder.setContentText(et8.getText().toString());

            if (p == 9)
                mbuilder.setContentText(et9.getText().toString());

            if (p == 10)
                mbuilder.setContentText(et10.getText().toString());

            if (p == 11)
                mbuilder.setContentText(et11.getText().toString());

            if (p == 12) {
                mbuilder.setContentText(et12.getText().toString());
                dayy = dy3.getText().toString();
            }

        }
        //       mbuilder.setContentText(et2.getText().toString());
        mbuilder.setAutoCancel(true);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alarmSound == null) {
            alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
            if (alarmSound == null) {
                alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            }
        }
        mbuilder.setSound(alarmSound);
        //Intent nw = new Intent(MainActivity.this, Activity2.class);
        //startActivity(nw);
        //PendingIntent pd= PendingIntent.getActivity(this,0,nw,PendingIntent.FLAG_UPDATE_CURRENT);
        // mbuilder.setContentIntent(pd);
        nm.notify(0, mbuilder.build());

    }

}



