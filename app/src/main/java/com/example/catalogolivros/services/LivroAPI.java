package com.example.catalogolivros.services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LivroAPI {

    private static LivroService instance;
    static String BASE_API = "http://biblio.aguiar.pro.br";

    public static LivroService getInstance() {
        if (instance == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_API)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            instance = retrofit.create(LivroService.class);
        }
        return instance;
    }
}
