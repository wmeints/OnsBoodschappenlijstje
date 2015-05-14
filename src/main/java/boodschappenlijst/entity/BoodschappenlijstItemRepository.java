package boodschappenlijst.entity;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface BoodschappenlijstItemRepository extends CrudRepository<BoodschappenlijstItem, Long> {

    public BoodschappenlijstItem findByID(Long id);

}

