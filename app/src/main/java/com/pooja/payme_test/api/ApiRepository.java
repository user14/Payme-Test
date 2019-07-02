package com.pooja.payme_test.api;

import com.pooja.payme_test.model.Book;
import com.pooja.payme_test.model.ApiResponse;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/*
    This class will provide all api response
 */


public class ApiRepository {

    private static final String TAG = "ApiRepository";
    public PaymeApi api;



    @Inject
    public ApiRepository(Retrofit retrofit) {
        this.api = retrofit.create(PaymeApi.class);
    }



    /*
        sigin method - this will call signin api and update the OnApiCallback of calling object
        @params - username and password , OnApiCallback
     */
    public void signin(String username, String password, final OnApiCallback apiCallback)
    {
            api.login(username, password)
                    .enqueue(new Callback<ApiResponse>() {
                        @Override
                        public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                            if(response.isSuccessful())
                            {
                                ApiResponse apiResponse = response.body();
                                if(apiResponse !=null)
                                {
                                    apiCallback.onSuccess(apiResponse);
                                }
                                else
                                {
                                    apiCallback.onError(response.message());
                                }
                            }
                            else
                                apiCallback.onError(response.message());

                        }

                        @Override
                        public void onFailure(Call<ApiResponse> call, Throwable t) {
                            apiCallback.onError(t.getLocalizedMessage());
                        }
                    });
    }


    /*
        getbooks method - this will call Get book api and update the OnApiCallback of calling object
        @param - OnApiCallback
     */
    public void getBooks(final OnApiCallback apiCallback)
    {
        api.getBooks()
                .enqueue(new Callback<List<Book>>() {
                    @Override
                    public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                        if(response.isSuccessful())
                        {

                            if(response!=null)
                            {
                                List<Book> books = response.body();
                                apiCallback.onSuccessBook(books);
                            }
                            else
                            {
                                apiCallback.onError(response.message());
                            }
                        }
                        else
                            apiCallback.onError(response.message());

                    }

                    @Override
                    public void onFailure(Call<List<Book>> call, Throwable t) {
                        apiCallback.onError(t.getLocalizedMessage());
                    }
                });
    }


    /*
        createBook method - this will call post book api and update the OnApiCallback of calling object
        @params - Book , OnApiCallback
     */
    public void createBook(Book book, final OnApiCallback apiCallback)
    {
        api.postBook(book.getIsbn(),book.getTitle(),book.getAuthor(),book.getPublisher(),book.getImage())
                .enqueue(new Callback<ApiResponse>() {
                    @Override
                    public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                        if(response.isSuccessful())
                        {
                            ApiResponse apiResponse = response.body();
                            if(apiResponse !=null)
                            {
                                apiCallback.onSuccess(apiResponse);
                            }
                            else
                            {
                                apiCallback.onError(response.message());
                            }
                        }
                        else
                            apiCallback.onError(response.message());

                    }

                    @Override
                    public void onFailure(Call<ApiResponse> call, Throwable t) {
                        apiCallback.onError(t.getLocalizedMessage());
                    }
                });
    }


}
