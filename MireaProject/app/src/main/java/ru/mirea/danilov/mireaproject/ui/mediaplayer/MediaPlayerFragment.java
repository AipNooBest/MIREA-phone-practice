package ru.mirea.danilov.mireaproject.ui.mediaplayer;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.mirea.danilov.mireaproject.MainActivity;
import ru.mirea.danilov.mireaproject.databinding.FragmentMediaPlayerBinding;

public class MediaPlayerFragment extends Fragment {

    private FragmentMediaPlayerBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentMediaPlayerBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.playButton.setOnClickListener(this::onClickPlayMusic);
        binding.stopButton.setOnClickListener(this::onClickStopMusic);

        return root;
    }

    public void onClickPlayMusic(View view) {
        MainActivity activity = (MainActivity) getActivity();
        assert activity != null;
        Log.d("MediaPlayerFragment", "onClickPlayMusic: " + activity.getLocalClassName());
        activity.startService(
                new Intent(activity, PlayerService.class));
    }
    public void onClickStopMusic(View view) {
        MainActivity activity = (MainActivity) getActivity();
        assert activity != null;
        Log.d("MediaPlayerFragment", "onClickStopMusic: ");
        activity.stopService(
                new Intent(activity, PlayerService.class));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}