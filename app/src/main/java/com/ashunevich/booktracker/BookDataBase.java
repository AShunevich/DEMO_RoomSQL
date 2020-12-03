package com.ashunevich.booktracker;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities = {BookItem.class}, version = 1, exportSchema = false)
public abstract class BookDataBase extends RoomDatabase {

    public abstract BookDAO bookDAO();

    private static final int NUMBER_OF_THREADS = 4;
    private static volatile BookDataBase INSTANCE;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static BookDataBase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (BookDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            BookDataBase.class, "book_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}

