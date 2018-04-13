package br.com.emersonluiz.spark;

import static spark.Spark.port;
import static spark.Spark.staticFiles;

import br.com.emersonluiz.spark.rest.UserResource;

public class Application {

	public static void main(String[] args) {

		port(8081);

		staticFiles.location("/public");
		
		new UserResource();

	}

}
