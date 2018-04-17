package br.com.emersonluiz.spark.rest;

import static br.com.emersonluiz.util.JsonUtil.json;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.delete;
import static spark.Spark.after;

import java.util.List;

import com.google.gson.Gson;

import br.com.emersonluiz.spark.model.User;
import br.com.emersonluiz.spark.repository.UserRepository;

public class UserResource {

	public UserResource() {

		UserRepository userRepository = new UserRepository();

		after((req, res) -> {
			res.type("application/json");
		});

		get("/users", (req, res) -> {
			List<User> users = userRepository.getUsers();
			return new Gson().toJson(users);
		});

		get("/users/:id", (req, res) -> {
			User user = userRepository.getUser(Integer.parseInt(req.params(":id")));
			return new Gson().toJson(user);
		});

		post("/users", (req, res) -> userRepository.create(new Gson().fromJson(req.body(), User.class)), json());

		delete("/users/:id", (req, res) -> {
			userRepository.delete(Integer.parseInt(req.params(":id")));
			res.status(204);
			return res;
		});

	}
}
