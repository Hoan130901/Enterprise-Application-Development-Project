/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ass3.PManager;

import ass3.web.PManager;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author andre
 */
@Named(value = "managerPropertyController")
@ManagedBean()
@RequestScoped
public class ManagerPropertyController {

     // Attributes             
    @EJB
    private ManagerPropertyEJB managerEJB;
    private PManager pmanager = new PManager();
    private List<PManager> pmanagerList = new ArrayList<>();
    

    
    
    // Public Methods           
    public String doCreatePManager() {
        try{
        pmanager = managerEJB.createPManagerProp(pmanager);
        pmanagerList = managerEJB.findPManagerProp();
        FacesMessage message = new FacesMessage("Property Manager: " + pmanager.getFirstName()+" " +pmanager.getLastName() + " has been created");
        FacesContext.getCurrentInstance().addMessage("managerForm:successMessage", message);
        }
        catch (Exception e) {
            FacesMessage message = new FacesMessage("Add Property Manager Fail! Please try Again " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage("managerForm:successMessage", message);
        }
        return "listManager.xhtml";
    }
    public String doListPManager() {
        pmanagerList = managerEJB.findPManagerProp();
        return "listManager.xhtml";
    }
    
    public String doListPManagerIndex() {
        pmanagerList = managerEJB.findPManagerProp();
        return "manager/listManager.xhtml";
    }
    //Getters & Setters      
    public int getListSize(){
        return pmanagerList.size();
    }
    public PManager getPManager() {
        return pmanager;
    }

    public void setPManager(PManager pmanager) {
        this.pmanager = pmanager;
    }

    public List<PManager> getPManagerList() {
        return pmanagerList;
    }

    public void setPManagerList(List<PManager> pmanagerList) {
        this.pmanagerList = pmanagerList;
    }
    
    public List<PManager> getPManagerListD() {
        pmanagerList = managerEJB.findPManagerProp();
        return pmanagerList;
    }
    
}
