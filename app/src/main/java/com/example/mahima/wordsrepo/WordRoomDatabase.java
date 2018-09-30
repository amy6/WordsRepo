package com.example.mahima.wordsrepo;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = Word.class, version = 1, exportSchema = false)
public abstract class WordRoomDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "word";
    private static final Object LOCK = new Object();

    private static WordRoomDatabase wordDatabase;

    public abstract WordDao getWordDao();

    public static WordRoomDatabase getInstance(Context context) {

        if (wordDatabase == null) {
            synchronized (LOCK) {
                if (wordDatabase == null) {
                    wordDatabase = Room.databaseBuilder(context, WordRoomDatabase.class,
                            DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .addCallback(callback)
                            .build();
                }
            }
        }
        return wordDatabase;
    }

    private static RoomDatabase.Callback callback = new Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsyncTask(wordDatabase).execute();
        }
    };

    public static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {

        private WordDao mWordDao;
        private String[] words = {"dolphin", "dog", "rabbit"};

        public PopulateDbAsyncTask(WordRoomDatabase database) {
            this.mWordDao = database.getWordDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            mWordDao.deleteAll();

            for (String word : words) {
                mWordDao.insert(new Word(word));
            }
            return null;
        }
    }
}
