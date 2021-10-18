package com.structs.parcial;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.structs.parcial.BD.ConexionSQLiteHelper;
import com.structs.parcial.models.User;

public class LoginActivity extends AppCompatActivity {
  //  Button btnLogin, btnRegistrar;

    Button loginButton;
    EditText editEmail, editPass;
    ConexionSQLiteHelper conexionSQLiteHelper;
    User user = new User();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        /////////////////////////
        conexionSQLiteHelper = new ConexionSQLiteHelper(getApplicationContext(), "BD", null, 1);

        editEmail = findViewById(R.id.editTextEmail);
        editPass = findViewById(R.id.editTextPassword);

        loginButton = findViewById(R.id.buttonLogin);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (user.validarLogin(editEmail.getText().toString(), editPass.getText().toString())) {
                    AlertDialog.Builder alertDatosInvalidos = new AlertDialog.Builder(LoginActivity.this);
                    alertDatosInvalidos.setTitle("Datos invalidos");
                    alertDatosInvalidos.setMessage("Los datos ingresados no son validos");
                    alertDatosInvalidos.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            editEmail.setText("");
                            editPass.setText("");
                        }

                    });
                    alertDatosInvalidos.create().show();


                } else {
                    SQLiteDatabase db = conexionSQLiteHelper.getReadableDatabase();

                    String[] parametros = {editEmail.getText().toString()};
                    String[] campos = {Constantes.CAMPO_EMAIL, Constantes.CAMPO_PASSWORD};

                    try {
                        Cursor cursor = db.query(Constantes.TABLA_USER, campos, Constantes.CAMPO_EMAIL + "=?", parametros, null, null, null);
                        cursor.moveToFirst();
                        if ((cursor.getString(0).equals(editEmail.getText().toString())) && (cursor.getString(1).equals(editPass.getText().toString()))) {
                            Intent intent = new Intent(LoginActivity.this, PrincipalActivity.class);
                            Bundle bundle = new Bundle();
                            user.setEmail(editEmail.getText().toString());
                            bundle.putSerializable("usuario", user);
                            intent.putExtras(bundle);
                            startActivity(intent);

                        } else {
                            AlertDialog.Builder alertLoginIncorrecto = new AlertDialog.Builder(LoginActivity.this);
                            alertLoginIncorrecto.setTitle("Error");
                            alertLoginIncorrecto.setMessage("Usuario y/o contraseña incorrectos");
                            alertLoginIncorrecto.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    editEmail.setText("");
                                    editPass.setText("");
                                }
                            });
                            alertLoginIncorrecto.create().show();
                        }
                        cursor.close();
                    } catch (Exception e) {
                        AlertDialog.Builder alertUsuarioInexistente = new AlertDialog.Builder(LoginActivity.this);
                        alertUsuarioInexistente.setTitle("Usuario no registrado");
                        alertUsuarioInexistente.setMessage("¿Desea registrarse?");
                        alertUsuarioInexistente.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                startActivity(new Intent(LoginActivity.this, CreateAccountActivity.class));
                            }
                        });
                        alertUsuarioInexistente.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                editEmail.setText("");
                                editPass.setText("");
                            }
                        });
                        alertUsuarioInexistente.create().show();
                    }

                }

                //////////////////////////

        //        getSupportActionBar().hide();
/*
        btnLogin = findViewById(R.id.buttonLogin);
        btnRegistrar = findViewById(R.id.registrar);

        // Definir el evento clic sobre el botón de Login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(LoginActivity.this, PrincipalActivity.class);
                startActivity(myIntent);
            }
        });



                btnRegistrar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent myIntent = new Intent(LoginActivity.this, CreateAccountActivity.class);
                        startActivity(myIntent);
                    }
                });
                */

            }
    });
    }
}