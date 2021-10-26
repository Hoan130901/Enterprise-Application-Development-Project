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
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

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
    public String doCreateInRent() {
        inrent = inRentPropEJB.createInrent(inrent.getPid(),inrent);
        inrentList = inRentPropEJB.findInRentProp();
        return "listInRent.xhtml";
    }
    public String doListInRent() {
        inrentList = inRentPropEJB.findInRentProp();
        return "listInRent.xhtml";
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
