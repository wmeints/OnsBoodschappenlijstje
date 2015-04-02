package boodschappenlijstje.entity;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class LijstRepositoryTest extends RepositoryTests {

    private EntityManager entityManager;
    private LijstRepositoryImpl lijstRepository;
    private Lijst brood;
    private Lijst snoep;
    private Lijst pindakaas;
    private Lijst paracetamol;

    @Before
    public void setup() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(
                "ons-boodschappen-lijstje_backend_war_1.0-SNAPSHOTPU");

        entityManager = entityManagerFactory.createEntityManager();
        lijstRepository = new LijstRepositoryImpl(entityManager);

        brood = new Lijst(false,"Brood","AH");
        snoep = new Lijst(true,"Snoep","Kruidvat");
        pindakaas = new Lijst(false,"Pindakaas","AH");
        paracetamol = new Lijst(true,"Paracetamol","AH");

        lijstRepository.save(brood);
        lijstRepository.save(snoep);
        lijstRepository.save(paracetamol);
        lijstRepository.save(pindakaas);
    }

    @Test
    public void testGetAllItems() {
        List<Lijst> items = lijstRepository.findAll();

        assertEquals("Verkeerd aantal items gevonden in de database", 2, items.size());
    }

    @Test
    public void testDelete() {
        lijstRepository.remove(snoep);
        List<Lijst> items = lijstRepository.findAll();

        assertEquals("Delete item niet gelukt", 3, items.size());
    }

    @Test
    public void testGetAllItemsSortedInRightOrder() {
        //brood, pindakaas, paracetamol, snoep.
        List<Lijst> compareTo = new ArrayList();
        compareTo.add(brood);
        compareTo.add(pindakaas);
        compareTo.add(paracetamol);
        compareTo.add(snoep);

        List<String> ExpectedOutcomesItem = new ArrayList();

        ExpectedOutcomesItem.add("Brood");
        ExpectedOutcomesItem.add("Pindakaas");
        ExpectedOutcomesItem.add("Paracetamol");
        ExpectedOutcomesItem.add("Snoep");
        
        List<Lijst> result = lijstRepository.findAll();
        List<String> resultOutcomesItem = new ArrayList();
        for (Lijst item : result) {
            resultOutcomesItem.add(item.getItem());
        }

        assertEquals("Name expected not equal to name in resultlist", ExpectedOutcomesItem, resultOutcomesItem);
    }
}
