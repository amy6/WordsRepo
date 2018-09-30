package com.example.mahima.wordsrepo;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class WordRepository {

    private WordDao mWordDao;
    private LiveData<List<Word>> mWordList;

    public WordRepository(Application application) {
        mWordDao = WordRoomDatabase.getInstance(application).getWordDao();
        mWordList = mWordDao.getAllWords();
    }

    LiveData<List<Word>> getWordList() {
        return mWordList;
    }

    void insert(Word word) {
        new WordDaoAsyncTask(mWordDao).execute(word);
    }

    public static class WordDaoAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao wordDao;

        public WordDaoAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            wordDao.insert(words[0]);
            return null;
        }
    }
}
