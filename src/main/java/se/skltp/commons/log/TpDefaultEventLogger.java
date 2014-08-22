package se.skltp.commons.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TpDefaultEventLogger extends ExtensibleEventLogger {
	private static final Logger log = LoggerFactory
			.getLogger(TpDefaultEventLogger.class);
	/**
	 * Flag to control if logging to JMS is on/off.
	 */
	private boolean doLogToJms = true;
	/**
	 * Name of JMS queue for logging info-events.
	 */
	private String jmsInfoEventQueue = "SOITOOLKIT.LOG.INFO";
	/**
	 * Name of JMS queue for logging error-events.
	 */
	private String jmsErrorEventQueue = "SOITOOLKIT.LOG.ERROR";

	public void setDoLogToJms(boolean doLogToJms) {
		log.debug("setting doLogToJms: {}", doLogToJms);
		this.doLogToJms = doLogToJms;
	}

	public void setJmsInfoEventQueue(String jmsInfoEventQueue) {
		log.debug("setting jmsInfoEventQueue: {}", jmsInfoEventQueue);
		this.jmsInfoEventQueue = jmsInfoEventQueue;
	}

	public void setJmsErrorEventQueue(String jmsErrorEventQueue) {
		log.debug("setting jmsErrorEventQueue: {}", jmsErrorEventQueue);
		this.jmsErrorEventQueue = jmsErrorEventQueue;
	}

	@Override
	protected void dispatchInfoEvent(String msg) {
		// writing a debug-log makes it easy to figure out if logging is done to
		// JMS
		log.debug("dispatchInfoEvent: doLogToJms: {}, jmsInfoEventQueue: {}",
				doLogToJms, jmsInfoEventQueue);
		if (doLogToJms) {
			dispatchEvent(jmsInfoEventQueue, msg);
		}
	}

	@Override
	protected void dispatchErrorEvent(String msg) {
		// writing a debug-log makes it easy to figure out if logging is done to
		// JMS
		log.debug("dispatchErrorEvent: doLogToJms: {}, jmsErrorEventQueue: {}",
				doLogToJms, jmsErrorEventQueue);
		if (doLogToJms) {
			dispatchEvent(jmsErrorEventQueue, msg);
		}
	}

}
