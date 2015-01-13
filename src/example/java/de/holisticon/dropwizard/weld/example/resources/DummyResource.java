package de.holisticon.dropwizard.weld.example.resources;

import com.google.common.base.Supplier;
import de.holisticon.dropwizard.weld.example.util.BarSupplier;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path(DummyResource.ROOT_PATH)
@Produces(MediaType.APPLICATION_JSON)
public class DummyResource {

    public static final String ROOT_PATH = "/dummy";

    @Inject
    private BarSupplier bar;

    @GET
    public String helloWorld() {
        return "hello " + bar.get();
    }

}
