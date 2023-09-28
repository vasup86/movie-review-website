/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4.frontend.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author student
 */
public class userCRUD {
      
    //database connection
    public static Connection getCon(){
        Connection con = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String connection = System.getenv("frontenddb");
            String s = "jdbc:mysql://"+connection+"/FRONTEND_PPS?allowPublicKeyRetrieval=true&useSSL=false";
            System.out.println(s);
            con = DriverManager.getConnection(s, "root", "student");
            System.out.println("DB Connection");
        }catch(Exception e){
            System.out.println(e);
        }  
        return con;
    }
    
    public static boolean getUser(String email, String password){
        try{
            Connection con = getCon();
            
            //email is not case sensitive, so all lowercase, password is case sensitive
            String query = "SELECT * FROM USER WHERE email = '" + email.toLowerCase() + "' and password = '" + password + "';";
            //System.out.println(query);
            
            PreparedStatement ps = con.prepareStatement(query);
            
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                System.out.println("login success");
                return true;
            }
            con.close();
        }catch (Exception e){
            System.out.println(e);
        }
        
       
        return false;
    
    }
        
}
