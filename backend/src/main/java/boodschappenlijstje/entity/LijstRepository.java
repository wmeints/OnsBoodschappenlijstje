package boodschappenlijstje.entity;

import java.util.List;

public interface LijstRepository {
    List<Lijst> findAll();

    Lijst findById(int id);

    Lijst save(Lijst item);

    void remove(Lijst item);
}
