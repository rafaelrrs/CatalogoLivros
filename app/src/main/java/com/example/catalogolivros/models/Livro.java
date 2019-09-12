package com.example.catalogolivros.models;

import java.io.Serializable;

public class Livro implements Serializable {
    private int id;
    private String titulo, resumo, paginas, edicao, ano, isbn;

    public Livro() {
    }

    public Livro(String titulo, String resumo, String paginas, String edicao, String ano, String isbn) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.paginas = paginas;
        this.edicao = edicao;
        this.ano = ano;
        this.isbn = isbn;
    }

    public Livro(int id, String titulo, String resumo, String paginas, String edicao, String ano, String isbn) {
        this.id = id;
        this.titulo = titulo;
        this.resumo = resumo;
        this.paginas = paginas;
        this.edicao = edicao;
        this.ano = ano;
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return isbn +
                "\n\t" + titulo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public String getPaginas() {
        return paginas;
    }

    public void setPaginas(String paginas) {
        this.paginas = paginas;
    }

    public String getEdicao() {
        return edicao;
    }

    public void setEdicao(String edicao) {
        this.edicao = edicao;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
