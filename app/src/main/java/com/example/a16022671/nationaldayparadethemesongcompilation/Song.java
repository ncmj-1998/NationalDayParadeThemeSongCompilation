package com.example.a16022671.nationaldayparadethemesongcompilation;

import java.io.Serializable;

public class Song implements Serializable{
    private int id;
    private String title;
    private String singers;
    private String year;
    private int stars;

    public Song(int id,String title, String singers, String year, int stars) {
        this.id = id;
        this.title = title;
        this.singers = singers;
        this.year = year;
        this.stars = stars;
    }

    public int getID(){
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSingers() {
        return singers;
    }

    public String getYear() {
        return year;
    }

    public int getStars() {
        return stars;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSingers(String singers) {
        this.singers = singers;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }
}
