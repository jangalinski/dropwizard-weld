package de.holisticon.dropwizard.weld.example;

import static io.dropwizard.testing.ResourceHelpers.resourceFilePath;
import static org.slf4j.LoggerFactory.getLogger;

import com.codahale.metrics.health.HealthCheck;
import de.holisticon.dropwizard.weld.WeldBundle;
import de.holisticon.dropwizard.weld.example.util.FooLogger;
import de.holisticon.dropwizard.weld.example.resources.DummyResource;
import de.holisticon.dropwizard.weld.example.servlets.MyServlet;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;

@ApplicationScoped
public class WeldExampleApplication extends Application<Configuration> {

    @Inject
    private FooLogger.Foo foo;

    @Inject
    private Event<FooLogger.Foo> fooEvent;

    @Inject
    private DummyResource dummyResource;

    private final Logger logger = getLogger(this.getClass());

    @Override
    public void run(final Configuration config, final Environment environment) throws Exception {
        logger.error("------------------- application-run-1");

        environment.healthChecks().register("dummy", new HealthCheck() {
            @Override
            protected Result check() throws Exception {
                return Result.healthy("dummy");
            }
        });

        //f ooEvent.fire(foo);
        environment.jersey().register(DummyResource.class);
        environment.getApplicationContext().addServlet(MyServlet.class, "/foo");

        logger.error("------------------- application-run-2");
    }

    @Override
    public void initialize(final Bootstrap<Configuration> bootstrap) {
        bootstrap.addBundle(new WeldBundle());
    }


    /**
     * Simplified main() to run this example from IDE/maven.
     *
     * @param _unused
     * @throws Exception
     */
    public static void main(String... _unused) throws Exception {
        new WeldExampleApplication().run("server", resourceFilePath("example.yaml"));
    }


}
