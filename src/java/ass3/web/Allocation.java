/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ass3.web;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 *
 * @author ilove
 */
@Entity
@NamedQuery(name = "getAllocationQuery", query = "select a from Allocation a")
@NamedQuery(name = "deleteAllocationByID", query = "DELETE FROM Allocation p where p.id = :dpId ")
@NamedQuery(name = "selectAllocationByID", query = "SELECT p FROM Allocation p where p.id = :SpId ")


public class Allocation implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch= FetchType.LAZY,
                cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name = "manager_id", referencedColumnName ="MID")
    private PManager manager;
    //attribute
    @ManyToOne(fetch= FetchType.LAZY,
                cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name = "properties_id", referencedColumnName ="PID")
    private Properties properties;
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    
    
    //constructor
    public Allocation() {
        
    }

    public Allocation(PManager manager, Properties properties, Date creationDate) {
        this.manager = manager;
        this.properties = properties;
        this.creationDate = creationDate;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public PManager getManager() {
        return manager;
    }

    //getter and setter
    public void setManager(PManager manager) {    
        this.manager = manager;
    }
    
    


}
