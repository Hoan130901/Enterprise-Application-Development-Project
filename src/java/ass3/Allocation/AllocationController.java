/*
 * To change this license header, choose License Headers in Project erties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ass3.Allocation;

import ass3.web.Allocation;
import java.util.*;
import javax.inject.Named;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author andre
 */
@Named(value = "allocationController")
@ManagedBean()
@RequestScoped
public class AllocationController {

    @EJB
    private AllocationEJB allocationEJB;
    private Allocation allocation = new Allocation();
    private String managerName;
    private String rentalStreetName;
    private String saleStreetName;
    private String inRentStreetName;

    private List<Allocation> allocationList = new ArrayList<>();

    // Public Methods           
    public String doListAllocationIndex() {//list all allocation from index
        allocationList = allocationEJB.findAllocation();
        if (allocationList.isEmpty()) {//check if list empty, then display message 
            FacesMessage message = new FacesMessage("No allocation has been set in the system");
            FacesContext.getCurrentInstance().addMessage("allocationForm:emptyListMessage", message);
            return "allocation/listAllocation.xhtml";
        }
        return "allocation/listAllocation.xhtml";
    }

    public String getAllocationById() {//search allocation by ID
        
        try {
            allocation = allocationEJB.findAllocationById(allocation.getId(), allocation);
        } catch (EJBException ee) { //cacth exception when id does not match in the database
            FacesMessage message = new FacesMessage("Allocation not found! Please try again.");
            FacesContext.getCurrentInstance().addMessage("searchForm:failMessage", message);
            return "searchAllocation.xhtml";
        }
        return "allocationDetails.xhtml";
    }

    public String doListAllocation() {//list all allocation
        allocationList = allocationEJB.findAllocation();
        if (allocationList.isEmpty()) {//check if list empty, then display message 
            FacesMessage message = new FacesMessage("No allocation has been set in the system");
            FacesContext.getCurrentInstance().addMessage("allocationForm:emptyListMessage", message);
            return "listAllocation.xhtml";
        }
        return "listAllocation.xhtml";
    }

    public void doCreateRental() {//allocate for rent property to selected manager   
        try {
            allocationEJB.addRentalProp(managerName, rentalStreetName, allocation);
            //display succesful added message
            FacesMessage message = new FacesMessage("Alllocation for " + allocation.getManager().getFirstName() + " has been created with property " + allocation.getProperties().getLocation());
            FacesContext.getCurrentInstance().addMessage("addAllocationForm:successMessage", message);
        } catch (EJBException e) {
            //display fail message if exception occur 
            FacesMessage message = new FacesMessage("Add Allocation Fail! Please try Again ");
            FacesContext.getCurrentInstance().addMessage("addAllocationForm:errorMessage", message);
        }
    }

    public void doCreateSale() {//allocate for sale property to selected manager   

        try {
            allocationEJB.addSaleProp(managerName, saleStreetName, allocation);
            //display succesful added message
            FacesMessage message = new FacesMessage("Alllocation for " + allocation.getManager().getFirstName() + " has been created with property " + allocation.getProperties().getLocation());
            FacesContext.getCurrentInstance().addMessage("addAllocationForm:successMessage", message);
        } catch (EJBException e) {
            //display fail message if exception occur 
            FacesMessage message = new FacesMessage("Add Allocation Fail! Please try Again ");
            FacesContext.getCurrentInstance().addMessage("addAllocationForm:errorMessage", message);
        }
    }

    public void doCreateInRent() {//allocate in rent property to selected manager       
        try {
            allocationEJB.addInRentProp(managerName, inRentStreetName, allocation);
            //display succesful added message
            FacesMessage message = new FacesMessage("Alllocation for " + allocation.getManager().getFirstName() + " has been created with property " + allocation.getProperties().getLocation());
            FacesContext.getCurrentInstance().addMessage("addAllocationForm:successMessage", message);
        } catch (EJBException e) {
            //display fail message if exception occur 
            FacesMessage message = new FacesMessage("Add Allocation Fail! Please try Again ");
            FacesContext.getCurrentInstance().addMessage("addAllocationForm:errorMessage", message);
        }

    }
     public String DeleteAllocationByID(Long ID) {//delete allocation       
        try {
            allocation = allocationEJB.DeleteAllocationWithID(ID, allocation);
            //display succesful deleted message
            FacesMessage message = new FacesMessage("Alllocation for " + allocation.getManager().getFirstName() + "with property " + allocation.getProperties().getLocation() +" has been deleted");
            FacesContext.getCurrentInstance().addMessage("allocationForm:deleteMessage", message);
        } catch (EJBException e) {
            //display fail message if exception occur 
            FacesMessage message = new FacesMessage("Delete Allocation Fail! Please try Again ");
            FacesContext.getCurrentInstance().addMessage("allocationForm:errorMessage", message);
        }
        return "listAllocation.xhtml";
    }

    //Getters & Setters         
    public Allocation getAllocation() {
        return allocation;
    }

    public void setAllocation(Allocation allocation) {
        this.allocation = allocation;
    }

    public String getManagerName() {
        return managerName;
    }

    public String getRentalStreetName() {
        return rentalStreetName;
    }

    public void setRentalStreetName(String rentalStreetName) {
        this.rentalStreetName = rentalStreetName;
    }

    public String getSaleStreetName() {
        return saleStreetName;
    }

    public void setSaleStreetName(String saleStreetName) {
        this.saleStreetName = saleStreetName;
    }

    public String getInRentStreetName() {
        return inRentStreetName;
    }

    public void setInRentStreetName(String inRentStreetName) {
        this.inRentStreetName = inRentStreetName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public List<Allocation> getAllocationList() {
        allocationList = allocationEJB.findAllocation();
        return allocationList;
    }

    public void setAllocationList(List<Allocation> allocationList) {
        this.allocationList = allocationList;
    }

    public int getListSize() {
        return allocationList.size();
    }
}
