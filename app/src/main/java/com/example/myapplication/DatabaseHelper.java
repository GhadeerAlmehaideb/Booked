package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String BOOK_TABLE = "Book_Table";
    public static final String COLUMN_BOOK_NAME = "Book_NAME";
    public static final String COLUMN_BOOK_ISBN = "BOOK_ISBN";
    public static final String COLUMN_AUTHOR = "AUTHOR_NAME";
    public static final String COLUMN_PUBYEAR = "PUB_YEAR";
    public static final String COLUMN_PRICE = "PRICE";


    public DatabaseHelper(@Nullable Context context) {
        super(context, "book.db", null, 1);
    }

    // when creating the database
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableStatement = "Create TABLE " + BOOK_TABLE + " (" + COLUMN_BOOK_NAME + " TEXT, " + COLUMN_BOOK_ISBN + " TEXT, " + COLUMN_AUTHOR + " TEXT, " + COLUMN_PUBYEAR + " TEXT, " + COLUMN_PRICE + " FLOAT )";
        sqLiteDatabase.execSQL(createTableStatement);

    }
    // when upgrading
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addOne(Book book){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();


        cv.put(COLUMN_BOOK_NAME, book.getBookName());
        cv.put(COLUMN_BOOK_ISBN, book.getIsbn());
        cv.put(COLUMN_AUTHOR, book.getAuthor());
        cv.put(COLUMN_PUBYEAR, book.getPubYear());
        cv.put(String.valueOf(COLUMN_PRICE), book.getPrice());


        long insert = db.insert(BOOK_TABLE, null, cv);
        if(insert == -1){
            return false;
        }
        else {
            return true;
        }
    }

    public boolean DeleteOne(Book book) {
        SQLiteDatabase db = this.getWritableDatabase();
        String whereClause = COLUMN_BOOK_ISBN + " = ?";
        String[] whereArgs = { book.getIsbn() };
        int rowsDeleted = db.delete(BOOK_TABLE, whereClause, whereArgs);
        db.close();
        return rowsDeleted > 0;
    }
    public List<Book> getEverything(){
        List<Book> returnList = new ArrayList<>();
        // get data from database
        String queryString = "Select * from "+ BOOK_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst()){
            // loop through cursor results
            do{
                String BName = cursor.getString(0);
                String ISBN = cursor.getString(1);
                String author = cursor.getString(2);
                String pubYear = cursor.getString(3);
                Float price = cursor.getFloat(4);

                Book newBook = new Book(BName,ISBN,author,pubYear,price);
                returnList.add(newBook);
            }while (cursor.moveToNext());
        } else{
            // nothing happens. no one is added.
        }
        //close
        cursor.close();
        db.close();
        return returnList;
    }
}