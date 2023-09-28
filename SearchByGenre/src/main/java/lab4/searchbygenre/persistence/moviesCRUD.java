/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4.searchbygenre.persistence;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Base64;
import lab4.searchbygenre.helper.Movie;

/**
 *
 * @author student
 */
public class moviesCRUD {
    
    public static Connection getCon(){
        Connection con = null;
        try{
            //for mysql under services
            //old: jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=CONVERT_TO_NULL
            //new: jdbc:mysql://localhost:3306/mysql?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC
            Class.forName("com.mysql.jdbc.Driver");
            String s = System.getenv("genredb");
            con = DriverManager.getConnection("jdbc:mysql://"+ s +"/GENRE_PPS?allowPublicKeyRetrieval=true&useSSL=false", "root", "student");
            System.out.println("DB Connection");
        }catch(Exception e){
            System.out.println(e);
        }  
        return con;
    }
    
     public static ArrayList<Movie> search(String genre2){
        ArrayList<Movie> moviesList = new ArrayList<>();
        try{
            Connection con = getCon();
            String query;
            if(genre2.equals("null")){
                query = "SELECT * FROM MOVIES";
            }else{
                query = "SELECT * FROM MOVIES where genre = '" + genre2 + "';";
            }
            
            System.out.println(query);
            
            PreparedStatement ps = con.prepareStatement(query);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String title= rs.getString("title");
                String genre= rs.getString("genre");
                double rating= rs.getDouble("rating");
                Blob filmPoster=  (Blob)rs.getBlob("filmPoster");
                
                //convert the poster blob to image string
                byte[] imgData = filmPoster.getBytes(1,(int)filmPoster.length()); ;// blob field 
                String posterString = Base64.getEncoder().encodeToString(imgData);
                
                moviesList.add(new Movie(id, title, genre, rating, "success", posterString));
            }
            con.close();
        }catch (Exception e){
            System.out.println(e);
        }
        return moviesList;
    }
    
}
