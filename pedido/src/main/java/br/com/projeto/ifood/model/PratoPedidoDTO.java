package br.com.projeto.ifood.model;

import java.math.BigDecimal;

public class PratoPedidoDTO {
	
	private Long id;

    private String nome;

    private String descricao;

    private BigDecimal preco;
    
    
    
    
	public PratoPedidoDTO(Long id, String nome, String descricao, BigDecimal preco) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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


	@Override
	public String toString() {
		return "PratoPedidoDTO [id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", preco=" + preco + "]";
	}



}
