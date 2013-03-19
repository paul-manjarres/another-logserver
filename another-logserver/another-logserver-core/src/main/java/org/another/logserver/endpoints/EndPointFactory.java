package org.another.logserver.endpoints;

import org.another.logserver.endpoints.api.IEndPoint;
import org.another.logserver.endpoints.imp.RESTEndPointImpl;
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
	private static final Logger LOGGER = LoggerFactory
			.getLogger(EndPointFactory.class);

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
	public static IEndPoint create(final EndPointDefinition endPoint) {

		LOGGER.debug("Creating a new EndPoint [{}]", endPoint);

		IEndPoint newEndPoint = null;

		switch (endPoint) {

		case REST_ENDPOINT:
			newEndPoint = new RESTEndPointImpl();

			break;

		case SOAP_ENDPOINT:

			break;

		case SYSLOG_ENDPOINT:

			break;

		case TCP_ENDPOINT:

			break;

		default:
			break;
		}

		return newEndPoint;

	}

}
