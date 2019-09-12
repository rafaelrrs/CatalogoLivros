package com.example.catalogolivros.services;

import com.example.catalogolivros.models.Livro;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface LivroService {

    @GET("/api/livros")
    Call<List<Livro>> list();

    @GET("/api/livros/{id}")
    Call<Livro> show(@Path("id") int id);

    @POST("/api/livros")
    Call<Livro> store(@Body Livro livro);

    @PUT("/api/livros/{id}")
    Call<Livro> update(@Path("id") int id, @Body Livro livro);

    @DELETE("/api/livros/{id}")
    Call<Livro> delete(@Path("id") Livro id);
}
