package com.jie.bean;

public class BaseResponse {

    /**
     * 返回图片的相对路径
     */
    private String path;


    /**
     * 返回图片的https格式
     */
    private String url;


    /**
     * base64格式的图片
     */
    private String base;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }
}