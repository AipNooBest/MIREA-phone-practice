package ru.mirea.danilov.mireaproject.ui.database;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.mirea.danilov.mireaproject.R;
import ru.mirea.danilov.mireaproject.ui.database.room.AppDatabase;

public class ScoresAdapter extends RecyclerView.Adapter<ScoresAdapter.ViewHolder> {
    private final LayoutInflater inflater;
    private final AppDatabase database;

    ScoresAdapter(Context context, AppDatabase database) {
        this.database = database;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_score, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Score score = database.scoreDao().getById(position + 1);
        holder.name.setText(score.getName());
        holder.score.setText(String.valueOf(score.getScore()));
    }

    @Override
    public int getItemCount() {
        return database.scoreDao().getAll().size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, score;
        ViewHolder(View view){
            super(view);
            name = view.findViewById(R.id.scoreUser);
            score = view.findViewById(R.id.scoreValue);
        }
    }
}
