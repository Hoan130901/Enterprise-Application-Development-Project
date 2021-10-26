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
    public List<PManager> findPManagerProp() {
        TypedQuery<PManager> query = em.createNamedQuery("getManagerQuery",PManager.class);
        return query.getResultList();
    }

    public PManager createPManagerProp(PManager pmanager) {
        em.persist(pmanager);
        return pmanager;
    }
    
}
