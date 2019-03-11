package cc.edt.frame.common.controller;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Maps;

import cc.edt.frame.common.constant.CommonDictionary;
import cc.edt.frame.common.constant.SystemConstant;
import cc.edt.frame.common.result.ActionResult;
import cc.edt.iceutils3.date.IceDateFormatUtils;
import cn.hutool.core.util.IdUtil;

/**
 * 文件上传
 *
 * @author 刘钢 2017/12/18 10:41
 */
@Controller
@RequestMapping("/upload")
public class UploadController extends BaseController {
    @Resource
    private CommonDictionary commonDictionary;

    /**
     * 文件上传方法
     *
     * @param file file
     * @author 刘钢
     * @date 2017/5/28 23:29
     */
    @RequestMapping("uploadFileAction")
    @ResponseBody
    public void uploadFile(@RequestParam("file") MultipartFile file) {
        ActionResult<String, String> actionResult = new ActionResult<>();
        // 文件后缀
        String fileMark = StringUtils.substring(file.getOriginalFilename(),
                StringUtils.lastIndexOf(file.getOriginalFilename(), "."));
        // 文件名称
        String fileName = IdUtil.simpleUUID();
        // 文件目录
        String fileDir = IceDateFormatUtils.getNowDateTime("yyyyMMdd");
        // 文件磁盘
        String fileDisk = commonDictionary.getUploadPathDisk();
        // 文件全路径
        String filePath = fileDisk + "/" + fileDir + "/" + fileName + fileMark;

        File saveFile = new File(filePath);
        if (!saveFile.getParentFile().exists()) {
            boolean isDel = saveFile.getParentFile().mkdirs();
        }
        try {
            file.transferTo(saveFile);
            actionResult.getStatus().setSuccess(true);
            actionResult.setResult(fileDir + "/" + fileName + fileMark);
            writerToPageByJson(actionResult);
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * UE文件上传方法
     *
     * @param file file
     * @author 刘钢
     * @date 2017/5/28 23:31
     */
    @RequestMapping("uploadUEFile")
    @ResponseBody
    public void uploadUEFile(@RequestParam("file") MultipartFile file) {
        // 文件后缀
        String fileMark = StringUtils.substring(file.getOriginalFilename(),
                StringUtils.lastIndexOf(file.getOriginalFilename(), "."));
        // 文件名称
        String fileName = IdUtil.simpleUUID();
        // 文件目录
        String fileDir = IceDateFormatUtils.getNowDateTime("yyyyMMdd");
        // 文件磁盘
        String fileDisk = commonDictionary.getUploadPathDisk();
        // 文件全路径
        String filePath = fileDisk + "/" + fileDir + "/" + fileName + fileMark;
        Map<String, Object> params = Maps.newHashMap();
        File saveFile = new File(filePath);
        if (!saveFile.getParentFile().exists()) {
            boolean isDel = saveFile.getParentFile().mkdirs();
        }
        try {
            file.transferTo(saveFile);
            params.put("state", SystemConstant.SUCCESS_STRING);
            params.put("url", commonDictionary.getUploadPathWeb() + "/"
                    + fileDir + "/" + fileName + fileMark);
            params.put("size", file.getSize());
            params.put("original", fileName + fileMark);
            params.put("type", file.getContentType());
            writerToPageByJson(params);
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
    }
}
