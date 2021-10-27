/*
 * To change this license header, choose License Headers in Project erties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ass3.Allocation;

import ass3.web.Allocation;
import ass3.web.ForRent;
import ass3.web.ForSale;
import ass3.web.InRent;
import ass3.web.PManager;
import ass3.web.Properties;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author andre
 */
@Stateless
public class AllocationEJB {
        //Attributes             
    @PersistenceContext(unitName = "Assignment3_EAPU")
    private EntityManager em;

    //Public methods           
    public List<Allocation> findAllocation() {
        TypedQuery<Allocation> query = em.createNamedQuery("getAllocationQuery",Allocation.class);
        return query.getResultList();
    }

    public Allocation createAllocation(Allocation allocation) {
        em.persist(allocation);
        return allocation;
    }
    public Allocation addRentalProp(String managerName,String rentalStreetName, Allocation allocation) {
        Query query1 = em.createNamedQuery("getManagerFullName",PManager.class);
        query1.setParameter("mFullName", managerName);
        Query query2 = em.createNamedQuery("getStreetName",ForRent.class);
        query2.setParameter("fStreetName", rentalStreetName);
        PManager manager = (PManager)query1.getSingleResult();
        Properties prop = (Properties) query2.getSingleResult();

        allocation.setManager(manager);
        allocation.setProperties(prop);
        allocation.setCreationDate(new Date());
        em.persist(allocation);
        
        
        TypedQuery<Allocation> query3 = em.createNamedQuery("getAllocationQuery",Allocation.class);
        ArrayList<Allocation> allocationList1 = new ArrayList<>();
        List<Allocation> allocationLists = query3.getResultList();
        
        
        for(int i=0;i<allocationLists.size();i++) {
            allocation = allocationLists.get(i);
            String fullname = allocation.getManager().getFirstName() + " " +allocation.getManager().getLastName();
            if(fullname.equals(managerName)  ) {
                allocationList1.add(allocation);
            }
        }
        allocationList1.add(allocation);
        manager.setAllocation(allocationList1);
        em.persist(allocation);
        return allocation;
    }
        public Allocation addInRentProp(String managerName,String InRentStreetName, Allocation allocation) {
        Query query1 = em.createNamedQuery("getManagerFullName",PManager.class);
        query1.setParameter("mFullName", managerName);
        Query query2 = em.createNamedQuery("getInRentStreetName",InRent.class);
        query2.setParameter("fStreetName", InRentStreetName);
        PManager manager = (PManager)query1.getSingleResult();
        Properties prop = (Properties) query2.getSingleResult();
        
        
        
        allocation.setManager(manager);
        allocation.setProperties(prop);
        allocation.setCreationDate(new Date());
        em.persist(allocation);
        TypedQuery<Allocation> query3 = em.createNamedQuery("getAllocationQuery",Allocation.class);
        ArrayList<Allocation> allocationList1 = new ArrayList<>();
        List<Allocation> allocationLists = query3.getResultList();
        for(int i=0;i<allocationLists.size();i++) {
            allocation = allocationLists.get(i);
            String fullname = allocation.getManager().getFirstName() + " " +allocation.getManager().getLastName();
            if(fullname.equals(managerName)  ) {
                allocationList1.add(allocation);
            }
        }
        allocationList1.add(allocation);
        manager.setAllocation(allocationList1);
        em.persist(allocation);
        return allocation;
    }
        public Allocation addSaleProp(String managerName,String SaleStreetName, Allocation allocation) {
        Query query1 = em.createNamedQuery("getManagerFullName",PManager.class);
        query1.setParameter("mFullName", managerName);
        Query query2 = em.createNamedQuery("getSaleStreetName",ForSale.class);
        query2.setParameter("fStreetName", SaleStreetName);
        PManager manager = (PManager)query1.getSingleResult();
        Properties prop = (Properties) query2.getSingleResult();
        allocation.setManager(manager);
        allocation.setProperties(prop);
        allocation.setCreationDate(new Date());
        em.persist(allocation);
        TypedQuery<Allocation> query3 = em.createNamedQuery("getAllocationQuery",Allocation.class);
        ArrayList<Allocation> allocationList1 = new ArrayList<>();
        List<Allocation> allocationLists = query3.getResultList();
        for(int i=0;i<allocationLists.size();i++) {
            allocation = allocationLists.get(i);
            String fullname = allocation.getManager().getFirstName() + " " +allocation.getManager().getLastName();
            if(fullname.equals(managerName)  ) {
                allocationList1.add(allocation);
            }
        }
        allocationList1.add(allocation);
        manager.setAllocation(allocationList1);
        em.persist(allocation);
        return allocation;
    }
        public Allocation DeleteAllocationWithID(Long ID, Allocation allocation) {
            
          Query query1 = em.createNamedQuery("selectAllocationByID", Allocation.class);
          query1.setParameter("SpId", ID);
          allocation = (Allocation)query1.getSingleResult();
          TypedQuery<Allocation> query3 = em.createNamedQuery("getAllocationQuery",Allocation.class);
          List<Allocation> allocationLists = query3.getResultList();
          
          
          if(allocationLists.contains(allocation)) {
              allocationLists.remove(allocation);
          }
         allocation.getManager().setAllocation(allocationLists);
        Query query = em.createNamedQuery("deleteAllocationByID", Allocation.class);  
        query.setParameter("dpId", ID).executeUpdate();
        return allocation;
    }
    
}
