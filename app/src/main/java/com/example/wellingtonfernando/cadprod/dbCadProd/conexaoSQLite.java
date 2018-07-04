package com.example.wellingtonfernando.cadprod.dbCadProd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class conexaoSQLite extends SQLiteOpenHelper {

    private static conexaoSQLite INSTANCIA_CONEXAO;
    private static final int VERSAO_DB = 1;
    private static String NOME_DB = "Cadastro_de_Produto_app";

    public conexaoSQLite(Context context) {
        super(context, NOME_DB, null, VERSAO_DB);
    }

    public static conexaoSQLite getInstancia(Context context){
        if (INSTANCIA_CONEXAO == null){
            INSTANCIA_CONEXAO = new conexaoSQLite(context);
        }
        return INSTANCIA_CONEXAO;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlTabelaProduto =
                "CREATE TABLE IF NOT EXISTS produto" +
                        "(" +
                        "id_prod INTEGER PRIMARY KEY," +
                        "nome TEXT NOT NULL," +
                        "quantidade_estoque INTEGER," +
                        "preco REAL," +
                        "funcionario TEXT NOT NULL" +
                        ")";
        db.execSQL(sqlTabelaProduto);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
