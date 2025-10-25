package model;

import model.enums.MovieJoner;

public class Movie extends BaseModel{
    private String movieName;
    private MovieJoner movieJoner;

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public MovieJoner getMovieJoner() {
        return movieJoner;
    }

    public void setMovieJoner(MovieJoner movieJoner) {
        this.movieJoner = movieJoner;
    }
}
