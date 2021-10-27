package ass3.ForSale;

import ass3.web.ForRent;
import ass3.web.ForSale;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.persistence.Query;

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
    //method to list all for sale properties
    public List<ForSale> findForSaleProp() {
        TypedQuery<ForSale> query = em.createNamedQuery("getForSaleQuery", ForSale.class);
        return query.getResultList();
    }

    //method to create for sale property 
    public ForSale createForSaleProp(ForSale forsale) {
        em.persist(forsale);
        return forsale;
    }

    //method to search for sale property
    public ForSale searchForSale(Long pId) {
        TypedQuery<ForSale> query = em.createNamedQuery("searchForSaleQuery", ForSale.class);
        query.setParameter("pId", pId);
        return query.getSingleResult();
    }

    //method to view details of for sale property from dataTable
    public ForSale findForSaleWithID(Long ID, ForSale forSale) {
        Query query = em.createNamedQuery("searchForSaleQuery", ForSale.class);
        query.setParameter("pId", ID);
        forSale = (ForSale) query.getSingleResult();
        return forSale;
    }
}
