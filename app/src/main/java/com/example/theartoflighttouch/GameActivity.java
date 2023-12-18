package com.example.theartoflighttouch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

public class GameActivity extends AppCompatActivity implements ResetFragment.OnResetSelectedListener{
    private static final int MIN_CLICK_INTERVAL = 5000; // В миллисекундах (5 секунд)
    private ImageButton player1Button, player2Button, resetButton;
    private TextView player1Text, player2Text;
    // переменная служит для проверки кто нажал на кнопку
    private boolean isPlayerTurn = true;
    private CountDownTimer turnTimer;
    public static final String APP_PREFERENCES1 = "settings";
    public static final String APP_PREFERENCES2 = "settings";
    public static final String APP_PREFERENCES_FIRST = "text_first";
    public static final String APP_PREFERENCES_SECOND = "text_second";
    SharedPreferences mSettings1;
    SharedPreferences mSettings2;
    private TextView firstWinCounter;
    private TextView secondWinCounter;
    private TextView Pobed1;
    private TextView Pobed2;
    private int firstCounter;
    private int secondCounter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        mSettings1 = getSharedPreferences(APP_PREFERENCES1, Context.MODE_PRIVATE);
        mSettings2 = getSharedPreferences(APP_PREFERENCES2, Context.MODE_PRIVATE);
        firstWinCounter = findViewById(R.id.counter_first);
        secondWinCounter = findViewById(R.id.counter_second);

        Pobed1 = findViewById(R.id.pobed1);
        Pobed2 = findViewById(R.id.pobed2);
        player1Button = findViewById(R.id.button_circle_first);
        player2Button = findViewById(R.id.button_circle_second);
        player1Text = findViewById(R.id.text_first);
        player2Text = findViewById(R.id.text_second);
// WinText = findViewById(R.id.winText);

//закрепляет положение экрана в вертикальном состоянии
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
            root1.setBackgroundResource(R.drawable.circle_button_dark1);
//меняет вторую кнопку на если темную полученное значение равно 1
            View root2 = findViewById(R.id.button_circle_second);
            root2.setBackgroundResource(R.drawable.circle_button_dark2);
//меняет кнопку заново на светлую если полученное значение равно 2

// меняет на темную тему если полученное значение равно 2
        } else {
//меняет фон на темный фон если полученное значение равно 2
            View root = findViewById(android.R.id.content);
            root.setBackgroundResource(R.drawable.background_dark);
//меняет первую кнопку на светлую если полученное значение равно 2
            View root1 = findViewById(R.id.button_circle_first);
            root1.setBackgroundResource(R.drawable.circle_button_white1);
//меняет вторую кнопку на светлую если полученное значение равно 2
            View root2 = findViewById(R.id.button_circle_second);
            root2.setBackgroundResource(R.drawable.circle_button_white2);
//меняет кнопку заново на светлую если полученное значение равно 2
            player1Text.setTextColor(Color.WHITE);
            player2Text.setTextColor(Color.WHITE);
            firstWinCounter.setTextColor(Color.WHITE);
            secondWinCounter.setTextColor(Color.WHITE);
            Pobed1.setTextColor(Color.WHITE);
            Pobed2.setTextColor(Color.WHITE);
        }
//обработка нажатия кнопки первого игрока
        player1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateRandomNumber(player1Text);
                checkWinner();
                isPlayerTurn = false;
                startTurnTimer();
            }
        });
//обработка нажатия кнопки второго игрока
        player2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateRandomNumber(player2Text);
                checkWinner();
                isPlayerTurn = true;
                startTurnTimer();
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();

        if (mSettings1.contains(APP_PREFERENCES_FIRST) && mSettings2.contains(APP_PREFERENCES_SECOND)) {
// Получаем число из настроек
            firstCounter = mSettings1.getInt(APP_PREFERENCES_FIRST, 0);
            secondCounter = mSettings2.getInt(APP_PREFERENCES_SECOND, 0);
// Выводим на экран данные из настроек
            firstWinCounter.setText(String.valueOf(firstCounter));
            secondWinCounter.setText(String.valueOf(secondCounter));
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
// Запоминаем данные
        SharedPreferences.Editor editor1 = mSettings1.edit();
        SharedPreferences.Editor editor2 = mSettings2.edit();
        editor1.putInt(APP_PREFERENCES_FIRST, firstCounter);
        editor2.putInt(APP_PREFERENCES_SECOND, secondCounter);
        editor1.apply();
        editor2.apply();
    }
    public void onClickButtonReset(){
        replaceFragment(new NothingFragment());
        player1Button.setEnabled(true);
        player2Button.setEnabled(true);
    }
    //метод для создания рандомного числа
    private void generateRandomNumber(TextView playerNumberField) {
        Random random = new Random();
        int randomNumber = random.nextInt(20) + 1;
        playerNumberField.setText(String.valueOf(randomNumber));
    }
    //метод для проверки числа на соответствие
    private void checkWinner() {
        String player1Number = player1Text.getText().toString();
        String player2Number = player2Text.getText().toString();

        if (player1Number.equals(player2Number)) {
            if (isPlayerTurn) {
                replaceFragment(new SecondWinFragment());
                secondCounter++;
                secondWinCounter.setText(String.valueOf(secondCounter));
            } else {
                replaceFragment(new FirstWinFragment());
                firstCounter++;
                firstWinCounter.setText(String.valueOf(firstCounter));
            }
            player1Button.setEnabled(false);
            player2Button.setEnabled(false);
            resetGame();
        }
    }
    // метод создания таймера
    private void startTurnTimer() {
        if (turnTimer != null) {
            turnTimer.cancel();
        }
        turnTimer = new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
            }
            @Override
            public void onFinish() {
                if (isPlayerTurn) {
                    replaceFragment(new SecondWinFragment());
                    secondCounter++;
                    secondWinCounter.setText(String.valueOf(secondCounter));
                } else {
                    replaceFragment(new FirstWinFragment());
                    firstCounter++;
                    firstWinCounter.setText(String.valueOf(firstCounter));
                }
                player1Button.setEnabled(false);
                player2Button.setEnabled(false);
                resetGame();
            }
        }.start();
    }
    // метод для перезапуска игры
    private void resetGame() {
        if (turnTimer != null) {
            turnTimer.cancel();
        }
        player1Text.setText("");
        player2Text.setText("");
        isPlayerTurn = true;
    }
    private void replaceFragment (Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame,fragment);
        fragmentTransaction.commit();
    }
}