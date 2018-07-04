package com.example.wellingtonfernando.cadprod.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wellingtonfernando.cadprod.R;
import com.example.wellingtonfernando.cadprod.controller.ProdutoCtrl;
import com.example.wellingtonfernando.cadprod.dbCadProd.conexaoSQLite;
import com.example.wellingtonfernando.cadprod.model.Produto;

public class ActivityEditarProduto extends AppCompatActivity {

    private EditText edtIdProduto;
    private EditText edtNomeProduto;
    private EditText edtPrecoProduto;
    private EditText edtquantidadeProduto;
    private EditText edtNomeFuncionario;

    private Produto produto;

    private Button btnSalvarAlteracoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_produto);

        edtIdProduto = (EditText) findViewById(R.id.edtIdProduto);
        edtNomeProduto = (EditText) findViewById(R.id.edtNomeProduto);
        edtquantidadeProduto = (EditText) findViewById(R.id.edtquantidadeProduto);
        edtPrecoProduto = (EditText) findViewById(R.id.edtPrecoProduto);
        edtNomeFuncionario = (EditText) findViewById(R.id.edtNomeFuncionario);

        this.btnSalvarAlteracoes = (Button) findViewById(R.id.btnSalvarProduto);

        Bundle bundleDadosProduto = getIntent().getExtras();

        Produto produto = new Produto();

        produto.setId(bundleDadosProduto.getLong("id_prod"));
        produto.setNomeProd(bundleDadosProduto.getString("nomeProd"));
        produto.setPreco(bundleDadosProduto.getDouble("precoProduto"));
        produto.setQuantidadeEmEstoque(bundleDadosProduto.getInt("quantidadeEmEstoque"));
        produto.setNomeFuncionario(bundleDadosProduto.getString("nomeFuncionario"));

        this.setDadosProduto(produto);

        this.clickNoBotaoSalvarListener();
    }

    private void setDadosProduto(Produto produto){
        this.edtIdProduto.setText(String.valueOf(produto.getId()));
        this.edtNomeProduto.setText(produto.getNomeProd());
        this.edtPrecoProduto.setText(String.valueOf(produto.getPreco()));
        this.edtquantidadeProduto.setText(String.valueOf(produto.getQuantidadeEmEstoque()));
        this.edtNomeFuncionario.setText(produto.getNomeFuncionario());
    }

    private Produto getDadosProdutoDoFormulario()
    {
        this.produto = new Produto();
        if (this.edtIdProduto.getText().toString().isEmpty() == false){
            this.produto.setId(Long.parseLong(this.edtIdProduto.getText().toString()));
        }
        else {
            return null;
        }

        if(this.edtNomeProduto.getText().toString().isEmpty() == false){
            this.produto.setNomeProd(this.edtNomeProduto.getText().toString());
        }
        else {
            return null;
        }

        if (this.edtquantidadeProduto.getText().toString().isEmpty() == false){
            int quantidadeProduto = Integer.parseInt(this.edtquantidadeProduto.getText().toString());
            this.produto.setQuantidadeEmEstoque(quantidadeProduto);
        }
        else {
            return null;
        }

        if (this.edtPrecoProduto.getText().toString().isEmpty() == false){
            double precoProduto = Double.parseDouble(this.edtPrecoProduto.getText().toString());
            this.produto.setPreco(precoProduto);
        }
        else {
            return null;
        }

        if (this.edtNomeFuncionario.getText().toString().isEmpty() == false){
            this.produto.setNomeFuncionario(this.edtNomeFuncionario.getText().toString());
        }
        else {
            return null;
        }
        return produto;

    }

    private void clickNoBotaoSalvarListener(){
        this.btnSalvarAlteracoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Produto produtoAAtualizar = getDadosProdutoDoFormulario();
                if (produtoAAtualizar != null){

                    ProdutoCtrl produtoCtrl = new ProdutoCtrl(conexaoSQLite.getInstancia(ActivityEditarProduto.this));
                    boolean atualizou = produtoCtrl.atualizarProdutoCtrl(produtoAAtualizar);

                    if (atualizou == true){
                        Toast.makeText(ActivityEditarProduto.this, "Produto alterado com sucesso", Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(ActivityEditarProduto.this, "Produto não pode ser alterado", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(ActivityEditarProduto.this, "Todos os campos são obrigatórios ", Toast.LENGTH_LONG).show();
               }
            }
        });
    }
}
