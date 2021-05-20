package br.com.projeto.ifood.model;

import java.util.List;

import io.quarkus.mongodb.panache.MongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntity;

@MongoEntity(collection = "pedido",database = "pedido")
public class Pedido  extends PanacheMongoEntity{
	
	private String cliente;
	private List<Prato> prato;
	private Restaurante restaurante;
	private String entregador;
	private Localizacao localizacaoEntregador;
	
	
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public List<Prato> getPrato() {
		return prato;
	}
	public void setPrato(List<Prato> prato) {
		this.prato = prato;
	}
	public Restaurante getRestaurante() {
		return restaurante;
	}
	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}
	public String getEntregador() {
		return entregador;
	}
	public void setEntregador(String entregador) {
		this.entregador = entregador;
	}
	public Localizacao getLocalizacaoEntregador() {
		return localizacaoEntregador;
	}
	public void setLocalizacaoEntregador(Localizacao localizacaoEntregador) {
		this.localizacaoEntregador = localizacaoEntregador;
	}

	
	
	
}
