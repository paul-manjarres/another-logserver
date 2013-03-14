package com.another.logserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vertx.java.core.Handler;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.http.HttpServer;
import org.vertx.java.core.http.HttpServerRequest;

public class MainServer {

	/** Class logger */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(MainServer.class);

	public static void main(String[] args) {

		Vertx vertx = Vertx.newVertx();

		HttpServer httpServer = vertx.createHttpServer();

		httpServer.requestHandler(new Handler<HttpServerRequest>() {

			@Override
			public void handle(HttpServerRequest req) {
				// TODO Auto-generated method stub

				LOGGER.info("Headers: {}", req.headers().values());

				req.response.putHeader("Content-type", "text/html");
				req.response
						.end("<html><head></head><body> <h1> VERTX </h1> </body><html>",
								"UTF-8");

			}
		});

		LOGGER.debug("Starting server");

		httpServer.listen(8080, "127.0.0.1");

		// try {
		// Thread.sleep(60000);
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

	}

}
