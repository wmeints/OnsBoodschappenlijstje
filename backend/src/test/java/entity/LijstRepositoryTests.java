/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author ame20844
 */
public class LijstRepositoryTests {
    private EntityManager entityManager;
    private LijstRepository lijstRepository;
    
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
        Lijst l2 = new Lijst();
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
        Lijst l3 = new Lijst();
        l3.setDone(Boolean.TRUE);
        l3.setItem("toiletpapier");
        l3.setWinkel("kruidvat");
        lijstRepository.update(l3);
        lijstRepository.delete(l3);
        List<Lijst> items = lijstRepository.getItems();
        Assert.assertEquals("Delete item niet gelukt", 2, items.size());
    }
    
    @Test
    public void testSorteren() {
        
    }
    
}
