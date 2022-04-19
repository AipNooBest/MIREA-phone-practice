package ru.mirea.danilov.lesson4;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showCurrentThread();
    }

    public void onCountAverageLessonsOnMonth(View view) {
        @SuppressLint("SetTextI18n") Runnable runnable = () -> {
            EditText editText = findViewById(R.id.pairsText);
            float pairs = Float.parseFloat(editText.getText().toString());
            EditText editText2 = findViewById(R.id.daysText);
            float days = Float.parseFloat(editText2.getText().toString());
            TextView textView = findViewById(R.id.resultView);
            textView.setText("Среднее количество пар в день: " + pairs / days);
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    public void onClickToCreateNewThread(View view) {
        Runnable runnable = new Runnable() {
            public void run() {
                int numberThread = counter++;
                Log.i("ThreadProject", "Запущен поток № " + numberThread);
                long endTime = System.currentTimeMillis()
                        + 20 * 1000;
                while (System.currentTimeMillis() < endTime) {
                    synchronized (this) {
                        try {
                            wait(endTime -
                                    System.currentTimeMillis());
                        } catch (Exception ignored) {}
                    }
                }
                Log.i("ThreadProject", "Выполнен поток № " + numberThread);
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    @SuppressLint("SetTextI18n")
    private void showCurrentThread(){
        TextView infoTextView = findViewById(R.id.curThreadView);
        Thread mainThread = Thread.currentThread();
        infoTextView.setText("Текущий поток: " + mainThread.getName());
        mainThread.setName("MireaThread");
        infoTextView.append("\n Новое имя потока: " + mainThread.getName());
    }
}