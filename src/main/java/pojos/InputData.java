package pojos;

/*
 * For a new ride to be added in DB
 */
public class InputData {
    
    private String customerName;
    
    private String driverName;
    
    private Integer customerRating;
    
    private Integer driverRating;
    
    public InputData(String customerName, String driverName, Integer customerRating, Integer driverRating) {
        this.customerName = customerName;
        this.driverName = driverName;
        this.customerRating = customerRating;
        this.driverRating = driverRating;
    }
    
    public String getCustomerName() {
        return customerName;
    }
    
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
    public String getDriverName() {
        return driverName;
    }
    
    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }
    
    public Integer getCustomerRating() {
        return customerRating;
    }
    
    public void setCustomerRating(Integer customerRating) {
        this.customerRating = customerRating;
    }
    
    public Integer getDriverRating() {
        return driverRating;
    }
    
    public void setDriverRating(Integer driverRating) {
        this.driverRating = driverRating;
    }
}
