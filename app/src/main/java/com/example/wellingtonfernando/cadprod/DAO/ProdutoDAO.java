package com.example.wellingtonfernando.cadprod.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.wellingtonfernando.cadprod.dbCadProd.conexaoSQLite;
import com.example.wellingtonfernando.cadprod.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    private final conexaoSQLite conexaoSQLite;

    public ProdutoDAO(com.example.wellingtonfernando.cadprod.dbCadProd.conexaoSQLite conexaoSQLite) {
        this.conexaoSQLite = conexaoSQLite;
    }

    public long salvarProdutoDAO(Produto pProduto){
        SQLiteDatabase db = conexaoSQLite.getReadableDatabase();

        try {

            ContentValues values = new ContentValues();
            values.put("id_prod", pProduto.getId());
            values.put("nome", pProduto.getNomeProd());
            values.put("quantidade_estoque", pProduto.getQuantidadeEmEstoque());
            values.put("preco",pProduto.getPreco());
            values.put("funcionario",pProduto.getNomeFuncionario());

            long idProdutoInserido = db.insert("produto", null, values);
            return idProdutoInserido;

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (db != null){
                db.close();
            }
        }
        return 0;
    }

    public List<Produto> getListaProdutoDAO(){

        List<Produto> listaProdutos = new ArrayList<>();
        SQLiteDatabase db = null;
        Cursor cursor;

        String query = "SELECT * FROM produto;";

        try {

            db = conexaoSQLite.getReadableDatabase();

            cursor = db.rawQuery(query,null);

            if (cursor.moveToFirst()){
                Produto produto = null;
                do {
                    produto = new Produto();
                    produto.setId(cursor.getLong(0));
                    produto.setNomeProd(cursor.getString(1));
                    produto.setQuantidadeEmEstoque(cursor.getInt(2));
                    produto.setPreco(cursor.getDouble(3));
                    produto.setNomeFuncionario(cursor.getString(4));

                    listaProdutos.add(produto);

                }while (cursor.moveToNext());
            }

        }catch (Exception e){
            Log.d("ERRO LISTA PRODUTO","Erro ao retornar os rpodutos");
            return null;
        }finally {
            if (db != null){
                db.close();
            }
        }

        return listaProdutos;
    }

    public boolean excluirProdutoDAO(long pidProduto){
        SQLiteDatabase db = null;

        try {
            db = this.conexaoSQLite.getWritableDatabase();
            db.delete(
                    "produto",
                    "id_prod = ?",
                    new String[]{String.valueOf(pidProduto)}
            );

        }catch (Exception e){
            Log.d("PRODUTODAO","Erro ao deleter produto");
            return false;
        }finally {
            if (db != null){
                db.close();
            }
        }
        return true;
    }

    public boolean atualizarProdutoDAO(Produto pProduto){
        SQLiteDatabase db = null;
        try {

            db = this.conexaoSQLite.getWritableDatabase();

            ContentValues produtoAtributos = new ContentValues();
            produtoAtributos.put("id_prod",pProduto.getId());
            produtoAtributos.put("nome",pProduto.getNomeProd());
            produtoAtributos.put("quantidade_estoque",pProduto.getQuantidadeEmEstoque());
            produtoAtributos.put("preco", pProduto.getPreco());
            produtoAtributos.put("funcionario", pProduto.getNomeFuncionario());
            int atualizou = db.update(
                    "produto",
                     produtoAtributos,
                    "id_prod = ?",
                    new String[]{String.valueOf(pProduto.getId())}
                );
            if (atualizou > 0){
                return true;
            }

        }catch (Exception e){
            Log.d("PRODUTODAO", "Não foi possivel atualizar produto");
            return false;
        }
        finally {
            if (db != null){
                db.close();
            }
        }
        return false;
    }
}
