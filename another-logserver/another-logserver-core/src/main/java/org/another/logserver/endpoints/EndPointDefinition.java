package org.another.logserver.endpoints;

/**
 *
 * Enum that defines the posible EndPoints handled by the server.
 *
 * @author <a href="mailto:paul.manjarres@gmail.com">Jean Paul Manjarres
 *         Correal. </a> 14/03/2013
 *
 */
public enum EndPointDefinition {

	REST_ENDPOINT("rest"),

	SOAP_ENDPOINT("soap"),

	TCP_ENDPOINT("tcp"),

	SYSLOG_ENDPOINT("syslog");

	/** Defines a short identificator for the enum */
	private String shortDefinition;

	/**
	 *
	 * @param shortDefintion
	 */
	private EndPointDefinition(String shortDefintion) {
		this.shortDefinition = shortDefintion;
	}

	public String getShortDefinition() {
		return shortDefinition;
	}

}
