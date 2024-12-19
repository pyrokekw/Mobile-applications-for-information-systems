package com.example.lab5;

public class Note {
    private int id;
    private String description;

    public Note(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}