package nu.te4.moviefx.entities;

/**
 * Represents a movie director.
 * @author Adrian Klasson
 */
public class Director {
    private Integer id;
    private String name;

    public Director(String name) {
        this.name = name;
    }

    public Director(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
}
