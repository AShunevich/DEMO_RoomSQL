package com.ashunevich.booktracker;




import javax.inject.Inject;

public class MainActivityVm extends BaseViewModel {
    final BookRepository mBookRepository;

    @Inject
    public MainActivityVm (BookRepository BookRepository) {
        super(BookRepository);
        this.mBookRepository = BookRepository;
    }
}
