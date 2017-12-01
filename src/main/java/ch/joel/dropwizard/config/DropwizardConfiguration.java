package ch.joel.dropwizard.config;

import io.dropwizard.Configuration;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class DropwizardConfiguration extends Configuration {

	private DatabaseConfiguration database;

}
