package br.com.emersonluiz.spark.rest;

import static br.com.emersonluiz.util.JsonUtil.json;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.after;
import java.util.UUID;

import com.google.gson.Gson;

import br.com.emersonluiz.spark.model.User;

public class UserResource {

	public UserResource() {
		
		after((req, res) -> {
			res.type("application/json");
		});

		get("/users", (req, res) -> {
			res.type("application/json");
			User user = new User();
			user.setId(UUID.randomUUID().toString());
			user.setName("Vinicius");
			user.setEmail("test@test.com.br");
			return new Gson().toJson(user);
		});

		get("/users/:name", (req, res) -> {
			return "Name is: " + req.params(":name");
		});

		post("/users", (req, res) -> new Gson().fromJson(req.body(), User.class), json());

	}
}
