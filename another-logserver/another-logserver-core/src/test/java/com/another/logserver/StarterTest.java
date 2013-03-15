package com.another.logserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

/**
 * @author <a href="mailto:paul.manjarres@gmail.com">Jean Paul Manjarres
 *         Correal. </a> 14/03/2013
 *
 */
public class StarterTest {

	/** Class logger */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(StarterTest.class);

	@Test
	public void dummyTest() {

		LOGGER.info("Dummy Test");

	}

}
