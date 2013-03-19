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

package org.another.logserver.appender;

import org.another.logserver.common.LogEventVO;
import org.apache.commons.lang3.StringUtils;
import org.jboss.resteasy.client.ClientRequest;

import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import ch.qos.logback.core.encoder.Encoder;

import com.google.gson.Gson;

/**
 * @author <a href="mailto:paul.manjarres@gmail.com">Jean Paul Manjarres
 *         Correal. </a> 17/03/2013
 *
 */
public class LogbackRestAppender<E> extends UnsynchronizedAppenderBase<E> {

	/** The URL of the REST Endpoint */
	private String url;

	private Encoder<E> encoder;

	private ClientRequest restClient;

	/*
	 * (non-Javadoc)
	 *
	 * @see ch.qos.logback.core.AppenderBase#start()
	 */
	@Override
	public void start() {

		int errors = 0;

		if (StringUtils.isBlank(this.url)) {
			errors++;
			addError("The URL is null or empty. Start failed. ");
		}

		// Starts the appender only if there are no errors
		if (errors == 0) {
			addInfo("Starting Another Logeserver - LogbackRestAppender");
			addInfo("Configured URL: " + this.url);
			super.start();
		} else {
			started = false;
		}

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see ch.qos.logback.core.AppenderBase#stop()
	 */
	@Override
	public void stop() {
		super.stop();
		addInfo("Stoppig Another Logeserver - LogbackRestAppender");

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see ch.qos.logback.core.AppenderBase#append(java.lang.Object)
	 */
	@Override
	protected void append(E arg0) {

		LoggingEvent evt = null;

		if (arg0 instanceof LoggingEvent) {
			evt = (LoggingEvent) arg0;

			// System.out.println("LOGGED EVENT: " +
			// arg0+" - CLass:"+arg0.getClass().getName());

			System.out.println("LOGGED EVENT: " + evt);

			System.out.println("evt.getContextBirthTime(): "+evt.getContextBirthTime());
			System.out.println("evt.getFormattedMessage(): "+evt.getFormattedMessage());
			System.out.println("evt.getLoggerName(): "+evt.getLoggerName());
			System.out.println("evt.getMessage(): "+evt.getMessage());
			System.out.println("evt.getThreadName(): "+evt.getThreadName());
			System.out.println("evt.getTimeStamp(): "+evt.getTimeStamp());
			System.out.println("evt.getArgumentArray()"+evt.getArgumentArray());
			System.out.println("evt.getCallerData(): "+evt.getCallerData());
			System.out.println("evt.getClass(): "+evt.getClass());
			System.out.println("evt.getLevel(): "+evt.getLevel());
			System.out.println("evt.getLoggerContextVO(): "+evt.getLoggerContextVO());
			System.out.println("evt.getMarker(): "+evt.getMarker());
			System.out.println("evt.getMdc(): "+evt.getMdc());
			System.out.println("evt.getMDCPropertyMap()"+evt.getMDCPropertyMap());
			System.out.println("evt.getThrowableProxy(): "+evt.getThrowableProxy());


			LogEventVO vo = new LogEventVO();

			vo.setHostName(evt.getLoggerContextVO().getPropertyMap().get("HOSTNAME"));
			vo.setLevel(evt.getLevel().levelStr);
			vo.setLoggerName(evt.getLoggerName());
			vo.setMessage(evt.getMessage());
			vo.setStackTraceElements(evt.getCallerData());
			vo.setThreadName(evt.getThreadName());
			vo.setTimeStamp(evt.getTimeStamp());


			String json = new Gson().toJson(vo);

			System.out.println("JSON: "+json);

			restClient = new ClientRequest(this.url);
			restClient.body("text/json", json);
			try {
				restClient.post();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}






		}

	}

	/**
	 * @return the encoder
	 */
	public Encoder<E> getEncoder() {
		return encoder;
	}

	/**
	 * @param encoder
	 *            the encoder to set
	 */
	public void setEncoder(Encoder<E> encoder) {
		this.encoder = encoder;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

}
