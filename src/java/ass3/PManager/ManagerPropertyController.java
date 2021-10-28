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
    public String doCreatePManager() {//method to create Pmanager
        try{
        pmanager = managerEJB.createPManagerProp(pmanager);
        pmanagerList = managerEJB.findPManagerProp();
        //display successful added message
        FacesMessage message = new FacesMessage("Property Manager: " + pmanager.getFirstName()+" " +pmanager.getLastName() + " has been created");
        FacesContext.getCurrentInstance().addMessage("managerForm:successMessage", message);
        }
        catch (EJBException e) {//display fail message if catch exception
        FacesMessage message = new FacesMessage("Add Property Manager Fail! Please try Again " + e.getMessage());
        FacesContext.getCurrentInstance().addMessage("managerForm:failMessage", message);
        }
        return "listManager.xhtml";
    }
    public String doListPManager() {//method to list all manager
        pmanagerList = managerEJB.findPManagerProp();
        if (pmanagerList.isEmpty()) {//check if list is empty 
            FacesMessage message = new FacesMessage("There is no property manager in the system");
            FacesContext.getCurrentInstance().addMessage("managerForm:emptyListMessage", message);
            return "listManager.xhtml";
        }
        return "listManager.xhtml";
    }
    
    public String doListPManagerIndex() {//method to list all manager from index
        pmanagerList = managerEJB.findPManagerProp();
        if (pmanagerList.isEmpty()) {//check if list is empty 
            FacesMessage message = new FacesMessage("There is no property manager in the system");
            FacesContext.getCurrentInstance().addMessage("managerForm:emptyListMessage", message);
            return "manager/listManager.xhtml";
        }
        return "manager/listManager.xhtml";
    }
    //view details function
    public String getManagerName(Long mId) {
        pmanager = managerEJB.findManagerWithName(mId, pmanager);
        if(pmanager.getAllocation().isEmpty()){
            FacesMessage message = new FacesMessage("There is no Allocation for this Manager");
            FacesContext.getCurrentInstance().addMessage("detailForm:emptyListMessage", message);
            return "managerDetails.xhtml";
        }
        return "managerDetails.xhtml";
    }
    
    //method to search property manager
    public String doSearchManager() {
        try {
            pmanager = managerEJB.searchPManager(pmanager.getFirstName(),pmanager.getLastName());
            if(pmanager.getAllocation().isEmpty()){
            FacesMessage message = new FacesMessage("There is no Allocation for this Manager");
            FacesContext.getCurrentInstance().addMessage("detailForm:emptyListMessage", message);
            return "managerDetails.xhtml";
            }
        } catch (EJBException ee) { //cacth exception when id does not match in the database
            FacesMessage message = new FacesMessage("Property Manager not found! Please try again.");
            FacesContext.getCurrentInstance().addMessage("searchForm:failMessage", message);
            return "searchManager.xhtml";
        }
        return "managerDetails.xhtml";
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

    public void setPManagerList(List<PManager> pmanagerList) {
        this.pmanagerList = pmanagerList;
    }
    
    public List<PManager> getPManagerListD() {
        pmanagerList = managerEJB.findPManagerProp();
        return pmanagerList;
    }
    
}
