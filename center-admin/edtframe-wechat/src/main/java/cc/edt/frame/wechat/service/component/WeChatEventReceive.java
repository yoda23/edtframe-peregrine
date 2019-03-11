package cc.edt.frame.wechat.service.component;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

/**
 * 微信事件处理
 *
 * @author 刘钢
 * @date 2018/11/5 10:23
 */
@Component
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class WeChatEventReceive {
    @Resource
    private WeChatFansEventReceive weChatFansEventReceive;

    @RabbitHandler
    @RabbitListener(queues = "wechat.event")
    public void eventReceive(String eventXml) throws DocumentException {
        Document doc = DocumentHelper.parseText(eventXml);
        Element root = doc.getRootElement();
        String msgType = root.element("MsgType").getText();
        switch (StringUtils.trim(msgType)) {
            case "event":
                String event = root.element("Event").getText();
                switch (event) {
                    /* 用户关注 */
                    case "subscribe":
                        weChatFansEventReceive.subscribeEvent(eventXml);
                        break;
                    /* 扫码关注 */
                    case "SCAN":
                        break;
                    /* 用户取消关注 */
                    case "unsubscribe":
                        weChatFansEventReceive.unSubscribeEvent(eventXml);
                        break;
                    /* 用户菜单击 */
                    case "CLICK":
                        log.debug("CLICK------>" + eventXml);
                        break;
                    /* 用户菜单跳转URL */
                    case "VIEW":
                        break;
                    /* 用户上报地理位置 */
                    case "LOCATION":
                        break;
                    default:
                }
                break;
            /* 文本消息 */
            case "text":
                break;
            /* 图片消息 */
            case "image":
                break;
            /* 语音消息 */
            case "voice":
                break;
            /* 小视频消息 */
            case "shortvideo":
                break;
            /* 地理位置消息 */
            case "location":
                break;
            /* 连接消息 */
            case "link":
                break;
            default:
        }
    }
}
