package ch.joel.dropwizard;

import ch.joel.dropwizard.config.DropwizardConfiguration;
import ch.joel.dropwizard.database.AbstractRepository;
import ch.joel.dropwizard.database.Repository;
import lombok.AllArgsConstructor;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

import javax.inject.Singleton;

@AllArgsConstructor
public class ServiceBinder extends AbstractBinder {

	private final DropwizardConfiguration configuration;

	protected void configure() {
		bind(this.configuration).to(DropwizardConfiguration.class);

		bindAsContract(Repository.class).in(Singleton.class);
	}
}
