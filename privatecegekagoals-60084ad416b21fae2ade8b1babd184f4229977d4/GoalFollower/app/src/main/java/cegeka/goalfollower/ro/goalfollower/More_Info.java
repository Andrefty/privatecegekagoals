package cegeka.goalfollower.ro.goalfollower;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class More_Info extends AppCompatActivity {

    static boolean[] check = new boolean[1001];
    CheckBox done ;
    int index;
    static int sum ;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_more__info);
        Intent in = getIntent();
        index = in.getIntExtra("com.example.cristi.firstcegeka.Item" , -1);
        Toast.makeText(this, index + "" , Toast.LENGTH_LONG).show();
        done = (CheckBox) findViewById(R.id.done_cb);
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

        final Intent sumIntent = new Intent (getApplicationContext() , MainActivity.class);
        sumIntent.putExtra("com.example.cristi.firstcegeka.sum" , sum);
        save = (Button) findViewById(R.id.save_check_btn);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(sumIntent);
            }
        });

    }

}
