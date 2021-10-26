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
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author ilove
 */
@Named(value = "forSaleController")
@ManagedBean
@SessionScoped
public class ForSaleController implements Serializable {

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

    public String doListSaleInDex() {
        saleList = forSalePropEJB.findForSaleProp();
        return "saleProp/listSaleProperties.xhtml";
    }
    
    public String doListSale() {
        saleList = forSalePropEJB.findForSaleProp();
        return "listSaleProperties.xhtml";
    }

    public String doSearchSale() {
        forsale = forSalePropEJB.searchForSale(forsale.getPid());
        return "searchForSaleLanding.xhtml";
    }
    //methods for View Detais
    public List<ForSale> searchFSDetails(){
        saleList = forSalePropEJB.forSaleDetails(forsale.getPid()); 
        return saleList;
    }
    
    public ForSale viewDetails(Long pid){
        return forsale = forSalePropEJB.searchForSale(pid);
    } 
    public String fsDetails(Long pId){ 
        System.out.print(pId);  
        viewDetails(pId);
        return "/salePropertyDetails.xhtml";
    }

    //Getters & Setters         
    public ForSale getForSaleProp() {
        System.out.println("1");
        return forsale;
    }

    public void setSaleProp(ForSale forsale) {
        this.forsale = forsale;
    }
    

    public List<ForSale> getSaleList() {
        System.out.println("2");
        return saleList;
    }

    public void setSaleList(List<ForSale> saleList) {
        this.saleList = saleList;
    }

    public Integer getSaleListSize() {
        Integer listSize = saleList.size();
        return listSize;
    }
}
