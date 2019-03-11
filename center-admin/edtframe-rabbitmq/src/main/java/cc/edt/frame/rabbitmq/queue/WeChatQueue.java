package cc.edt.frame.rabbitmq.queue;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 微信消息队列
 *
 * @author 刘钢
 * @date 2018/8/28 15:58
 */
@Configuration
public class WeChatQueue {

    /**
     * 微信事件队列
     *
     * @return org.springframework.amqp.core.Queue
     * @author 刘钢
     * @date 2018/12/21 14:28
     */
    @Bean
    public Queue wechatEventQueue() {
        return new Queue("wechat.event");
    }

    /**
     * 粉丝队列
     *
     * @return org.springframework.amqp.core.Queue
     * @author 刘钢
     * @date 2018/9/12 15:57
     */
    @Bean
    public Queue wechatFansQueue() {
        return new Queue("wechat.fans");
    }
}
