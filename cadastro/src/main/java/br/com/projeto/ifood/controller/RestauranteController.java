package br.com.projeto.ifood.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
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

import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.SimplyTimed;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.security.OAuthFlow;
import org.eclipse.microprofile.openapi.annotations.security.OAuthFlows;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import br.com.projeto.ifood.model.Restaurante;
import br.com.projeto.ifood.model.Telefone;
import br.com.projeto.ifood.model.DTO.AdicionarRestauranteDTO;
import br.com.projeto.ifood.model.DTO.AtualizaRestauranteDTO;
import br.com.projeto.ifood.model.DTO.RestauranteDTO;
import br.com.projeto.ifood.model.DTO.RestauranteMapper;

@Path("/restaurantes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Restaurante")
@RolesAllowed("proprietario")
@SecurityScheme(securitySchemeName = "ifood-ouath", type =SecuritySchemeType.OAUTH2,flows = 
	@OAuthFlows(password = @OAuthFlow(tokenUrl = "http://192.168.1.107:8180/auth/realms/ifood/protocol/openid-connect/token/")))
//@SecurityRequirement(name = "ifood-ouath")
public class RestauranteController {

	@Inject
	RestauranteMapper restaurantesMapper;
	
	@Inject
	@Channel(value = "restaurantes")
	Emitter<Restaurante> emitter;
	

	@GET
	@Counted(name= "Qauntidade busca restaurantes")
	@SimplyTimed(name ="Tempo Simples de Busca")
	@Timed(name = "Tempo completo de busca")
	public List<RestauranteDTO> getRestaurante() {
		Stream<Restaurante> restaurantes = Restaurante.streamAll();
		return restaurantes.map(r -> restaurantesMapper.toRestauranteDTO(r)).collect(Collectors.toList());

	}

	@POST
	@Transactional
	public Response adicionar(AdicionarRestauranteDTO dto) {
		System.out.println("Entro no Post");
		Restaurante restaurante = restaurantesMapper.toRestaurante(dto);
		
		
//		restaurante.setTelefone(Arrays.asList(new Telefone("Celular",dto.getNumeroCelular(),restaurante),
//				new Telefone("Residencial",dto.getNumeroResdencia(),restaurante),
//				new Telefone("Comercial",dto.getNumeroComercial(),restaurante)));
		restaurante.persist();
		//Telefone.persist(restaurante.getTelefone());
		
		
		System.out.println(restaurante.toString());
		emitter.send(restaurante);
		return Response.ok().build();
	}

	@PUT
	@Path("/{id}")
	@Transactional
	public void atualiza(@PathParam("id") Long id, AtualizaRestauranteDTO dto) {
		Optional<Restaurante> restauranteOP = Restaurante.findByIdOptional(id);

		if (restauranteOP.isEmpty()) {
			throw new NotFoundException();
		}

		Restaurante restaurante = restauranteOP.get();
		
		restaurantesMapper.toRestaurante(dto, restaurante);
				
		restaurante.persist();

	}

	@DELETE
	@Path("/{id}")
	@Transactional
	public void delete(@PathParam("id") Long id) {
		Optional<Restaurante> restauranteOP = Restaurante.findByIdOptional(id);

		restauranteOP.ifPresentOrElse(Restaurante::delete, () -> {
			throw new NotFoundException();
		});

	}
}
