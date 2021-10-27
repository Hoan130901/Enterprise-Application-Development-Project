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
@NamedQuery(name = "getForSaleQuery", query = "select fs from ForSale fs")
@NamedQuery (name = "searchForSaleQuery", query = "select p from ForSale p where p.pId = :pId")
@NamedQuery(name = "getSaleStreetName", query = "select p from ForSale p WHERE  CONCAT(p.streetNum,' ',p.streetName) = :fStreetName")
@NamedQuery(name = "getForSaleByID", query = "select p from ForSale p where p.pId = :fspId")
public class ForSale extends Properties {

    //Attributes
    private Float salePrice;
    
    //constructor
    public ForSale() {
    }

    public ForSale(Float salePrice, Integer numberOfBedrooms, String description, Integer numberOfBathroom, String streetNum, String streetName, String city, String state, String country, Integer postCode, String propertyType) {
        super(numberOfBedrooms, description, numberOfBathroom, streetNum, streetName, city, state, country, postCode, propertyType);
        this.salePrice = salePrice;
    }

    
    
    //getter and setter
    public Float getSalePrice() {
        return salePrice;
    }
    public String getSalePriceFormat(){
        String fsPrice ="$"+getSalePrice();
        return fsPrice;
    }

    public void setSalePrice(Float salePrice) {
        this.salePrice = salePrice;
    }
    
    
}
