package ru.mirea.danilov.loadermanager;

import android.os.Bundle;

import androidx.loader.content.AsyncTaskLoader;

public class MyLoader extends AsyncTaskLoader<String> {
    private String preShuffledText;
    public static final String ARG_WORD = "word";

    public MyLoader(android.content.Context context, Bundle args) {
        super(context);
        if (args != null) {
            preShuffledText = args.getString(ARG_WORD);
        }
    }

    @Override
    public String loadInBackground() {
        return shuffleString(preShuffledText);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    private String shuffleString(String text) {
        char[] chars = text.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int random = (int) (Math.random() * chars.length);
            char temp = chars[i];
            chars[i] = chars[random];
            chars[random] = temp;
        }
        return new String(chars);
    }
}
