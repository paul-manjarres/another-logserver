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
package org.another.logserver.endpoints;

import java.util.Map;

import org.another.logserver.Constants;
import org.another.logserver.endpoints.api.IEndPoint;
import org.another.logserver.endpoints.imp.RESTEndPointImpl;
import org.another.logserver.endpoints.imp.TCPEndPointImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Factory used to create different EndPoint implementations.
 *
 * @author <a href="mailto:paul.manjarres@gmail.com">Jean Paul Manjarres
 *         Correal. </a> 14/03/2013
 *
 */
public class EndPointFactory {

	/** Class logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(EndPointFactory.class);

	/**
	 * Private constructor. Avoid instantiating the factory.
	 */
	private EndPointFactory() {
		// No constructor code.
	}

	/**
	 * Creates and endPoint based on a Enumeration {@link EndPointDefinition}
	 * parameter.
	 *
	 * @param endPoint
	 *            the Enumeration that define which endpoint create.
	 * @return new Instance of an EndPoint
	 */
	public static IEndPoint create(final EndPointDefinition endPoint, Map<String, Object> properties) {

		LOGGER.debug("Creating a new EndPoint [{}]", endPoint);

		IEndPoint newEndPoint = null;

		switch (endPoint) {

		case REST_ENDPOINT:
			// Create and configure a Rest End point
			newEndPoint = new RESTEndPointImpl();
			((RESTEndPointImpl) newEndPoint).setListenAddress((String) properties
					.get(Constants.END_POINT_REST_CONFIG_HOST_PARAM));
			((RESTEndPointImpl) newEndPoint).setPath((String) properties
					.get(Constants.END_POINT_REST_CONFIG_PATH_PARAM));
			((RESTEndPointImpl) newEndPoint).setPort((Integer) properties
					.get(Constants.END_POINT_REST_CONFIG_PORT_PARAM));
			((RESTEndPointImpl) newEndPoint).setUser((String) properties
					.get(Constants.END_POINT_REST_CONFIG_USER_PARAM));
			((RESTEndPointImpl) newEndPoint).setPassword((String) properties
					.get(Constants.END_POINT_REST_CONFIG_PASSWORD_PARAM));

			break;

		case SOAP_ENDPOINT:

			break;

		case SYSLOG_ENDPOINT:

			break;

		case TCP_ENDPOINT:
			// Create and configure a TCP End point
			newEndPoint = new TCPEndPointImpl();
			((TCPEndPointImpl) newEndPoint).setHost((String) properties.get(Constants.END_POINT_TCP_CONFIG_HOST_PARAM));
			((TCPEndPointImpl) newEndPoint)
					.setPort((Integer) properties.get(Constants.END_POINT_TCP_CONFIG_PORT_PARAM));
			((TCPEndPointImpl) newEndPoint).setUser((String) properties.get(Constants.END_POINT_TCP_CONFIG_USER_PARAM));
			((TCPEndPointImpl) newEndPoint).setPassword((String) properties
					.get(Constants.END_POINT_TCP_CONFIG_PASSWORD_PARAM));

			break;

		default:
			break;
		}

		return newEndPoint;

	}

}
