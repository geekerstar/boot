package com.geekerstar.function.okhttp;

import com.google.common.collect.Maps;
import io.github.admin4j.http.core.Pair;
import io.github.admin4j.http.util.HttpJsonUtil;
import io.github.admin4j.http.util.HttpUtil;
import okhttp3.Response;
import org.junit.Test;

import java.util.Map;

/**
 * @author geekerstar
 * @date 2023/5/15 20:43
 * https://mp.weixin.qq.com/s/C08vPlXdk_M9t36M4A7Aww
 */
public class OkHttpUtilTest {

    @Test
    public void get(){
        Response response = HttpUtil.get("https://github.com/search", Pair.of("q", "okhttp"));
        System.out.println("response = " + response);
    }

    @Test
    public void post(){
        // JSON 格式的body
        Response post = HttpUtil.post("https://oapi.dingtalk.com/robot/send?access_token=27f5954ab60ea8b2e431ae9101b1289c138e85aa6eb6e3940c35ee13ff8b6335", "{\"msgtype\": \"text\",\"text\": {\"content\":\"【反馈提醒】我就是我, 是不一样的烟火\"}}");
        System.out.println("post = " + post);

        // form 请求
        Map<String, Object> formParams = Maps.newHashMap();
        formParams.put("username", "admin");
        formParams.put("password", "admin123");
        Response response = HttpUtil.postForm("http://192.168.1.13:9100/auth/login",
                formParams
        );
        System.out.println("response = " + response);

        Map<String, Object> stringObjectMap = HttpJsonUtil.get("https://github.com/search",
                Pair.of("q", "http"),
                Pair.of("username", "agonie201218"));
        System.out.println("object = "+stringObjectMap);
    }
}
