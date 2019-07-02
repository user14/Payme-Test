package com.pooja.payme_test.api;

import com.pooja.payme_test.model.Book;
import com.pooja.payme_test.model.ApiResponse;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/*
    Interface to define all api signature
 */
public interface PaymeApi {

    /*
        Signin
     */
    @Headers({
            "Content-Type: application/x-www-form-urlencoded",

    })
    @FormUrlEncoded
    @POST("api/signin")
    Call<ApiResponse> login(

            @Field("username") String username,
            @Field("password") String password
    );

    /*
        Signup
     */
    @FormUrlEncoded
    @POST("api/signup")
    Call<ApiResponse> signup(

            @Field("username") String username,
            @Field("password") String password
    );

    /*
        Get List of Books
     */
    @Headers({
            "Content-Type: application/x-www-form-urlencoded",
            "Authorization: JWT eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyIkX18iOnsic3RyaWN0TW9kZSI6dHJ1ZSwic2VsZWN0ZWQiOnt9LCJnZXR0ZXJzIjp7fSwid2FzUG9wdWxhdGVkIjpmYWxzZSwiYWN0aXZlUGF0aHMiOnsicGF0aHMiOnsicGFzc3dvcmQiOiJpbml0IiwidXNlcm5hbWUiOiJpbml0IiwiX192IjoiaW5pdCIsIl9pZCI6ImluaXQifSwic3RhdGVzIjp7Imlnbm9yZSI6e30sImRlZmF1bHQiOnt9LCJpbml0Ijp7Il9fdiI6dHJ1ZSwicGFzc3dvcmQiOnRydWUsInVzZXJuYW1lIjp0cnVlLCJfaWQiOnRydWV9LCJtb2RpZnkiOnt9LCJyZXF1aXJlIjp7fX0sInN0YXRlTmFtZXMiOlsicmVxdWlyZSIsIm1vZGlmeSIsImluaXQiLCJkZWZhdWx0IiwiaWdub3JlIl19LCJlbWl0dGVyIjp7ImRvbWFpbiI6bnVsbCwiX2V2ZW50cyI6e30sIl9ldmVudHNDb3VudCI6MCwiX21heExpc3RlbmVycyI6MH19LCJpc05ldyI6ZmFsc2UsIl9kb2MiOnsiX192IjowLCJwYXNzd29yZCI6IiQyYSQxMCRVZW9JeVlsYTRaZjdLLk54YlFLbGUuTEc5WXJEZlRiNWNVa3JndmQ1RGo3S0FvWUxHOWhiYSIsInVzZXJuYW1lIjoiaHNiYyIsIl9pZCI6IjVkMTA4MmI4Nzc0NTk2MDAxYWRkMDkxNSJ9LCJpYXQiOjE1NjEzNjkyMDB9.kw0b0bWaAncWq38XcK45OWhuAupS5fzZ3Ez8REhmlP4"
    })
    @GET("api/book")
    Call<List<Book>> getBooks();



    /*
        Create new Book
     */
    @Headers({
            "Content-Type: application/x-www-form-urlencoded",
            "Authorization: JWT eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyIkX18iOnsic3RyaWN0TW9kZSI6dHJ1ZSwic2VsZWN0ZWQiOnt9LCJnZXR0ZXJzIjp7fSwid2FzUG9wdWxhdGVkIjpmYWxzZSwiYWN0aXZlUGF0aHMiOnsicGF0aHMiOnsicGFzc3dvcmQiOiJpbml0IiwidXNlcm5hbWUiOiJpbml0IiwiX192IjoiaW5pdCIsIl9pZCI6ImluaXQifSwic3RhdGVzIjp7Imlnbm9yZSI6e30sImRlZmF1bHQiOnt9LCJpbml0Ijp7Il9fdiI6dHJ1ZSwicGFzc3dvcmQiOnRydWUsInVzZXJuYW1lIjp0cnVlLCJfaWQiOnRydWV9LCJtb2RpZnkiOnt9LCJyZXF1aXJlIjp7fX0sInN0YXRlTmFtZXMiOlsicmVxdWlyZSIsIm1vZGlmeSIsImluaXQiLCJkZWZhdWx0IiwiaWdub3JlIl19LCJlbWl0dGVyIjp7ImRvbWFpbiI6bnVsbCwiX2V2ZW50cyI6e30sIl9ldmVudHNDb3VudCI6MCwiX21heExpc3RlbmVycyI6MH19LCJpc05ldyI6ZmFsc2UsIl9kb2MiOnsiX192IjowLCJwYXNzd29yZCI6IiQyYSQxMCRVZW9JeVlsYTRaZjdLLk54YlFLbGUuTEc5WXJEZlRiNWNVa3JndmQ1RGo3S0FvWUxHOWhiYSIsInVzZXJuYW1lIjoiaHNiYyIsIl9pZCI6IjVkMTA4MmI4Nzc0NTk2MDAxYWRkMDkxNSJ9LCJpYXQiOjE1NjEzNjkyMDB9.kw0b0bWaAncWq38XcK45OWhuAupS5fzZ3Ez8REhmlP4"
    })
    @FormUrlEncoded
    @POST("api/book")
    Call<ApiResponse> postBook(
            @Field("isbn") String isbn,
            @Field("title") String title,
            @Field("author") String author,
            @Field("publisher") String publisher,
            @Field("image") String image
     );
}
