package com.example.mahima.wordsrepo;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class WordViewModel extends AndroidViewModel {

    private WordRepository mWordRepository;
    private LiveData<List<Word>> mWordList;

    public WordViewModel(@NonNull Application application) {
        super(application);
        mWordRepository = new WordRepository(application);
        mWordList = mWordRepository.getWordList();
    }


    public LiveData<List<Word>> getWordList() {
        return mWordList;
    }

    public void insert(Word word) {
        mWordRepository.insert(word);
    }
}
