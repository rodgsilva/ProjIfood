package br.com.projeto.ifood.controller;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import br.com.projeto.ifood.model.Prato;
import br.com.projeto.ifood.model.DTO.PratoDTO;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;

@Path("/restaurantes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestauranteController {

	@Inject
	PgPool pgPool;


	@GET
	@Path("{idRestaurante}/pratos")
	@APIResponse(responseCode = "200", content = @Content(schema = @Schema(type = SchemaType.ARRAY, implementation = PratoDTO.class)))
	public Multi<PratoDTO> pratoRest(@PathParam(value = "idRestaurante") Long idRestaurante) {

		return Prato.findAll(pgPool, idRestaurante);
//		return Prato.findById(pgPool, idRestaurante).onItem()
//				.transform(pratoDTO -> pratoDTO != null ? Response.ok(pratoDTO) : Response.status(Status.NOT_FOUND))
//				.onItem().transform(ResponseBuilder::build);

	}

}
