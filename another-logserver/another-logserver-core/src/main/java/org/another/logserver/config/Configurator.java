package org.another.logserver.config;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.another.logserver.endpoints.EndPointDefinition;
import org.another.logserver.endpoints.EndPointFactory;
import org.another.logserver.endpoints.api.IEndPoint;
import org.another.logserver.endpoints.imp.RESTEndPointImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.google.common.base.Strings;

/**
 *
 * @author jeanpaul.manjarres
 *
 */
@Component
public class Configurator {

	/** Class logger */
	private final static Logger LOGGER = LoggerFactory
			.getLogger(Configurator.class);

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

		configuredEndPoints = new ConcurrentHashMap<>();

		HashSet<String> endPointsHashSet = new HashSet<>();
		Collections.addAll(endPointsHashSet, endPoints);

		if (endPointsHashSet.contains(EndPointDefinition.REST_ENDPOINT
				.getShortDefinition())) {
			configureRestEndPoint();
		}

		if (endPointsHashSet.contains(EndPointDefinition.TCP_ENDPOINT
				.getShortDefinition())) {
			configureTCPEndPoint();
		}

	}

	/**
	 * Configures the Rest end point
	 */
	private void configureRestEndPoint() {

		LOGGER.debug("Configuring REST End Point ");
		LOGGER.debug("endpoint.rest.host: {}", endPointRestHost);
		LOGGER.debug("endpoint.rest.port: {}", endPointRestPort);
		LOGGER.debug("endpoint.rest.path: {}", endPointRestPath);
		LOGGER.debug("endpoint.rest.user: {}", endPointRestUser);
		LOGGER.debug("endpoint.rest.password: {}", endPointRestPassword);

		Map<String, Object> properties = new HashMap<>();

		if (!Strings.isNullOrEmpty(this.endPointRestHost)) {
			properties.put("endPointRestHost", this.endPointRestHost);
		}

		if (!Strings.isNullOrEmpty(this.endPointRestUser)) {
			properties.put("endPointRestUser", this.endPointRestUser);
		}

		if (!Strings.isNullOrEmpty(this.endPointRestPassword)) {
			properties.put("endPointRestPassword", this.endPointRestPassword);
		}

		if (this.endPointRestPort != null) {
			properties.put("endPointRestPort", this.endPointRestPort);
		}

		if (!Strings.isNullOrEmpty(this.endPointRestPath)) {
			properties.put("endPointRestPath", this.endPointRestPath);
		}

		IEndPoint endPoint = EndPointFactory.create(
				EndPointDefinition.REST_ENDPOINT, properties);

		configuredEndPoints.put(EndPointDefinition.REST_ENDPOINT, endPoint);

		// TODO: Should we start this endpoint here?
		endPoint.start();

		// ((AbstractApplicationContext)applicationContext).getBeanFactory().registerSingleton("restEndPointBean",
		// endPoint);

	}

	/**
	 * Configures the TCP End Point
	 */
	private void configureTCPEndPoint() {

		LOGGER.debug("Configuring TCP End Point ");

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
	private String endPointTcpPort;

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
