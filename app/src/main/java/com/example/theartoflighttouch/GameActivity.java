package com.example.theartoflighttouch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

public class GameActivity extends AppCompatActivity {
    private static final int MIN_CLICK_INTERVAL = 5000; // В миллисекундах (5 секунд)
private ImageButton player1Button, player2Button, resetButton;
private TextView player1Text, player2Text, WinText;
// переменная служит для проверки кто нажал на кнопку
private boolean isPlayerTurn = true;
private CountDownTimer turnTimer;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_game);

            player1Button = findViewById(R.id.button_circle_first);
            player2Button = findViewById(R.id.button_circle_second);
            player1Text = findViewById(R.id.text_first);
            player2Text = findViewById(R.id.text_second);
            WinText = findViewById(R.id.winText);

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
                root1.setBackgroundResource(R.drawable.circle_button_dark);
                //меняет вторую кнопку на если темную полученное значение равно 1
                View root2 = findViewById(R.id.button_circle_second);
                root2.setBackgroundResource(R.drawable.circle_button_dark);
                //меняет кнопку  заново на светлую если полученное значение равно 2
                View root3 = findViewById(R.id.reset_button);
                root3.setBackgroundResource(R.drawable.button_reset);

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
                //меняет кнопку  заново на светлую если полученное значение равно 2
                View root3 = findViewById(R.id.reset_button);
                root3.setBackgroundResource(R.drawable.button_reset_white);
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
        public void onClickButtonReset(View view){
            WinText.setText("");
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
                    WinText.setText("Выиграл второй игрок!");
                } else {
                    WinText.setText("Выиграл первый игрок!");
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
                        WinText.setText("Выиграл второй игрок!");
                    } else {
                        WinText.setText("Выиграл первый игрок!");
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
    }