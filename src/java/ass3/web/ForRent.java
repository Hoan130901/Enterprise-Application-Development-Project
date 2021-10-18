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
public class ForRent extends Properties {
    //Attributes
    private Float rentalPrice;

    public ForRent() {
    }

    public ForRent(Float rentalPrice, Integer numberOfBedrooms, String description, Integer numberOfBathroom, String streetNum, String streetName, String city, String statte, String country, Integer postCode, String propertyType) {
        super(numberOfBedrooms, description, numberOfBathroom, streetNum, streetName, city, statte, country, postCode, propertyType);
        this.rentalPrice = rentalPrice;
    }
    



    public Float getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(Float rentalPrice) {
        this.rentalPrice = rentalPrice;
    }    
       
}
