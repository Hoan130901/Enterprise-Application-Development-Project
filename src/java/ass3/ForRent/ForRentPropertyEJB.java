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
    public List<ForRent> findForRentProp() {
        TypedQuery<ForRent> query = em.createNamedQuery("getForRentQuery",ForRent.class);
        return query.getResultList();
    }

     public ForSale searchForSale(Long pId){
        TypedQuery<ForSale> query = em.createNamedQuery("searchForSaleQuery",ForSale.class);
        query.setParameter("pId", pId);
        return query.getSingleResult();
    }
     
    public ForRent createForRentProp(ForRent forrent) {
        em.persist(forrent);
        return forrent;
    }
}
