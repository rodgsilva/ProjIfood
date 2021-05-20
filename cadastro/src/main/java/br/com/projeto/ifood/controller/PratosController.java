package br.com.projeto.ifood.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import br.com.projeto.ifood.model.Prato;
import br.com.projeto.ifood.model.Restaurante;
import br.com.projeto.ifood.model.DTO.AdicionarPratoDTO;
import br.com.projeto.ifood.model.DTO.AtualizaPratoDTO;
import br.com.projeto.ifood.model.DTO.PratoDTO;
import br.com.projeto.ifood.model.DTO.PratoMapper;

@Path("/restaurante/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Prato")
public class PratosController {
	
	@Inject
	PratoMapper pratoMapper;

	@GET
	@Path("{idRestaurante}/pratos")
	public List<PratoDTO> getPrato(@PathParam("idRestaurante") Long idRest) {
		Optional<Restaurante> restaurante = Restaurante.findByIdOptional(idRest);
		if (restaurante.isEmpty()) {
			throw new NotFoundException("Restaurante não existe");
		}

		Stream<Prato> pratos=Prato.stream("restaurante", restaurante.get());
		return pratos.map(p -> pratoMapper.toDTO(p)).collect(Collectors.toList());

	}

	@POST
	@Path("{idRestaurante}/pratos")
	@Transactional
	public Response adicionar(@PathParam("idRestaurante") Long idRest, AdicionarPratoDTO dto) {
		Optional<Restaurante> restaurante = Restaurante.findByIdOptional(idRest);
		if (restaurante.isEmpty()) {
			throw new NotFoundException("Restaurante não existe");
		}
		Prato prato = pratoMapper.toPrato(dto);
	
		prato.restaurante = restaurante.get();
		prato.persist();
		return Response.ok().build();
	}

	@PUT
	@Path("{idRestaurante}/pratos/{id}")
	@Transactional
	public void atualiza(@PathParam("idRestaurante") Long idRest, @PathParam("id") Long id, AtualizaPratoDTO dto) {
		Optional<Restaurante> restaurante = Restaurante.findByIdOptional(idRest);
		if (restaurante.isEmpty()) {
			throw new NotFoundException("Restaurante não existe");
		}

		Optional<Prato> pratoOP = Prato.findByIdOptional(id);

		if (pratoOP.isEmpty()) {
			throw new NotFoundException("Restaurante não existe");
		}

		Prato prato = pratoOP.get();

		pratoMapper.toPrato(dto,prato);

		prato.persist();

	}

	@DELETE
	@Path("{idRestaurante}/pratos/{id}")
	@Transactional
	public void delete(@PathParam("idRestaurante") Long idRest, @PathParam("id") Long id) {
		Optional<Restaurante> restaurante = Restaurante.findByIdOptional(idRest);
		if (restaurante.isEmpty()) {
			throw new NotFoundException("Restaurante não existe");
		}
		
		Optional<Prato> pratoOP = Prato.findByIdOptional(id);

		pratoOP.ifPresentOrElse(Prato::delete, () -> {
			throw new NotFoundException();
		});

	}
}
