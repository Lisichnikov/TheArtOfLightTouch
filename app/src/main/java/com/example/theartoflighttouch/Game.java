package com.example.theartoflighttouch;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class Game {
    //метод для создания рандомного числа
    private void generateRandomNumber(TextView playerNumberField) {
        Random random = new Random();
        int randomNumber = random.nextInt(20) + 1;
        playerNumberField.setText(String.valueOf(randomNumber));
    }
    //метод для проверки числа на соответствие
    private void checkWinner(String player1Number, String player) {
        //String player1Number = player1Text.getText().toString();
        //String player2Number = player2Text.getText().toString();

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
