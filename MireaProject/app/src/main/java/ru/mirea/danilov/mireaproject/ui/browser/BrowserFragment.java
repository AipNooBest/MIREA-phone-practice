package ru.mirea.danilov.mireaproject.ui.browser;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import ru.mirea.danilov.mireaproject.R;
import ru.mirea.danilov.mireaproject.databinding.FragmentBrowserBinding;

public class BrowserFragment extends Fragment {

    private FragmentBrowserBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        BrowserViewModel browserViewModel =
                new ViewModelProvider(this).get(BrowserViewModel.class);

        binding = FragmentBrowserBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final EditText searchBar = binding.searchBar;

        SendHttpRequestTask task = new SendHttpRequestTask();
        task.execute("https://i.aipserver.ru/Безымянный-файл-изображения-(30)-2.jpg");

        browserViewModel.getText().observe(getViewLifecycleOwner(), searchBar::setText);

        binding.searchButton.setOnClickListener(this::onSearchButtonClicked);
        return root;
    }

    public void onSearchButtonClicked(View v) {
        String searchText = binding.searchBar.getText().toString();
        if (searchText.isEmpty()) return;
        String searchUrl = "https://www.google.com/search?q=" + searchText;
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(searchUrl));
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private class SendHttpRequestTask extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                return BitmapFactory.decodeStream(input);
            }catch (Exception e){
                Log.d(TAG,e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            ImageView imageView = (ImageView) getActivity().findViewById(R.id.picture);
            imageView.setImageBitmap(result);
        }
    }
}