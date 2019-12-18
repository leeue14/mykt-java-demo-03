package com.leeue.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;

/**
 * 创建发送消息管道
 *
 * @author liyue
 * @date 2019/12/18 11:37
 */
public interface SendMessageInterface {

    /**
     * 创建发送消息通道
     * <p>
     * my_stream_test 通道名称
     *
     * @return
     */
    @Output("my_stream_test")
    SubscribableChannel sendMsg();
}
