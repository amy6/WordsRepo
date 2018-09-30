package com.example.mahima.wordsrepo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Word {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    @ColumnInfo(name = "word")
    private String mWord;

    public Word(int id, @NonNull String mWord) {
        this.id = id;
        this.mWord = mWord;
    }

    @Ignore
    public Word(@NonNull String word) {
        this.mWord = word;
    }

    public String getWord() {
        return this.mWord;
    }

    @NonNull
    public int getId() {
        return id;
    }
}
