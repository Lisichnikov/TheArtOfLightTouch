package com.example.theartoflighttouch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // закрепляет положение экрана в вертикальном состоянии
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
    // по нажатию на кнопку со светлой темой отправляет значение 1
    public void onClickButtonWhite(View view){
        int id = 1;
        Intent intent = new Intent(MainActivity.this, GameActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    // по нажатию на кнопку со темной темой отправляет значение 2
    public void onClickButtonBlack(View view){
        int id = 2;
        Intent intent = new Intent(MainActivity.this, GameActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }
}