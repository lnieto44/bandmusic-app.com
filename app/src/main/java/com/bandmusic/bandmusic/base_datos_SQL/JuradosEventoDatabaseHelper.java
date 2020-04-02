package com.bandmusic.bandmusic.base_datos_SQL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.bandmusic.bandmusic.modelos.JuradosEvento;

import java.util.ArrayList;
import java.util.List;

public class JuradosEventoDatabaseHelper extends SQLiteOpenHelper {

    private JuradosEventoDatabaseHelper DBHelper;
    private SQLiteDatabase db;

    // Database Version
    private static final int DATABASE_VERSION = 2;

    // Database Name
    private static final String DATABASE_NAME = "JuradosEvento.db";

    public static final class JuradosEntry implements BaseColumns {

        public static final String TABLE_NAME = "jurado_evento";
        public static final String COLUMN_JURADOS_EVENTO = "evento";
        public static final String COLUMN_JURADOS_CATEGORIA = "categoria";
        public static final String COLUMN_JURADOS_NOMBRE_EVENTO = "nombre";
        public static final String COLUMN_JURADOS_NUM_JURADOS = "num";
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATE_FAVORITE_TABLE = "CREATE TABLE " + JuradosEntry.TABLE_NAME  + " (" +
                JuradosEntry._ID + " INTEGER NOT NULL," +
                JuradosEntry.COLUMN_JURADOS_EVENTO  + " TEXT NOT NULL, " +
                JuradosEntry.COLUMN_JURADOS_CATEGORIA  + " TEXT NOT NULL, " +
                JuradosEntry.COLUMN_JURADOS_NOMBRE_EVENTO + " TEXT NOT NULL, " +
                JuradosEntry.COLUMN_JURADOS_NUM_JURADOS + " TEXT NOT NULL " +
                "); ";

        sqLiteDatabase.execSQL(SQL_CREATE_FAVORITE_TABLE);
    }
    //drop Jurados table
    private String DROP_JURADOS_TABLE = "DROP TABLE IF EXISTS " + JuradosEntry.TABLE_NAME;

    public JuradosEventoDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    //---opens the database---
    public JuradosEventoDatabaseHelper open() throws SQLException
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

    public void addJurados(JuradosEvento jurados) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(JuradosEntry._ID, jurados.getId());
        values.put(JuradosEntry.COLUMN_JURADOS_EVENTO, jurados.getEvento());
        values.put(JuradosEntry.COLUMN_JURADOS_CATEGORIA, jurados.getCategoria());
        values.put(JuradosEntry.COLUMN_JURADOS_NOMBRE_EVENTO, jurados.getNombre());
        values.put(JuradosEntry.COLUMN_JURADOS_NUM_JURADOS, jurados.getNum());

        db.insert(JuradosEntry.TABLE_NAME, null, values);
        db.close();
    }

    public boolean checkUser(String evento) {

        // array of columns to fetch
        String[] columns = {
                JuradosEntry._ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = JuradosEntry.COLUMN_JURADOS_EVENTO + " = ?";

        // selection argument
        String[] selectionArgs = {evento};

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





    public List<JuradosEvento> getAllJurados() {
        // array of columns to fetch
        String[] columns = {
                JuradosEntry._ID,
                JuradosEntry.COLUMN_JURADOS_EVENTO,
                JuradosEntry.COLUMN_JURADOS_CATEGORIA,
                JuradosEntry.COLUMN_JURADOS_NOMBRE_EVENTO,
                JuradosEntry.COLUMN_JURADOS_NUM_JURADOS
        };
        // sorting orders
        String sortOrder =
                JuradosEntry.COLUMN_JURADOS_NOMBRE_EVENTO + " ASC";
        List<JuradosEvento> juradosList = new ArrayList<JuradosEvento>();

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
                JuradosEvento jurados = new JuradosEvento();
                jurados.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(JuradosEntry._ID))));
                jurados.setEvento(cursor.getString(cursor.getColumnIndex(JuradosEntry.COLUMN_JURADOS_EVENTO)));
                jurados.setCategoria(cursor.getString(cursor.getColumnIndex(JuradosEntry.COLUMN_JURADOS_CATEGORIA)));
                jurados.setNombre(cursor.getString(cursor.getColumnIndex(JuradosEntry.COLUMN_JURADOS_NOMBRE_EVENTO)));
                jurados.setNum(cursor.getString(cursor.getColumnIndex(JuradosEntry.COLUMN_JURADOS_NUM_JURADOS)));
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
