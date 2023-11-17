package com.example.theartoflighttouch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class WinActivity extends AppCompatActivity {

    TextView WinText;
    String win_first;
    String win_second;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);

        // закрепляет положение экрана в вертикальном состоянии
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        WinText = findViewById(R.id.WinText);

        // вводим новые переменные
        win_first = "Победил игрок номер 1";
        win_second = "Победил игрок номер 2";

        // получаем данные из MainActivity
        Intent intent = getIntent();
        Bundle extra = intent.getExtras();
        int id = extra.getInt("id");

        // условие при котором будет выводиться какой игрок выйграл
        if (id == 1) {
            // выигрывает второй игрок
            WinText.setText(win_second);
        }else{
            // выигрывает первый игрок
            WinText.setText(win_first);
        }
    }
    public void onClickButtonBack(View view){
        Intent intent = new Intent(WinActivity.this, MainActivity.class);
        startActivity(intent);
    }
}