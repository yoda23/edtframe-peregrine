package cc.edt.frame.common.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * 通用数据字典
 *
 * @author 刘钢
 * @date 2018/8/29 14:27
 */
@Component
@Data
public class CommonDictionary {
    @Value("${application.version}")
    private String applicationVersion;
    @Value("${application.title}")
    private String applicationTitle;
    @Value("${common.upload.context.path}")
    private String uploadContextPath;
    @Value("${common.upload.path.disk}")
    private String uploadPathDisk;
    @Value("${common.upload.path.web}")
    private String uploadPathWeb;
    @Value("${common.worker.id}")
    private Integer workerId;
    @Value("${common.datacenter.id}")
    private Integer datacenterId;
    @Value("${common.batch}")
    private Integer batch;
    @Value("${quartz.server}")
    private String quartzServer;
}
