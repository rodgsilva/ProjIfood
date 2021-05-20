package br.com.projeto.ifood;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import io.smallrye.mutiny.Multi;

@Path("/pedido")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class pedidoController {
	
	

	@GET
	public void buscaPrato() {
	
	}


}
