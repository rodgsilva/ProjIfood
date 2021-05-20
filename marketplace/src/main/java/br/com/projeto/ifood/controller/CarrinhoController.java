package br.com.projeto.ifood.controller;

import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import br.com.projeto.ifood.model.Prato;
import br.com.projeto.ifood.model.PratoCarrinho;
import br.com.projeto.ifood.model.DTO.PedidoRealizaoDTO;
import br.com.projeto.ifood.model.DTO.PratoDTO;
import br.com.projeto.ifood.model.DTO.PratoPedidoDTO;
import br.com.projeto.ifood.model.DTO.RestauranteDTO;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;

@Path("carrinho")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CarrinhoController {

	private static final String CLIENTE = "a";

	@Inject
	PgPool client;

	@Inject
	@Channel("pedidos")
	Emitter<PedidoRealizaoDTO> emitterPedido;

	@GET
	public Uni<List<PratoCarrinho>> buscarcarrinho() {

		return PratoCarrinho.findCarrinho(client, CLIENTE);

	}

	@POST
	@Path("/prato/{idPrato}")
	public Uni<Long> adicinarPrato(@PathParam("idPrato") Long idPrato) {
		  //poderia retornar o pedido atual
        PratoCarrinho pc = new PratoCarrinho();
        pc.setCliente( CLIENTE);
        pc.setPrato(idPrato);
        return PratoCarrinho.save(client, CLIENTE, idPrato);
	}

    @POST
    @Path("/realizar-pedido")
	public Uni<Boolean> finaliza() {
		PedidoRealizaoDTO pedido = new PedidoRealizaoDTO();
		String cliente = CLIENTE;
		pedido.setCliente(cliente);
		List<PratoCarrinho> pratoCarrinho = PratoCarrinho.findCarrinho(client, cliente).await().indefinitely();
		// Utilizar maostrutus
		List<PratoPedidoDTO> prato = pratoCarrinho.stream().map(pc -> from(pc)).collect(Collectors.toList());
		pedido.setPrato(prato);
		RestauranteDTO restaurante = new RestauranteDTO();
		restaurante.setNome("Teste restaurante");
		pedido.setRestaurante(restaurante);
		System.out.println("Envio KAFKA");
		System.out.println(pedido);
		
	 CompletionStage<Void> teste= 	emitterPedido.send(pedido);
	 System.out.println(teste);
		return  PratoCarrinho.delete(client, cliente);
	}

	private PratoPedidoDTO from(PratoCarrinho pratoCarrinho) {
		PratoDTO dto = Prato.findById(client, pratoCarrinho.getPrato()).await().indefinitely();
		return new PratoPedidoDTO(dto.getNome(), dto.getDescricao(), dto.getPreco());
	}

}
