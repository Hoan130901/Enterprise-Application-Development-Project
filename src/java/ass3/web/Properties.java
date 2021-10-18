/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ass3.web;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;


/**
 *
 * @author ilove
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
//@NamedQuery (name = "getPropertiesQuery", query = "select p from Properties p where p.address = :address")
public class Properties implements Serializable {
    //Attributes
    @Id
    @GeneratedValue
    protected Long pId;
    protected Integer numberOfBedrooms  ;
    protected String description;
    protected Integer numberOfBathroom;
    protected String streetNum;
    protected String streetName;
    protected String city;
    protected String state;
    protected String country;
    protected Integer postCode;
    protected String propertyType;

    public Properties(Integer numberOfBedrooms, String description, Integer numberOfBathroom, String streetNum, String streetName, String city, String state, String country, Integer postCode, String propertyType) {
        this.numberOfBedrooms = numberOfBedrooms;
        this.description = description;
        this.numberOfBathroom = numberOfBathroom;
        this.streetNum = streetNum;
        this.streetName = streetName;
        this.city = city;
        this.state = state;
        this.country = country;
        this.postCode = postCode;
        this.propertyType = propertyType;
    }
    
    //Constructor
    public Properties(){
        
    }
    
    //Getters and Setters
    public String getLocation(){
        String location = getStreetNum()+" ,"+getStreetName();
        return location;
    }
    public Long getPid() {
        return pId;
    }

    public void setPid(Long id) {
        this.pId = id;
    }

    public Integer getNumberOfBathroom() {
        return numberOfBathroom;
    }

    public void setNumberOfBathroom(Integer numberOfBathroom) {
        this.numberOfBathroom = numberOfBathroom;
    }

    public String getStreetNum() {
        return streetNum;
    }

    public void setStreetNum(String streetNum) {
        this.streetNum = streetNum;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getPostCode() {
        return postCode;
    }

    public void setPostCode(Integer postCode) {
        this.postCode = postCode;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public Integer getNumberOfBedrooms() {
        return numberOfBedrooms;
    }

    public void setNumberOfBedrooms(Integer numberOfBedrooms) {
        this.numberOfBedrooms = numberOfBedrooms;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
