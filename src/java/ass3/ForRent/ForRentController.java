/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ass3.ForRent;

import ass3.web.ForRent;
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
@Named(value = "forRentController")
@ManagedBean()
@RequestScoped
public class ForRentController {

    @EJB
    private ForRentPropertyEJB forRentPropEJB;
    private ForRent forrent = new ForRent();
    private List<ForRent> rentList = new ArrayList<>();

    // Public Methods           
    public String doCreateRent() {
        try {
            forrent = forRentPropEJB.createForRentProp(forrent);
            rentList = forRentPropEJB.findForRentProp();
            //display succesful added message
            FacesMessage message = new FacesMessage("Rental Property " + forrent.getLocation() + " has been created");
            FacesContext.getCurrentInstance().addMessage("forRentForm:successMessage", message);
        } catch (EJBException e) {
            //display fail message if exception occur 
            FacesMessage message = new FacesMessage("Add For Rent Property Fail! Please try Again ");
            FacesContext.getCurrentInstance().addMessage("addForRentForm:errorMessage", message);
            return "addForRent.xhtml";
        }
        return "listForRent.xhtml";
    }

    public String doListRent() {
        rentList = forRentPropEJB.findForRentProp();
        return "listForRent.xhtml";
    }

    public String doListRentInDex() {
        rentList = forRentPropEJB.findForRentProp();
        if (rentList.isEmpty()) {//check if list empty, then display message 
            FacesMessage message = new FacesMessage("There is no For Rent property in the system");
            FacesContext.getCurrentInstance().addMessage("forRentForm:emptyListMessage", message);
            return "forRent/listForRent.xhtml";
        }
        return "forRent/listForRent.xhtml";
    }

    public String doSearchForRent() {
        try {
            forrent = forRentPropEJB.searchForRent(forrent.getPid());
        } catch (EJBException ee) { //cacth exception when id does not match in the database
            FacesMessage message = new FacesMessage("Rental Property not found! Please try again.");
            FacesContext.getCurrentInstance().addMessage("searchForm:failMessage", message);
            return "searchForRent.xhtml";
        }

        return "forRentDetails.xhtml";
    }

    public String getForRentID(Long ID) {
        forrent = forRentPropEJB.findForRentWithID(ID, forrent);
        return "forRentDetails.xhtml";
    }

    //Getters & Setters 
    public int getForRentListSize() {
        return rentList.size();
    }

    public ForRent getForRentProp() {
        return forrent;
    }

    public void setForRentProp(ForRent forrent) {
        this.forrent = forrent;
    }

    public List<ForRent> getForRentPropList() {
        rentList = forRentPropEJB.findForRentProp();
        return rentList;
    }

    public void setForRentPropList(List<ForRent> rentlist) {
        this.rentList = rentlist;
    }

}
