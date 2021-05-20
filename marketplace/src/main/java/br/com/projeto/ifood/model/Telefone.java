package br.com.projeto.ifood.model;

public class Telefone {
	
	
	private Long id;
	private String tipo;
	private Long numero;
	
	
	public Telefone() {
	
	}
	
	private Restaurante restaurante;	
	
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Long getNumero() {
		return numero;
	}
	public void setNumero(Long numero) {
		this.numero = numero;
	}
	public Telefone(String tipo, Long numero,Restaurante restaurante) {
		super();
		this.tipo = tipo;
		this.numero = numero;
		this.restaurante = restaurante;
	}
	
	

}
