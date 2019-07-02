package com.pooja.payme_test.activity;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pooja.payme_test.adapter.BooksAdapter;
import com.pooja.payme_test.R;
import com.pooja.payme_test.api.ApiRepository;
import com.pooja.payme_test.api.OnApiCallback;
import com.pooja.payme_test.model.ApiResponse;
import com.pooja.payme_test.model.Book;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import java.util.List;

/*
    BooksActivity class display list of all books
 */
public class BooksActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private BooksAdapter booksAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        recyclerView = findViewById(R.id.recyclerView) ;

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        if(isInternetAvailable())
            getBooks();
        else
            showAlertDialog(getResources().getString(R.string.alert), getResources().getString(R.string.internet_issue));


        /*
            New book create
         */
        FloatingActionButton fab = findViewById(R.id.addBook);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BooksActivity.this, CreateBookActivity.class);
                startActivity(intent);
            }
        });
    }

    /*
        getBooks method - this method call the user book api from Api repository and display list of books

     */
    public void getBooks()
    {
        showProgressDialog(getResources().getString(R.string.app_name),getResources().getString(R.string.loading));
        apiRepository.getBooks(new OnApiCallback() {
            @Override
            public void onSuccess(ApiResponse apiResponse) {
                // ignore
            }

            @Override
            public void onSuccessBook(List<Book> books) {
                hideDialog();
                booksAdapter = new BooksAdapter(books);
                recyclerView.setAdapter(booksAdapter);

            }

            @Override
            public void onError(String message) {
                hideDialog();
                showAlertDialog(getResources().getString(R.string.alert),message);
            }
        });
    }


}
