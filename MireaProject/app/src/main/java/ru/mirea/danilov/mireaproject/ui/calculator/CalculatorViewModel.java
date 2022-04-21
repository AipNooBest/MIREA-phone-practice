package ru.mirea.danilov.mireaproject.ui.calculator;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CalculatorViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public CalculatorViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("0");
    }

    public LiveData<String> getText() {
        return mText;
    }
}