package ch.joel.dropwizard;

import ch.joel.dropwizard.config.DropwizardConfiguration;
import ch.joel.dropwizard.database.DatabaseBundle;
import ch.joel.dropwizard.resource.DropwizardResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Slf4j
public class DropwizardApplication extends Application<DropwizardConfiguration> {

	public static void main(String[] args) throws Exception {
		String config = loadConfig();
		new DropwizardApplication().run("server", config);
	}

	private static String loadConfig() {
		new File("Configuration").mkdir();
		File file = new File("Configuration/config.yml");
		if (!file.exists()) {
			try (InputStream is = DropwizardApplication.class.getResourceAsStream("/config.yml");
				 OutputStream os = new FileOutputStream(file)) {
				IOUtils.copy(is, os);
			} catch (IOException ex) {
				log.error("Could not load config", ex);
				throw new RuntimeException("Cannot load config");
			}
		}
		return file.getPath();
	}

	@Override
	public void initialize(Bootstrap<DropwizardConfiguration> bootstrap) {
		bootstrap.addBundle(new DatabaseBundle());
	}

	public void run(DropwizardConfiguration configuration, Environment environment) {

		environment.jersey().register(new ServiceBinder(configuration));
		environment.jersey().register(DropwizardResource.class);
	}

}
