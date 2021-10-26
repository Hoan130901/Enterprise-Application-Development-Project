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
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

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
    public String doCreateAllocation() {
        allocation = allocationEJB.createAllocation(allocation);
        allocationList = allocationEJB.findAllocation();
        return "listAllocation.xhtml";
    }

    public String doListAllocationIndex() {
        allocationList = allocationEJB.findAllocation();
        return "allocation/listAllocation.xhtml";
    }

    public void doCreateRental() {
        allocationEJB.addRentalProp(managerName, rentalStreetName, allocation);
    }

    public void doCreateSale() {
        allocationEJB.addSaleProp(managerName, saleStreetName, allocation);
    }

    public void doCreateInRent() {
        allocationEJB.addInRentProp(managerName, inRentStreetName, allocation);
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
}
