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
