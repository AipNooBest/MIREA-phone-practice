package ru.mirea.danilov.resultactivity;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewResult = findViewById(R.id.textViewResult);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data != null) {
            String result = data.getStringExtra("name");
            setUniversityTextView(result);
        }
    }

    public void startDataActivityOnClick(View view) {
        Intent intent = new Intent(this, DataActivity.class);
        activityLauncher.launch(intent);
    }

    ActivityResultLauncher<Intent> activityLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    String resultName = null;
                    if (result.getData() != null) {
                        resultName = result.getData().getStringExtra("name");
                    }
                    setUniversityTextView(resultName);
                }
            });

    public void setUniversityTextView(String university) {
        textViewResult.setText(university);
    }
}