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
import org.vertx.java.core.net.NetServer;
import org.vertx.java.core.net.NetSocket;

/**
 * {@link IEndPoint} used to listen the log request using TCP transport
 *
 * @author <a href="mailto:paul.manjarres@gmail.com">Jean Paul Manjarres
 *         Correal. </a> 19/03/2013
 *
 */
public class TCPEndPointImpl implements IEndPoint {

	/** Class logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(TCPEndPointImpl.class);

	/**
	 * The {@link NetServer} Vert.x based
	 */
	private NetServer netServer = null;

	/**
	 * Host used to listen messages
	 */
	private String host;

	/**
	 * Port used to listen messages
	 */
	private Integer port;

	/**
	 * User for authentication
	 */
	private String user;

	/**
	 * Password for authentication
	 */
	private String password;

	/**
	 * Check if the end point is currently started
	 */
	private boolean started;

	/*
	 * (non-Javadoc)
	 *
	 * @see org.another.logserver.endpoints.api.IEndPoint#start()
	 */
	@Override
	public void start() {

		Vertx vertx = Vertx.newVertx();
		netServer = vertx.createNetServer();

		netServer.connectHandler(new Handler<NetSocket>() {

			@Override
			public void handle(NetSocket socket) {
				socket.dataHandler(new Handler<Buffer>() {
					@Override
					public void handle(Buffer buffer) {
						

						LOGGER.debug("RECEIVED DATA: {}",buffer.toString() );



					}
				});

			}
		});

		netServer.listen(this.port, this.host);

		LOGGER.debug("Starting TCP server on {}:{}", this.host, this.port);
		started = true;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.another.logserver.endpoints.api.IEndPoint#stop()
	 */
	@Override
	public void stop() {
		// TODO Auto-generated method stub

	}

	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @param host
	 *            the host to set
	 */
	public void setHost(String host) {
		this.host = host;
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
