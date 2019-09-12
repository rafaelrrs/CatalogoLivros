package com.example.catalogolivros;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.catalogolivros.models.Livro;
import com.example.catalogolivros.services.LivroAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormLivroActivity extends AppCompatActivity {

    int livroID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_livro);

        Livro livroASalvar = new Livro();

        LivroAPI.getInstance().store(livroASalvar).enqueue(new Callback<Livro>() {
            @Override
            public void onResponse(Call<Livro> call, Response<Livro> response) {
                // MSG de sucesso
            }
            @Override
            public void onFailure(Call<Livro> call, Throwable t) {
                // t.getMessage()
            }
        });
        //String receberLivro = getIntent().getStringExtra("livro", );


        if (getIntent().hasExtra("livro")){
            Livro livroEdicao = (Livro) getIntent().getSerializableExtra("livro");
            livroID = livroEdicao.getId();
            ((EditText) findViewById(R.id.txtVwFormLivroTitulo)).setText(livroEdicao.getTitulo());
            ((EditText) findViewById(R.id.txtVwFormLivroResumo)).setText(livroEdicao.getResumo());
            ((EditText) findViewById(R.id.txtVwFormLivroPaginas)).setText(livroEdicao.getPaginas());
            ((EditText) findViewById(R.id.txtVwFormLivroEdicao)).setText(livroEdicao.getEdicao());
            ((EditText) findViewById(R.id.txtVwFormLivroAno)).setText(livroEdicao.getAno());
            ((EditText) findViewById(R.id.txtVwFormLivroIsbn)).setText(livroEdicao.getIsbn());
        }

    }


    public void actionFormLivroSalvar(View view){
        Livro livroASalvar = new Livro(
                ((EditText) findViewById(R.id.txtVwFormLivroTitulo)).getText().toString(),
                ((EditText) findViewById(R.id.txtVwFormLivroResumo)).getText().toString(),
                ((EditText) findViewById(R.id.txtVwFormLivroPaginas)).getText().toString(),
                ((EditText) findViewById(R.id.txtVwFormLivroEdicao)).getText().toString(),
                ((EditText) findViewById(R.id.txtVwFormLivroAno)).getText().toString(),
                ((EditText) findViewById(R.id.txtVwFormLivroIsbn)).getText().toString()
        );

        Call<Livro> calllLivro;
        if(livroID > 0)
            calllLivro = LivroAPI.getInstance().update(livroID, livroASalvar);
        else
            calllLivro = LivroAPI.getInstance().store(livroASalvar);

        calllLivro.enqueue(new Callback<Livro>() {
            @Override
            public void onResponse(Call<Livro> call, Response<Livro> response) {
                if (response.message().equalsIgnoreCase("created")){
                    Toast.makeText(getBaseContext(), "Livro cadastrado com sucesso.", Toast.LENGTH_LONG).show();
                } else if (response.message().equalsIgnoreCase("OK")){
                    Toast.makeText(getBaseContext(), "Livro ATUALIZADO com sucesso.", Toast.LENGTH_LONG).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Livro> call, Throwable t) {
                Toast.makeText(getBaseContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

//        LivroAPI.getInstance().store(livroASalvar).enqueue(new Callback<Livro>() {
//            @Override
//            public void onResponse(Call<Livro> call, Response<Livro> response) {
//                if (response.message().equalsIgnoreCase("created")){
//                    Toast.makeText(getBaseContext(), "Livro cadastrado com sucesso.", Toast.LENGTH_LONG).show();
//                }
//
//            }
//            @Override
//            public void onFailure(Call<Livro> call, Throwable t) {
//                Toast.makeText(getBaseContext(), t.getMessage(), Toast.LENGTH_LONG).show();
//            }
//        });
    }
}
