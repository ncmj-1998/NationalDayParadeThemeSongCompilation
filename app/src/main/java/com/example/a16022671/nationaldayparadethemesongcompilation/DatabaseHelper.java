package com.example.a16022671.nationaldayparadethemesongcompilation;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "NDPSong.db";
    private static final int DATABASE_VERSION = 2;
    private static final String TABLE_Song = "Songs";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_SONG_TITLE = "Song_Title";
    private static final String COLUMN_SONG_SINGERS = "Singers";
    private static final String COLUMN_SONG_YEAR = "Year";
    private static final String COLUMN_SONG_STAR = "Stars";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createSongTableSql = "CREATE TABLE " + TABLE_Song + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_SONG_TITLE + " TEXT, " + COLUMN_SONG_SINGERS + " TEXT, " + COLUMN_SONG_YEAR + " TEXT, " + COLUMN_SONG_STAR + " NUMBERS " + ") ";
        db.execSQL(createSongTableSql);
        Log.i("info", "created tables");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ArrayList<Song> getAllNotes() {
        ArrayList<Song> songs = new ArrayList<Song>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_SONG_TITLE, COLUMN_SONG_SINGERS, COLUMN_SONG_YEAR, COLUMN_SONG_STAR};

        Cursor cursor = db.query(TABLE_Song, columns, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String singers = cursor.getString(2);
                String year = cursor.getString(3);
                int star = cursor.getInt(4);
                Song song = new Song(id,title, singers, year, star);
                songs.add(song);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return songs;

    }

    public long insertSong(Song song) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String title= song.getTitle();
        String singers = song.getSingers();
        String year = song.getYear();
        int star = song.getStars();
        int id = song.getID();
        values.put(COLUMN_ID,id);
        values.put(COLUMN_SONG_TITLE,title );
        values.put(COLUMN_SONG_SINGERS,singers);
        values.put(COLUMN_SONG_YEAR,year);
        values.put(COLUMN_SONG_STAR,star);

        long result = db.insert(TABLE_Song, null, values);
        db.close();
        Log.d("SQL Insert ","pass"); //id returned, shouldn’t be -1
        return result;
    }

    public int updateSong(Song song){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SONG_TITLE,song.getTitle());
        values.put(COLUMN_SONG_SINGERS,song.getSingers());
        values.put(COLUMN_SONG_YEAR,song.getYear());
        values.put(COLUMN_SONG_STAR, song.getStars());

        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(song.getID())};
        int result = db.update(TABLE_Song, values, condition, args);
        db.close();
        return result;
    }
    public int deleteSong(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_Song, condition, args);
        db.close();
        return result;
    }
}
