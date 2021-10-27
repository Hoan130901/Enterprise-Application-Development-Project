/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ass3.ForSale;

import ass3.web.ForSale;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author ilove
 */
@Named(value = "forSaleController")
@ManagedBean()
@RequestScoped
public class ForSaleController implements Serializable {

    // Attributes             
    @EJB
    private ForSalePropertyEJB forSalePropEJB;
    private ForSale forsale = new ForSale();
    private List<ForSale> saleList = new ArrayList<>();

    // Public Methods    
    public String doCreateSale() {//method to cretae for sale property
        try {
            forsale = forSalePropEJB.createForSaleProp(forsale);
            saleList = forSalePropEJB.findForSaleProp();
            //display succesful added message
            FacesMessage message = new FacesMessage("Sale Property " + forsale.getLocation() + " has been created");
            FacesContext.getCurrentInstance().addMessage("saleForm:successMessage", message);
        } catch (EJBException e) {
            //display fail message if catch exception
            FacesMessage message = new FacesMessage("Add Sale Property Fail! Please try Again ");
            FacesContext.getCurrentInstance().addMessage("addSaleForm:errorMessage", message);
            return "addSaleProperty.xhtml";
        }
        return "listSaleProperties.xhtml";
    }

    public String doListSaleInDex() {//method to list all property
        saleList = forSalePropEJB.findForSaleProp();
        if (saleList.isEmpty()) {//check if list is empty 
            FacesMessage message = new FacesMessage("There is no For Sale property in the system");
            FacesContext.getCurrentInstance().addMessage("saleForm:emptyListMessage", message);
            return "saleProp/listSaleProperties.xhtml";
        }    
        return "saleProp/listSaleProperties.xhtml";
    }
    //method to list all sale properties
    public String doListSale() {
        saleList = forSalePropEJB.findForSaleProp();
        return "listSaleProperties.xhtml";
    }
    //method for search sale function
    public String doSearchSale() {
        try {
            forsale = forSalePropEJB.searchForSale(forsale.getPid());
        } catch (EJBException ee) { //cacth exception when id does not match in the database
            FacesMessage message = new FacesMessage("Sale Property not found! Please try again.");
            FacesContext.getCurrentInstance().addMessage("searchForm:failMessage", message);
            return "searchSaleProperty.xhtml";
        }
        return "salePropertyDetails.xhtml";
    }

    //methods for View Detais
    public String viewDetails(Long pId) {
        forsale = forSalePropEJB.findForSaleWithID(pId,forsale);
        return "salePropertyDetails.xhtml";
    }

    //Getters & Setters         
    public ForSale getForSaleProp() {
        return forsale;
    }

    public void setSaleProp(ForSale forsale) {
        this.forsale = forsale;
    }

    public void setForSalePropList(List<ForSale> saleList) {
        this.saleList = saleList;
    }

    public Integer getSaleListSize() {
        Integer listSize = saleList.size();
        return listSize;
    }

    public List<ForSale> getForSalePropList() {
        saleList = forSalePropEJB.findForSaleProp();
        return saleList;
    }
}
