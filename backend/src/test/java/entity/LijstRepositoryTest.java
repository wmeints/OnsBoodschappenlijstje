package entity;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import junit.framework.Assert;
import org.eclipse.persistence.internal.jpa.EntityManagerFactoryImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LijstRepositoryTest {
    private EntityManager entityManager;
    private LijstRepository lijstRepository;
    private Lijst l2;
    
    @Before
    public void setup() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(
                "ons-boodschappen-lijstje_backend_war_1.0-SNAPSHOTPU");
        
        entityManager = entityManagerFactory.createEntityManager();
        lijstRepository = new LijstRepository(entityManager);
        Lijst l = new Lijst();
        l.setDone(Boolean.FALSE);
        l.setItem("Brood");
        l.setWinkel("AH");
        lijstRepository.update(l);
        l2 = new Lijst();
        l2.setDone(Boolean.TRUE);
        l2.setItem("Paracetamol");
        l2.setWinkel("Kruidvat");
        lijstRepository.update(l2);
    }
    
    @After
    public void deleteContentDB() {
        lijstRepository.clearTable();
    }
    
    @Test
    public void testGetAllItems() {
        List<Lijst> items = lijstRepository.getItems();
        
        Assert.assertEquals("Verkeerd aantal items gevonden in de database", 2, items.size());
    }
    
    @Test
    public void testDelete() {
        lijstRepository.delete(l2);
        List<Lijst> items = lijstRepository.getItems();
        Assert.assertEquals("Delete item niet gelukt", 2, items.size());
    }
    
    @Test
    public void testSorteren() {
        
    }
    
}
