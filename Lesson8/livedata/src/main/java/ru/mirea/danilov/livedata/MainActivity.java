package ru.mirea.danilov.livedata;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements Observer {
    private TextView networkNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        networkNameTextView = findViewById(R.id.networkNameTextView);
        TimeController.TimeLiveData.getTime().observe(this, (androidx.lifecycle.Observer<? super Long>) this);
        Handler handler = new Handler();
        handler.postDelayed(TimeController.TimeLiveData::setTime, 5000);
    }

    @Override
    public void update(Observable observable, Object o) {
        networkNameTextView.setText(o.toString());
    }
}