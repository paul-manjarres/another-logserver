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
 * distributed under the License is distributed on akn "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package org.another.logserver;

import org.another.logserver.config.Configurator;
import org.another.logserver.endpoints.api.IEndPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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

		LOGGER.debug("Starting Spring context ...");
		final ApplicationContext springContext = new ClassPathXmlApplicationContext("spring.xml");

		LOGGER.info("Spring intilization: {}", springContext.getStartupDate());

		Runtime.getRuntime().addShutdownHook(new Thread() {

			@Override
			public void run() {
				super.run();
				LOGGER.debug("Running Shutdown Hook");

				Configurator conf = springContext.getBean("configurator", Configurator.class);
				for (IEndPoint ep : conf.getConfiguredEndPoints().values()) {
					ep.stop();
				}

			}

		});

		((AbstractApplicationContext) springContext).registerShutdownHook();

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
