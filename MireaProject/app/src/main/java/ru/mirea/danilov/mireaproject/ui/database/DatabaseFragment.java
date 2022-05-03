package ru.mirea.danilov.mireaproject.ui.database;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.mirea.danilov.mireaproject.databinding.FragmentDatabaseBinding;
import ru.mirea.danilov.mireaproject.ui.database.room.App;
import ru.mirea.danilov.mireaproject.ui.database.room.AppDatabase;
import ru.mirea.danilov.mireaproject.ui.database.room.ScoreDao;

public class DatabaseFragment extends Fragment {
    private FragmentDatabaseBinding binding;
    private AppDatabase database;
    private ScoreDao scoreDao;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDatabaseBinding.inflate(inflater, container, false);
        database = App.getInstance().getDatabase();
        scoreDao = database.scoreDao();
        makeLeaderboard();

        ScoresAdapter adapter = new ScoresAdapter(getActivity(), database);
        binding.scoresRecyclerView.setAdapter(adapter);

        return binding.getRoot();
    }

    private void makeLeaderboard(){
        Score score = new Score("Aksuma", 13651);
        scoreDao.insert(score);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}