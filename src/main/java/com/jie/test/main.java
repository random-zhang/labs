package com.jie.test;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.jie.bean.User;

public class main {

    public static void main(String[] args) {
        String str="{\"userName\":\"tom\",\"userPassword\":\"zqt19980805\",\"userPhone\":\"17551020806\"}";
        User  user= JSONObject.parseObject(str, new TypeReference<User>(){});
    }

}
