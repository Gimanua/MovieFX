package nu.te4.moviefx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Produces connections to the database used by the project.
 * @author Adrian Klasson
 */
public class ConnectionFactory {
    
    /**
     * This method does some important initialization to connect to the database. Call this method once before calling the {@link #getConnection() getConnection} method.
     */
    public static void init(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        } catch(Exception ex){
            System.out.println("ConnectionFactory.init: " + ex.getMessage());
        }
    }
    
    /**
     * Gets a connection to the database. Don't forget to call the {@link #init() init} method once before calling this method.
     * @return 
     */
    public static Connection getConnection(){
        try{
            return DriverManager.getConnection("jdbc:mysql://localhost/movie_db?user=user&password=zFp0MhPAQoeLLAbR");
        } catch(SQLException ex){
            System.out.println("ConnectionFactory.getConnection: " + ex.getMessage());
            return null;
        }
    }
}
