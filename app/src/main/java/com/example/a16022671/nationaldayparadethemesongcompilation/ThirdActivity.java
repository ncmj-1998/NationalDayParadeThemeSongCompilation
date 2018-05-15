package com.example.a16022671.nationaldayparadethemesongcompilation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ThirdActivity extends AppCompatActivity {
    EditText etID,etTitle,etSingers,etYear;
    RadioGroup rgStar;
    Button btnCancel,btnDelete,btnUpdate;
    Song song;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        etID = (EditText)findViewById(R.id.etID);
        etTitle = (EditText)findViewById(R.id.etTitle);
        etSingers = (EditText)findViewById(R.id.etSingers);
        etYear = (EditText)findViewById(R.id.etYear);
        rgStar = (RadioGroup)findViewById(R.id.rgStar);
        btnDelete = (Button)findViewById(R.id.btnDelete);
        btnUpdate = (Button)findViewById(R.id.btnUpdate);
        btnCancel = (Button)findViewById(R.id.btnCancel);



        final Intent i = getIntent();
        song = (Song) i.getSerializableExtra("song");

        etID.setText(String.valueOf(song.getID()));
        etSingers.setText(song.getSingers());
        etTitle.setText(song.getTitle());
        etYear.setText(song.getYear());
        ((RadioButton)rgStar.getChildAt(song.getStars()-1)).setChecked(true);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = etID.getText().toString();

                DatabaseHelper dbh = new DatabaseHelper(ThirdActivity.this);
                dbh.deleteSong(Integer.parseInt(id));
                dbh.close();
                i.putExtra("resultCode",9);
                setResult(RESULT_OK,i);
                finish();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper dbh = new DatabaseHelper(ThirdActivity.this);
                song.setSingers(etSingers.getText().toString());
                song.setTitle(etTitle.getText().toString());
                song.setYear(etYear.getText().toString());
                int selectedButtonId = rgStar.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton)findViewById(selectedButtonId);
                String stars =  rb.getText().toString();
                int star = Integer.parseInt(stars);
                song.setStars(star);
                dbh.updateSong(song);
                dbh.close();
                i.putExtra("resultCode",9);
                setResult(RESULT_OK,i);
                finish();
            }
        });

    }
}
