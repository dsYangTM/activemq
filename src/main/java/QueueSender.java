import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
* 消息队列发送者
* @Author:Ds.Yang
* @Date:12:17 2019/3/13
*/
public class QueueSender {
    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(Boolean.TRUE,Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("my-queue");
        MessageProducer producer = session.createProducer(destination);
        for (int i = 0; i < 3; i++) {
            TextMessage message = session.createTextMessage("message--"+i);
            //通过消息生产者发出消息
            producer.send(message);
        }
        session.commit();
        session.close();
        connection.close();
    }
}
