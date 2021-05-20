package br.com.projeto.ifood.model;

import java.math.BigDecimal;
import java.util.stream.StreamSupport;

import br.com.projeto.ifood.model.DTO.PratoDTO;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowSet;
import io.vertx.mutiny.sqlclient.Tuple;

public class Prato {

	public Long id;

	public String nome;

	public String descricao;

	public Restaurante restaurante;

	public BigDecimal preco;

	public static Multi<PratoDTO> findAll(PgPool pgPool) {
//		return pgPool.query("select * from prato").execute()
//				.onItem().transformToMulti(item -> Multi.createFrom().iterable(item))
//				.onItem().transform(PratoDTO::from);
		 Uni<RowSet<Row>> preparedQuery = pgPool.query("select * from prato").execute();
	        return unitToMulti(preparedQuery);

	}
	
    public static Multi<PratoDTO> findAll(PgPool client, Long idRestaurante) {
        Uni<RowSet<Row>> preparedQuery = client
                .preparedQuery("SELECT * FROM prato where prato.restaurante_id = $1 ORDER BY nome ASC").execute(
                        Tuple.of(idRestaurante));
        return unitToMulti(preparedQuery);
    }
	
	public static Uni<PratoDTO> findById(PgPool client, Long idRestaurante) {		
//		return pgPool.preparedQuery("SELECT * FROM prato WHERE id = $1").execute(Tuple.of(idRestaurante))
//				.onItem().transform(RowSet::iterator)
//				.onItem().transform(iterator -> iterator.hasNext() ? from(iterator.next()) : null);
		
		 return client.preparedQuery("SELECT * FROM prato WHERE id = $1").execute(Tuple.of(idRestaurante))
	                .map(RowSet::iterator)
	                .map(iterator -> iterator.hasNext() ? PratoDTO.from(iterator.next()) : null);
	}

    private static Multi<PratoDTO> unitToMulti(Uni<RowSet<Row>> queryResult) {
        return queryResult.onItem()
                .produceMulti(set -> Multi.createFrom().items(() -> {
                    return StreamSupport.stream(set.spliterator(), false);
                }))
                .onItem().apply(PratoDTO::from);
    }

	public Prato() {

	}

	public Prato(Long id, String nome, String descricao, BigDecimal preco) {
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

	public Restaurante getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	@Override
	public String toString() {
		return "Prato [id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", restaurante=" + restaurante
				+ ", preco=" + preco + "]";
	}




}
