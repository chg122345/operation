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

    public static void downloadFile(HttpServletRequest request, HttpServletResponse response){
        String fileName = "aim_test.txt";// 设置文件名，根据业务需要替换成要下载的文件名
        if (fileName != null) {
            String realPath = "D://aim//";
            File file = new File(realPath , fileName);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    System.out.println("success");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
