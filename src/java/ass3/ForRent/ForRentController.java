/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ass3.ForRent;

import ass3.web.ForRent;
import ass3.web.ForSale;
import java.util.*;
import javax.inject.Named;
import javax.ejb.EJB;
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
        try{
        forrent = forRentPropEJB.createForRentProp(forrent);
        rentList = forRentPropEJB.findForRentProp();
        FacesMessage message = new FacesMessage("Rental Property " + forrent.getLocation() + " has been created");
        FacesContext.getCurrentInstance().addMessage("forRentForm:successMessage", message);
        }
        catch (Exception e) {
            FacesMessage message = new FacesMessage("Add For Rent Property Fail! Please try Again " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage("forRentForm:successMessage", message);
        }
        return "listForRent.xhtml";
    }

    public String doListRent() {
        rentList = forRentPropEJB.findForRentProp();
        return "listForRent.xhtml";
    }

    public String doListRentInDex() {
        rentList = forRentPropEJB.findForRentProp();
        return "forRent/listForRent.xhtml";
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
