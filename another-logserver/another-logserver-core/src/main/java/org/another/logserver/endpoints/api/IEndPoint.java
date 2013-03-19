package org.another.logserver.endpoints.api;

/**
 * Interface that defines the behaviour of an EndPoint
 *
 * @author <a href="mailto:paul.manjarres@gmail.com">Jean Paul Manjarres
 *         Correal. </a> 14/03/2013
 *
 */
public interface IEndPoint {

	/**
	 * Starts the endpoint in order to receive requests.
	 */
	void start();

	/**
	 * Stops the endpoint
	 */
	void stop();

}
