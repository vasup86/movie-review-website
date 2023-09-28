/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4.searchlog.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 *
 * @author student
 */
public class searchCRUD {
    
    
     public static Connection getCon(){
        Connection con = null;
        try{
            //for mysql under services
            //old: jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=CONVERT_TO_NULL
            //new: jdbc:mysql://localhost:3306/mysql?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC
            String connection = System.getenv("searchdb");
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://"+connection+"/SEARCH_PPS?allowPublicKeyRetrieval=true&useSSL=false", "root", "student");
            System.out.println("DB Connection");
        }catch(Exception e){
            System.out.println(e);
        }  
        return con;
    }
    
    public static void insertSearch(int id, String email){
        try{
            Connection con = getCon();
            String query = "INSERT INTO SEARCH (movieId, email) VALUES (" + id + ", '" + email + "');";
            PreparedStatement ps = con.prepareStatement(query);
            ps.execute(query);
            System.out.println("Search inserted");
            con.close();
        }catch (Exception e){
            System.out.println(e);
        }
    
    }
}