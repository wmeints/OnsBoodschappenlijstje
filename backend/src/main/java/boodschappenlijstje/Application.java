package boodschappenlijstje;

import boodschappenlijstje.endpoints.BoodschappenResource;
import boodschappenlijstje.endpoints.VersionResource;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class Application extends ResourceConfig {
    public Application() {
        register(VersionResource.class);
        register(BoodschappenResource.class);

        // Register the Jackson JSON provider.
        // This is required, so that we can get JSON in/out of the REST service.
        register(JacksonJsonProvider.class);
    }
}
