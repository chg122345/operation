/**
 * @author (c) 2018,
 * @date 2018/11/18  11:36
 * @version 1.0
 *
 * <p>
 * Find a way for success and not make excuses for failure.
 * </p>
 */
package org.jleopard.datam.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Slf4j
public class FileUtils {

    /**
     * 保存上传文件
     * @param file
     */
    public static String uploadFile(MultipartFile file){
        if (file.isEmpty()) {
            log.info("上传的文件为空");
            throw new RuntimeException("文件为空");
        }
        String fileName = file.getOriginalFilename();
        String filePath = PathUtils.getBasePath();
        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
            log.info("上传成功");
        } catch (IOException e) {
            e.printStackTrace();
            log.error("上传失败", e);
        }
        return fileName;
    }
    public static void saveFile(MultipartFile... files){
        if (files.length == 0){
           return;
        }
        for(MultipartFile file:files){
            if (file.isEmpty()) {
                log.info("上传的文件为空");
               throw new RuntimeException("文件为空");
            }
            String fileName = file.getOriginalFilename();
            String filePath = PathUtils.getBasePath();
            File dest = new File(filePath + fileName);
            try {
                file.transferTo(dest);
                log.info("上传成功");
            } catch (IOException e) {
                e.printStackTrace();
                log.error("上传失败", e);
            }
        }
    }

    public static void deleteFile(String path){
        int lastIndexOf = path.lastIndexOf("/");
        String sb = path.substring(lastIndexOf + 1, path.length());
        sb = PathUtils.getBasePath() + sb;
        File file = new File(sb);
        if (file.exists()) {
            if (file.delete()) {
                log.info("删除成功");
            } else {
                log.info("删除失败");
            }
        }
    }
}
