package com.example.a16022671.nationaldayparadethemesongcompilation;

import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SongAdapter extends ArrayAdapter<Song> {


    private ArrayList<Song> songs;
    private Context context;
    private TextView tvTitle;
    private  TextView tvSingers;
    private TextView tvYear;
    private ImageView star1;
    private ImageView star2;
    private ImageView star3;
    private ImageView star4;
    private ImageView star5;



    public SongAdapter(Context context, int resource, ArrayList<Song> objects) {
        super(context, resource, objects);
        // Store the food that is passed to this adapter
        songs = objects;
        // Store Context object as we would need to use it later
        this.context = context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // The usual way to get the LayoutInflater object to
        //  "inflate" the XML file into a View object
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // "Inflate" the row.xml as the layout for the View object
        View rowView = inflater.inflate(R.layout.row, parent, false);

        // Get the TextView object
        tvSingers = (TextView) rowView.findViewById(R.id.tvSingers);
        tvYear = (TextView)rowView.findViewById(R.id.tvYear);
        tvTitle = (TextView)rowView.findViewById(R.id.tvTitle);
        star1 = (ImageView)rowView.findViewById(R.id.imageView1);
        star2 = (ImageView)rowView.findViewById(R.id.imageView2);
        star3 = (ImageView)rowView.findViewById(R.id.imageView3);
        star4 = (ImageView)rowView.findViewById(R.id.imageView4);
        star5 = (ImageView)rowView.findViewById(R.id.imageView5);

        Song currentSong = songs.get(position);

        tvSingers.setText(currentSong.getSingers());
        tvTitle.setText(currentSong.getTitle());
        tvYear.setText(currentSong.getYear());
        int noOfStars = currentSong.getStars();

        if (noOfStars == 1) {
            star1.setImageResource(android.R.drawable.star_big_on);
        }else if(noOfStars == 2) {
            star1.setImageResource(android.R.drawable.star_big_on);
            star2.setImageResource(android.R.drawable.star_big_on);
        }else if(noOfStars == 3) {
            star1.setImageResource(android.R.drawable.star_big_on);
            star2.setImageResource(android.R.drawable.star_big_on);
            star3.setImageResource(android.R.drawable.star_big_on);
        }else if(noOfStars == 4) {
            star1.setImageResource(android.R.drawable.star_big_on);
            star2.setImageResource(android.R.drawable.star_big_on);
            star3.setImageResource(android.R.drawable.star_big_on);
            star4.setImageResource(android.R.drawable.star_big_on);
        }else {
            star1.setImageResource(android.R.drawable.star_big_on);
            star2.setImageResource(android.R.drawable.star_big_on);
            star3.setImageResource(android.R.drawable.star_big_on);
            star4.setImageResource(android.R.drawable.star_big_on);
            star5.setImageResource(android.R.drawable.star_big_on);

        }

        return rowView;
    }

}


