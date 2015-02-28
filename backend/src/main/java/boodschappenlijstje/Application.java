package boodschappenlijstje;

import boodschappenlijstje.endpoints.VersionResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class Application extends ResourceConfig {
    public Application() {
        register(VersionResource.class);
    }
}
