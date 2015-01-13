package de.holisticon.dropwizard.weld.example.util;

import javax.enterprise.inject.Produces;
import javax.inject.Named;
import java.io.Serializable;
import java.util.UUID;

public class FooProducer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Produces
    @Named("foo")
    private final String FOO = "foo" + UUID.randomUUID();

}
