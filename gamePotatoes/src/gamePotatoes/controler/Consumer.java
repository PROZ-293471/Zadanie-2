package gamePotatoes.controler;

import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.TextMessage;

public class Consumer {

	private JMSContext jmsContext;
	private JMSConsumer jmsConsumer;
	private Queue queue;
	
	public JMSConsumer getJMSConsumer() {
		return jmsConsumer;
	}

	public Consumer(String url, String queueName) throws JMSException {
		ConnectionFactory connectionFactory = new com.sun.messaging.ConnectionFactory();
		jmsContext = connectionFactory.createContext();
		// 7676 numer portu, na którym JMS Service nas³uchuje po³¹czeñ

		((com.sun.messaging.ConnectionFactory) connectionFactory)
				.setProperty(com.sun.messaging.ConnectionConfiguration.imqAddressList, url);
		queue = new com.sun.messaging.Queue(queueName);

		jmsConsumer = jmsContext.createConsumer(queue);
	}

	public String receiveQueueMessage() throws JMSException {
		Message msg = jmsConsumer.receive(10);
		// 10 ms
		if (msg instanceof TextMessage)
			return ((TextMessage) msg).getText();
		return null;
	}

	protected void finalize() {
		if (jmsConsumer != null)
			jmsConsumer.close();
		if (jmsContext != null)
			jmsContext.close();
	}

	/*public void receiveQueueMessageAsync() throws InterruptedException {
		jmsConsumer.setMessageListener(new AsynchConsumer());
		/for (int i = 0; i < 3; ++i) {
			System.out.printf("U¿ytkownik Rafa³");
			Thread.sleep(1000);
		}
	}*/

}
