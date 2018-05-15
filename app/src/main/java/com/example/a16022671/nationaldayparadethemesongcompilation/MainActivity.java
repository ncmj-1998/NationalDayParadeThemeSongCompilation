package com.example.a16022671.nationaldayparadethemesongcompilation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etTitle,etSingers,etYear;
    RadioGroup rgStar;
    Button btnShow,btnInsert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTitle = (EditText)findViewById(R.id.etTitle);
        etSingers = (EditText)findViewById(R.id.etSingers);
        etYear = (EditText)findViewById(R.id.etYear);
        rgStar = (RadioGroup)findViewById(R.id.rgStar);
        btnShow = (Button)findViewById(R.id.btnShow);
        btnInsert = (Button)findViewById(R.id.btnInsert);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etTitle.getText().toString();
                String singers = etSingers.getText().toString();
                String year = etYear.getText().toString();
                int selectedButtonId = rgStar.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton)findViewById(selectedButtonId);
                String stars =  rb.getText().toString();
                int star = Integer.parseInt(stars);


                DatabaseHelper dbh = new DatabaseHelper(MainActivity.this);
                int size = dbh.getAllNotes().size();
                Song newSong = new Song(size+1,title,singers,year,star);

                long row_affected = dbh.insertSong(newSong);
                dbh.close();
                Toast.makeText(MainActivity.this,"Insert successfully",Toast.LENGTH_LONG);
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,
                        SecondActivity.class);
                startActivity(i);

            }
        });







    }
}
