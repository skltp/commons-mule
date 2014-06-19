package se.skltp.commons.log;

public class LoggingNoJMSTest extends LoggingUsingJMSTest {

	public LoggingNoJMSTest() {
		super();

		expectedNumberOfJMSInfoMessages = 0;

		// set properties used in flow as system-properties (we are not using
		// any properties file here)
		System.setProperty("TP_DO_LOG_TO_JMS", TP_DO_LOG_TO_JMS_FALSE);
		System.setProperty("SOITOOLKIT_LOG_INFO_QUEUE",
				SOITOOLKIT_LOG_INFO_QUEUE);
		System.setProperty("SOITOOLKIT_LOG_ERROR_QUEUE",
				SOITOOLKIT_LOG_ERROR_QUEUE);
	}

}
