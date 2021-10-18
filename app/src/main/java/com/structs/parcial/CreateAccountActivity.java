package com.structs.parcial;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.structs.parcial.BD.ConexionSQLiteHelper;
import com.structs.parcial.models.User;

public class CreateAccountActivity extends AppCompatActivity {
    EditText editName, editEmail, editPassword, editPassword2;
    User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        editName = findViewById(R.id.editName);
        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        editPassword2 = findViewById(R.id.editPassword2);
    }

    public void onClick(View view)
    {
        if(user.validarDatos(editName.getText().toString(),editEmail.getText().toString(),editPassword.getText().toString(),editPassword2.getText().toString())!=null)
        {

            AlertDialog.Builder alertDatosInvalidos = new AlertDialog.Builder(CreateAccountActivity.this);
            alertDatosInvalidos.setTitle("Datos invalidos");
            alertDatosInvalidos.setMessage("Los datos ingresados no son validos");
            alertDatosInvalidos.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    editEmail.setText("");
                    editName.setText("");
                    editPassword.setText("");
                    editPassword2.setText("");
                }

            });
            alertDatosInvalidos.create().show();
        }
        else
        {
            registrarUsuarios();
            AlertDialog.Builder alertUsuarioRegistrado = new AlertDialog.Builder(CreateAccountActivity.this);
            alertUsuarioRegistrado.setTitle("Registro exitoso!");
            alertUsuarioRegistrado.setMessage("Usuario registrado correctamente");
            alertUsuarioRegistrado.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    startActivity(new Intent(CreateAccountActivity.this, LoginActivity.class));
                }

            });
            alertUsuarioRegistrado.create().show();


        }

    }

    private void registrarUsuarios() {

        ConexionSQLiteHelper conexionSQLiteHelper = new ConexionSQLiteHelper(this,"BD", null,1);

        SQLiteDatabase db = conexionSQLiteHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Constantes.CAMPO_NOMBREAPELLIDO, editName.getText().toString());
        values.put(Constantes.CAMPO_EMAIL, editEmail.getText().toString());
        values.put(Constantes.CAMPO_PASSWORD, editPassword.getText().toString());
        Long resultado = db.insert(Constantes.TABLA_USER,Constantes.CAMPO_ID,values);
        System.out.println(resultado);




    }

}