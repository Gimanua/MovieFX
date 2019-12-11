/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.moviefx.beans;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import nu.te4.moviefx.ConnectionFactory;
import nu.te4.moviefx.entities.Director;
import nu.te4.moviefx.entities.Genre;
import nu.te4.moviefx.entities.Movie;

import javax.xml.transform.Result;

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

    /**
     * Inserts the director, genres and the movie itself as needed into the projects database.
     * @param movie The movie to insert.
     * @return True if success, false if failure.
     */
    public boolean postMovie(Movie movie){
        try(Connection connection = ConnectionFactory.getConnection()){
            if(movie.getDirector().getId() == null){
                Director updatedDirector = directorBean.insertDirector(movie.getDirector());
                movie.setDirector(updatedDirector);
            }

            for(int i = 0; i < movie.getGenres().size(); i++){
                Genre genre = movie.getGenres().get(i);
                if(genre.getId() == null){
                    Genre updatedGenre = genreBean.insertGenre(genre);
                    movie.getGenres().set(i, updatedGenre);
                }
            }

            Movie updatedMovie = insertMovie(movie);
            connectGenres(updatedMovie);

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Inserts a movie into the projects database.
     * @param movie
     * @throws SQLException If database is unavailable or movie already exists.
     */
    private Movie insertMovie(Movie movie) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        String sql = "INSERT INTO movies (id, title, grade, length, release_date, budget, revenue, director_id) VALUES(NULL, ?, ?, ?, ?, ?, ? ,?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, movie.getTitle());
        stmt.setDouble(2, movie.getGrade());
        stmt.setTime(3, movie.getLength());
        stmt.setTimestamp(4, movie.getReleaseDate());
        stmt.setLong(5, movie.getBudget());
        stmt.setLong(6, movie.getRevenue());
        stmt.setInt(7, movie.getDirector().getId());
        stmt.executeUpdate();

        sql = "SELECT LAST_INSERT_ID()";
        Statement idStmt = connection.createStatement();
        ResultSet data = idStmt.executeQuery(sql);
        data.first();
        int id = data.getInt(1);

        movie.setId(id);
        return movie;
    }

    /**
     * Connects all the genres with a movie in the projects database.
     * @param movie The movie which genres should be connected with itself.
     * @throws SQLException If database is unavailable or movies genres has already been connected;
     */
    private void connectGenres(Movie movie) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        for(Genre genre : movie.getGenres()){
            String sql = "INSERT INTO movies_genres (movie_id, genre_id) VALUES(?,?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, movie.getId());
            stmt.setInt(2, genre.getId());
            stmt.executeUpdate();
        }
    }
}
