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
