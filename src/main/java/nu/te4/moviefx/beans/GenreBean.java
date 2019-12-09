package nu.te4.moviefx.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import nu.te4.moviefx.ConnectionFactory;
import nu.te4.moviefx.entities.Genre;

/**
 * Controls all the logic concering a Genre or Genres for a Movie.
 * @author Adrian Klasson
 */
public class GenreBean {
    
    /**
     * Gets all the genres from the projects database.
     * @return A list of all the genres. The list is empty if it's not possible to retrieve the genres.
     */
    public List<Genre> getGenres(){
        List<Genre> genres = new ArrayList();
        
        try(Connection connection = ConnectionFactory.getConnection()){
            String sql = "SELECT * FROM genres";
            Statement stmt = connection.createStatement();
            ResultSet data = stmt.executeQuery(sql);
            
            while(data.next()){
                int genreID = data.getInt("id");
                String name = data.getString("name");
                
                genres.add(new Genre(genreID, name));
            }
        } catch(Exception ex){
            System.out.println("GenreBean.getGenres(): " + ex.getMessage());
        }
        
        return genres;
    }
    
    /**
     * Gets all the genres for a certain movie from the projects database.
     * @param movieID The id of the movie to retrieve genres from.
     * @return A list of genres the movie has. The list is empty if it's not possible to retrieve the genres.
     */
    public List<Genre> getGenres(int movieID){
        
        List<Genre> genres = new ArrayList();
        
        try(Connection connection = ConnectionFactory.getConnection()){
            String sql = "SELECT genre_id FROM movies_genres WHERE movie_id=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, movieID);
            ResultSet data = stmt.executeQuery();
            while(data.next()){
                int genreID = data.getInt("genre_id");
                genres.add(getGenre(genreID));
            }
            
        } catch(Exception ex){
            System.out.println("GenreBean.getGenres(int): " + ex.getMessage());
        }
        
        return genres;
    }
    
    /**
     * Gets a genre with a certain id from the projects database.
     * @param genreID The id of the genre to retrieve.
     * @return The genre with that id. Returns null if the genre can't be retrieved
     */
    private Genre getGenre(int genreID){
        try(Connection connection = ConnectionFactory.getConnection()){
            String sql = "SELECT * FROM genres WHERE id=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, genreID);
            ResultSet data = stmt.executeQuery();
            if(data.first())
                return new Genre(data.getInt("id"), data.getString("name"));
        } catch(Exception ex){
            System.out.println("GenreBean.getGenre: " + ex.getMessage());
        }
        
        return null;
    }
}
