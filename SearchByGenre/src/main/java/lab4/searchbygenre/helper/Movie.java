/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4.searchbygenre.helper;

import com.mysql.cj.jdbc.Blob;
import java.sql.SQLException;
import java.util.Base64;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author student
 */


@XmlRootElement(name = "Movie")
@XmlAccessorType (XmlAccessType.FIELD)
public class Movie{
    private int movieId;
    private String title;
    private String genre;
    private double rating;
    private String status;
    private String filmPoster;

    public Movie(int id, String title, String genre, double rating, String status,  String filmPoster) {
        this.movieId = id;
        this.title = title;
        this.genre = genre;
        this.rating = rating;
        this.status = status;
        this.filmPoster = filmPoster;
    }
    
    public  Movie(){               
    }
    
    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    
    public String getFilmPoster() {
        return filmPoster;
    }

    public void setFilmPoster(String filmPoster)  {
        this.filmPoster = filmPoster;
    }

}
