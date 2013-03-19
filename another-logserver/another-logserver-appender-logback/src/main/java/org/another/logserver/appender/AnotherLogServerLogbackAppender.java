/**
 * 
 */
package org.another.logserver.appender;

import ch.qos.logback.core.AppenderBase;

/**
 * @author <a href="mailto:paul.manjarres@gmail.com">Jean Paul Manjarres Correal. </a> 17/03/2013
 * @param <E>
 *
 */
public class AnotherLogServerLogbackAppender<E> extends AppenderBase<E> {

	
	/* (non-Javadoc)
	 * @see ch.qos.logback.core.AppenderBase#start()
	 */
	@Override
	public void start() {
		// TODO Auto-generated method stub
		super.start();
	}
	
	/* (non-Javadoc)
	 * @see ch.qos.logback.core.AppenderBase#append(java.lang.Object)
	 */
	@Override
	protected void append(E appendEvent) {
		// TODO Auto-generated method stub
		
	}

}
