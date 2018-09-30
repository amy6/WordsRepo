package com.example.mahima.wordsrepo;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface WordDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Word word);

    @Query("DELETE FROM word")
    void deleteAll();

    @Query("SELECT * FROM word ORDER BY word ASC")
    LiveData<List<Word>> getAllWords();

    @Query("SELECT * FROM word LIMIT 1")
    Word[] getAWord();

    @Delete
    void delete(Word word);
}
