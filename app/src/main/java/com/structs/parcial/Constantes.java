package com.structs.parcial;

public class Constantes {

    public static String TABLA_USER = " user";
    public static String CAMPO_ID = "id";
    public static String CAMPO_NOMBREAPELLIDO = "nombreApellido";
    public static String CAMPO_EMAIL = "email";
    public static String CAMPO_PASSWORD = "password";
    public static final String CREAR_TABLA_USER = "CREATE TABLE" + TABLA_USER+" " +
            "("+CAMPO_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
            ""+CAMPO_NOMBREAPELLIDO+" TEXT, " +
            ""+CAMPO_EMAIL+" TEXT unique, " +
            ""+CAMPO_PASSWORD+" TEXT)";

    public static String TABLA_FAV = " fav";
    public static String CAMPO_IDFAV = "id";
    public static String CAMPO_TITULO = "titulo";
    public static String CAMPO_DESCRIPCION = "descripcion";
    public static String CAMPO_URICOMPLETA = "uricompleta";

    public static final String CREAR_TABLA_FAV = "CREATE TABLE" + TABLA_FAV+" " +
            "("+ CAMPO_IDFAV +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
            ""+ CAMPO_TITULO +" TEXT unique, " +
            ""+ CAMPO_DESCRIPCION +" TEXT, " +
            ""+ CAMPO_URICOMPLETA +" TEXT)";
}
