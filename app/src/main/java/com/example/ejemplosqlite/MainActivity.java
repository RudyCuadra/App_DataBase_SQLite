package com.example.ejemplosqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "db_usuarios", null, 1);
    }

    public void onClick(View view) {
        Intent miIntent = null;
        switch (view.getId()){
            case R.id.btnRegistroDeUsuario:
                miIntent = new Intent(MainActivity.this, RegistroUsuariosActivity.class);
                break;
            case R.id.btnConsultaDeUsuario:
                miIntent = new Intent(MainActivity.this, ConsultaUsuariosActivity.class);
                break;
            case R.id.btnConsultaDeUConSp:
                miIntent = new Intent(MainActivity.this, ConsultaComboActivity.class);
                break;
            case R.id.btnConsultaDeUConListV:
                miIntent = new Intent(MainActivity.this, ConsultarListaListViewActivity.class);
                break;
        }
        if(miIntent!=null){
            startActivity(miIntent);
        }

    }
}
