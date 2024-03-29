package com.pooja.payme_test.activity;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.pooja.payme_test.DI.ApiRepositoryComponent;
import com.pooja.payme_test.DI.DaggerApiRepositoryComponent;
import com.pooja.payme_test.R;
import com.pooja.payme_test.api.ApiRepository;

import java.io.IOException;

/*
    BaseActivity class for some common utility methods
 */
public abstract class BaseActivity extends AppCompatActivity {

    // Logger for this class.
    private static final String TAG = "BaseActivity";

    private ProgressDialog mProgressDialog;

    // ApiRepository Class to provide all api services
    ApiRepository apiRepository;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Used DaggerApiRepositoryComponent to instantiate Api Repository
        ApiRepositoryComponent apiRepositoryComponent = DaggerApiRepositoryComponent.create();

        apiRepository = apiRepositoryComponent.getApiRepository();

    }
     /*
        Hide the soft keyboard from active screen
     */
    public void hideKeyboard() {
        try {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            if (getCurrentFocus() != null)
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    /*
        Show soft keyboard
     */
    public void showKeyboard(View view) {
        try {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            if (getCurrentFocus() != null)

                imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    /*
        Check if Internet connection is working
     */
    public boolean isInternetAvailable() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int     exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        }
        catch (IOException e)          { e.printStackTrace(); }
        catch (InterruptedException e) { e.printStackTrace(); }

        return false;
    }

    /*
        Show Alert Dialog
     */
    protected void showAlertDialog(String title,String msg) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle(title);
        dialogBuilder.setIcon(R.drawable.idea);
        dialogBuilder.setMessage(msg);
        dialogBuilder.setPositiveButton(getString(R.string.dialog_ok_btn), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        dialogBuilder.setCancelable(false);
        dialogBuilder.show();
    }


    /*
        Show progress Dialog
     */
    public void showProgressDialog(String title, String message) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            if (title != null)
                mProgressDialog.setTitle(title);
            mProgressDialog.setIcon(R.mipmap.ic_launcher);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setCancelable(false);
        }
        if (!mProgressDialog.isShowing()) {
            mProgressDialog.setMessage(message);
            mProgressDialog.show();
        }
    }

    /*
        Hide running Progress Dialog
     */
    public void hideDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }


}
