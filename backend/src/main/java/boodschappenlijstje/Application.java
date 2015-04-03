package boodschappenlijstje;

import boodschappenlijstje.endpoints.BoodschappenResource;
import boodschappenlijstje.endpoints.VersionResource;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.flywaydb.core.Flyway;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Application extends ResourceConfig {
    @Autowired
    Flyway migrator;

    Logger logger = LoggerFactory.getLogger(Application.class);
    
    public Application() {
        register(VersionResource.class);
        register(BoodschappenResource.class);

        // Register the Jackson JSON provider.
        // This is required, so that we can get JSON in/out of the REST service.
        register(JacksonJsonProvider.class);
    }
}
