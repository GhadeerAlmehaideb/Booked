package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity {
    Button btnLogout;
    FirebaseAuth mFirebaseAuth;
    Button btn_Add;
    ListView lv_BookList;
    EditText et_bname, et_isbn, et_author, et_pubYear,et_price;
    ArrayAdapter BookArrayAdapter;
    DatabaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btnLogout = findViewById(R.id.logout);
        btn_Add = findViewById(R.id.btn_Add);
        et_bname=findViewById(R.id.et_bname);
        et_isbn = findViewById(R.id.et_isbn);
        et_author=findViewById(R.id.et_author);
        et_pubYear = findViewById(R.id.et_pubYear);
        et_price=findViewById(R.id.et_price);
        lv_BookList = findViewById(R.id.lv_BookList);

        dataBaseHelper = new DatabaseHelper(Home.this);
        ShowBooksOnListView(dataBaseHelper);


        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intoMain= new Intent(Home.this,MainActivity.class);
                startActivity(intoMain);
            }
        });

        btn_Add.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                // create model
                Book book;
                try {
                    book = new Book(et_bname.getText().toString(),et_isbn.getText().toString(), et_author.getText().toString(), et_pubYear.getText().toString(), Float.valueOf(et_price.getText().toString()));
                    Toast.makeText(Home.this, book.toString(), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(Home.this, "Enter Valid input", Toast.LENGTH_SHORT).show();
                    book = new Book(et_bname.getText().toString(),et_isbn.getText().toString(), et_author.getText().toString(), et_pubYear.getText().toString(), Float.valueOf(et_price.getText().toString()));
                }

                DatabaseHelper dataBaseHelper = new DatabaseHelper(Home.this);
                boolean b = dataBaseHelper.addOne(book);
                Toast.makeText(Home.this, "SUCCESS= "+ b, Toast.LENGTH_SHORT).show();

                ShowBooksOnListView(dataBaseHelper);

            }
        });

        lv_BookList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Book ClickedBook = (Book) adapterView.getItemAtPosition(i);
                dataBaseHelper.DeleteOne(ClickedBook);
                ShowBooksOnListView(dataBaseHelper);
                Toast.makeText(Home.this, "Deleted" + ClickedBook.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void ShowBooksOnListView(DatabaseHelper dataBaseHelper) {
        BookArrayAdapter = new ArrayAdapter<Book>(Home.this, android.R.layout.simple_list_item_1, dataBaseHelper.getEverything());
        lv_BookList.setAdapter(BookArrayAdapter);
    }
}
