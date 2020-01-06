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
public class RevenueFilter extends Filter {

    private final long filterValue;
    private final FilterChoice filterChoice;

    public RevenueFilter(long filterValue, FilterChoice filterChoice) {
        this.filterValue = filterValue;
        this.filterChoice = filterChoice;
    }
    
    public enum FilterChoice{
        GreaterThan("är större än"),
        LessThan("är mindre än");
        
        private final String sentenceValue;
        
        private FilterChoice(String sentenceValue){
            this.sentenceValue = sentenceValue;
        }
        
        @Override
        public String toString(){
            return sentenceValue;
        }
    }
    
    @Override
    public boolean filter(Movie movie) {
        long revenue = movie.getRevenue();
        switch(filterChoice){
            case GreaterThan:
                return revenue > filterValue;
            case LessThan:
                return revenue < filterValue;
            default:
                return false;
        }
    }

    @Override
    public String toString() {
        return String.format("Filmens inkomst %s %d.", filterChoice.toString(), filterValue);
    }
    
}
