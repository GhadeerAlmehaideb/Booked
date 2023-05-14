package com.example.myapplication;

import android.widget.EditText;

public class Book {


    private String isbn;
    private String bookName;
    private String pubYear;
    private String Author;
    private Float price;


    public Book(String bookName, String isbn, String author, String pubYear, Float price) {
        this.isbn = isbn;
        this.bookName = bookName;
        this.pubYear = pubYear;
        Author = author;
        this.price = price;
    }

    public Book(String isbn, String bookName, String pubYear, String author, EditText et_price) {
    }

    public String getIsbn() {
        return isbn;
    }

    public String getBookName() {
        return bookName;
    }

    public String getPubYear() {
        return pubYear;
    }

    public String getAuthor() {
        return Author;
    }

    public Float getPrice() {
        return price;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setPubYear(String pubYear) {
        this.pubYear = pubYear;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return
                "Book Name:" + bookName +
                "\nISBN:" + isbn +
                "\nYoP:" + pubYear  +
                "\nAuthor:" + Author  +
                "\nPrice:" + price;
    }
}


