package br.com.projeto.ifood.model.DTO;

import java.math.BigDecimal;

import br.com.projeto.ifood.model.Prato;
import br.com.projeto.ifood.model.PratoCarrinho;

public class PratoPedidoDTO {
	
	public String nome;

	public String descricao;

	public BigDecimal preco;
    
    
	public PratoPedidoDTO() {	
	}

	public PratoPedidoDTO(String nome, String descricao, BigDecimal preco) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
	}
 
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
    
    
    
    

}
