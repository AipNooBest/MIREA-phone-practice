package ru.mirea.danilov.mireaproject.ui.maps;

import androidx.annotation.NonNull;

public class UniversityInfo {
    String name;
    String description;
    String address;

    UniversityInfo(String name, String description, String address){
        this.name = name;
        this.description = description;
        this.address = address;
    }

    @NonNull
    @Override
    public String toString() {
        return  name + '\n' +
                description + '\n' +
                address;
    }
}
