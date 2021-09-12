package witcher.cirilla.mq.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class QueueReceiver {

    public static void main(String[] args) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_USER, ActiveMQConnectionFactory.DEFAULT_PASSWORD, "tcp://localhost:61616");
        Connection connection = connectionFactory.createConnection();

        connection.start();

        final Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue("queue-test");
        MessageConsumer consumer = session.createConsumer(queue);
        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                try {
                    System.out.println("consumer: " + ((TextMessage) message).getText());

                    session.commit();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
