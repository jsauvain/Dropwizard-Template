package ch.joel.dropwizard.resource;

import ch.joel.dropwizard.beans.ExampleBean;
import ch.joel.dropwizard.database.Repository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.generated.tables.pojos.Example;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor(onConstructor = @__(@Inject))
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/dropwizard")
public class DropwizardResource {

	private Repository repository;


	@GET
	@Path("hello")
	public Response helloWorld() {
		log.info("Hello world requested =)");
		List<Example> examples = repository.getExamples();
		return Response.ok(examples.stream().map(example -> new ExampleBean(example.getFirstName(), example.getLastName())).collect(Collectors.toList())).build();
	}

}
