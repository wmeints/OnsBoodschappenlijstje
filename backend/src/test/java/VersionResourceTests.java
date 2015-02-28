import boodschappenlijstje.endpoints.VersionResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.core.Application;

public class VersionResourceTests extends JerseyTest {
    @Override
    protected Application configure() {
        return new ResourceConfig(VersionResource.class);
    }

    @Test
    public void getVersionReturnsValidVersion(){
        final String version = target("version").request().get(String.class);
        Assert.assertEquals("1.0", version);
    }
}
