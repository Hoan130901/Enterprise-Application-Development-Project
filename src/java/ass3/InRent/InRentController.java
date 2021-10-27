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
    public String doCreateInRent() {//method to create inrent

        try {
            inrent = inRentPropEJB.createInrent(inrent.getPid(), inrent);
            inrentList = inRentPropEJB.findInRentProp();
            //display succesful added message
            FacesMessage message = new FacesMessage("In Rent Property " + inrent.getLocation() + " has been created");
            FacesContext.getCurrentInstance().addMessage("inRentForm:successMessage", message);
        } catch (EJBException e) {
            //display fail message if catch exception
            FacesMessage message = new FacesMessage("Add In Rent Property Fail! Please try Again ");
            FacesContext.getCurrentInstance().addMessage("addinRentForm:errorMessage", message);
            return "addInRent.xhtml";
        }
        return "listInRent.xhtml";
    }

    public String doListInRent() {//method to list all in rent property
         inrentList = inRentPropEJB.findInRentProp();
        if (inrentList.isEmpty()) {//check if list is empty 
            FacesMessage message = new FacesMessage("There is no In Rent property in the system");
            FacesContext.getCurrentInstance().addMessage("inRentForm:emptyListMessage", message);
            return "listInRent.xhtml";
        }
        return "listInRent.xhtml";
    }

    public String doListInRentIndex() {//method to list all inrent property from index
        inrentList = inRentPropEJB.findInRentProp();
        if (inrentList.isEmpty()) {//check if list is empty 
            FacesMessage message = new FacesMessage("There is no In Rent property in the system");
            FacesContext.getCurrentInstance().addMessage("inRentForm:emptyListMessage", message);
            return "inRent/listInRent.xhtml";
        }
        return "inRent/listInRent.xhtml";
    }

    //view details function
    public String getInRentID(Long ID) {
        inrent = inRentPropEJB.findInRentWithID(ID, inrent);
        return "inRentDetails.xhtml";
    }
    //method to search for rent property

    public String doSearchInRent() {
        try {
            inrent = inRentPropEJB.searchInRent(inrent.getPid());
        } catch (EJBException ee) { //cacth exception when id does not match in the database
            FacesMessage message = new FacesMessage(" In Rent Property not found! Please try again.");
            FacesContext.getCurrentInstance().addMessage("searchForm:failMessage", message);
            return "searchInRent.xhtml";
        }

        return "inRentDetails.xhtml";
    }

    //Getters & Setters  
    public int getListSize(){
        return inrentList.size();
    }
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
}
