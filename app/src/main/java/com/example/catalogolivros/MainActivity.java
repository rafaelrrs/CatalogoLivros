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
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ProgressBar pgrBarContentMain;
    ListView lstVwLivrosIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        File file = new File(getFilesDir(), "texte.txt");
//        try {
//            FileOutputStream bos =
//                    new FileOutputStream(file, true);
//            bos.write("Teste\nteste".getBytes());
//            bos.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        pgrBarContentMain = findViewById(R.id.pgrBarContentMain);
        pgrBarContentMain.setVisibility(View.VISIBLE);
        lstVwLivrosIndex = findViewById(R.id.lstVwLivrosIndex);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FormLivroActivity.class);
                startActivity(intent);

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        Call<List<Livro>> livros = LivroAPI.getInstance().list();

        livros.enqueue(new Callback<List<Livro>>() {
            @Override
            public void onResponse(
                    Call<List<Livro>> call,
                    Response<List<Livro>> response) {
                List<Livro> livrosRetornados = new ArrayList<>();
                for (Livro lv : response.body()){
                    livrosRetornados.add(lv);
                }
                ArrayAdapter<Livro> arrayAdapter = new ArrayAdapter<>(getBaseContext(),
                        android.R.layout.simple_list_item_1, livrosRetornados);
                lstVwLivrosIndex.setAdapter(arrayAdapter);
                lstVwLivrosIndex.setOnItemClickListener((adapterView, view, i, l) -> {
//                    Toast.makeText(getBaseContext(),
//                            String.valueOf(livrosRetornados.get(i).getId()),
//                                    Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, LivroActivity.class);
                    intent.putExtra("livro", livrosRetornados.get(i));
                    intent.putExtra(
                            "livro_id",
                            livrosRetornados.get(i).getId());
                    startActivity(intent);

//                    Call<Livro> livro = LivroAPI.getInstance().show(livros.get(i).getId());
//                    livro.enqueue(new Callback<Livro>() {
//                        @Override
//                        public void onResponse(Call<Livro> call, Response<Livro> response) {
//                            Toast.makeText(getBaseContext(),
//                                    response.body().getTitulo().toString(),
//                                    Toast.LENGTH_LONG).show();
//                        }
//
//                        @Override
//                        public void onFailure(Call<Livro> call, Throwable t) {
//
//                        }
//                    });
                });
                pgrBarContentMain.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<List<Livro>> call, Throwable t) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
