/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4.searchbytitle.persistence;

import com.mysql.cj.jdbc.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Base64;
import lab4.searchbytitle.helper.MovieXML;

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
            String connection = System.getenv("titledb");
            con = DriverManager.getConnection("jdbc:mysql://"+connection+"/TITLE_PPS?allowPublicKeyRetrieval=true&useSSL=false", "root", "student");
            System.out.println("DB Connection");
        }catch(Exception e){
            System.out.println(e);
        }  
        return con;
    }
    
    public static MovieXML searchByTitle(String title2){
        MovieXML movie = null;
        try{
            Connection con = getCon();
            String query = "SELECT * FROM MOVIES WHERE title='" + title2 + "';";
            
            PreparedStatement ps = con.prepareStatement(query);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String title= rs.getString("title");
                String genre= rs.getString("genre");
                double rating= rs.getDouble("rating");
                Blob filmBlob= (Blob) rs.getBlob("filmPoster");
                byte[] imgData = filmBlob.getBytes(1,(int)filmBlob.length()); ;// blob field 
                String filmPoster = Base64.getEncoder().encodeToString(imgData);
                movie = new MovieXML(id, title, genre, rating,"success", filmPoster);
            }
            con.close();
        }catch (Exception e){
            System.out.println(e);
        }
        return movie;
    }
}
