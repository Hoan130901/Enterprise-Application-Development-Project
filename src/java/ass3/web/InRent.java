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
@NamedQuery(name = "getInRentQuery", query = "select p from InRent p")//get all in rent property query
@NamedQuery(name = "getInRentStreetName", query = "select p from InRent p WHERE  CONCAT(p.streetNum,' ',p.streetName) = :fStreetName")//get in rent street name query to persist inrent property from for rent
@NamedQuery(name = "searchInRentQuery", query = "select p from InRent p where p.pId = :pId")//search in rent property query
public class InRent extends Properties {

    //Attributes
    private Float rentalPrice;
    private String tenantName;
    private Boolean furnished;

    //Constructure
    public InRent() {
    }

    public InRent(Float rentalPrice, String tenantName, Boolean furnished, Integer rentalID, Integer numberOfBedrooms, String description, Integer numberOfBathroom, String streetNum, String streetName, String city, String state, String country, Integer postCode, String propertyType) {
        super(numberOfBedrooms, description, numberOfBathroom, streetNum, streetName, city, state, country, postCode, propertyType);
        this.rentalPrice = rentalPrice;
        this.tenantName = tenantName;
        this.furnished = furnished;
    }

    //getter and setter
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

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

}
