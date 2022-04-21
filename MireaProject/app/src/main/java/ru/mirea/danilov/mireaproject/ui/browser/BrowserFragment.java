package ru.mirea.danilov.mireaproject.ui.browser;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import ru.mirea.danilov.mireaproject.MainActivity;
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
}