package ru.mirea.danilov.mireaproject.ui.browser;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BrowserViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public BrowserViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("");
    }

    public LiveData<String> getText() {
        return mText;
    }
}