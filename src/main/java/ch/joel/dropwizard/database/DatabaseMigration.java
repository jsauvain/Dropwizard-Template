package ch.joel.dropwizard.database;

import com.bendb.dropwizard.jooq.JooqConfiguration;
import io.dropwizard.Configuration;
import io.dropwizard.ConfiguredBundle;
import io.dropwizard.db.DatabaseConfiguration;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import lombok.RequiredArgsConstructor;
import org.flywaydb.core.Flyway;

@RequiredArgsConstructor
public class DatabaseMigration<C extends Configuration> implements ConfiguredBundle<C> {

	private final DatabaseConfiguration<C> databaseConfiguration;

	@Override
	public void run(C configuration, Environment environment) {
		PooledDataSourceFactory factory = databaseConfiguration.getDataSourceFactory(configuration);
		Flyway flyway = new Flyway();
		flyway.setDataSource(factory.build(environment.metrics(), "Flyway"));
		flyway.setSchemas("exampledb");
		flyway.migrate();
	}

	@Override
	public void initialize(Bootstrap<?> bootstrap) {

	}

}
