package com.example.cours;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class FavDB extends SQLiteOpenHelper {
    private static  int DB_VERSION = 1;
    private static String DATABAS_NAME = "FestivalDB";
    private static String TABLE_NAME = "favoriteTable";
    public static String KEY_ID = "id";


    public FavDB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
