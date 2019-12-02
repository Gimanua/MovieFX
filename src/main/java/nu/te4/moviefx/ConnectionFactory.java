/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.moviefx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Adrian Klasson
 */
public class ConnectionFactory {
    
    public static void init(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        } catch(Exception ex){
            System.out.println("ConnectionFactory.init: " + ex.getMessage());
        }
    }
    
    public static Connection getConnection(){
        try{
            return DriverManager.getConnection("jdbc:mysql://localhost/movie_db?user=user&password=zFp0MhPAQoeLLAbR");
        } catch(SQLException ex){
            System.out.println("ConnectionFactory.getConnection: " + ex.getMessage());
            return null;
        }
    }
}
