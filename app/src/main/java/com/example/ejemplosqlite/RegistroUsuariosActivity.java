package com.example.ejemplosqlite;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ejemplosqlite.utilidades.Utilidades;

public class RegistroUsuariosActivity extends AppCompatActivity {

    EditText CampoId, CampoNombre, CampoTelefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuarios);

        CampoId = findViewById(R.id.etIdUsuario);
        CampoNombre = findViewById(R.id.etNombreUsuario);
        CampoTelefono = findViewById(R.id.etTelefonoUsuario);

    }

    public void onClick(View view) {
        //registrarUsuarios();
        registrarUsuariosSql();
    }

    private void registrarUsuariosSql() {
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "db_usuarios", null, 1);

        SQLiteDatabase db = conn.getWritableDatabase();

        //insert into usuario (id,nombre,telefono) values (123,'Cristian','85665223')

        String insert = "INSERT INTO "+Utilidades.TABLA_USUARIO
                +" ( " +Utilidades.CAMPO_ID+","+Utilidades.CAMPO_NOMRRE+","+Utilidades.CAMPO_TELEFONO+")" +
                " VALUES ("+CampoId.getText().toString()+", '"+CampoNombre.getText().toString()+"','"
                +CampoTelefono.getText().toString()+"')";

        db.execSQL(insert);
        Toast.makeText(getApplicationContext(), "Id Registro: "+CampoId.getText().toString(), Toast.LENGTH_SHORT).show();

        db.close();
    }

    private void registrarUsuarios() {
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "db_usuarios", null, 1);

        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_ID, CampoId.getText().toString());
        values.put(Utilidades.CAMPO_NOMRRE, CampoNombre.getText().toString());
        values.put(Utilidades.CAMPO_TELEFONO, CampoTelefono.getText().toString());

        Long idResultante = db.insert(Utilidades.TABLA_USUARIO, Utilidades.CAMPO_ID, values);

        Toast.makeText(getApplicationContext(), "Id Registro: "+idResultante, Toast.LENGTH_SHORT).show();
        db.close();
    }
}
