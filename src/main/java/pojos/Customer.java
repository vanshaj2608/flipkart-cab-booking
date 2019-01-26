package pojos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Customer {
    
    /*
     * Considering it to be unique
     */
    private String name;
    
    private Integer totalTrips;
    
    private Integer totalRating;
    
    private Double rating;
    
    private Set<Driver> oneStarDrivers;
    
    private Set<String> rideDoneWith;
    
    public Customer(String name,Integer totalTrips, Integer totalRating) {
        this.name = name;
        this.totalTrips = totalTrips;
        this.totalRating = totalRating;
        this.oneStarDrivers = new HashSet<>();
        this.rideDoneWith = new HashSet<>();
        this.rating = 3.0;
    }
    
    public Double getRating() {
        return rating;
    }
    
    public void setRating() {
        if(this.totalTrips !=0)
            this.rating = (1.0 * this.totalRating/this.totalTrips);
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Integer getTotalTrips() {
        return totalTrips;
    }
    
    public void setTotalTrips(Integer totalTrips) {
        this.totalTrips = totalTrips;
    }
    
    public Integer getTotalRating() {
        return totalRating;
    }
    
    public void setTotalRating(Integer totalRating) {
        this.totalRating = totalRating;
    }
    
    public Set<Driver> getOneStarDrivers() {
        return oneStarDrivers;
    }
    
    public void setOneStarDrivers(Set<Driver> oneStarDrivers) {
        this.oneStarDrivers = oneStarDrivers;
    }
    
    public Set<String> getRideDoneWith() {
        return rideDoneWith;
    }
    
    public void setRideDoneWith(Set<String> rideDoneWith) {
        this.rideDoneWith = rideDoneWith;
    }
}
