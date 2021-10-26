/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ass3.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author ilove
 */
@Entity
@NamedQuery(name = "getManagerQuery", query = "select m from PManager m")
@NamedQuery(name = "getManagerFullName", query = "select m from PManager m WHERE CONCAT(m.firstName,' ',m.LastName) = :mFullName")

public class PManager implements Serializable {

    //Attributes
    @Id
    @GeneratedValue
    protected Long mId;
    protected String firstName;
    protected String LastName;
    protected String email;
    protected String phone;
    
    @OneToMany(fetch = FetchType.LAZY)  
    private List<Allocation> allocation = new ArrayList <Allocation> ();
    @OneToMany
    private List<Properties> properties; 
    //constructor
    public PManager(){
        
    }

    public PManager(String firstName, String LastName, String email, String phone, List<Properties> properties) {
        this.firstName = firstName;
        this.LastName = LastName;
        this.email = email;
        this.phone = phone;
        this.properties = properties;
    }

    //getter and setter 

    public String getFullName(){
        String fullName = getFirstName()+" "+getLastName();
        return fullName;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }
    
    
    public Long getMid() {
        return mId;
    }

    public void setMid(Long id) {
        this.mId = id;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Allocation> getAllocation() {
        return allocation;
    }

    public void setAllocation(List<Allocation> allocation) {
        this.allocation = allocation;
    }

    

    public Long getmId() {
        return mId;
    }

    public void setmId(Long mId) {
        this.mId = mId;
    }

    public List<Properties> getProperties() {
        return properties;
    }

    public void setProperties(List<Properties> properties) {
        this.properties = properties;
    }

    
    
}
