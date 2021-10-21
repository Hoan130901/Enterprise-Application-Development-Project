/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ass3.ForSale;

import ass3.web.ForSale;
import static ass3.web.Properties_.pId;
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
@Named(value = "forSaleController")
@ManagedBean()
@RequestScoped
public class ForSaleController {

    // Attributes             
    @EJB
    private ForSalePropertyEJB forSalePropEJB;
    private ForSale forsale = new ForSale();
    private List<ForSale> saleList = new ArrayList<ForSale>();

    // Public Methods           
    public String doCreateSale() {
        try {
            forsale = forSalePropEJB.createForSaleProp(forsale);
            saleList = forSalePropEJB.findForSaleProp();
            FacesMessage message = new FacesMessage("Sale Property " + forsale.getLocation() + " has been created");
            FacesContext.getCurrentInstance().addMessage("saleForm:successMessage", message);
        } catch (Exception e) {
            FacesMessage message = new FacesMessage("Add Sale Property Fail! Please try Again " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage("saleForm:successMessage", message);
        }
        return "listSaleProperties.xhtml";
    }

    public String doListSale() {
        saleList = forSalePropEJB.findForSaleProp();
        return "saleProp/listSaleProperties.xhtml";
    }
    
    public String doListSaleFromAddPage() {
        saleList = forSalePropEJB.findForSaleProp();
        return "listSaleProperties.xhtml";
    }

    public String doSearchSale() {
        forsale = forSalePropEJB.searchForSale(forsale.getPid());
        return "salePropertyDetails.xhtml";
    }
   
    public String returnDetails(){
        return "salePropertyDetails.xhtml";
    }
    
    public ForSale fsDetails(Long pId){    
        System.out.println("1");
        forsale = forSalePropEJB.searchForSale(pId);
        System.out.println("1");
        returnDetails();
        System.out.println("1");
        return forsale;
    }

    //Getters & Setters         
    public ForSale getForSaleProp() {
        return forsale;
    }

    public void setForSaleProp(ForSale forsale) {
        this.forsale = forsale;
    }

    public List<ForSale> getForSalePropList() {
        return saleList;
    }

    public void setForSalePropList(List<ForSale> saleList) {
        this.saleList = saleList;
    }

    public Integer getSaleListSize() {
        Integer listSize = saleList.size();
        return listSize;
    }
}
