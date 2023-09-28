/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4.searchbygenre.business;

import java.util.ArrayList;
import lab4.searchbygenre.helper.Movie;
import lab4.searchbygenre.helper.MoviesXML;
import lab4.searchbygenre.persistence.moviesCRUD;

/**
 *
 * @author student
 */
public class SearchByGenre {
    
    public MoviesXML searchByGenre(String genre){
        ArrayList<Movie> movie =moviesCRUD.search(genre); 
        MoviesXML movies = new MoviesXML();
        movies.setMovies(movie);
        
        if(movie.size()>0){
            movies.setStatus("success");
        }else{
            movies.setStatus("error");
        }
        
        return movies;
    }
    
}
