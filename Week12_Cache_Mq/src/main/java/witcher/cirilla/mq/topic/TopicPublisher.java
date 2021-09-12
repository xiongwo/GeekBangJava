package witcher.cirilla.mq.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class TopicPublisher {

    public static void main(String[] args) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        Connection connection = connectionFactory.createConnection();

        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic("topic-test");
        MessageProducer producer = session.createProducer(topic);

        MapMessage mapMessage = session.createMapMessage();
        mapMessage.setString("topic", "test");
        producer.send(topic, mapMessage);

        connection.close();
    }

}
