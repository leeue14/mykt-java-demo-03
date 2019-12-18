package com.leeue;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * 管道中绑定消息
 *
 * @author liyue
 * @date 2019/12/18 12:04
 */
public interface ReadMessageInterface {

    @Input("my_stream_test")
    SubscribableChannel readMsg();
}
