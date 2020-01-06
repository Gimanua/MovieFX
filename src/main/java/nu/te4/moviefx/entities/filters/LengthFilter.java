/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.moviefx.entities.filters;

import java.sql.Time;
import java.util.Calendar;
import nu.te4.moviefx.entities.Filter;
import nu.te4.moviefx.entities.Movie;

/**
 *
 * @author giman
 */
public class LengthFilter extends Filter {
    private final Time filterValue;
    private final FilterChoice filterChoice;

    public LengthFilter(Time filterValue, FilterChoice filterChoice) {
        this.filterValue = filterValue;
        this.filterChoice = filterChoice;
    }
    
    public enum FilterChoice{
        LongerThan("längre än"),
        ShorterThan("mindre än");
        
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
        Time length = movie.getLength();
        switch(filterChoice){
            case LongerThan:
                return length.compareTo(filterValue) >= 1;
            case ShorterThan:
                return length.compareTo(filterValue) <= -1;
            default:
                return false;
        }
    }

    @Override
    public String toString() {
        return String.format("Filmen är %s %s.", filterChoice, filterValue.toString());
    }
}
