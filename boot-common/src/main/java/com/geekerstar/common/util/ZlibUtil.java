package com.geekerstar.common.util;

import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

/**
 * Created by 大雪冬至 on 2021/9/15.
 * JDK - ZLib 压缩算法工具类
 */
@Slf4j
public class ZlibUtil {

    public static void main(String[] args) {
        String content = "{\"Id\":\"ems00002\",\"Tp\":\"data\",\"Ac\":\"5\",\"Na\":\"Protector01\",\"Sn\":\"10008\",\"Pr\":\"modbus/rtu\",\"Sa\":0,\"Ts\":1664313146,\"Dt\":[{\"K\":\"7532\",\"V\":\"32\"},],\"Fd\":50}";
//        int length = content.getBytes(StandardCharsets.UTF_8).length;
//        System.out.println(length);
//        byte[] zlib = ZipUtil.zlib(content, "", 6);
//        System.out.println(zlib.length);
//        String s1 = byte2Hex(zlib);
//        System.out.println(s1);
//        byte[] bytes = ZipUtil.unZlib(zlib);
//        String s = new String(bytes);
//        System.out.println(s);
//        byte[] compress = compress(content.getBytes());
//        String s1 = byte2Hex(compress);
//        String s = decompressStr(compress);
//        System.out.println(s1);
//        System.out.println(s);


        String error = "789c3dcdbb91c2501000b05e882ff0b3f74b6ff4ce5c803265dad7fbfe7b9deae7a243373d149454d434b43f85231ce1084738c2118e7084231ce948473ad2918e74a4231de9484739ca518e7294a31ce5284739cad18e76b4a31ded68473bdad18e768c631ce318c738c6318e718c631ceb58c73ad6b18e75ac631debd8df31d745876e7a2828a9a869c8711cc7711cc771fe8fcfe70b9a24a24e";
        byte[] bytes = hexStr2HexBytes(error);
        String s = decompressStr(bytes);
        System.out.println(s);
    }

    //16进制字符串转为字节数组（两个为一组）
    public static byte[] hexStr2HexBytes(String hexStr) {
        if (null == hexStr || 0 == hexStr.length()) {
            return null;
        }
        hexStr = (hexStr.length() == 1) ? "0" + hexStr : hexStr;
        byte[] arr = new byte[hexStr.length() / 2];
        byte[] tmp = hexStr.getBytes();
        for (int i = 0; i < tmp.length / 2; i++) {
            arr[i] = unitBytes(tmp[i * 2], tmp[i * 2 + 1]);
        }
        return arr;
    }
    //字节数组分组
    private static byte unitBytes(byte src0, byte src1) {
        byte _b0 = Byte.decode("0x" + new String(new byte[]{src0}));
        _b0 = (byte) (_b0 << 4);
        byte _b1 = Byte.decode("0x" + new String(new byte[]{src1}));
        return (byte) (_b0 ^ _b1);
    }


    /**
     * 压缩字符串.
     * 使用 UTF8 字符集解码
     *
     * @param str : 字符串 （UTF-8编码）
     *            待压缩数据
     * @return byte[] 压缩后的数据
     */
    public static byte[] compress(String str) {
        if (str == null || "".equals(str)) {
            return null;
        }
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        return compress(bytes);
    }


    /**
     * 压缩
     *
     * @param data 待压缩数据
     * @return byte[] 压缩后的数据
     */
    public static byte[] compress(byte[] data) {
        byte[] output = new byte[0];
        Deflater compresser = new Deflater();

        compresser.reset();
        compresser.setInput(data);
        compresser.finish();
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream(data.length)) {
            byte[] buf = new byte[1024];
            while (!compresser.finished()) {
                int i = compresser.deflate(buf);
                bos.write(buf, 0, i);
            }
            output = bos.toByteArray();
        } catch (Exception e) {
            output = data;
            log.error("compress: ", e);
        } finally {
            compresser.end();
        }
        return output;
    }

    /**
     * 压缩
     *
     * @param data 待压缩数据
     * @param os   输出流
     */
    public static void compress(byte[] data, OutputStream os) {
        DeflaterOutputStream dos = new DeflaterOutputStream(os);
        try {
            dos.write(data, 0, data.length);

            dos.finish();

            dos.flush();
        } catch (IOException e) {
            log.error("compress: ", e);
        }
    }

    /**
     * 解压缩. (采用UTF-8编码)
     *
     * @param data 待压缩的数据
     * @return String 解压缩后的字符串
     */
    public static String decompressStr(byte[] data) {
        byte[] decompress = decompress(data);
        return new String(decompress, StandardCharsets.UTF_8);
    }

    /**
     * 解压缩
     *
     * @param data 待压缩的数据
     * @return byte[] 解压缩后的数据
     */
    public static byte[] decompress(byte[] data) {
        byte[] output = new byte[0];

        Inflater decompresser = new Inflater();
        decompresser.reset();
        decompresser.setInput(data);

        try (ByteArrayOutputStream o = new ByteArrayOutputStream(data.length)) {
            byte[] buf = new byte[4024];
            while (!decompresser.finished()) {
                int i = decompresser.inflate(buf);
                o.write(buf, 0, i);
            }
            output = o.toByteArray();
        } catch (Exception e) {
            output = data;
            log.error("compress: ", e);
        } finally {
            decompresser.end();
        }

        return output;
    }

    /**
     * 解压缩
     *
     * @param is 输入流
     * @return byte[] 解压缩后的数据
     */
    public static byte[] decompress(InputStream is) {
        InflaterInputStream iis = new InflaterInputStream(is);
        try (ByteArrayOutputStream o = new ByteArrayOutputStream(1024)) {
            int i = 1024;
            byte[] buf = new byte[i];

            while ((i = iis.read(buf, 0, i)) > 0) {
                o.write(buf, 0, i);
            }
            return o.toByteArray();
        } catch (IOException e) {
            log.error("compress: ", e);
        }
        return null;
    }

    public static String byte2Hex(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        StringBuilder builder = new StringBuilder();
        // 遍历byte[]数组，将每个byte数字转换成16进制字符，再拼接起来成字符串
        for (int i = 0; i < bytes.length; i++) {
            // 每个byte转换成16进制字符时，bytes[i] & 0xff如果高位是0，输出将会去掉，所以+0x100(在更高位加1)，再截取后两位字符
            builder.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        return builder.toString();
    }
}

