package br.com.projeto.ifood.model.DTO;

import java.math.BigDecimal;

import br.com.projeto.ifood.model.Prato;
import br.com.projeto.ifood.model.Restaurante;
import io.vertx.mutiny.sqlclient.Row;

public class PratoDTO {
	
	private Long id;

    private String nome;

    private String descricao;

    private BigDecimal preco;
    
    
    
    
	public PratoDTO(Long id, String nome, String descricao, BigDecimal preco) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
	}


	public static PratoDTO from(Row row) {
		return new PratoDTO(row.getLong("id"),
				row.getString("nome"),
				row.getString("descricao"),
				row.getBigDecimal("preco"));
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
		return "PratoDTO [id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", preco=" + preco + "]";
	}


}
