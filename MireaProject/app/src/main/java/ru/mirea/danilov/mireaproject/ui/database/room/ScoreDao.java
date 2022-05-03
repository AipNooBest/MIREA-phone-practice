package ru.mirea.danilov.mireaproject.ui.database.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import ru.mirea.danilov.mireaproject.ui.database.Score;

@Dao
public interface ScoreDao {
    @Query("SELECT * FROM score")
    List<Score> getAll();
    @Query("SELECT * FROM score WHERE id = :id")
    Score getById(long id);
    @Insert
    void insert(Score score);
    @Delete
    void delete(Score score);
    @Update
    void update(Score score);
}
