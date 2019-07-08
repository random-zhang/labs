package com.jie.Enum;

import java.util.HashMap;
import java.util.Map;

public enum ReturnTypeEnum {

    URL("url"),
    STREAM("stream"),
    BASE64("base");

    private String type;

    ReturnTypeEnum(String type) {
        this.type = type;
    }


    private static Map<String, ReturnTypeEnum> map;

    static {
        map = new HashMap<>(3);
        for(ReturnTypeEnum e: ReturnTypeEnum.values()) {
            map.put(e.type, e);
        }
    }

    public static ReturnTypeEnum getEnum(String type) {
        if (type == null) {
            return URL;
        }

        ReturnTypeEnum e = map.get(type.toLowerCase());
        return e == null ? URL : e;
    }
}