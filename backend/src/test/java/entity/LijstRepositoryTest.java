package entity;

import java.util.ArrayList;
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
    private Lijst l;
    private Lijst l2;

    @Before
    public void setup() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(
                "ons-boodschappen-lijstje_backend_war_1.0-SNAPSHOTPU");

        entityManager = entityManagerFactory.createEntityManager();
        lijstRepository = new LijstRepository(entityManager);
        l = new Lijst();
        l.setDone(Boolean.FALSE);
        l.setItem("Brood");
        l.setWinkel("AH");
        lijstRepository.CreateOrChangeItem(l);
        l2 = new Lijst();
        l2.setDone(Boolean.TRUE);
        l2.setItem("Snoep");
        l2.setWinkel("Kruidvat");
        lijstRepository.CreateOrChangeItem(l2);
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
    public void testGetAllItemsSortedInRightOrder() {
        Lijst l3 = new Lijst();
        l3.setDone(Boolean.FALSE);
        l3.setItem("Pindakaas");
        l3.setWinkel("AH");
        lijstRepository.CreateOrChangeItem(l3);
        Lijst l4 = new Lijst();
        l4.setDone(Boolean.TRUE);
        l4.setItem("Paracetamol");
        l4.setWinkel("AH");
        lijstRepository.CreateOrChangeItem(l4);
        //l, l3, l4, l2.
        List<Lijst> compareTo = new ArrayList();
        compareTo.add(l);
        compareTo.add(l3);
        compareTo.add(l4);
        compareTo.add(l2);       

        List<String> ExpectedOutcomesItem = new ArrayList();
        ExpectedOutcomesItem.add("Brood");
        ExpectedOutcomesItem.add("Pindakaas");
        ExpectedOutcomesItem.add("Paracetamol");
        ExpectedOutcomesItem.add("Snoep");
        
        List<Lijst> result = lijstRepository.getItems();
        List<String> resultOutcomesItem = new ArrayList();
        for (Lijst item : result) {
            resultOutcomesItem.add(item.getItem());
        }
        Assert.assertEquals("Name expected not equal to name in resultlist", ExpectedOutcomesItem, resultOutcomesItem);
        
    }
}
