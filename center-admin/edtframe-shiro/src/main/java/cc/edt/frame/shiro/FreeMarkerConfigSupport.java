package cc.edt.frame.shiro;

import com.jagregory.shiro.freemarker.ShiroTags;
import freemarker.template.TemplateModelException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * FreeMarker支持Shiro标签
 *
 * @author 刘钢
 * @date 2018/6/13 11:26
 */
@Component
public class FreeMarkerConfigSupport {
    @Resource
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @PostConstruct
    public void setSharedVariable() {
        freeMarkerConfigurer.getConfiguration().setSharedVariable("shiro",
                new ShiroTags());
    }
}
