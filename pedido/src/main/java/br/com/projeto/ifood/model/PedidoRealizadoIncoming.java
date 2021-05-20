package br.com.projeto.ifood.model;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.reactive.messaging.Incoming;

@ApplicationScoped
public class PedidoRealizadoIncoming {
	
	@Incoming("pedido")
	public void lerPedidos(PedidoRealizadoDTO dto) {
		System.out.println(dto);
	}

}
