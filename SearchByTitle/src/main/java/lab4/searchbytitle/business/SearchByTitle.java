/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4.searchbytitle.business;

import java.io.IOException;
import lab4.searchbytitle.helper.MovieXML;
import lab4.searchbytitle.persistence.moviesCRUD;
/**
 *
 * @author student
 */
public class SearchByTitle {
    
    public MovieXML search(String query, String email) {
        MovieXML movie = moviesCRUD.searchByTitle(query);
        
        //send message to the search log microservice
//        try{
//            if(movie != null){
//                Messaging.sendmessage("LOG:"+ (movie.getMovieId()) +":"+email);
//            }
//        }catch (Exception e){}
        
        
        if (movie ==  null){
            movie = new MovieXML(-1,"null", "null", 0.0, "error", "null");
        }
        return (movie);
    }
    
}
