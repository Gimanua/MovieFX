package nu.te4.moviefx.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import nu.te4.moviefx.ConnectionFactory;
import nu.te4.moviefx.entities.Director;

/**
 * Controls all the logic concering a Director for a Movie.
 * @author Adrian Klasson
 */
public class DirectorBean {
    
    /**
     * Gets the director with a certain id from the projects database.
     * @param directorID The id of the director.
     * @return The director with that id. Returns null if the director can't be retrieved.
     */
    public Director getDirector(int directorID){
        try(Connection connection = ConnectionFactory.getConnection()){
            String sql = "SELECT * FROM directors WHERE id=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, directorID);
            ResultSet data = stmt.executeQuery();
            if(data.first())
                return new Director(data.getInt("id"), data.getString("name"));
            
        } catch(Exception ex){
            System.out.println("DirectorBean.getDirector: " + ex.getMessage());
        }
        
        return null;
    }
    
    /**
     * Gets all the directors from the projects database.
     * @return A list of all the directors. Returns an empty list of the directors can't be retrieved.
     */
    public List<Director> getDirectors(){
        
        List<Director> directors = new ArrayList();
        
        try(Connection connection = ConnectionFactory.getConnection()){
            String sql = "SELECT * FROM directors";
            Statement stmt = connection.createStatement();
            ResultSet data = stmt.executeQuery(sql);
            
            while(data.next()){
                int id = data.getInt("id");
                String name = data.getString("name");
                
                directors.add(new Director(id, name));
            }
        } catch(Exception ex){
            System.out.println("DirectorBean.getDirectors: " + ex.getMessage());
        }
        
        return directors;
    }
}
