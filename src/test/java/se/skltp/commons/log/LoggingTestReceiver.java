package se.skltp.commons.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingTestReceiver {

	private static final Logger log = LoggerFactory
			.getLogger(LoggingTestReceiver.class);

	public void process(Object message) {
		log.info(getClass().getSimpleName() + " received the message: {}",
				message);
	}
}
