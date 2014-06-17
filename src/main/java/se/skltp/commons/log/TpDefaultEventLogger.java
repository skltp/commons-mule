package se.skltp.commons.log;

public class TpDefaultEventLogger extends ExtensibleEventLogger {
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
		this.doLogToJms = doLogToJms;
	}

	public void setJmsInfoEventQueue(String jmsInfoEventQueue) {
		this.jmsInfoEventQueue = jmsInfoEventQueue;
	}

	public void setJmsErrorEventQueue(String jmsErrorEventQueue) {
		this.jmsErrorEventQueue = jmsErrorEventQueue;
	}

	@Override
	protected void dispatchInfoEvent(String msg) {
		if (doLogToJms) {
			dispatchEvent(jmsInfoEventQueue, msg);
		}
	}

	@Override
	protected void dispatchErrorEvent(String msg) {
		if (doLogToJms) {
			dispatchEvent(jmsErrorEventQueue, msg);
		}
	}

}
