/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ass3.ForRent;

import ass3.web.ForRent;
import ass3.web.ForSale;
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
public class ForRentPropertyEJB {
    //Attributes             

    @PersistenceContext(unitName = "Assignment3_EAPU")
    private EntityManager em;

    //Public methods         
    //method for list all query to geta all for rent properties
    public List<ForRent> findForRentProp() {
        TypedQuery<ForRent> query = em.createNamedQuery("getForRentQuery", ForRent.class);
        return query.getResultList();
    }
    //method for search query by pId
    public ForRent searchForRent(Long pId) {
        TypedQuery<ForRent> query = em.createNamedQuery("searchForRentQuery", ForRent.class);
        query.setParameter("pId", pId);
        return query.getSingleResult();
    }
    //method to persist for rent property to the table 
    public ForRent createForRentProp(ForRent forrent) {
        em.persist(forrent);
        return forrent;
    }
    //method for view details query within dataTable by pId
    public ForRent findForRentWithID(Long pId, ForRent forrent) {
        Query query = em.createNamedQuery("searchForRentQuery", ForRent.class);
        query.setParameter("pId", pId);
        forrent = (ForRent) query.getSingleResult();
        return forrent;
    }
}
