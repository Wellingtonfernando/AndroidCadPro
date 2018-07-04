package com.example.wellingtonfernando.cadprod.controller;

import com.example.wellingtonfernando.cadprod.DAO.ProdutoDAO;
import com.example.wellingtonfernando.cadprod.dbCadProd.conexaoSQLite;
import com.example.wellingtonfernando.cadprod.model.Produto;

import java.util.List;

public class ProdutoCtrl {

    private final ProdutoDAO produtoDAO;

    public ProdutoCtrl(conexaoSQLite pConexaoSQLite){
        produtoDAO = new ProdutoDAO(pConexaoSQLite);
    }

    public long salvarProdutoCtrl(Produto pProduto){
        return this.produtoDAO.salvarProdutoDAO(pProduto);
    }

    public List<Produto> getListaProdutoCtrl(){
        return this.produtoDAO.getListaProdutoDAO();
    }


    public boolean excluirProdutCtrl(long pidProduto){
        return this.produtoDAO.excluirProdutoDAO(pidProduto);
    }

    public boolean atualizarProdutoCtrl(Produto pProduto){
        return this.produtoDAO.atualizarProdutoDAO(pProduto);
    }
}
