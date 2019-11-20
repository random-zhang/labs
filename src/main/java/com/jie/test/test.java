package com.jie.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jie.bean.Point;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.*;

public class test {
    int n = 0;

    public void aa() {

    }
    public static void main(String[] q) {

        JSONObject json=new JSONObject();
        json.put("title","22");
        json.put("text","完成加热,更多信息请看历史记录");
        json.put("date","33d");
        HashMap map=JSONObject.parseObject(json.toJSONString(),HashMap.class);

    }
}