package com.pooja.payme_test.api;


/*
    Callback interface to handle http request response
 */

import com.pooja.payme_test.model.Book;
import com.pooja.payme_test.model.ApiResponse;
import java.util.List;

public interface OnApiCallback {

    public void onSuccess(ApiResponse apiResponse);

    public void onSuccessBook(List<Book> books);

    public void onError(String message);
}
