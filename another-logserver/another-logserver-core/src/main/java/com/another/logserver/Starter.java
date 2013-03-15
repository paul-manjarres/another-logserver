package com.another.logserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.another.logserver.endpoints.imp.RESTEndPointImpl;

/**
 *
 * @author <a href="mailto:paul.manjarres@gmail.com">Jean Paul Manjarres
 *         Correal. </a> 14/03/2013
 *
 */
public class Starter {

	/** Class logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(Starter.class);

	/**
	 *
	 * @param args
	 */
	public static void main(String[] args) {

		LOGGER.info("Starting Another Log server ...");

		// Initialize components


		RESTEndPointImpl impl = new RESTEndPointImpl();

		impl.start();



		LOGGER.debug("Entering wait loop");
		try {
			while (true) {
				Thread.sleep(1000 * 60 * 60);
			}
		} catch (Exception e) {
			LOGGER.error("", e);
		}

	}

}
