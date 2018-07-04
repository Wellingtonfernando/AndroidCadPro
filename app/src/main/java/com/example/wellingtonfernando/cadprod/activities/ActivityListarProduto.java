package com.example.wellingtonfernando.cadprod.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.wellingtonfernando.cadprod.R;
import com.example.wellingtonfernando.cadprod.adapters.AdapterListaProdutos;
import com.example.wellingtonfernando.cadprod.controller.ProdutoCtrl;
import com.example.wellingtonfernando.cadprod.dbCadProd.conexaoSQLite;
import com.example.wellingtonfernando.cadprod.model.Produto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ActivityListarProduto extends AppCompatActivity {
    private ListView lsvProduto;
    private List<Produto> produtoList;
    private AdapterListaProdutos adapterListaProdutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_produto);

        final ProdutoCtrl produtoCtrl = new ProdutoCtrl(conexaoSQLite.getInstancia(ActivityListarProduto.this));
        produtoList = produtoCtrl.getListaProdutoCtrl();

        this.lsvProduto = (ListView) findViewById(R.id.lsvProduto);

        this.adapterListaProdutos = new AdapterListaProdutos(ActivityListarProduto.this, this.produtoList);

        this.lsvProduto.setAdapter(this.adapterListaProdutos);

        this.lsvProduto.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int posicao, long id) {
                final Produto produtoSelecionado = (Produto) adapterListaProdutos.getItem(posicao);

                AlertDialog.Builder janelaDeEscolha = new AlertDialog.Builder(ActivityListarProduto.this);

                janelaDeEscolha.setTitle("Escolha: ");
                janelaDeEscolha.setMessage("Oque deseja Fazer com o produto?");
                janelaDeEscolha.setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

                janelaDeEscolha.setNegativeButton("Excluir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        boolean excluiu = produtoCtrl.excluirProdutCtrl(produtoSelecionado.getId());

                        dialog.cancel();
                        if (excluiu == true){
                            adapterListaProdutos.removerProduto(posicao);

                            Toast.makeText(ActivityListarProduto.this, "Produto excluido com suceeso", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(ActivityListarProduto.this, "Erro ao excluir produto", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                janelaDeEscolha.setPositiveButton("Editar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        Bundle bundleDadosProduto = new Bundle();

                        bundleDadosProduto.putLong("id_prod", produtoSelecionado.getId());
                        bundleDadosProduto.putString("nomeProd",produtoSelecionado.getNomeProd());
                        bundleDadosProduto.putDouble("precoProduto",produtoSelecionado.getPreco());
                        bundleDadosProduto.putInt("quantidadeEmEstoque",produtoSelecionado.getQuantidadeEmEstoque());
                        bundleDadosProduto.putString("nomeFuncionario",produtoSelecionado.getNomeFuncionario());

                        Intent intentEdiatrProdutos = new Intent(ActivityListarProduto.this, ActivityEditarProduto.class);
                        intentEdiatrProdutos.putExtras(bundleDadosProduto);
                        startActivity(intentEdiatrProdutos);
                    }
                });

                janelaDeEscolha.create().show();
                return false;
            }
        });

        /*
        adapter = new ArrayAdapter<String>(this, R.layout.layout_produto) ;


        filtro.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                (ActivityListarProduto.this).adapter.getFilter().filter(s);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                (ActivityListarProduto.this).adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
                (ActivityListarProduto.this).adapter.getFilter().filter(s);
            }
        });
        */
    }

}
