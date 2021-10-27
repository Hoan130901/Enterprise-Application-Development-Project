/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ass3.InRent;

import ass3.web.ForRent;
import ass3.web.InRent;
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
public class InRentPropertyEJB {

    //Attributes             
    @PersistenceContext(unitName = "Assignment3_EAPU")
    private EntityManager em;

    //Public methods   
    //method to list all in rent property
    public List<InRent> findInRentProp() {
        TypedQuery<InRent> query = em.createNamedQuery("getInRentQuery",InRent.class);
        return query.getResultList();
    }
    //method to persist in rent property into table
    public InRent createInRentProp(InRent inrent) {
        em.persist(inrent);
        return inrent;
    }
    ////method to persist in rent property into table with for rent property info
    public InRent createInrent(long forrentID,InRent inrent) {
      Query query = em.createNamedQuery("getForRentByID",ForRent.class);
      query.setParameter("fpId", forrentID);
      ForRent forrent = (ForRent)query.getSingleResult();
      inrent.setPropertyType(forrent.getPropertyType());
      inrent.setNumberOfBedrooms(forrent.getNumberOfBedrooms());
      inrent.setNumberOfBathroom(forrent.getNumberOfBathroom());
      inrent.setDescription(forrent.getDescription());
      inrent.setStreetNum(forrent.getStreetNum());
      inrent.setStreetName(forrent.getStreetName());
      inrent.setPostCode(forrent.getPostCode());
      inrent.setFurnished(forrent.getFurnished());
      inrent.setRentalPrice(forrent.getRentalPrice());
      inrent.setCity(forrent.getCity());
      inrent.setState(forrent.getState());
      inrent.setCountry(forrent.getCountry());
      inrent.setPid(forrentID);
      Query query1 = em.createNamedQuery("deleteForRentID",ForRent.class);
      query1.setParameter("dpId", forrentID).executeUpdate();
      em.persist(inrent);
      return inrent;
    }
    //method for search inrent with pId
     public InRent searchInRent(Long pId) {
        TypedQuery<InRent> query = em.createNamedQuery("searchInRentQuery", InRent.class);
        query.setParameter("pId", pId);
        return query.getSingleResult();
    }

    //method to view details of in rent property from dataTable
    public InRent findInRentWithID(Long ID, InRent inRent) {
        Query query = em.createNamedQuery("searchInRentQuery", InRent.class);
        query.setParameter("pId", ID);
        inRent = (InRent) query.getSingleResult();
        return inRent;
    }
}
