package br.com.emersonluiz.spark;

import static spark.Spark.*;

import java.util.UUID;

import com.google.gson.Gson;

import br.com.emersonluiz.spark.model.User;

public class Application {

	public static void main(String[] args) {

		port(8081);

		staticFiles.location("/public");

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

		post("/users", (req, res) -> {
			res.type("application/json");
			User user = new Gson().fromJson(req.body(), User.class);
			return new  Gson().toJson(user);
		});

	}

}
