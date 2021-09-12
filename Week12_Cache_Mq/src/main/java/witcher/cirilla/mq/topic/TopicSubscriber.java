package witcher.cirilla.mq.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class TopicSubscriber {

    public static void main(String[] args) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        Connection connection = connectionFactory.createConnection();

        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic("topic-test");
        MessageConsumer consumer = session.createConsumer(topic);

        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                MapMessage mapMessage = (MapMessage) message;
                try {
                    System.out.println(mapMessage.getString("topic"));
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
