package com.ashunevich.booktracker;

import android.content.Context;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;

public class BookRepository {
    private BookDAO mBookDAO;
    private LiveData<List<BookItem>> mAllBooks;

    @Inject
    public BookRepository(Context context) {
        BookDataBase db = BookDataBase.getDatabase(context);
        mBookDAO = db.bookDAO();
        mAllBooks = mBookDAO.getAlphabetizedWords();
    }

    public LiveData<List<BookItem>> getAllBooks() {
        return mAllBooks;
    }

    public void insert(BookItem bookItem) {
        BookDataBase.databaseWriteExecutor.execute(() -> {
            mBookDAO.insert(bookItem);
        });
    }


    public void deleteBookItme(int bookId) {
        BookDataBase.databaseWriteExecutor.execute(() -> {
            mBookDAO.delete(bookId);
        });
    }
}
