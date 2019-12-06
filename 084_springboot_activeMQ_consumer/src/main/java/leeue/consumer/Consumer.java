package leeue.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;


/**
 * <p>
 * 消费者 消费队列里产生的消息
 * </p>
 *
 * @author liyue
 * @date 2019/12/6 15:15
 */
@Component
public class Consumer {

    @JmsListener(destination = "${queue}")
    public void receive(String message) {

        System.out.println("我接收到了点对点模式发送来的消息=" + message);
    }

    @JmsListener(destination = "${topic}")
    public void receiveByTopic(String message) {

        System.out.println("我接收到了点对点模式发送来的消息=" + message);
    }
}
