package br.com.projeto.ifood.model;

import java.util.List;

public class PedidoRealizadoDTO {
	
	private List<PratoPedidoDTO> prato;
	private RestauranteDTO restaurante;
	private String cliente;
	
	
	public PedidoRealizadoDTO(List<PratoPedidoDTO> prato, RestauranteDTO restaurante, String cliente) {
		super();
		this.prato = prato;
		this.restaurante = restaurante;
		this.cliente = cliente;
	}
	
	public List<PratoPedidoDTO> getPrato() {
		return prato;
	}
	public void setPrato(List<PratoPedidoDTO> prato) {
		this.prato = prato;
	}
	public RestauranteDTO getRestaurante() {
		return restaurante;
	}
	public void setRestaurante(RestauranteDTO restaurante) {
		this.restaurante = restaurante;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "PedidoRealizadoDTO [prato=" + prato + ", restaurante=" + restaurante + ", cliente=" + cliente + "]";
	}


}
