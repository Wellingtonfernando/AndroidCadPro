package com.example.wellingtonfernando.cadprod.model;

public class Produto {
    private long id;
    private String nomeProd;
    private String nomeFuncionario;
    private int quantidadeEmEstoque;
    private double preco;

    public Produto() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomeProd() {
        return nomeProd;
    }

    public void setNomeProd(String nomeProd) {
        this.nomeProd = nomeProd;
    }

    public  String getNomeFuncionario(){return nomeFuncionario;}

    public void setNomeFuncionario(String nomeFuncionario){this.nomeFuncionario = nomeFuncionario;}

    public int getQuantidadeEmEstoque() {
        return quantidadeEmEstoque;
    }

    public void setQuantidadeEmEstoque(int quantidadeEmEstoque) {
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nomeProd='" + nomeProd + '\'' +
                ", nomeFuncionario='" + nomeFuncionario + '\'' +
                ", quantidadeEmEstoque=" + quantidadeEmEstoque +
                ", preco=" + preco +
                '}';
    }
}
