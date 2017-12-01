package ch.joel.dropwizard.config;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class DatabaseConfiguration {

	private String username;
	private String password;
	private String host;
	private int port;
	@NotNull
	private ConnectionsConfiguration connections;

	@Data
	public static class ConnectionsConfiguration {

		private int minPoolSize;
		private int maxPoolSize;
	}

}
