package de.holisticon.dropwizard.weld.example;


import static io.dropwizard.testing.ResourceHelpers.resourceFilePath;
import static org.assertj.core.api.Assertions.assertThat;

import io.dropwizard.Configuration;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.ClassRule;
import org.junit.Test;

public class WeldExampleApplicationTest {

    @ClassRule
    public static final DropwizardAppRule<Configuration> RULE =
        new DropwizardAppRule<Configuration>(WeldExampleApplication.class, resourceFilePath("example.yaml"));


    @Test
    public void starts_without_error() {
        assertThat(RULE.getApplication()).isNotNull();
    }
}
