package com.geekerstar.function.pdf;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author geekerstar
 * @date 2023/6/18 12:00
 */
public class PdfTest {

    @Test
    public void testPDFBox(){
        try {
            // 读取原始 PDF 文件
            PDDocument document = PDDocument.load(new File("/Users/geekerstar/work/code/java/local/boot/boot-function/src/main/resources/pdf/test.pdf"));

            // 遍历 PDF 中的所有页面
            for (int i = 0; i < document.getNumberOfPages(); i++) {
                PDPage page = document.getPage(i);
                PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true, true);

                // 设置字体和字号
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 36);

                // 设置透明度
                contentStream.setNonStrokingColor(200, 200, 200);

                // 添加文本水印
                contentStream.beginText();
                contentStream.newLineAtOffset(100, 100); // 设置水印位置
                contentStream.showText("Watermark"); // 设置水印内容
                contentStream.endText();

                contentStream.close();
            }

            // 保存修改后的 PDF 文件
            document.save(new File("/Users/geekerstar/work/code/java/local/boot/boot-function/src/main/resources/pdf/output.pdf"));
            document.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testIText(){
        try {
            // 读取原始 PDF 文件
            PdfReader reader = new PdfReader("/Users/geekerstar/work/code/java/local/boot/boot-function/src/main/resources/pdf/test.pdf");
            PdfStamper stamper = new PdfStamper(reader, new FileOutputStream("/Users/geekerstar/work/code/java/local/boot/boot-function/src/main/resources/pdf/output.pdf"));

            // 获取 PDF 中的页数
            int pageCount = reader.getNumberOfPages();

            // 添加水印
            for (int i = 1; i <= pageCount; i++) {
                PdfContentByte contentByte = stamper.getUnderContent(i); // 或者 getOverContent()
                contentByte.beginText();
                contentByte.setFontAndSize(BaseFont.createFont(), 36f);
                contentByte.setColorFill(BaseColor.LIGHT_GRAY);
                contentByte.showTextAligned(Element.ALIGN_CENTER, "IText", 300, 400, 45);
                contentByte.endText();
            }

            // 保存修改后的 PDF 文件并关闭文件流
            stamper.close();
            reader.close();
        } catch (IOException | DocumentException e) {
            throw new RuntimeException(e);
        }
    }
}
