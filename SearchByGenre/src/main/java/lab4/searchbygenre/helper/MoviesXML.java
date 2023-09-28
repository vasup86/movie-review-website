/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4.searchbygenre.helper;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author student
 */
@XmlRootElement(name = "Movies")
@XmlAccessorType (XmlAccessType.FIELD)

public class MoviesXML {
    @XmlElement(name="status")
    private String status;
    
    @XmlElement(name="movie")
    private ArrayList<Movie> movies;
    
   
    public MoviesXML(){
    }
    
    public ArrayList<Movie> getMovies(){
        return this.movies;
    }
    
    public void setMovies(ArrayList<Movie>  movies){
        this.movies = movies;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
