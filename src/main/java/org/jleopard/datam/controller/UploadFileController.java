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
import org.jleopard.datam.dao.UserMapper;
import org.jleopard.datam.model.UploadFile;
import org.jleopard.datam.model.User;
import org.jleopard.datam.util.FileUtils;
import org.jleopard.datam.util.PathUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UploadFileController {

    @Autowired
    private UploadFileMapper uploadFileMapper;

    @Autowired
    private UserMapper userMapper;

    @SysLog("上传附件")
    @PostMapping("/uploadFile")
    public Map upload(MultipartFile file) {
        String flileName = FileUtils.uploadFile(file);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "上传成功");
        map.put("data", flileName);
        return map;
    }

    @SysLog("去上文件信息")
    @PostMapping("/upload")
    public Msg upload(HttpSession session, HttpServletRequest request, UploadFile uploadFile) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return Msg.msg("请先登录再上传");
        }
        String remoteAddr = request.getRemoteAddr();
        uploadFile.setUserIp(remoteAddr);
        uploadFile.setUserId(user.getId());
        try {
            int temp = uploadFileMapper.insert(uploadFile);
            if (temp > 0) {
                return Msg.success();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.fail();
        }
        return Msg.msg("上传失败");
    }

    @SysLog("删除文件操作")
    @PostMapping("/fileDelete")
    public Msg delete(HttpSession session, @RequestParam("id") Integer id, @RequestParam("userId") String userId) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return Msg.msg("请先登录再操作");
        }
        if (userId.equalsIgnoreCase(user.getId()) || user.getRole() == 1) {
            try {
                int temp = uploadFileMapper.deleteByPrimaryKey(id);
                if (temp > 0) {
                    return Msg.success();
                }
            } catch (Exception e) {
                e.printStackTrace();
                return Msg.fail();
            }
        }
        return Msg.msg("没有权限操作");
    }

    @SysLog("修改文件信息")
    @PostMapping("/fileUpdate")
    public Msg update(HttpSession session, UploadFile uploadFile) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return Msg.msg("请先登录再操作");
        }
        if (uploadFile.getUserId().equalsIgnoreCase(user.getId()) || user.getRole() == 1) {
            try {
                int temp = uploadFileMapper.updateByPrimaryKey(uploadFile);
                if (temp > 0) {
                    return Msg.success();
                }
            } catch (Exception e) {
                e.printStackTrace();
                return Msg.fail();
            }
        }
        return Msg.msg("没有权限操作");
    }

    @SysLog("文件下载")
    @RequestMapping("/fileDownload")
    public ResponseEntity download(@RequestParam("fileName") String fileName, @RequestParam(value = "id",required = false) Integer id) {
        String path=PathUtils.getBasePath();
        File f=new File(path+fileName);
        InputStream in;
        ResponseEntity<byte[]> response=null ;
        try {
            in = new FileInputStream(f);
            byte[] b=new byte[in.available()];
            in.read(b);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment;filename="+fileName);
            HttpStatus statusCode= HttpStatus.OK;
            response = new ResponseEntity<byte[]>(b, headers, statusCode);
            in.close();
            uploadFileMapper.updateViewCountByPrimaryKey(id);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
}

