/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4.searchbytitle.helper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author student
 */
@XmlRootElement(name = "Movie")
@XmlAccessorType (XmlAccessType.FIELD)
public class MovieNull {
    
     private int movieId;
    private String title;
    private String genre;
    private double rating;
    private String status;
    private String filmPoster;
    
    public MovieNull(){
    }
    
    public MovieNull(String msg){
        this.status = msg;
        this.movieId = -1;
        this.title = "error";
        this.genre = "error";
        this.filmPoster = "error";
        this.rating = 0.0;
    }
}
