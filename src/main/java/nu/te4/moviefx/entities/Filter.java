/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.moviefx.entities;

/**
 * The base class for all filters used to filter movies.
 * @author Adrian Klasson
 */
public abstract class Filter {
    
    /**
     * Decides if a movie should be included by this filter or not.
     * @param movie The movie to filter.
     * @return Returns true if the movie is included by this filter, false otherwise.
     */
    public abstract boolean filter(Movie movie);
    @Override
    public abstract String toString();
}
