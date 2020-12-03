package com.ashunevich.booktracker;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BookDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert (BookItem word);

    @Query("DELETE FROM book_model WHERE idNumber = :id")
    void delete(int id);
    

    @Query("SELECT * FROM book_model")
    List<BookItem> findallBooks();

    @Update(onConflict = OnConflictStrategy.REPLACE )
    void updateBook(BookItem bookItem);

    @Query("SELECT * from book_model ORDER BY idNumber DESC")
    LiveData<List<BookItem>> getAlphabetizedWords();


}
