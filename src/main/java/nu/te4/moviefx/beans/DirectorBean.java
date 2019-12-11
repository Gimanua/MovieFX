package nu.te4.moviefx.beans;

import java.sql.*;
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

    /**
     * Insets a director into the projects database.
     * @param director The director to insert
     * @return The same director but with the auto-generated id set.
     * @throws SQLException If database is unavailable or the director already exists.
     */
    public Director insertDirector(Director director) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        String sql = "INSERT INTO directors (id, name) VALUES(NULL, ?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, director.getName());
        stmt.executeUpdate();

        sql = "SELECT LAST_INSERT_ID()";
        Statement idStmt = connection.createStatement();
        ResultSet data = idStmt.executeQuery(sql);
        data.first();
        int id = data.getInt(1);

        director.setId(id);
        return director;
    }
}
