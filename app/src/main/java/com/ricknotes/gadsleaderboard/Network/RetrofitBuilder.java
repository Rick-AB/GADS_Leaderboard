package com.ricknotes.gadsleaderboard.Network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.ricknotes.gadsleaderboard.Utils.Constants.BASE_URL;

public class RetrofitBuilder {
    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());
    private static Retrofit mRetrofit = builder.build();
    
    public static  <S> S buildApi(Class<S> apiType){
        return mRetrofit.create(apiType);
    }
    
}
