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

package org.another.logserver.config;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.another.logserver.Constants;
import org.another.logserver.common.utils.CustomChainableMap;
import org.another.logserver.endpoints.EndPointDefinition;
import org.another.logserver.endpoints.EndPointFactory;
import org.another.logserver.endpoints.api.IEndPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Spring bean that starts the system configuration.<br/>
 * Uses the configured properties source to get the main configuration.
 *
 * @author <a href="mailto:paul.manjarres@gmail.com">Jean Paul Manjarres
 *         Correal. </a> 19/03/2013
 *
 */
@Component
public class Configurator {

	/** Class logger */
	private final static Logger LOGGER = LoggerFactory.getLogger(Configurator.class);

	/**
	 * Spring application context
	 */
	@Autowired
	private ApplicationContext applicationContext;

	/** Defined End Points for the application */
	@Value("${endpoints:}")
	private String[] endPoints;

	/** Map of the configured end points. */
	private Map<EndPointDefinition, IEndPoint> configuredEndPoints;

	/**
	 * Initializes the component.
	 */
	@PostConstruct
	public void init() {
		LOGGER.debug("Defined endpoints: {}", (Object[]) endPoints);

		this.configuredEndPoints = new ConcurrentHashMap<>();

		HashSet<String> endPointsHashSet = new HashSet<>();
		Collections.addAll(endPointsHashSet, endPoints);

		if (endPointsHashSet.contains(EndPointDefinition.REST_ENDPOINT.getShortDefinition())) {
			configureRestEndPoint();
		}

		if (endPointsHashSet.contains(EndPointDefinition.TCP_ENDPOINT.getShortDefinition())) {
			configureTCPEndPoint();
		}

		LOGGER.debug("Finalizing Configure task");

	}

	/**
	 * Configures the Rest end point
	 */
	private void configureRestEndPoint() {

		if (LOGGER.isDebugEnabled()) {
			Object[] args = new Object[] { endPointRestHost, endPointRestPort, endPointRestPath, endPointRestUser };
			LOGGER.debug("Configuring REST End Point with: Host:[{}] Port:[{}] Path:[{}] User:[{}] Password:[****]",
					args);
		}

		CustomChainableMap<String, Object> properties = new CustomChainableMap<>();

		properties.cPut(Constants.END_POINT_REST_CONFIG_HOST_PARAM, this.endPointRestHost)
				.cPut(Constants.END_POINT_REST_CONFIG_USER_PARAM, this.endPointRestUser)
				.cPut(Constants.END_POINT_REST_CONFIG_PASSWORD_PARAM, this.endPointRestPassword)
				.cPut(Constants.END_POINT_REST_CONFIG_PORT_PARAM, this.endPointRestPort)
				.cPut(Constants.END_POINT_REST_CONFIG_PATH_PARAM, this.endPointRestPath);

		IEndPoint endPoint = EndPointFactory.create(EndPointDefinition.REST_ENDPOINT, properties);

		configuredEndPoints.put(EndPointDefinition.REST_ENDPOINT, endPoint);

		// TODO: Should we start this endpoint here?
		endPoint.start();
	}

	/**
	 * Configures the TCP End Point
	 */
	private void configureTCPEndPoint() {

		LOGGER.debug("Configuring TCP End Point ");

		if (LOGGER.isDebugEnabled()) {
			Object[] args = new Object[] { endPointTcpHost, endPointTcpPort, endPointTcpUser };
			LOGGER.debug("Configuring TCP End Point with: Host:[{}] Port:[{}] User:[{}] Password:[****]", args);
		}

		CustomChainableMap<String, Object> properties = new CustomChainableMap<>();

		properties.cPut(Constants.END_POINT_TCP_CONFIG_HOST_PARAM, this.endPointTcpHost)
				.cPut(Constants.END_POINT_TCP_CONFIG_PORT_PARAM, this.endPointTcpPort)
				.cPut(Constants.END_POINT_REST_CONFIG_USER_PARAM, this.endPointTcpUser)
				.cPut(Constants.END_POINT_REST_CONFIG_PASSWORD_PARAM, this.endPointTcpPassword);

		IEndPoint endPoint = EndPointFactory.create(EndPointDefinition.TCP_ENDPOINT, properties);

		configuredEndPoints.put(EndPointDefinition.TCP_ENDPOINT, endPoint);

		// TODO: Should we start this endpoint here?
		endPoint.start();

	}

	/*
	 * CONFIGURATION FOR REST END POINT
	 */
	/** Host address for Rest End Point */
	@Value("${endpoint.rest.host:}")
	private String endPointRestHost;

	/** Port for Rest End Point */
	@Value("${endpoint.rest.port:}")
	private Integer endPointRestPort;

	/** Path for Rest End Point */
	@Value("${endpoint.rest.path:}")
	private String endPointRestPath;

	/** User for Rest End Point */
	@Value("${endpoint.rest.user:}")
	private String endPointRestUser;

	/** Password for Rest End Point */
	@Value("${endpoint.rest.password:}")
	private String endPointRestPassword;

	/*
	 * CONFIGURATION FOR TCP END POINT
	 */
	/** Host for the TCP end point */
	@Value("${endpoint.tcp.host:}")
	private String endPointTcpHost;

	/** Port for the TCP end point */
	@Value("${endpoint.tcp.port:}")
	private Integer endPointTcpPort;

	/** User for the TCP end point */
	@Value("${endpoint.tcp.user:}")
	private String endPointTcpUser;

	@Value("${endpoint.tcp.user:}")
	private String endPointTcpPassword;

	/**
	 * @return the configuredEndPoints
	 */
	public Map<EndPointDefinition, IEndPoint> getConfiguredEndPoints() {
		return configuredEndPoints;
	}

}
