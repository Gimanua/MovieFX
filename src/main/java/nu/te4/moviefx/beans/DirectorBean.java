package nu.te4.moviefx.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
}
