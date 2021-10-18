/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ass3.InRent;

import ass3.web.InRent;
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
public class InRentPropertyEJB {

    //Attributes             
    @PersistenceContext(unitName = "Assignment3_EAPU")
    private EntityManager em;

    //Public methods           
    public List<InRent> findInRentProp() {
        TypedQuery<InRent> query = em.createNamedQuery("getInRentQuery",InRent.class);
        return query.getResultList();
    }

    public InRent createInRentProp(InRent inrent) {
        em.persist(inrent);
        return inrent;
    }
}
