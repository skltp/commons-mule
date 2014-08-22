package se.skltp.commons.log;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.mule.api.MuleContext;
import org.mule.transport.jms.JmsConnector;
import org.soitoolkit.commons.mule.log.DefaultEventLogger;
import org.soitoolkit.commons.mule.util.MuleUtil;

/**
 * Makes the <code>org.soitoolkit.commons.mule.log.DefaultEventLogger</code>
 * more extension friendly by replicating the needed parts that have private
 * access.
 * <p>
 * The intention with this class is not to add new functionality (that should
 * take place in sub-classes), but to make existing functionality accessible.
 * Only the parts that are actually needed should be replicated from the super
 * class into this class.
 * <p>
 * 2014-06-18: designed based on soi-toolkit 0.6.1.
 */
public class ExtensibleEventLogger extends DefaultEventLogger {
	protected MuleContext muleContext = null;

	@Override
	public void setMuleContext(MuleContext muleContext) {
		super.setMuleContext(muleContext);
		this.muleContext = muleContext;
	}

	// private void dispatchEvent(String queue, String msg) {
	protected void dispatchEvent(String queue, String msg) {
		try {
			Session s = null;
			try {
				s = getSession();
				sendOneTextMessage(s, queue, msg);
			} finally {
				if (s != null)
					s.close();
			}
		} catch (JMSException e) {
			throw new RuntimeException(e);
		}
	}

	private Session getSession() throws JMSException {
		// JmsConnector jmsConn =
		// (JmsConnector)MuleServer.getMuleContext().getRegistry().lookupConnector("soitoolkit-jms-connector");
		JmsConnector jmsConn = (JmsConnector) MuleUtil.getSpringBean(
				muleContext, "soitoolkit-jms-connector");
		Connection c = jmsConn.getConnection();
		Session s = c.createSession(false, Session.AUTO_ACKNOWLEDGE);
		return s;
	}

	private void sendOneTextMessage(Session session, String queueName,
			String message) {
		MessageProducer publisher = null;
		try {
			publisher = session.createProducer(session.createQueue(queueName));
			TextMessage textMessage = session.createTextMessage(message);
			publisher.send(textMessage);
		} catch (JMSException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (publisher != null)
					publisher.close();
			} catch (JMSException e) {
			}
		}
	}

}
