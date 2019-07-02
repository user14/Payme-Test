package com.pooja.payme_test.DI;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/*
    Dagger Module class to create Singleton Retrofit object
 */
@Module
public class RetrofitModule {
    private static final String BASE_URL = "http://13.70.7.71:8080/";

    @Singleton
    @Provides
    public static Retrofit provideRetrofit()
    {
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
