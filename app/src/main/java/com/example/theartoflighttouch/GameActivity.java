package com.example.theartoflighttouch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

public class GameActivity extends AppCompatActivity {


    final Random random = new Random();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Intent intent = getIntent();
        Bundle extra = intent.getExtras();
        int id = extra.getInt("id");

        // меняет на светлую тему
        if (id == 1){
            //меняет фон на светлый
            View root = findViewById(android.R.id.content);
            root.setBackgroundResource(R.drawable.background);
            //меняет первую кнопку на темную
            View root1 = findViewById(R.id.button_circle_first);
            root1.setBackgroundResource(R.drawable.circle_button_dark);
            //меняет вторую кнопку на темную
            View root2 = findViewById(R.id.button_circle_second);
            root2.setBackgroundResource(R.drawable.circle_button_dark);

            // меняет на светлую тему
        }else{
            //меняет фон на темный фон
            View root = findViewById(android.R.id.content);
            root.setBackgroundResource(R.drawable.background_dark);
            //меняет первую кнопку на светлую
            View root1 = findViewById(R.id.button_circle_first);
            root1.setBackgroundResource(R.drawable.circle_button_white);
            //меняет вторую кнопку на светлую
            View root2 = findViewById(R.id.button_circle_second);
            root2.setBackgroundResource(R.drawable.circle_button_white);
        }

//        public void onClickButtonFirst(View view){
//            TextView textFirst = findViewById(R.id.text_first);
//            textFirst.setText(String.valueOf(random.nextInt(20) + 1));
//        }
    }
}