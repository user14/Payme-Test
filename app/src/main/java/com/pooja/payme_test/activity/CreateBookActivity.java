package com.pooja.payme_test.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.pooja.payme_test.R;
import com.pooja.payme_test.api.ApiRepository;
import com.pooja.payme_test.api.OnApiCallback;
import com.pooja.payme_test.model.Book;
import com.pooja.payme_test.model.ApiResponse;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
    CreateBookActivity class create new book and post on api service
 */
public class CreateBookActivity extends BaseActivity {

    /*
    @BindView annotation is used to initialize views
     */
    @Nullable
    @BindView(R.id.editText_isbn)
    EditText editTextISBN;

    @Nullable
    @BindView(R.id.editText_title)
    EditText editTextTitle;

    @Nullable
    @BindView(R.id.editText_author)
    EditText editTextAuthor;

    @Nullable
    @BindView(R.id.editText_publisher)
    EditText editTextPublisher;

    @Nullable
    @BindView(R.id.editText_image)
    EditText editTextURL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_book);
        /*
        Below call initializes Butterknife library to find and process annotated fields and
        methods in the current activity. Current activity layout is used as the view root.
         */
        ButterKnife.bind(this);
    }

    /*
        submit method - this method calls when user click on create book button, it will call post book api and create new book
        @param View
     */
    @OnClick(R.id.button_submit)
    public void submit() {

        if(isInternetAvailable()) {
            Book book = new Book();
            book.setIsbn(editTextISBN.getText().toString());
            book.setTitle(editTextTitle.getText().toString());
            book.setAuthor(editTextAuthor.getText().toString());
            book.setPublisher(editTextPublisher.getText().toString());
            book.setImage(editTextURL.getText().toString());

            if(validateFields(book)) {
                showProgressDialog(getResources().getString(R.string.app_name), getResources().getString(R.string.loading));
                apiRepository.createBook(book, new OnApiCallback() {
                    @Override
                    public void onSuccess(ApiResponse apiResponse) {
                        hideDialog();
                        if (apiResponse.getSuccess().equals("true")) {
                            Toast.makeText(CreateBookActivity.this, apiResponse.getMessage(), Toast.LENGTH_LONG).show();
                            // clear all fields
                            editTextISBN.setText("");
                            editTextTitle.setText("");
                            editTextAuthor.setText("");
                            editTextPublisher.setText("");
                            editTextURL.setText("");
                        } else
                            showAlertDialog(getResources().getString(R.string.alert), apiResponse.getMessage());
                    }

                    @Override
                    public void onSuccessBook(List<Book> books) {// ignore
                    }

                    @Override
                    public void onError(String message) {
                        hideDialog();
                        showAlertDialog(getResources().getString(R.string.alert), message);

                    }
                });
            }


        }
        else
            showAlertDialog(getResources().getString(R.string.alert), getResources().getString(R.string.internet_issue));



    }

    /*
      validateFields method - this method do input text field validation check
      @param - Book
      @return boolean - if input is valid than true else false
   */
    private boolean validateFields(Book book){
        if(book.getIsbn() == null || book.getIsbn().trim().length() == 0){
            showAlertDialog(getResources().getString(R.string.alert), getResources().getString(R.string.isbn_is_required));
            return false;
        }
        if(book.getTitle() == null || book.getTitle().trim().length() == 0){
            showAlertDialog(getResources().getString(R.string.alert), getResources().getString(R.string.title_is_required));
            return false;
        }
        if(book.getAuthor() == null || book.getAuthor().trim().length() == 0){
            showAlertDialog(getResources().getString(R.string.alert), getResources().getString(R.string.author_is_required));
            return false;
        }
        if(book.getPublisher() == null || book.getPublisher().trim().length() == 0){
            showAlertDialog(getResources().getString(R.string.alert), getResources().getString(R.string.publisher_is_required));
            return false;
        }
        if(book.getImage() == null || book.getImage().trim().length() == 0){
            showAlertDialog(getResources().getString(R.string.alert), getResources().getString(R.string.url_is_required));
            return false;
        }
        return true;
    }
}
