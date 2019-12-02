/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.moviefx.entities;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
/**
 *
 * @author Adrian Klasson
 */
public class Movie {
    private Integer id;
    private String title;
    private long budget;
    private long revenue;
    private Time length;
    private double grade;
    private Timestamp releaseDate;
    private Director director;
    private List<Genre> genres;

    public Movie(String title, long budget, long revenue, Time length, double grade, Timestamp releaseDate, Director director, List<Genre> genres) {
        this.title = title;
        this.budget = budget;
        this.revenue = revenue;
        this.length = length;
        this.grade = grade;
        this.releaseDate = releaseDate;
        this.director = director;
        this.genres = genres;
    }

    public Movie(Integer id, String title, long budget, long revenue, Time length, double grade, Timestamp releaseDate, Director director, List<Genre> genres) {
        this.id = id;
        this.title = title;
        this.budget = budget;
        this.revenue = revenue;
        this.length = length;
        this.grade = grade;
        this.releaseDate = releaseDate;
        this.director = director;
        this.genres = genres;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getBudget() {
        return budget;
    }

    public void setBudget(long budget) {
        this.budget = budget;
    }

    public long getRevenue() {
        return revenue;
    }

    public void setRevenue(long revenue) {
        this.revenue = revenue;
    }

    public Time getLength() {
        return length;
    }

    public void setLength(Time length) {
        this.length = length;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public Timestamp getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Timestamp releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }
}
