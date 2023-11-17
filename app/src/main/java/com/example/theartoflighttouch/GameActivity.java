package com.example.theartoflighttouch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class GameActivity extends AppCompatActivity {
    final Random random = new Random();
    TextView mTimerText;
    TextView mTimerText2;
    CountDownTimer timer;
    CountDownTimer timer2;
    int previousNumber;
    int previousNumber2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // закрепляет положение экрана в вертикальном состоянии
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // получаем данные из MainActivity
        Intent intent = getIntent();
        Bundle extra = intent.getExtras();
        int id = extra.getInt("id");

        // меняет на светлую тему если полученное значение равно 1
        if (id == 1) {
            //меняет фон на светлый если полученное значение равно 1
            View root = findViewById(android.R.id.content);
            root.setBackgroundResource(R.drawable.background);
            //меняет первую кнопку на если темную полученное значение равно 1
            View root1 = findViewById(R.id.button_circle_first);
            root1.setBackgroundResource(R.drawable.circle_button_dark);
            //меняет вторую кнопку на если темную полученное значение равно 1
            View root2 = findViewById(R.id.button_circle_second);
            root2.setBackgroundResource(R.drawable.circle_button_dark);

            // меняет на темную тему если полученное значение равно 2
        } else {
            //меняет фон на темный фон если полученное значение равно 2
            View root = findViewById(android.R.id.content);
            root.setBackgroundResource(R.drawable.background_dark);
            //меняет первую кнопку на светлую если полученное значение равно 2
            View root1 = findViewById(R.id.button_circle_first);
            root1.setBackgroundResource(R.drawable.circle_button_white);
            //меняет вторую кнопку на светлую если полученное значение равно 2
            View root2 = findViewById(R.id.button_circle_second);
            root2.setBackgroundResource(R.drawable.circle_button_white);
        }

    }

    public void onClickButtonFirst(View view) {
        if (timer != null) {
            timer.cancel();
        }
        TextView textFirst = findViewById(R.id.text_first);
        // рандом
        int currentNumber = random.nextInt(20) + 1;
        textFirst.setText(String.valueOf(currentNumber));

        // проверка на то, одинаковое ли предыдущее число
        if (currentNumber == previousNumber) {
            int id = 1;
            Intent intent = new Intent(GameActivity.this, WinActivity.class);
            intent.putExtra("id", id);
            startActivity(intent);
        } else {
            previousNumber = currentNumber;
        }

        mTimerText = findViewById(R.id.mTimerText);

        // условие при котором старый таймер будет останавливаться
        // сам таймер объявляем
        timer = new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long l) {
                mTimerText.setText("" + l / 1000);
            }

            @Override
            public void onFinish() {
                int id = 1;
                Intent intent = new Intent(GameActivity.this, WinActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        }.start();
    }

    public void onClickButtonSecond(View view) {
        if (timer2 != null) {
            timer2.cancel();
        }
        TextView textFirst = findViewById(R.id.text_second);
        // рандом
        int currentNumber = random.nextInt(20) + 1;
        textFirst.setText(String.valueOf(currentNumber));

        // условие при котором старый таймер будет останавливаться
        if (currentNumber == previousNumber2) {
            int id = 2;
            Intent intent = new Intent(GameActivity.this, WinActivity.class);
            intent.putExtra("id", id);
            startActivity(intent);
        } else {
            previousNumber2 = currentNumber;
        }

        mTimerText2 = findViewById(R.id.mTimerText2);
        // условие при котором старый таймер будет останавливаться
        // сам таймер объявляем
        timer2 = new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long l) {
                mTimerText2.setText("" + l / 1000);
            }

            @Override
            public void onFinish() {
                int id = 2;
                Intent intent = new Intent(GameActivity.this, WinActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        }.start();
    }
}
