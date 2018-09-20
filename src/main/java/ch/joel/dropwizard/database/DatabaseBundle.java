package ch.joel.dropwizard.database;

import ch.joel.dropwizard.config.DatabaseConfiguration;
import ch.joel.dropwizard.config.DropwizardConfiguration;
import com.bendb.dropwizard.jooq.JooqBundle;
import com.bendb.dropwizard.jooq.JooqFactory;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.db.PooledDataSourceFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DatabaseBundle extends JooqBundle<DropwizardConfiguration> {
	@Override
	public PooledDataSourceFactory getDataSourceFactory(DropwizardConfiguration configuration) {
		DatabaseConfiguration dbConfig = configuration.getDatabase();
		DataSourceFactory dataSourceFactory = new DataSourceFactory();
		dataSourceFactory.setUrl("jdbc:mariadb://" + dbConfig.getUrl());
		dataSourceFactory.setUser(dbConfig.getUsername());
		log.info("Connection to jdbc:mariadb://{}. Authenticating as User {}.", dbConfig.getUrl(), dbConfig.getUsername());
		dataSourceFactory.setPassword(dbConfig.getPassword());
		dataSourceFactory.setDriverClass("org.mariadb.jdbc.Driver");
		log.info("Driver is {}", dataSourceFactory.getDriverClass());
		dataSourceFactory.setMaxSize(dbConfig.getConnections().getMaxPoolSize());
		dataSourceFactory.setMinSize(dbConfig.getConnections().getMinPoolSize());
		dataSourceFactory.setInitialSize(dbConfig.getConnections().getMinPoolSize());
		log.info("Initial pool size is {}. Min size is {} and max size is {}.", dataSourceFactory.getInitialSize(), dataSourceFactory.getMinSize(), dataSourceFactory.getMaxSize());
		dataSourceFactory.setRollbackOnReturn(true);
		return dataSourceFactory;

	}

	@Override
	public String primaryDataSourceName() {
		return "Dropwizard DB";
	}

	@Override
	public JooqFactory getJooqFactory(DropwizardConfiguration configuration) {
		JooqFactory factory = super.getJooqFactory(configuration);
		factory.setExecuteLogging(true);
		return factory;
	}
}
