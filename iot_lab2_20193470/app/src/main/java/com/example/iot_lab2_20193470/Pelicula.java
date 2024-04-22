package com.example.iot_lab2_20193470;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Pelicula {
    @SerializedName("Title")
    private String title;
    @SerializedName("Year")
    private String year;
    @SerializedName("Rated")
    private String rated;
    @SerializedName("Genre")
    private String genre;
    @SerializedName("Runtime")
    private String runtime;
    @SerializedName("Director")
    private String director;
    @SerializedName("Plot")
    private String plot;
    @SerializedName("Rating")
    private List<Rating> rating;

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }
    public void setYear(String year) {
        this.year = year;
    }

    public String getRated() { return rated; }
    public void setRated(String rated) { this.rated = rated; }

    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getRuntime() {
        return runtime;
    }
    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getDirector() {
        return director;
    }
    public void setDirector(String director) {
        this.director = director;
    }

    public String getPlot() {
        return plot;
    }
    public void setPlot(String plot) {
        this.plot = plot;
    }

    public List<Rating> getRating() {
        return rating;
    }
    public void setRatings(List<Rating> rating) {
        this.rating = rating;
    }


    public static class Rating {
        @SerializedName("Source")
        private String source;
        @SerializedName("Value")
        private String value;

        public String getSource() { return source; }
        public void setSource(String source) { this.source = source; }

        public String getValue() { return value; }
        public void setValue(String value) { this.value = value; }
    }

}
