package com.geekerstar.function.javacv;

import lombok.extern.slf4j.Slf4j;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


@SpringBootTest
@Slf4j
class JavaCvTest {

    @Test
    public void test() throws IOException {
        String file = "rtsp://admin:123456@192.168.1.19:554/h264/ch1/main/stream";
        FFmpegFrameGrabber grabber = FFmpegFrameGrabber.createDefault(file);
        grabber.setOption("rtsp_transport", "tcp"); // 使用tcp的方式，不然会丢包很严重
        grabber.setImageWidth(1920);
        grabber.setImageHeight(1080);
        System.out.println("grabber start");
        grabber.start();

        File outPut = new File("C:\\Users\\PC\\Desktop\\1.jpg");
        while (true) {
            Frame frame = grabber.grabImage();
            if (frame != null) {
                ImageIO.write(FrameToBufferedImage(frame), "jpg", outPut);
                grabber.stop();
                grabber.release();
                System.out.println("图片已保存");
                break;
            }

        }

    }

    /**
     * 创建BufferedImage对象
     */
    public static BufferedImage FrameToBufferedImage(Frame frame) {
        Java2DFrameConverter converter = new Java2DFrameConverter();
        BufferedImage bufferedImage = converter.getBufferedImage(frame);
//		bufferedImage=rotateClockwise90(bufferedImage);
        return bufferedImage;
    }

    /**
     * 处理图片，将图片旋转90度。
     */
    public static BufferedImage rotateClockwise90(BufferedImage bi) {
        int width = bi.getWidth();
        int height = bi.getHeight();
        BufferedImage bufferedImage = new BufferedImage(height, width, bi.getType());
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++)
                bufferedImage.setRGB(j, i, bi.getRGB(i, j));
        return bufferedImage;
    }


}

