package nu.te4.moviefx.entities.filters;

import nu.te4.moviefx.entities.Filter;
import nu.te4.moviefx.entities.Movie;

/**
 * A filter which filters based on the movies title.
 * @author Adrian Klasson
 */
public class TitleFilter extends Filter {

    private final String filterValue;
    private final FilterChoice filterChoice;

    public TitleFilter(String filterValue, FilterChoice filterChoice) {
        this.filterValue = filterValue;
        this.filterChoice = filterChoice;
    }
    
    public enum FilterChoice{
        Contains("innehåller"),
        DoesNotContain("innehåller inte"),
        StartsWith("börjar med"),
        DoesNotStartWith("börjar inte med"),
        EndsWith("slutar med"),
        DoesNotEndWith("slutar inte med");
        
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
        String title = movie.getTitle();
        
        switch(filterChoice){
            case Contains:
                return title.contains(filterValue);
            case DoesNotContain:
                return !title.contains(filterValue);
            case StartsWith:
                return title.startsWith(filterValue);
            case DoesNotStartWith:
                return !title.startsWith(filterValue);
            case EndsWith:
                return title.endsWith(filterValue);
            case DoesNotEndWith:
                return !title.endsWith(filterValue);
            default:
                //This shouldn't be possible to reach
                return false;
        }
    }

    @Override
    public String toString() {
        return String.format("Filmens titel %s \"%s\".", filterChoice.toString(), filterValue);
    }
}
