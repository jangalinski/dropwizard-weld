package de.holisticon.dropwizard.weld.example.jersey;

import de.holisticon.dropwizard.weld.example.resources.DummyResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.junit.After;
import org.junit.Test;
import org.slf4j.bridge.SLF4JBridgeHandler;

import javax.ws.rs.core.Application;

import static org.assertj.core.api.Assertions.assertThat;

public class JerseyCdiSpike extends JerseyTest {

    static {
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();
    }

    private Weld weld;
    private WeldContainer container;

    @Override
    protected Application configure() {
        weld = new Weld();
        container = weld.initialize();
        return new ResourceConfig(DummyResource.class);
    }

    @Test
    public void test() {
        assertThat(target(DummyResource.ROOT_PATH).request().get(String.class)).startsWith("hello foo");
    }

    @After
    public void after() {
        weld.shutdown();
    }
}
