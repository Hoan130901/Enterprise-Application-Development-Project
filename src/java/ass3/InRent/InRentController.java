/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ass3.InRent;

import ass3.web.InRent;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author andre
 */
@Named(value = "inRentController")
@ManagedBean()
@RequestScoped
public class InRentController {

  // Attributes             
    @EJB
    private InRentPropertyEJB inRentPropEJB;
    private InRent inrent = new InRent();
    private List<InRent> inrentList = new ArrayList<>();
    

    
    
    // Public Methods  
    //method to create inrent prop
    public String doCreateInRent() {
        inrent = inRentPropEJB.createInrent(inrent.getPid(),inrent);
        inrentList = inRentPropEJB.findInRentProp();
        return "listInRent.xhtml";
    }
    //method to list all in rent prop
    public String doListInRent() {
        inrentList = inRentPropEJB.findInRentProp();
        return "listInRent.xhtml";
    }
    //method to list all in rent prop from index
    public String doListInRentIndex() {
        inrentList = inRentPropEJB.findInRentProp();
        return "inRent/listInRent.xhtml";
    }
    //method to search in rent prop with pId
    //method to search for rent property
    public String doSearchInRent() {
        try {
            inrent = inRentPropEJB.searchInRent(inrent.getPid());
        } catch (EJBException ee) { //cacth exception when id does not match in the database
            FacesMessage message = new FacesMessage("Rental Property not found! Please try again.");
            FacesContext.getCurrentInstance().addMessage("searchForm:failMessage", message);
            return "searchForRent.xhtml";
        }

        return "forRentDetails.xhtml";
    }

    //method to view detail from dataTable
    public String getForRentID(Long ID) {
        inrent = inRentPropEJB.findInRentWithID(ID, inrent);
        return "forRentDetails.xhtml";
    }
    //Getters & Setters         
    public InRent getInRentProp() {
        return inrent;
    }

    public void setInRentProp(InRent inrent) {
        this.inrent = inrent;
    }
    
    public List<InRent> getInRentPropList() {
        inrentList = inRentPropEJB.findInRentProp();
        return inrentList;
    }

    public void setInRentPropList(List<InRent> inrentList) {
        this.inrentList = inrentList;
    }
     // Public Methods           


    //Getters & Setters    
    
}
