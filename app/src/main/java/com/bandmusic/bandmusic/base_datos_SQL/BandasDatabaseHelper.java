package com.bandmusic.bandmusic.base_datos_SQL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.bandmusic.bandmusic.modelos.Bandas;

import java.util.ArrayList;
import java.util.List;

public class BandasDatabaseHelper extends SQLiteOpenHelper {

    private BandasDatabaseHelper DBHelper;
    private SQLiteDatabase db;

    // Database Version
    private static final int DATABASE_VERSION = 2;

    // Database Name
    private static final String DATABASE_NAME = "Bandas.db";

    public static final class BandasEntry implements BaseColumns {

        public static final String TABLE_NAME = "bandas";
        public static final String COLUMN_NOMBRE_BANDA = "nombre";
        public static final String COLUMN_ORGANIZACION = "organizacion";
        public static final String COLUMN_CIUDAD = "ciudad";
        public static final String COLUMN_CATEGORIA = "categoria";
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATE_FAVORITE_TABLE = "CREATE TABLE " + BandasEntry.TABLE_NAME  + " (" +
                BandasEntry._ID + " INTEGER NOT NULL," +
                BandasEntry.COLUMN_NOMBRE_BANDA + " TEXT NOT NULL, " +
                BandasEntry.COLUMN_ORGANIZACION + " TEXT NOT NULL, " +
                BandasEntry.COLUMN_CIUDAD  + " TEXT NOT NULL, " +
                BandasEntry.COLUMN_CATEGORIA + " TEXT NOT NULL " +
                "); ";

        sqLiteDatabase.execSQL(SQL_CREATE_FAVORITE_TABLE);
    }
    //drop Bandas table
    private String DROP_TABLE = "DROP TABLE IF EXISTS " + BandasEntry.TABLE_NAME;

    public BandasDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    //---opens the database---
    public BandasDatabaseHelper open() throws SQLException
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }


    //---closes the database---
    public void close()
    {
        DBHelper.close();
    }


    @Override
    public void onUpgrade(SQLiteDatabase db1, int oldVersion, int newVersion) {

        //Drop User Table if exist

        db1.execSQL(DROP_TABLE);

        // Create tables again
        onCreate(db1);

    }


    //Method to create Bandas records

    public void addBandas(Bandas bandas) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(BandasEntry._ID, bandas.getId());
        values.put(BandasEntry.COLUMN_NOMBRE_BANDA, bandas.getNombre());
        values.put(BandasEntry.COLUMN_ORGANIZACION , bandas.getOrganizacion());
        values.put(BandasEntry.COLUMN_CIUDAD, bandas.getCiudad());
        values.put(BandasEntry.COLUMN_CATEGORIA , bandas.getCategoria());

        db.insert(BandasEntry.TABLE_NAME, null, values);
        db.close();
    }

    public boolean checkUser(String nombre) {

        // array of columns to fetch
        String[] columns = {
                BandasEntry._ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = BandasEntry.COLUMN_NOMBRE_BANDA + " = ?";

        // selection argument
        String[] selectionArgs = {nombre};

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(BandasEntry.TABLE_NAME, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }





    public List<Bandas> getAllBandas() {
        // array of columns to fetch
        String[] columns = {
                BandasEntry._ID,
                BandasEntry.COLUMN_NOMBRE_BANDA,
                BandasEntry.COLUMN_ORGANIZACION,
                BandasEntry.COLUMN_CIUDAD,
                BandasEntry.COLUMN_CATEGORIA
        };
        // sorting orders
        String sortOrder =
                BandasEntry.COLUMN_NOMBRE_BANDA + " ASC";
        List<Bandas> bandasList = new ArrayList<Bandas>();

        SQLiteDatabase db = this.getReadableDatabase();


        Cursor cursor = db.query(BandasEntry.TABLE_NAME, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Bandas bandas = new Bandas();
                bandas.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(BandasEntry._ID))));
                bandas.setNombre(cursor.getString(cursor.getColumnIndex(BandasEntry.COLUMN_NOMBRE_BANDA)));
                bandas.setOrganizacion(cursor.getString(cursor.getColumnIndex(BandasEntry.COLUMN_ORGANIZACION)));
                bandas.setCiudad(cursor.getString(cursor.getColumnIndex(BandasEntry.COLUMN_CIUDAD)));
                bandas.setCategoria(cursor.getString(cursor.getColumnIndex(BandasEntry.COLUMN_CATEGORIA)));
                // Adding user record to list
                bandasList.add(bandas);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return bandasList;
    }


}


