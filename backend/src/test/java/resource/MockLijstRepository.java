package resource;

import boodschappenlijstje.entity.Lijst;
import boodschappenlijstje.entity.LijstRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MockLijstRepository implements LijstRepository {
    @Override
    public List<Lijst> findAll() {
        return null;
    }

    @Override
    public Lijst findById(int id) {
        return null;
    }

    @Override
    public Lijst save(Lijst item) {
        return null;
    }

    @Override
    public void remove(Lijst item) {

    }
}
