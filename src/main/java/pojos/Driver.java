package pojos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Driver {
    
    /*
     * Considering it to be unique
     */
    private String name;
    
    private Integer totalTrips;
    
    private Integer totalRating;
    
    private Double rating;
    
    private Boolean isOnline;
    
    private Set<Customer> oneStarCustomers;
    
    public Driver(String name, Integer totalTrips, Integer totalRating, Boolean isOnline) {
        this.name = name;
        this.totalTrips = totalTrips;
        this.totalRating = totalRating;
        this.isOnline = isOnline;
        this.oneStarCustomers = new HashSet<>();
        this.rating = 3.0;
    }
    
    public Double getRating() {
        return rating;
    }
    
    public void setRating() {
        if(this.totalTrips !=0)
            this.rating = ( 1.0 * this.totalRating/this.totalTrips);
    }
    
    public Set<Customer> getOneStarCustomers() {
        return oneStarCustomers;
    }
    
    public void setOneStarCustomers(Set<Customer> oneStarCustomers) {
        this.oneStarCustomers = oneStarCustomers;
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
    
    public Boolean getOnline() {
        return isOnline;
    }
    
    public void setOnline(Boolean online) {
        isOnline = online;
    }
}
