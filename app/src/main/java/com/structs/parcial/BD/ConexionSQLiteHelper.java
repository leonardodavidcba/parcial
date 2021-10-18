package com.structs.parcial.BD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.structs.parcial.Constantes;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {
    public ConexionSQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Constantes.CREAR_TABLA_USER);
        sqLiteDatabase.execSQL(Constantes.CREAR_TABLA_FAV);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int versionAntigua, int versionNueva) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+Constantes.TABLA_USER);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+Constantes.TABLA_FAV);
        onCreate(sqLiteDatabase);

    }
}
