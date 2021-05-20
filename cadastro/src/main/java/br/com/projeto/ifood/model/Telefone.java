package br.com.projeto.ifood.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
@Table(name= "telefone")
public class Telefone extends PanacheEntityBase {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String tipo;
	private Long numero;
	
	
	public Telefone() {
	
	}
	@ManyToOne
	@JoinColumn(name="restaurante_id")
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
