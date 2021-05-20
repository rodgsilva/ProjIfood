package br.com.projeto.ifood.model;

import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowSet;
import io.vertx.mutiny.sqlclient.Tuple;

public class Localizacao {

	private Long id;

	private Double latitude;

	private Double longitude;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Long persist(PgPool pgPool) {

		try {
			RowSet<Row> result = pgPool
					.preparedQuery("insert into localizacao (id, latitude, longitude) values ($1, $2,$3)")
					.execute(Tuple.of(id, latitude, longitude)).await().indefinitely();
			Long idResult = result.iterator().next().getLong("id");

			return idResult;


		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

	}

}
