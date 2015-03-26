package entity;

import java.util.List;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

class LijstRepository {
    
    @PersistenceContext
    private EntityManager entityManager;
       
    public LijstRepository() {   
    }
    
    public LijstRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public List<Lijst> getItems() {
        return entityManager.createQuery("SELECT l FROM Lijst l",Lijst.class).getResultList();
    }
    
    public Lijst findByID(int id) {
        return entityManager.createNamedQuery("Lijst.findById", Lijst.class).setParameter("id", id).getSingleResult();
    }
    
    public Lijst update(Lijst item){
        EntityTransaction et = entityManager.getTransaction();
        et.begin();
        Lijst updatedRows = entityManager.merge(item);
        entityManager.flush();
        et.commit();
        return updatedRows;
    }
    
    public void delete(Lijst item){
        EntityTransaction et = entityManager.getTransaction();
        et.begin();
        entityManager.remove(item);
        
        //int deletedItem = entityManager.createQuery("DELETE FROM Lijst l WHERE l.id = :id").setParameter("id", item.getId()).executeUpdate();
        entityManager.flush();
        et.commit();  
    }

    public void clearTable(){
        EntityTransaction et = entityManager.getTransaction();
        et.begin();
        entityManager.createQuery("DELETE FROM Lijst").executeUpdate();
        entityManager.flush();
        et.commit();
    }
    
    public List<Lijst> sorteren(){
        EntityTransaction et = entityManager.getTransaction();
        et.begin();
        List<Lijst> resultSet = entityManager.createQuery("SELECT * FROM Lijst l ORDER BY p.done, p.winkel, p.item", Lijst.class).getResultList();
        entityManager.flush();
        et.commit();
        return resultSet;
    }    
}

