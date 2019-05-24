package com.example.ejemplosqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ejemplosqlite.utilidades.Utilidades;

public class ConsultaUsuariosActivity extends AppCompatActivity {

    EditText campoId, campoNombre, campoTel;
    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_usuarios);

        campoId = findViewById(R.id.etNDocumento);
        campoNombre = findViewById(R.id.etNombreU);
        campoTel = findViewById(R.id.etTelefonoU);

        conn = new ConexionSQLiteHelper(getApplicationContext(), "db_usuarios",null,1);
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnBucarU:
                //consultar();
                consultarSQL();
                break;

            case R.id.btnActualizarU:
                actualizarUsuario();
                break;

            case R.id.btnEliminarUsuario:
                eliminarUsuario();
                break;
        }
    }

    private void eliminarUsuario() {
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] parametros={campoId.getText().toString()};

        db.delete(Utilidades.TABLA_USUARIO,Utilidades.CAMPO_ID+"=?",parametros);
        Toast.makeText(getApplicationContext(),"Se elimino el usuario",Toast.LENGTH_SHORT).show();
        campoId.setText("");
        limpiar();
        db.close();
    }


    private void actualizarUsuario() {
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] parametros={campoId.getText().toString()};
        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_NOMRRE,campoNombre.getText().toString());
        values.put(Utilidades.CAMPO_TELEFONO,campoTel.getText().toString());

        db.update(Utilidades.TABLA_USUARIO,values,Utilidades.CAMPO_ID+"=?",parametros);

        Toast.makeText(getApplicationContext(),"Ya se actualizo el usuario",Toast.LENGTH_SHORT).show();

        db.close();

    }

    private void consultarSQL() {
        SQLiteDatabase db = conn.getReadableDatabase();
        String[] parametros={campoId.getText().toString()};

        try{
            //select nombre,telefono from usuario where codigo=?
            Cursor cursor = db.rawQuery("SELECT "+Utilidades.CAMPO_NOMRRE+","+Utilidades.CAMPO_TELEFONO+
                    " FROM "+Utilidades.TABLA_USUARIO+" WHERE "+Utilidades.CAMPO_ID+"=? ",parametros);

            cursor.moveToFirst();
            campoNombre.setText(cursor.getString(0));
            campoTel.setText(cursor.getString(1));

        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"El documento no exite",Toast.LENGTH_SHORT).show();
            limpiar();
        }
    }

    private void consultar() {
        SQLiteDatabase db = conn.getReadableDatabase();
        String[] parametros={campoId.getText().toString()};
        String[] campos={Utilidades.CAMPO_NOMRRE,Utilidades.CAMPO_TELEFONO};

        try{
            Cursor cursor = db.query(Utilidades.TABLA_USUARIO,campos,Utilidades.CAMPO_ID+"=?",parametros,null,null,null);
            cursor.moveToFirst();
            campoNombre.setText(cursor.getString(0));
            campoTel.setText(cursor.getString(1));
            cursor.close();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"El documento no exite",Toast.LENGTH_SHORT).show();
            limpiar();
        }


    }

    private void limpiar() {
        campoNombre.setText("");
        campoTel.setText("");
    }
}
