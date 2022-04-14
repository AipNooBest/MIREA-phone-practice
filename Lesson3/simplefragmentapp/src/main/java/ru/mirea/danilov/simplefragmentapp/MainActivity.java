package ru.mirea.danilov.simplefragmentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.frag1) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, new BlankFragment1()).commit();
        }
        if (view.getId() == R.id.frag2) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, new BlankFragment2()).commit();
        }
    }
}