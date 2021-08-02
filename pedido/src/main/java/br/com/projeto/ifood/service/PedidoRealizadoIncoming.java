package br.com.projeto.ifood.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import org.bson.types.Decimal128;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import br.com.projeto.ifood.model.Pedido;
import br.com.projeto.ifood.model.PedidoRealizadoDTO;
import br.com.projeto.ifood.model.Prato;
import br.com.projeto.ifood.model.PratoPedidoDTO;
import br.com.projeto.ifood.model.Restaurante;

@ApplicationScoped
public class PedidoRealizadoIncoming {
	
	@Inject
	ESService elastic;
	
	@Incoming("pedidos")
	public void lerPedidos(PedidoRealizadoDTO dto) {
		System.out.println("Ler Pedido");
		System.out.println(dto);
		System.out.println(dto.getPrato().toString());
		Pedido p = new Pedido();	
	
		dto.getPrato().forEach(prato -> p.getPrato().add(from(prato)));
		Restaurante restaurante= new Restaurante();
		restaurante.setNome(dto.getRestaurante().getNome());
		p.setRestaurante(restaurante);
		String json = JsonbBuilder.create().toJson(dto);
		elastic.index("pedido", json);
		p.persist();
	}
	
	private Prato from(PratoPedidoDTO prato) {
		Prato p = new Prato();
		p.setDescricao(prato.getDescricao());
		p.setNome(prato.getNome());
		p.setPreco(new Decimal128(prato.getPreco()));
		return p;
		
	}

}
