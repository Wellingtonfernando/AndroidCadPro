package com.example.wellingtonfernando.cadprod.model;

import java.util.Date;

public class Excluir {
    private long id;
    private Produto excluiProduto;
    private Date dataExcuir;
    private String funcionario;

    public Excluir() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Produto getExcluiProduto() {
        return excluiProduto;
    }

    public void setExcluiProduto(Produto excluiProduto) {
        this.excluiProduto = excluiProduto;
    }

    public Date getDataExcuir() {
        return dataExcuir;
    }

    public void setDataExcuir(Date dataExcuir) {
        this.dataExcuir = dataExcuir;
    }

    public String getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(String funcionario) {
        this.funcionario = funcionario;
    }

    @Override
    public String toString() {
        return "Excluir{" +
                "id=" + id +
                ", excluiProduto=" + excluiProduto.toString() +
                ", dataExcuir=" + dataExcuir +
                ", funcionario='" + funcionario + '\'' +
                '}';
    }
}
