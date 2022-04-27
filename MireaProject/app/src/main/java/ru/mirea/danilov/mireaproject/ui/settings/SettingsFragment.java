package ru.mirea.danilov.mireaproject.ui.settings;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import ru.mirea.danilov.mireaproject.R;
import ru.mirea.danilov.mireaproject.databinding.FragmentSettingsBinding;

public class SettingsFragment extends Fragment {
    private FragmentSettingsBinding binding;
    private SharedPreferences preferences;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        preferences = getActivity().getSharedPreferences("settings", Context.MODE_PRIVATE);
        binding.killEyesSwitch.setOnClickListener(this::onClickKillEyes);
        binding.notAllowToSleepSwitch.setOnClickListener(this::onClickNotAllowToSleep);
        binding.killEyesSwitch.setChecked(preferences.getBoolean("killEyes", false));
        binding.notAllowToSleepSwitch.setChecked(preferences.getBoolean("notAllowToSleep", false));
        applySettings();
        return root;
    }

    @SuppressLint("ResourceAsColor")
    public void onClickKillEyes(View view) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("killEyes", binding.killEyesSwitch.isChecked());
        editor.apply();

        if (binding.killEyesSwitch.isChecked()) {
            binding.getRoot().setBackgroundColor(R.color.purple_500);
        } else {
            binding.getRoot().setBackgroundColor(getResources().getColor(R.color.white));
        }
    }
    
    @SuppressLint("ResourceAsColor")
    private void applySettings() {
        if (binding.killEyesSwitch.isChecked())
            binding.getRoot().setBackgroundColor(R.color.purple_500);
        else
            binding.getRoot().setBackgroundColor(getResources().getColor(R.color.white));

        
        if (binding.notAllowToSleepSwitch.isChecked())
            Log.d(TAG, "applySettings: Студенты спят");
        else
            Log.d(TAG, "applySettings: Студенты не спят");
    }
    
    public void onClickNotAllowToSleep(View view) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("notAllowToSleep", binding.notAllowToSleepSwitch.isChecked());
        editor.apply();

        if (binding.notAllowToSleepSwitch.isChecked())
            Toast.makeText(getActivity(), "Поздравляю, студенты теперь никогда не будут высыпаться", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getActivity(), "Теперь один студент в альтернативной вселенной выспится", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}