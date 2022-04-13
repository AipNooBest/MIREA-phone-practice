package ru.mirea.danilov.dialog;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class TimeDialogFragment extends DialogFragment {  //Диалог для выбора времени
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new TimePickerDialog(getActivity(), (timePicker, hour, minute) -> {
            // Do something with the time chosen by the user
        }, 0, 0, true);
    }
}
