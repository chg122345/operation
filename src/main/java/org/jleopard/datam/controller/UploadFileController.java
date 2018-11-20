/**
 * @author (c) 2018,
 * @date 2018/11/18  10:58
 * @version 1.0
 *
 * <p>
 * Find a way for success and not make excuses for failure.
 * </p>
 */
package org.jleopard.datam.controller;

import org.jleopard.datam.annotation.SysLog;
import org.jleopard.datam.common.Msg;
import org.jleopard.datam.dao.UploadFileMapper;
import org.jleopard.datam.model.UploadFile;
import org.jleopard.datam.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UploadFileController {

    @Autowired
    private UploadFileMapper uploadFileMapper;

    @SysLog("上传附件")
    @PostMapping("/uploadFile")
    public Map upload(MultipartFile file){
        String flileName = FileUtils.uploadFile(file);
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","上传成功");
        map.put("data",flileName);
        return map;
    }

    @SysLog("去上文件信息")
    @PostMapping("/upload")
    public Msg upload(HttpServletRequest request,UploadFile uploadFile){
        String remoteAddr = request.getRemoteAddr();
        uploadFile.setUserIp(remoteAddr);
        try {
            int temp = uploadFileMapper.insert(uploadFile);
            if (temp > 0){
                return Msg.success();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.fail();
        }
        return Msg.msg("上传失败");
    }
}
