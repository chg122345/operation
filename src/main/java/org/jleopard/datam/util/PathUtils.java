/**
 * @author (c) 2018,
 * @date 2018/11/16  16:03
 * @version 1.0
 *
 * <p>
 * Find a way for success and not make excuses for failure.
 * </p>
 */
package org.jleopard.datam.util;


public class PathUtils {
    private static String separator = System.getProperty("file.separator"); // 文件类型

    /**
     * 获取服务器本地路径
     */
    public static String getBasePath(){
        String basePath = "";
        String os=System.getProperty("os.name");    // 获取系统 linux windows
        if (os.toLowerCase().startsWith("win")){
            basePath = "D:/tempFile/";
        } else {
            basePath = "/home/tempFile/";
        }
        basePath = basePath.replace("/",separator);
        return basePath;
    }
}
