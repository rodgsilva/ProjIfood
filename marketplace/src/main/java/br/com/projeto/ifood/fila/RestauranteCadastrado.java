package br.com.projeto.ifood.fila;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.reactive.messaging.Incoming;

import br.com.projeto.ifood.model.Restaurante;
import io.vertx.core.json.JsonObject;
import io.vertx.mutiny.pgclient.PgPool;

@ApplicationScoped
public class RestauranteCadastrado {

	@Inject
//	@ReactiveDataSource("test")
	PgPool pgPool;
	
	@Incoming("restaurantes")
	public void receberRestaurante(JsonObject  json) {
		System.out.println("---------Antes do json-----------");
		System.out.println(json);
		//Gson teste = Gson
		
		Restaurante fomJson= json.mapTo(Restaurante.class); //, Restaurante.class;
		System.out.println("------------------------");
		System.out.println(json.toString());		
		System.out.println("------------------------");
		//System.out.println(fomJson.toString());	
		fomJson.persist(pgPool);
	//	System.out.println(test.toString());
	}
}
