package br.com.projeto.ifood.model.DTO;

import java.util.List;

public class PedidoRealizaoDTO {
	
	public List<PratoPedidoDTO> prato;
	
	public RestauranteDTO restaurante;
	
	public String cliente;
	
	public PedidoRealizaoDTO() {
		
	}

	public PedidoRealizaoDTO(List<PratoPedidoDTO> prato, RestauranteDTO restaurante, String cliente) {
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
		return "PedidoRealizaoDTO [prato=" + prato + ", restaurante=" + restaurante + ", cliente=" + cliente + "]";
	}
	
	
	

}
