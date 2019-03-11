package cc.edt.frame.wechat.service.component.fans;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 微信粉丝OpenId列表
 *
 * @author 奚艺轩
 * @date 2018/9/12 14:54
 */
@Data
public class WeChatFansInfoList implements Serializable {
    private static final long serialVersionUID = 2024022836968772517L;
    private String accountId;
    private List<String> listOpenIds;
}
