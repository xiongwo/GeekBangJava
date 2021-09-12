package witcher.cirilla.mq.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class QueueSender {

    public static void main(String[] args) throws JMSException, InterruptedException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_USER, ActiveMQConnectionFactory.DEFAULT_PASSWORD, "tcp://localhost:61616");
        Connection connection = connectionFactory.createConnection();

        connection.start();

        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);

        Queue queue = session.createQueue("queue-test");
        MessageProducer producer = session.createProducer(queue);
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        for (int i = 0; i < 10; i++) {
            TextMessage message = session.createTextMessage("hello");
            Thread.sleep(1000);
            producer.send(message);
        }

        session.commit();
        session.close();
        connection.close();
    }

}
