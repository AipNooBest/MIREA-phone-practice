package ru.mirea.danilov.multiactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MultiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi);
    }

    public void onClickNewActivity(android.view.View view) {
        android.content.Intent intent = new android.content.Intent(this, SecondActivity.class);
        intent.putExtra("key", "MIREA - ДАНИЛОВ ДЕНИС ОЛЕГОВИЧ");
        startActivity(intent);
    }
}