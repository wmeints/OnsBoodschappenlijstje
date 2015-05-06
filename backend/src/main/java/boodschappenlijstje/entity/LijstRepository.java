package boodschappenlijstje.entity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface LijstRepository extends CrudRepository<Lijst, Integer> {
    Lijst findById(Integer id);
}
