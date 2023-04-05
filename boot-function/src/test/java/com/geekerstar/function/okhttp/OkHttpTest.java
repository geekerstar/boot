package com.geekerstar.function.okhttp;

import okhttp3.*;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author geekerstar
 * @date 2023/4/5 15:58
 */
public class OkHttpTest {

    /**
     * get 同步请求
     */
    @Test
    public void get(){
        String url = "https://www.baidu.com/";

        OkHttpClient client = new OkHttpClient();
        // 配置GET请求
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        // 发起同步请求
        try (Response response = client.newCall(request).execute()){
            // 打印返回结果
            System.out.println(response.body().string());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * post 表单同步请求
     */
    @Test
    public void post(){
        String url = "https://www.baidu.com/";

        OkHttpClient client = new OkHttpClient();
        // 配置 POST + FORM 格式数据请求
        RequestBody body = new FormBody.Builder()
                .add("userName", "zhangsan")
                .add("userPwd", "123456")
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        // 发起同步请求
        try (Response response = client.newCall(request).execute()){
            // 打印返回结果
            System.out.println(response.body().string());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * post 表单 + 文件上传，同步请求
     */
    @Test
    public void upload(){
        String url = "https://www.baidu.com/";

        OkHttpClient client = new OkHttpClient();

        // 要上传的文件
        File file = new File("/doc/Downloads/429545913565844e9b26f97dbb57a1c3.jpeg");
        RequestBody fileBody = RequestBody.create(MediaType.parse("image/jpg"), file);

        // 表单 + 文件数据提交
        RequestBody multipartBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("userName", "zhangsan")
                .addFormDataPart("userPwd", "123456")
                .addFormDataPart("userFile", "00.png", fileBody)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(multipartBody)
                .build();

        // 发起同步请求
        try (Response response = client.newCall(request).execute()){
            // 打印返回结果
            System.out.println(response.body().string());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * post + json 数据，同步请求
     */
    @Test
    public void postJson(){
        MediaType contentType = MediaType.get("application/json; charset=utf-8");
        String url = "https://www.baidu.com/";
        String json = "{}";

        OkHttpClient client = new OkHttpClient();
        // 配置 POST + JSON 请求
        RequestBody body = RequestBody.create(contentType, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        // 发起同步请求
        try (Response response = client.newCall(request).execute()){
            // 打印返回结果
            System.out.println(response.body().string());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void download(){
        //目标存储文件
        String targetFile = "/doc/Downloads/1.png";
        //需要下载的原始文件
        String url = "https://www.baidu.com/img/PCtm_d9c8750bed0b3c7d089fa7d55720d6cf.png";

        OkHttpClient client = new OkHttpClient();
        // 配置GET请求
        Request request = new Request.Builder()
                .url(url)
                .build();

        // 发起同步请求
        try (Response response = client.newCall(request).execute()){
            // 获取文件字节流
            byte[] stream = response.body().bytes();
            // 写入目标文件
            writeFile(targetFile, stream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 写入目标文件
     * @param targetFile
     * @param stream
     * @throws IOException
     */
    private static void writeFile(String targetFile, byte[] stream) throws IOException {
        String filePath = StringUtils.substringBeforeLast(targetFile, "/");
        Path folderPath = Paths.get(filePath);
        if(!Files.exists(folderPath)){
            Files.createDirectories(folderPath);
        }
        Path targetFilePath = Paths.get(targetFile);
        if(!Files.exists(targetFilePath)){
            Files.write(targetFilePath, stream, StandardOpenOption.CREATE);
        }
    }

    /**
     * 自定义添加请求头部
     */
    @Test
    public void custom(){
        MediaType contentType = MediaType.get("application/json; charset=utf-8");
        String url = "https://www.baidu.com/";
        String json = "{}";

        OkHttpClient client = new OkHttpClient();

        // 配置 header 头部请求参数
        Headers headers = new Headers.Builder()
                .add("token", "11111-22222-333")
                .build();

        // 配置 POST + JSON 请求
        RequestBody body = RequestBody.create(contentType, json);
        Request request = new Request.Builder()
                .url(url)
                .headers(headers)
                .post(body)
                .build();

        // 发起同步请求
        try (Response response = client.newCall(request).execute()){
            // 打印返回结果
            System.out.println(response.body().string());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发起异步请求
     */
    @Test
    public void async(){
        String url = "https://www.baidu.com/";
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        // 发起异步请求
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("请求异常 + " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("请求完成，返回结果：" + response.body().string());
            }
        });
    }
}
