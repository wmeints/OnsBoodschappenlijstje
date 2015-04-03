package boodschappenlijstje.entity;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

@Repository
public class LijstRepositoryImpl implements LijstRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public LijstRepositoryImpl() {

    }

    public LijstRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Lijst> findAll() {
        List<Lijst> resultSet = entityManager.createQuery("SELECT p FROM Lijst p ORDER BY p.done, p.winkel, p.item", Lijst.class).getResultList();
        return resultSet;
    }

    @Override
    public Lijst findById(int id) {
        return entityManager.createNamedQuery("Lijst.findById", Lijst.class).setParameter("id", id).getSingleResult();
    }

    @Override
    @Transactional
    public Lijst save(Lijst item){

        Lijst updatedRows = entityManager.merge(item);
        entityManager.flush();

        return updatedRows;
    }

    @Override
    @Transactional
    public void remove(Lijst item){
        entityManager.remove(item);
        entityManager.flush();
    }
}

