package resource;

import boodschappenlijstje.endpoints.BoodschappenResource;
import boodschappenlijstje.entity.Lijst;
import boodschappenlijstje.entity.LijstRepository;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.SpringLifecycleListener;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.*;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.RequestContextFilter;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@Ignore
public class BoodschappenResourceIT extends JerseyTest {

    public BoodschappenResourceIT() {

    }

    @Override
    protected Application configure() {
        ResourceConfig configuration = new ResourceConfig(BoodschappenResource.class);

        configuration.register(SpringLifecycleListener.class);
        configuration.register(RequestContextFilter.class);

        configuration.property("contextConfigLocation", "*/test-applicationConfiguration.xml");

        return configuration;
    }

    @Test
    public void testGetAllItems() {
        Lijst brood = new Lijst(false,"Brood","AH");
        Lijst pindakaas = new Lijst(false,"Pindakaas","AH");

        List<Lijst> items = new ArrayList<Lijst>();

        items.add(brood);
        items.add(pindakaas);

        Response response = target("/item").request().get();

        assertEquals(200,response.getStatus());
    }
}
