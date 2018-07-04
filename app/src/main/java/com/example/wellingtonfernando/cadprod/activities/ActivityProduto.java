package com.example.wellingtonfernando.cadprod.activities;

import android.content.Intent;
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

public class ActivityProduto extends AppCompatActivity {

    protected EditText edtIdProduto;
    private EditText edtNomeProduto;
    private EditText edtquantidadeProduto;
    private EditText edtPrecoProduto;
    private EditText edtNomeFuncionario;

    private Button btnSalvarProduto;
    private Button btnEscanerCodigo;


    private Produto produto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);


        edtIdProduto = (EditText) findViewById(R.id.edtIdProduto);
        edtNomeProduto = (EditText) findViewById(R.id.edtNomeProduto);
        edtquantidadeProduto = (EditText) findViewById(R.id.edtquantidadeProduto);
        edtPrecoProduto = (EditText) findViewById(R.id.edtPrecoProduto);
        edtNomeFuncionario = (EditText) findViewById(R.id.edtNomeFuncionario);

        btnEscanerCodigo = (Button) findViewById(R.id.btnCapturarCodigo);
        btnSalvarProduto = (Button) findViewById(R.id.btnSalvarProduto);
        this.clickNoBotaoSalvarListener();

        btnEscanerCodigo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.google.zxing.client.android.SCAN");
                startActivityForResult(intent,0);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == 0){
            edtIdProduto.setText(data.getStringExtra("SCAN_RESULT"));
        }
    }
    private void clickNoBotaoSalvarListener(){
        this.btnSalvarProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Produto produtoACadastrar = getDadosProdutoDoFormulario();
                if (produtoACadastrar != null){

                    ProdutoCtrl produtoCtrl = new ProdutoCtrl(conexaoSQLite.getInstancia(ActivityProduto.this));
                    long idProduto = produtoCtrl.salvarProdutoCtrl(produtoACadastrar);

                    if (idProduto > 0){
                        Toast.makeText(ActivityProduto.this, "Produto cadastrado com sucesso", Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(ActivityProduto.this, "Produto não pode ser cadastrado", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(ActivityProduto.this, "Todos os campos são obrigatórios ", Toast.LENGTH_LONG).show();
                }
            }

        });
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
}
