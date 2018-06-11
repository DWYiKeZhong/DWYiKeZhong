package com.example.yikezhong.bean;

/**
 * Created   by   Dewey .
 * 发布 段子
 */
public class FaBiaoDuanBean {

    /**
     * msg : 发布成功
     * code : 0
     */

    private String msg;
    private String code;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "FaBiaoDuanBean{" +
                "msg='" + msg + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
