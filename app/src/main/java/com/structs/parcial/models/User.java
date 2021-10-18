package com.structs.parcial.models;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Patterns;

import com.structs.parcial.BD.ConexionSQLiteHelper;
import com.structs.parcial.Constantes;

import java.io.Serializable;
import java.util.regex.Pattern;

public class User implements Serializable {

    private Integer id;
    private String nombreApellido;
    private String email;
    private String password;


    public User(){}
    public User(Integer id, String nombreApellido, String email, String password) {
        this.id = id;
        this.nombreApellido = nombreApellido;
        this.email = email;
        this.password = password;
    }
    public User( String nombreApellido, String email, String password) {
        this.nombreApellido = nombreApellido;
        this.email = email;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreApellido() {
        return nombreApellido;
    }

    public void setNombreApellido(String nombreApellido) {
        this.nombreApellido = nombreApellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean validarEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    public boolean validarLogin(String email,String password)
    {
        if(email.equals(null)||email.trim().length()==0||!validarEmail(email)||password.equals(null) || password.trim().length()==0) return true;
        return false;
    }

    public String validarDatos(String nombreApellido, String email, String password, String password2)
    {
        if (nombreApellido.equals(null)||nombreApellido.trim().length()==0)
            return "El nombre ingresado no es valido";
        if(email.equals(null)||email.trim().length()==0||!validarEmail(email))
            return "El email ingresado no es valido";
        if (password.equals(null)||password.trim().length()==0 || password.length()<8 ||
                password2.equals(null)||password2.trim().length()==0 || password2.length()<8)
            return "La contraseña ingresada no es valida";
        if (!password.equals(password2))
            return "Las contraseñas no coinciden";

        return null;
    }

    public User findUserByEmail(String email, ConexionSQLiteHelper con){

        SQLiteDatabase db = con.getReadableDatabase();
        String[] param = {email};
        String[] campos = {Constantes.CAMPO_NOMBREAPELLIDO, Constantes.CAMPO_PASSWORD };
        User u = new User();
        try {
            Cursor cursor = db.query(Constantes.TABLA_USER, campos, Constantes.CAMPO_EMAIL +"=?",
                    param,null, null, null);
            cursor.moveToFirst();
            u.setNombreApellido(cursor.getString(0));
            u.setPassword(cursor.getString(1));
            u.setEmail(email);
            cursor.close();
        }catch (Exception e){        }
        return u;
    }

    public void actualizarPassword(String email, String password,ConexionSQLiteHelper con){
        SQLiteDatabase db = con.getWritableDatabase();
        String[] param = {email};
        ContentValues values = new ContentValues();
        values.put(Constantes.CAMPO_PASSWORD, password);
        db.update(Constantes.TABLA_USER, values, Constantes.CAMPO_EMAIL +"=?",param);
        db.close();

    }

    public void actualizarEmail(String emailViejo, String emailNuevo,ConexionSQLiteHelper con){
        SQLiteDatabase db = con.getWritableDatabase();
        String[] param = {emailViejo};
        ContentValues values = new ContentValues();
        values.put(Constantes.CAMPO_EMAIL, emailNuevo);
        db.update(Constantes.TABLA_USER, values, Constantes.CAMPO_EMAIL +"=?",param);
        db.close();

    }

}
