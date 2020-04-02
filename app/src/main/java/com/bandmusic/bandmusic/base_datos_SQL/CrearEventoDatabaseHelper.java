package com.bandmusic.bandmusic.base_datos_SQL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.bandmusic.bandmusic.modelos.CrearEventos;

import java.util.ArrayList;
import java.util.List;

public class CrearEventoDatabaseHelper extends SQLiteOpenHelper {

    private CrearEventoDatabaseHelper DBHelper;
    private SQLiteDatabase db;

    // Database Version
    private static final int DATABASE_VERSION = 2;

    // Database Name
    private static final String DATABASE_NAME = "CrearEventos.db";

    public static final class EventosEntry implements BaseColumns {

        public static final String TABLE_NAME = "crear_evento";
        public static final String COLUMN_EVENTO = "evento";
        public static final String COLUMN_CATEGORIA = "categoria";
        public static final String COLUMN_NOMBRE = "nombre";
        public static final String COLUMN_CIUDAD = "ciudad";
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATE_FAVORITE_TABLE = "CREATE TABLE " + EventosEntry.TABLE_NAME  + " (" +
                EventosEntry._ID + " INTEGER NOT NULL," +
                EventosEntry.COLUMN_EVENTO  + " TEXT NOT NULL, " +
                EventosEntry.COLUMN_CATEGORIA  + " TEXT NOT NULL, " +
                EventosEntry.COLUMN_NOMBRE+ " TEXT NOT NULL, " +
                EventosEntry.COLUMN_CIUDAD + " TEXT NOT NULL " +
                "); ";

        sqLiteDatabase.execSQL(SQL_CREATE_FAVORITE_TABLE);
    }
    //drop CrearEventos table
    private String DROP_TABLE = "DROP TABLE IF EXISTS " + EventosEntry.TABLE_NAME;

    public CrearEventoDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    //---opens the database---
    public CrearEventoDatabaseHelper open() throws SQLException
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


    //Method to create CrearEventos records

    public void addEventos(CrearEventos crearEventos) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(EventosEntry._ID, crearEventos.getId());
        values.put(EventosEntry.COLUMN_EVENTO, crearEventos.getEvento());
        values.put(EventosEntry.COLUMN_CATEGORIA, crearEventos.getCategoria());
        values.put(EventosEntry.COLUMN_NOMBRE, crearEventos.getNombre());
        values.put(EventosEntry.COLUMN_CIUDAD, crearEventos.getCiudad());

        db.insert(EventosEntry.TABLE_NAME, null, values);
        db.close();
    }

    public boolean checkUser(String nombre) {

        // array of columns to fetch
        String[] columns = {
                EventosEntry._ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = EventosEntry.COLUMN_NOMBRE + " = ?";

        // selection argument
        String[] selectionArgs = {nombre};

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(EventosEntry.TABLE_NAME, //Table to query
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





    public List<CrearEventos> getAllEventos() {
        // array of columns to fetch
        String[] columns = {
                EventosEntry._ID,
                EventosEntry.COLUMN_EVENTO,
                EventosEntry.COLUMN_CATEGORIA,
                EventosEntry.COLUMN_NOMBRE,
                EventosEntry.COLUMN_CIUDAD
        };
        // sorting orders
        String sortOrder =
                EventosEntry.COLUMN_NOMBRE + " ASC";
        List<CrearEventos> crearEventosList = new ArrayList<CrearEventos>();

        SQLiteDatabase db = this.getReadableDatabase();


        Cursor cursor = db.query(EventosEntry.TABLE_NAME, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                CrearEventos crearEventos = new CrearEventos();
                crearEventos.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(EventosEntry._ID))));
                crearEventos.setEvento(cursor.getString(cursor.getColumnIndex(EventosEntry.COLUMN_EVENTO)));
                crearEventos.setCategoria(cursor.getString(cursor.getColumnIndex(EventosEntry.COLUMN_CATEGORIA)));
                crearEventos.setNombre(cursor.getString(cursor.getColumnIndex(EventosEntry.COLUMN_NOMBRE)));
                crearEventos.setCiudad(cursor.getString(cursor.getColumnIndex(EventosEntry.COLUMN_CIUDAD)));
                // Adding user record to list
                crearEventosList.add(crearEventos);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return crearEventosList;
    }


}

