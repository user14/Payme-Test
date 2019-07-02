package com.pooja.payme_test.DI;


import com.pooja.payme_test.api.ApiRepository;

import javax.inject.Singleton;

import dagger.Component;

/*
    Dagger Component interface to to provide ApiRepository object
 */
@Singleton
@Component (modules = RetrofitModule.class)
public interface ApiRepositoryComponent {

    ApiRepository getApiRepository();
}
