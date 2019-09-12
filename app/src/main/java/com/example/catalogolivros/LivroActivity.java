package com.example.catalogolivros;

import android.content.Intent;
import android.os.Bundle;

import com.example.catalogolivros.models.Livro;
import com.example.catalogolivros.services.LivroAPI;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LivroActivity extends AppCompatActivity {
    Livro livroInfo;
    int livroID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livro);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        int livroID = getIntent().getIntExtra("livro_id", 0);
//        Livro livro = (Livro) getIntent().getSerializableExtra("livro");
//
//        Toast.makeText(getBaseContext(),
//                String.valueOf(livroID),
//                Toast.LENGTH_LONG).show();

        Call<Livro> livro = LivroAPI.getInstance().show(livroID);

        livro.enqueue(new Callback<Livro>() {
            @Override
            public void onResponse(Call<Livro> call, Response<Livro> response) {
                livroInfo = response.body();
                ((TextView) findViewById(R.id.txtTitulo)).setText(response.body().getTitulo());
                ((TextView) findViewById(R.id.txtResumo)).setText(response.body().getResumo());
                ((TextView) findViewById(R.id.txtPaginas)).setText(response.body().getPaginas());
                ((TextView) findViewById(R.id.txtAno)).setText(response.body().getAno());
                ((TextView) findViewById(R.id.txtEdicao)).setText(response.body().getEdicao());
                ((TextView) findViewById(R.id.txtIsbn)).setText(response.body().getIsbn());
            }

            @Override
            public void onFailure(Call<Livro> call, Throwable t) {

            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Intent intent = new Intent(LivroActivity.this,FormLivroActivity.class);
                intent.putExtra("livro", livroInfo);
                startActivity(intent);
            }
        });
    }

    public void deletarLivro(View view) {
        Livro livroADeletar = new Livro(
                ((EditText) findViewById(R.id.txtVwFormLivroTitulo)).getText().toString(),
                ((EditText) findViewById(R.id.txtVwFormLivroResumo)).getText().toString(),
                ((EditText) findViewById(R.id.txtVwFormLivroPaginas)).getText().toString(),
                ((EditText) findViewById(R.id.txtVwFormLivroEdicao)).getText().toString(),
                ((EditText) findViewById(R.id.txtVwFormLivroAno)).getText().toString(),
                ((EditText) findViewById(R.id.txtVwFormLivroIsbn)).getText().toString()
        );

        Call<Livro> calllLivro;

        calllLivro = LivroAPI.getInstance().delete(livroADeletar);

    }
}
