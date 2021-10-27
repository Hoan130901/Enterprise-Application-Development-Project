/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ass3.PManager;

import ass3.web.PManager;
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
public class ManagerPropertyEJB {

     //Attributes             
    @PersistenceContext(unitName = "Assignment3_EAPU")
    private EntityManager em;

    //Public methods  
    //find all Property Manager method
    public List<PManager> findPManagerProp() {
        TypedQuery<PManager> query = em.createNamedQuery("getManagerQuery",PManager.class);
        return query.getResultList();
    }
    //create property manager method
    public PManager createPManagerProp(PManager pmanager) {
        em.persist(pmanager);
        return pmanager;
    }
    //search property manager
    public PManager searchPManager(String mfn, String mln) {
        TypedQuery<PManager> query = em.createNamedQuery("searchManagerQuery", PManager.class);
        query.setParameter("mfn", mfn);
        query.setParameter("mln", mln);
        return query.getSingleResult();
    }
    //view details property manager
    public PManager findManagerWithName(String mId, PManager pManager) {
        Query query = em.createNamedQuery("viewDetailsQuery", PManager.class);
        query.setParameter("mId", mId);
        pManager = (PManager) query.getSingleResult();
        return pManager;
    }
}
