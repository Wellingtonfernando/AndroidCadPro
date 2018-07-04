package com.example.wellingtonfernando.cadprod;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;

import com.example.wellingtonfernando.cadprod.activities.ActivityContato;
import com.example.wellingtonfernando.cadprod.activities.ActivityListarProduto;
import com.example.wellingtonfernando.cadprod.activities.ActivityProduto;
import com.example.wellingtonfernando.cadprod.activities.Activity_ajuda;
import com.example.wellingtonfernando.cadprod.dbCadProd.conexaoSQLite;

public class MainActivity extends AppCompatActivity {

    private CardView getCdvCardCadPro;
    private CardView getCdvCardListarProd;
    private CardView cdvCardAjuda;
    private CardView getCdvCardContato;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        com.example.wellingtonfernando.cadprod.dbCadProd.conexaoSQLite.getInstancia(this);

        this.getCdvCardCadPro = (CardView) findViewById(R.id.cdvCardCadPro);

        this.getCdvCardListarProd = (CardView) findViewById(R.id.cdvCardListarProd);

        this.cdvCardAjuda = (CardView) findViewById(R.id.cdvCardAjuda);

        this.getCdvCardContato = (CardView) findViewById(R.id.cdvCardContato);

        this.getCdvCardCadPro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityProduto.class);
                startActivity(intent);
            }
        });

        this.getCdvCardListarProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityListarProduto.class);
                startActivity(intent);
            }
        });

        this.cdvCardAjuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, Activity_ajuda.class);
                startActivity(intent);
            }
        });

        this.getCdvCardContato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, ActivityContato.class);
                startActivity(intent);
            }
        });
    }
}
