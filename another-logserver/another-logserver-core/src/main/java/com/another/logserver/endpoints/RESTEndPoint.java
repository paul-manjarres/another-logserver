package com.another.logserver.endpoints;

import org.vertx.java.core.Handler;
import org.vertx.java.core.http.HttpServer;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.logging.Logger;
import org.vertx.java.deploy.Verticle;


/**
 *
 * @author <a href="mailto:paul.manjarres@gmail.com">Jean Paul Manjarres
 *         Correal. </a> 12/03/2013
 *
 */
public class RESTEndPoint extends Verticle {

	/*
	 * (non-Javadoc)
	 *
	 * @see org.vertx.java.deploy.Verticle#start()
	 */
	@Override
	public void start() throws Exception {

		HttpServer httpServer = vertx.createHttpServer();
		final Logger logger = getContainer().getLogger();

		httpServer.requestHandler(new Handler<HttpServerRequest>() {

			@Override
			public void handle(HttpServerRequest req) {
				logger.info("Headers: "+ req.headers().values());

				req.response.putHeader("Content-type", "text/html");
				req.response.end("<html><head></head><body> <h1> VERTX </h1> </body><html>","UTF-8");
			}
		});

		logger.debug("Starting server");
		httpServer.listen(8080, "127.0.0.1");

	}

}
