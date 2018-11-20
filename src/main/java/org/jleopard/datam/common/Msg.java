/**
 * @author (c) 2018,
 * @date 2018/11/16  16:40
 * @version 1.0
 *
 * <p>
 * Find a way for success and not make excuses for failure.
 * </p>
 */
package org.jleopard.datam.common;

import java.util.HashMap;
import java.util.Map;

public class Msg {

    /**
     * 响应状态码  成功：200  失败：500  自定义消息：100
     */
    private int code;

    private String msg;  //提示信息

    /**
     * map存储返回给浏览器的数据
     */
    private Map<String, Object> data = new HashMap<String, Object>();

    public static Msg msg(String msg) {

        return new Msg(100, msg);
    }

    public static Msg msg(int code, String msg) {

        return new Msg(code, msg);
    }
    /**
     * 处理成功的返回信息
     *
     * @return
     */
    public static Msg success() {

        return new Msg(200, "成功！");
    }

    /**
     * 响应处理失败的信息
     *
     * @return
     */
    public static Msg fail() {

        return new Msg(500, "服务器异常！");
    }

    /**
     * 添加返回浏览器的数据
     *
     * @param key
     * @param value
     *
     * @return
     */
    public Msg put(String key, Object value) {
        this.getData().put(key, value);
        return this;
    }

    private Msg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Msg() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
