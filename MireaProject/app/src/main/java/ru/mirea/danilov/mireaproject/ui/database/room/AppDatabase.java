package ru.mirea.danilov.mireaproject.ui.database.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import ru.mirea.danilov.mireaproject.ui.database.Score;

@Database(entities = {Score.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ScoreDao scoreDao();
}
