package com.bandmusic.bandmusic.base_datos_SQL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.bandmusic.bandmusic.modelos.Jurados;

import java.util.ArrayList;
import java.util.List;

public class JuradosDatabaseHelper extends SQLiteOpenHelper {

    private JuradosDatabaseHelper DBHelper;
    private SQLiteDatabase db;

    // Database Version
    private static final int DATABASE_VERSION = 2;

    // Database Name
    private static final String DATABASE_NAME = "Jurados.db";


    public static final class JuradosEntry implements BaseColumns {

        public static final String TABLE_NAME = "jurados";
        public static final String COLUMN_JURADOS_NOMBRE = "nombre";
        public static final String COLUMN_JURADOS_CORREO = "correo";
        public static final String COLUMN_JURADOS_CLAVE = "clave";
        public static final String COLUMN_JURADOS_CATEGORIA = "categoria";
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATE_FAVORITE_TABLE = "CREATE TABLE " + JuradosEntry.TABLE_NAME  + " (" +
                JuradosEntry._ID + " INTEGER NOT NULL," +
                JuradosEntry.COLUMN_JURADOS_NOMBRE + " TEXT NOT NULL, " +
                JuradosEntry.COLUMN_JURADOS_CORREO + " TEXT NOT NULL, " +
                JuradosEntry.COLUMN_JURADOS_CLAVE + " TEXT NOT NULL, " +
                JuradosEntry.COLUMN_JURADOS_CATEGORIA + " TEXT NOT NULL " +
                "); ";

        sqLiteDatabase.execSQL(SQL_CREATE_FAVORITE_TABLE);
    }
    //drop Jurados table
    private String DROP_JURADOS_TABLE = "DROP TABLE IF EXISTS " + JuradosEntry.TABLE_NAME;

    public JuradosDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    //---opens the database---
    public JuradosDatabaseHelper open() throws SQLException
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

        db1.execSQL(DROP_JURADOS_TABLE);

        // Create tables again
        onCreate(db1);

    }


    //Method to create Jurados records

    public void addJurados(Jurados jurados) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(JuradosEntry._ID, jurados.getId());
        values.put(JuradosEntry.COLUMN_JURADOS_NOMBRE, jurados.getNombre());
        values.put(JuradosEntry.COLUMN_JURADOS_CORREO, jurados.getCorreo());
        values.put(JuradosEntry.COLUMN_JURADOS_CLAVE, jurados.getClave());
        values.put(JuradosEntry.COLUMN_JURADOS_CATEGORIA, jurados.getCategoria());

        db.insert(JuradosEntry.TABLE_NAME, null, values);
        db.close();
    }

    public boolean checkUser(String trim,String clave) {

        // array of columns to fetch
        String[] columns = {
                JuradosEntry._ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = JuradosEntry.COLUMN_JURADOS_CLAVE + " = ?";

        // selection argument
        String[] selectionArgs = {clave};

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(JuradosEntry.TABLE_NAME, //Table to query
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





    public List<Jurados> getAllJurados() {
        // array of columns to fetch
        String[] columns = {
                JuradosEntry._ID,
                JuradosEntry.COLUMN_JURADOS_NOMBRE,
                JuradosEntry.COLUMN_JURADOS_CORREO,
                JuradosEntry.COLUMN_JURADOS_CLAVE,
                JuradosEntry.COLUMN_JURADOS_CATEGORIA
        };
        // sorting orders
        String sortOrder =
                JuradosEntry.COLUMN_JURADOS_NOMBRE + " ASC";
        List<Jurados> juradosList = new ArrayList<Jurados>();

        SQLiteDatabase db = this.getReadableDatabase();


        Cursor cursor = db.query(JuradosEntry.TABLE_NAME, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Jurados jurados = new Jurados();
                jurados.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(JuradosEntry._ID))));
                jurados.setNombre(cursor.getString(cursor.getColumnIndex(JuradosEntry.COLUMN_JURADOS_NOMBRE)));
                jurados.setCorreo(cursor.getString(cursor.getColumnIndex(JuradosEntry.COLUMN_JURADOS_CORREO)));
                jurados.setClave(cursor.getString(cursor.getColumnIndex(JuradosEntry.COLUMN_JURADOS_CLAVE)));
                jurados.setCategoria(cursor.getString(cursor.getColumnIndex(JuradosEntry.COLUMN_JURADOS_CATEGORIA)));
                // Adding user record to list
                juradosList.add(jurados);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return juradosList;
    }


}
