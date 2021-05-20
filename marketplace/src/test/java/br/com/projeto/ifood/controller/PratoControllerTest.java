package br.com.projeto.ifood.controller;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class PratoControllerTest {
	
	@Test
	public void testPratoEndpoint() {
		String body = given()
					  .when()
					  .get("/pratos")
					  .then().statusCode(200).extract().asString();
		System.out.println(body);
	}

}