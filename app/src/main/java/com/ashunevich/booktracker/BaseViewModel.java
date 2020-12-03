package com.ashunevich.booktracker;

import androidx.lifecycle.ViewModel;

public abstract class BaseViewModel extends ViewModel {
    private final BookRepository mBookRepository;

    public BaseViewModel(BookRepository bookRepository){
        this.mBookRepository = bookRepository;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    public BookRepository getmBookRepository() {
        return mBookRepository;
    }
}
