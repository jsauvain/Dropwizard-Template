package ch.joel.dropwizard.database;

import com.bendb.dropwizard.jooq.jersey.DSLContextFactory;
import org.jooq.generated.tables.pojos.Example;

import javax.inject.Inject;
import java.util.List;

import static org.jooq.generated.tables.Example.EXAMPLE;

public class Repository extends AbstractRepository {

	@Inject
	public Repository(DSLContextFactory factory) {
		super(factory);
	}

	public List<Example> getExamples() {
		return getContext()
				.selectFrom(EXAMPLE)
				.fetchInto(Example.class);
	}
}
