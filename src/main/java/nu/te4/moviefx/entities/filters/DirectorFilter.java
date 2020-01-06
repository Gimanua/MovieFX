/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.moviefx.entities.filters;

import nu.te4.moviefx.entities.Filter;
import nu.te4.moviefx.entities.Movie;

/**
 *
 * @author giman
 */
public class DirectorFilter extends Filter{

    private final String filterValue;
    private final FilterChoice filterChoice;

    public DirectorFilter(String filterValue, FilterChoice filterChoice) {
        this.filterValue = filterValue;
        this.filterChoice = filterChoice;
    }
    
    public enum FilterChoice{
        DirectedBy("har regisserats"),
        NotDirectedBy("har inte regisserad");
        
        private final String sentenceValue;

        private FilterChoice(String sentenceValue) {
            this.sentenceValue = sentenceValue;
        }

        @Override
        public String toString() {
            return sentenceValue;
        }
    }
    
    @Override
    public boolean filter(Movie movie) {
        switch(filterChoice){
            case DirectedBy:
                return movie.getDirector().getName().equals(filterValue);
            case NotDirectedBy:
                return !movie.getDirector().getName().equals(filterValue);
            default:
                return false;
        }
    }

    @Override
    public String toString() {
        return String.format("Filmen %s av %s.", filterChoice.toString(), filterValue);
    }
    
}
