package br.com.projeto.ifood.model.DTO;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;


public class PratoDTO {

	public Long id;
	
	public String nome;
	
	public String descricao;
	
	public  RestauranteDTO restaurante;
	
	public BigDecimal preco;
}
