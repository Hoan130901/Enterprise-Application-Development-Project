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
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

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
        forrent = forRentPropEJB.createForRentProp(forrent);
        rentList = forRentPropEJB.findForRentProp();
        return "listForRent.xhtml";
    }
    
    public String doListRent() {
        rentList = forRentPropEJB.findForRentProp();
        return "listForRent.xhtml";
    }
    

    //Getters & Setters         
    public ForRent getForRentProp() {
        return forrent;
    }

    public void setForRentProp(ForRent forrent) {
        this.forrent = forrent;
    }

    public List<ForRent> getForRentPropList() {
        return rentList;
    }

    public void setForRentPropList(List<ForRent> rentlist) {
        this.rentList = rentlist;
    }
     // Public Methods           


    //Getters & Setters         

    
}
