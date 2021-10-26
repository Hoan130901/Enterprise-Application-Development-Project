    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ass3.web;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;

/**
 *
 * @author ilove
 */
@Entity
@NamedQuery(name = "getForRentQuery", query = "select p from ForRent p")
@NamedQuery (name = "searchForRentQuery", query = "select p from ForRent p where p.pId = :pId")
public class ForRent extends Properties {
    //Attributes
    private Float rentalPrice;
    private Boolean furnished;

    public ForRent() {
    }

    public ForRent(Float rentalPrice, Boolean furnished, Integer numberOfBedrooms, String description, Integer numberOfBathroom, String streetNum, String streetName, String city, String state, String country, Integer postCode, String propertyType) {
        super(numberOfBedrooms, description, numberOfBathroom, streetNum, streetName, city, state, country, postCode, propertyType);
        this.rentalPrice = rentalPrice;
        this.furnished = furnished;
    }
    
    //Getters and Setters
    public Boolean getFurnished() {
        return furnished;
    }
    public void setFurnished(Boolean furnished) {    
        this.furnished = furnished;
    }

    public Float getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(Float rentalPrice) {
        this.rentalPrice = rentalPrice;
    }    
       
}
