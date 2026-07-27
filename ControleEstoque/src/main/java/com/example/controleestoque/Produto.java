package com.example.controleestoque;

public class Produto {

    private int codigo;
    private String nome;
    private String categoria;
    private double preco;
    private int quantidade;
    private int estoque;

    public Produto(int codigo, String nome, String categoria, double preco, int quantidade, int estoque) {
        this.codigo = codigo;
        this.nome = nome;
        this.categoria = categoria;
        this.preco = preco;
        this.quantidade = quantidade;
        this.estoque = estoque;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public int getEstoque() {
        return estoque;
    }

    public double getValorEstoque() {
        return preco * quantidade;
    }
}
