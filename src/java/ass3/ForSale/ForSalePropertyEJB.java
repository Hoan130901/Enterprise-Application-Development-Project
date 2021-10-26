package ass3.ForSale;

import ass3.web.ForSale;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import javax.faces.context.FacesContext;

/**
 *
 * @author andre
 */
@Stateless
public class ForSalePropertyEJB {

    //Attributes             
    @PersistenceContext(unitName = "Assignment3_EAPU")
    private EntityManager em;

    //Public methods           
    public List<ForSale> findForSaleProp() {
        TypedQuery<ForSale> query = em.createNamedQuery("getForSaleQuery",ForSale.class);
        return query.getResultList();
    }

    public ForSale createForSaleProp(ForSale forsale) {
        em.persist(forsale);
        return forsale;
    }
    public ForSale searchForSale(Long pId){
        TypedQuery<ForSale> query = em.createNamedQuery("searchForSaleQuery",ForSale.class);
        query.setParameter("pId", pId);
        return query.getSingleResult();
    }
    public List<ForSale> forSaleDetails(Long pId){
        TypedQuery<ForSale> query = em.createNamedQuery("searchForSaleQuery",ForSale.class);
        query.setParameter("pId", pId);
        return query.getResultList();
    }
}