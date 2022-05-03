package ru.mirea.danilov.mireaproject.ui.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Score {
    @PrimaryKey(autoGenerate = true)
    public int id = 0;
    private String name;
    private int score;

    public Score(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
