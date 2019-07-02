package com.pooja.payme_test.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.pooja.payme_test.DI.ApiRepositoryComponent;
import com.pooja.payme_test.DI.DaggerApiRepositoryComponent;
import com.pooja.payme_test.R;
import com.pooja.payme_test.api.ApiRepository;
import com.pooja.payme_test.api.OnApiCallback;
import com.pooja.payme_test.model.ApiResponse;
import com.pooja.payme_test.api.PaymeApi;
import com.pooja.payme_test.model.Book;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/*
    LoginActivity class to do user login
 */
public class LoginActivity extends BaseActivity {

    /*
    @BindView annotation is used to initialize views
     */
    @Nullable
    @BindView(R.id.username)
    EditText editTextUsername;

    @Nullable
    @BindView(R.id.password)
    EditText editTextPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

   /*
        Below call initializes Butterknife library to find and process annotated fields and
        methods in the current activity. Current activity layout is used as the view root.
         */
        ButterKnife.bind(this);

    }

    /*
        onClick method - this method calls when user click on login button
        @param View
     */
    @OnClick(R.id.login)
    public void onClick() {
        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();

        if(isInternetAvailable()) {
            if (validateLogin(username, password)) {
                showProgressDialog(getResources().getString(R.string.app_name), getResources().getString(R.string.loading));
                doLogin(username, password);
            }
        }
        else
            showAlertDialog(getResources().getString(R.string.alert), getResources().getString(R.string.internet_issue));

    }

    /*
        doLogin method - this method call the user signin api from Api repository and display result
        @param username and password
     */

    private void doLogin(String username, String password) {

         apiRepository.signin(username, password, new OnApiCallback() {
            @Override
            public void onSuccess(ApiResponse apiResponse) {
                hideDialog();
                if(apiResponse.getSuccess().equals("true")) {

                    Toast.makeText(LoginActivity.this, getResources().getString(R.string.login_pass_message), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, BooksActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    if(apiResponse.getMessage()==null)
                        showAlertDialog(getResources().getString(R.string.alert), getResources().getString(R.string.login_failed_message));
                    else
                        showAlertDialog(getResources().getString(R.string.alert), apiResponse.getMessage());
                }
            }

            @Override
            public void onSuccessBook(List<Book> books) {// ignore
                 }

            @Override
            public void onError(String message) {
                hideDialog();
                showAlertDialog(getResources().getString(R.string.alert),message);
            }
        });

    }

    /*
       validateLogin method - this method do input text field validation check
       @param - username and password
       @return boolean - if input is valid than true else false
    */
    private boolean validateLogin(String username, String password){
        if(username == null || username.trim().length() == 0){
            showAlertDialog(getResources().getString(R.string.alert), getResources().getString(R.string.username_is_required));
            return false;
        }
        if(password == null || password.trim().length() == 0){
            showAlertDialog(getResources().getString(R.string.alert), getResources().getString(R.string.password_is_required));
            return false;
        }
        return true;
    }
}
