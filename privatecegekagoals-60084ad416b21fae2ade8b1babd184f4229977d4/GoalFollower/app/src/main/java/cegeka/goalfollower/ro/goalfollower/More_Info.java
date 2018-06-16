package cegeka.goalfollower.ro.goalfollower;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class More_Info extends AppCompatActivity {

    static boolean[] check = new boolean[1001];
    CheckBox done ;
    static int index;
    public static int sum ;
    String filenameforcheck="checkbox";
    Button set;
    EditText name_not;
    EditText description_not;
    EditText duration_not;
    public static  String[] S_name_not = new String[1001];
    public  static String[] S_description_not = new String[1001];
    String S_duration_not;
    int I_duration_not;
    int[] durations = new int[1001];
    AlarmManager[] alarmManagers = new AlarmManager[1001] ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Readfcheck();
        setContentView(R.layout.activity_more__info);
        Intent in = getIntent();
        index = in.getIntExtra("com.example.cristi.firstcegeka.Item" , -1);
        Toast.makeText(this, index + "" , Toast.LENGTH_LONG).show();
        done = (CheckBox) findViewById(R.id.done_cb);
        Readfcheck();
        done.setChecked(check[index]);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check[index]==false)
                {
                    check[index] = true ;
                    sum = sum + 100;
                    Toast.makeText(getApplicationContext() , "" + sum  , Toast.LENGTH_LONG).show();
                }
                else
                {
                    check[index] = false;
                    sum = sum - 100;
                    Toast.makeText(getApplicationContext() , "" + sum , Toast.LENGTH_LONG).show();
                }
            }
        });

        Addgcheck();

        set = (Button) findViewById(R.id.set_not_info_btn);
        name_not = (EditText) findViewById(R.id.not_name_edit_text);
        description_not = (EditText) findViewById(R.id.not_description_edit_text);
        duration_not = (EditText) findViewById(R.id.not_time_edit_text);
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                S_duration_not = duration_not.getText().toString();
                I_duration_not = Integer.parseInt(S_duration_not);
                S_name_not[index] = name_not.getText().toString();
                S_description_not[index] = description_not.getText().toString();
                durations[index] = I_duration_not;

               // for (int f=0 ; f<val ; f++)
               // {

                    Intent intent13 = new Intent(getApplicationContext() , Notification_reciever_2.class);
                    intent13.putExtra("index" , index);
                    intent13.setAction("MY_NOTIFICATION_MESSAGE_2");
                    PendingIntent pendingIntent12 = PendingIntent.getBroadcast(getApplicationContext(), index, intent13, PendingIntent.FLAG_UPDATE_CURRENT);
                    alarmManagers[index] = (AlarmManager) getSystemService(ALARM_SERVICE);
                    alarmManagers[index].setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 60000 *durations[index], pendingIntent12);
                   // Toast.makeText(getApplicationContext() , "" + f , Toast.LENGTH_LONG).show();
                    //intentArray.add(pendingIntent12);
                //}
                Toast.makeText(getApplicationContext(), "Notificare setata", Toast.LENGTH_LONG).show();
               // Intent intent6 = new Intent(getApplicationContext(), ListActivity.class);
               // startActivity(intent6);
                finish();


            }
        });

    }
    public void Addgcheck() {
        File myfile = new File(this.getFilesDir(), filenameforcheck);
        FileOutputStream outputStream;
        try {
            outputStream = openFileOutput(filenameforcheck, MODE_PRIVATE);
            ObjectOutputStream o = new ObjectOutputStream(outputStream);
            o.reset();
            o.writeObject(check);
            o.write(sum);
            o.flush();
            o.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void Readfcheck() {
        FileInputStream fis;
        try {
            fis = openFileInput(filenameforcheck);
            ObjectInputStream ois = new ObjectInputStream(fis);
            check = (boolean[]) ois.readObject();
            sum=(int) ois.readInt();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
