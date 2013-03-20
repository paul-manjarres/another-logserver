/*
 * Copyright (C) 2013 Jean Paul Manjarres Correal
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.another.logserver.endpoints.imp;

import org.another.logserver.endpoints.api.IEndPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vertx.java.core.Handler;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.http.HttpServer;
import org.vertx.java.core.http.HttpServerRequest;

import com.google.common.base.Preconditions;

/**
 * {@link IEndPoint} used to Handle the requests in HTTP protocol. RESTful
 * interface.
 *
 * @author <a href="mailto:paul.manjarres@gmail.com">Jean Paul Manjarres
 *         Correal. </a> 12/03/2013
 *
 */
public class RESTEndPointImpl implements IEndPoint {

	/** Class logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(RESTEndPointImpl.class);

	/** The {@link HttpServer} Vert.x based */
	private HttpServer httpServer = null;

	/** Flag to check if the server is started */
	private boolean started = false;

	/**
	 * Address used to listen the requests. Defaults to 127.0.0.1
	 */
	private String listenAddress = "127.0.0.1";

	/** Port used to listen requests */
	private Integer port = 8080;

	/** Path used to listen requests */
	private String path;

	/** */
	private String user;

	/** */
	private String password;

	/*
	 * (non-Javadoc)
	 *
	 * @see com.another.logserver.endpoints.api.IEndPoint#start()
	 */
	@Override
	public synchronized void start() {

		Vertx vertx = Vertx.newVertx();
		httpServer = vertx.createHttpServer();
		httpServer.requestHandler(new Handler<HttpServerRequest>() {

			@Override
			public void handle(HttpServerRequest req) {
				// TODO Auto-generated method stub

				LOGGER.info("Headers: {}", req.headers().values());

				req.response.putHeader("Content-type", "text/html");
				req.response.end("<html><head></head><body> <h1> VERTX </h1> </body><html>", "UTF-8");

				req.bodyHandler(new Handler<Buffer>() {

					@Override
					public void handle(Buffer event) {
						LOGGER.info("Body received: {}", new String(event.getBytes()));
					}
				});

			}
		});

		LOGGER.debug("Starting server on {}:{}", this.listenAddress, this.port);

		started = true;
		httpServer.listen(this.port, this.listenAddress);

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.another.logserver.endpoints.api.IEndPoint#stop()
	 */
	@Override
	public synchronized void stop() {
		Preconditions.checkNotNull(httpServer, "Server was not created. ");

		if (!started) {
			LOGGER.warn("Server was not yet started. Ignoring Stop Call. ");
		} else {
			LOGGER.debug("Closing Http Server");
			httpServer.close();
			started = false;
		}
	}

	/**
	 * @return the started
	 */
	public boolean isStarted() {
		return started;
	}

	/**
	 * @param started
	 *            the started to set
	 */
	public void setStarted(boolean started) {
		this.started = started;
	}

	/**
	 * @return the listenAddress
	 */
	public String getListenAddress() {
		return listenAddress;
	}

	/**
	 * @param listenAddress
	 *            the listenAddress to set
	 */
	public void setListenAddress(String listenAddress) {
		this.listenAddress = listenAddress;
	}

	/**
	 * @return the port
	 */
	public Integer getPort() {
		return port;
	}

	/**
	 * @param port
	 *            the port to set
	 */
	public void setPort(Integer port) {
		this.port = port;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path
	 *            the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
