/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.moviefx.beans;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import nu.te4.moviefx.ConnectionFactory;
import nu.te4.moviefx.entities.Director;
import nu.te4.moviefx.entities.Genre;
import nu.te4.moviefx.entities.Movie;

/**
 * Controls all the logic concering a Movie.
 * @author Adrian Klasson
 */
public class MovieBean {
    
    private final DirectorBean directorBean = new DirectorBean();
    private final GenreBean genreBean = new GenreBean();
    
    /**
     * Gets all the movies from the projects database.
     * @return A list of the movies. The list is empty if it's not possible to retrieve the movies.
     */
    public List<Movie> getMovies(){
        List<Movie> movies = new ArrayList();
        
        try(Connection connection = ConnectionFactory.getConnection()){
            String sql = "SELECT * FROM movies";
            Statement stmt = connection.createStatement();
            ResultSet data = stmt.executeQuery(sql);
            
            while(data.next()){
                int id = data.getInt("id");
                String title = data.getString("title");
                double grade = data.getDouble("grade");
                Time length = data.getTime("length");
                Timestamp releaseDate = data.getTimestamp("release_date");
                long budget = data.getLong("budget");
                long revenue = data.getLong("revenue");
                int directorID = data.getInt("director_id");
                Director director = directorBean.getDirector(directorID);
                List<Genre> genres = genreBean.getGenres(id);
                
                movies.add(new Movie(id, title, budget, revenue, length, grade, releaseDate, director, genres));
            }
            
        } catch(Exception ex){
            System.out.println("MovieBean.getMovies: " + ex.getMessage());
        }
        
        return movies;
    }
}
