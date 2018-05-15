package com.example.a16022671.nationaldayparadethemesongcompilation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    ListView lv;
    ArrayList<Song> al;
    ArrayAdapter aa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        al = new ArrayList<Song>();

        lv = (ListView) findViewById(R.id.lvSong);
        DatabaseHelper dbh = new DatabaseHelper(SecondActivity.this);
        al.clear();
        al.addAll(dbh.getAllNotes());
        dbh.close();
        aa = new SongAdapter(this, R.layout.row, al);

lv.setAdapter(aa);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                Intent i = new Intent(SecondActivity.this,
                        ThirdActivity.class);
                Song song = al.get(position);
                i.putExtra("song", song);
                startActivityForResult(i, 9);
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 9){
            lv = (ListView)findViewById(R.id.lvSong);
            al = new ArrayList<Song>();
            DatabaseHelper dbh = new DatabaseHelper(SecondActivity.this);
            al.clear();
            al.addAll(dbh.getAllNotes());
            dbh.close();
            aa = new SongAdapter(this, R.layout.row, al);

            lv.setAdapter(aa);
        }
    }
}
