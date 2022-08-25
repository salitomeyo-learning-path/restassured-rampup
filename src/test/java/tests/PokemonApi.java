package tests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class PokemonApi {
	
	@Test
	public void getPokemon() {
		baseURI = "https://pokeapi.co/api/v2";
		when().
			get("/pokemon/charmander").
		then().
			statusCode(200).
			body("name", equalTo("charmander"));
	}
	
	@Test
	public void getPokemonByName() {
		baseURI = "https://pokeapi.co/api/v2";
		String pokemonName = "piplup";
		
		given().
			pathParam("name", pokemonName).
		when().
			get("/pokemon/{name}").
		then().
			statusCode(200).
			body("name", equalTo(pokemonName));
	}
}
