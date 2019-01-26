import Util.Constants;
import pojos.Customer;
import pojos.Driver;
import pojos.InputData;

import java.io.BufferedReader;
import java.io.Console;
import java.io.InputStreamReader;
import java.util.*;

public class Starter {
    
    private List<Customer> customers = new ArrayList<>();
    private List<Driver> drivers = new ArrayList<>();
    
    public static void main(String[] args) {
        
        Starter cabBooking = new Starter();
        cabBooking.addStaticData();
        
        cabBooking.findCustomersAndDriversRating();
        cabBooking.test();
    }
    
    public void findCustomersAndDriversRating() {
        
        for(Customer customer : customers) {
            System.out.println("Rating for " + customer.getName() + " is - " + customer.getRating() );
        }
    
        for(Driver driver : drivers) {
            System.out.println("Rating for " + driver.getName() + " is - " + driver.getRating() );
        }
    }
    
    public void test() {
        
        for(Customer customer : customers) {
            Set<Driver> driverSet = findAvailableDrivers(customer);
            System.out.println("Available drivers for " + customer.getName() + " with rating - " + customer.getRating());
            for(Driver driver : driverSet) {
                System.out.println(driver.getName() + " with rating - " + driver.getRating());
            }
            if(driverSet.isEmpty()) {
                System.out.println("No available drivers for this customer");
            }
        }
    }
    
    public Set<Driver> findAvailableDrivers(Customer customer) {
        
        Set<Driver> eligibleDrivers = new HashSet<>();
        
        Boolean isNewCustomer = false;
        Double customerRating = 0.0;
        if(customer.getTotalTrips() == 0){
            isNewCustomer = true;
        }else{
            customerRating = customer.getRating();
        }
        
        if (isNewCustomer) {
            
            eligibleDrivers.addAll(drivers);
            
        }else {
            
            for(Driver driver : drivers){
                if(driver.getRating() < customerRating || !driver.getOnline() ||
                        !driver.getOneStarCustomers().contains(customer) || !customer.getOneStarDrivers().contains(driver) ) {
                    continue;
                }
                eligibleDrivers.add(driver);
            }
            
            if(eligibleDrivers.isEmpty()){
                
                /*
                 * Finding drivers with whom ride has been done
                 */
                
                Set<String> eligibleDriverNames = customer.getRideDoneWith();
                
                for(Driver driver : drivers) {
                    if(eligibleDriverNames.contains(driver.getName())){
                        
                        if(!driver.getOneStarCustomers().contains(customer) && driver.getOnline() && !customer.getOneStarDrivers().contains(driver))
                            eligibleDrivers.add(driver);
                    }
                }
                eligibleDrivers.removeAll(customer.getOneStarDrivers());
                
            }
        }
        
        return eligibleDrivers;
    }
    
    public void addData(InputData data) {
        
        String customerName = data.getCustomerName();
        String driverName = data.getDriverName();
        
        /*
         * Finding customer
         */
        
        Customer customer = null;
        for(Customer customer1 : customers) {
            if(customer1.getName().equals(customerName)){
                customer = customer1;
                break;
            }
        }
        
        if(customer == null){
            /*
             * Means no customer found create a new one.
             */
            Customer customerNew = new Customer("Customer " + customers.size() + 1,0,0);
            customers.add(customerNew);
        }
        
        /*
         * Finding Driver
         */
        
        Driver driver = null;
        for(Driver driver1 : drivers) {
            if(driver1.getName().equals(driverName)) {
                driver = driver1;
                break;
            }
        }
        
        if(driver == null) {
            /*
             * Means no driver found create a new one.
             */
            Driver driverNew = new Driver("Driver "+ drivers.size() + 1 , 0 , 0,true);
            drivers.add(driverNew);
        }
        
        /*
         * Now adding ride details corresponding
         * to the customer and driver
         */
        
        customer.setTotalRating(customer.getTotalRating() + data.getCustomerRating());
        customer.setTotalTrips(customer.getTotalTrips() + 1);
        customer.setRating();
        Set<String> names = customer.getRideDoneWith();
        names.add(driverName);
        customer.setRideDoneWith(names);
        
        if(data.getCustomerRating().equals(Constants.MINIMUM_THRESHHOLD_RATING_CUSTOMER)) {
            Set<Driver> oneStarDrivers = customer.getOneStarDrivers();
            oneStarDrivers.add(driver);
            customer.setOneStarDrivers(oneStarDrivers);
        }
        
        driver.setTotalRating(driver.getTotalRating() + data.getDriverRating());
        driver.setTotalTrips(driver.getTotalTrips() + 1);
        driver.setRating();
        
        if(data.getDriverRating().equals(Constants.MINIMUM_THRESHHOLD_RATING_DRIVER)) {
            Set<Customer> oneStarCustomers = driver.getOneStarCustomers();
            oneStarCustomers.add(customer);
            driver.setOneStarCustomers(oneStarCustomers);
        }
        
    }
    public void addStaticData() {
        
        /*
         * Adding new customers to DB
         */
        
        Customer customer1 = new Customer("Customer 1",0,0);
        Customer customer2 = new Customer("Customer 2",0,0);
        Customer customer3 = new Customer("Customer 3",0,0);
        
        customers.add(customer1);
        customers.add(customer2);
        customers.add(customer3);
        
        /*
         * Adding new drivers to DB
         */
    
        Driver driver1 = new Driver("Driver 1",0,0,true);
        Driver driver2 = new Driver("Driver 2",0,0,true);
        Driver driver3 = new Driver("Driver 3",0,0,true);
        
        drivers.add(driver1);
        drivers.add(driver2);
        drivers.add(driver3);
        
        /*
         * Adding ride details
         */
    
        List<InputData> rideHistory = new ArrayList<InputData>() {
            {
                add(new InputData( "Customer 1","Driver 1",5,4));
                add(new InputData( "Customer 2","Driver 1",4,5));
                add(new InputData( "Customer 3","Driver 1",2,1));
                add(new InputData( "Customer 1","Driver 2",1,5));
                add(new InputData( "Customer 2","Driver 2",5,5));
                add(new InputData( "Customer 3","Driver 2",5,4));
                add(new InputData( "Customer 1","Driver 3",2,3));
                add(new InputData( "Customer 2","Driver 3",5,4));
                add(new InputData( "Customer 3","Driver 3",3,3));
                add(new InputData( "Customer 1","Driver 3",1,3));
                add(new InputData( "Customer 2","Driver 2",5,4));
                add(new InputData( "Customer 3","Driver 1",3,1));
            }
        };
        
        for(InputData data : rideHistory) {
            addData(data);
        }
        System.out.println(customers.toString());
        System.out.println(drivers.toString());
    }
    
}
