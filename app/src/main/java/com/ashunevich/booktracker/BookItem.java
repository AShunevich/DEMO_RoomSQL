package com.ashunevich.booktracker;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "book_model")
public class BookItem implements Parcelable {
    public int getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    @PrimaryKey(autoGenerate = true)
    private int idNumber;
    @ColumnInfo(name = "bookName")
    private String bookName;

    public BookItem(String bookName, String bookAuthor, String bookType, String bookProgress) {
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookType = bookType;
        this.bookProgress = bookProgress;
    }

    @ColumnInfo(name = "bookAuthor")
    private String bookAuthor;
    @ColumnInfo(name = "bookType")
    private String bookType;
    @ColumnInfo(name = "bookSize")
    private String bookProgress;


    protected BookItem(Parcel in) {
        bookName = in.readString();
        bookAuthor = in.readString();
        bookType = in.readString();
        bookProgress = in.readString();
    }

    public BookItem(){};




    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(bookName);
        dest.writeString(bookAuthor);
        dest.writeString(bookType);
        dest.writeString(bookProgress);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<BookItem> CREATOR = new Creator<BookItem>() {
        @Override
        public BookItem createFromParcel(Parcel in) {
            return new BookItem(in);
        }

        @Override
        public BookItem[] newArray(int size) {
            return new BookItem[size];
        }
    };

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    public String getBookProgress() {
        return bookProgress;
    }

    public void setBookProgress(String bookProgress) {
        this.bookProgress = bookProgress;
    }





}
