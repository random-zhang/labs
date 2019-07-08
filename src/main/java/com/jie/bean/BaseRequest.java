package com.jie.bean;

import com.jie.Enum.MediaTypeEnum;
import com.jie.Enum.ReturnTypeEnum;

import java.util.HashMap;
import java.util.Map;

public class BaseRequest {
    private static final long serialVersionUID = 1146303518394712013L;

    /**
     * 输出图片方式:
     *
     *  url : http地址 （默认方式）
     *  base64 : base64编码
     *  stream : 直接返回图片
     *
     */
    private String outType;

    /**
     * 返回图片的类型
     * jpg | png | webp | gif
     */
    private String mediaType;


    public ReturnTypeEnum returnType() {
        return ReturnTypeEnum.getEnum(outType);
    }


    public MediaTypeEnum mediaType() {
        return MediaTypeEnum.getEnum(mediaType);
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getOutType() {
        return outType;
    }

    public void setOutType(String outType) {
        this.outType = outType;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }
}
