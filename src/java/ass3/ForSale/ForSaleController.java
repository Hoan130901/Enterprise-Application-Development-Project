/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ass3.ForSale;

import ass3.web.ForSale;
import java.util.*;
import javax.inject.Named;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

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
        forsale = forSalePropEJB.createForSaleProp(forsale);
        saleList = forSalePropEJB.findForSaleProp();
        return "listSaleProperties.xhtml";
    }
    public String doListSale() {
        saleList = forSalePropEJB.findForSaleProp();
        return "listSaleProperties.xhtml";
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
    public Integer getSaleListSize(){
        Integer listSize = saleList.size();
        return listSize;
    }
}
