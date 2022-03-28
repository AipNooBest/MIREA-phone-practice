package ru.mirea.ddo.clickbuttons;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView tvOut;
    private Button buttonOk;
    private Button buttonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvOut = findViewById(R.id.textView);
        buttonOk = findViewById(R.id.okButton);
        buttonCancel = findViewById(R.id.cancelButton);

        View.OnClickListener oclBtnOk = view -> tvOut.setText("Нажата кнопка ОК");
        buttonOk.setOnClickListener(oclBtnOk);
    }

    public void onMyButtonClick(View view){
        Toast.makeText(this, "Нажата кнопка Cancel", Toast.LENGTH_SHORT).show();
        tvOut.setText("Нажата кнопка Cancel");
    }
}