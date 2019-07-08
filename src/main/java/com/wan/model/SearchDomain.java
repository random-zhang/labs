package com.wan.model;

/**
 * Created by Administrator on 2017/11/8.
 */
public class SearchDomain {
    private String text;
    private String texTime;
    private String type;

    public SearchDomain() {
    }

    public SearchDomain(String text, String texTime, String type) {
        this.text = text;
        this.texTime = texTime;
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTexTime() {
        return texTime;
    }

    public void setTexTime(String texTime) {
        this.texTime = texTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
