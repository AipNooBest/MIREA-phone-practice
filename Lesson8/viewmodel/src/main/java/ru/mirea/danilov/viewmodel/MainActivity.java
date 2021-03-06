package ru.mirea.danilov.viewmodel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressBar);
        ProgressViewModel viewModel = new ViewModelProvider(this).get(ProgressViewModel.class);

        viewModel.getProgressState().observe(this, (Boolean isShown) -> {
            progressBar.setVisibility(isShown ? ProgressBar.VISIBLE : ProgressBar.INVISIBLE);
        });
        viewModel.showProgress();
    }
}