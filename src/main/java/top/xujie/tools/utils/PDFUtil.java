package top.xujie.tools.utils;

import com.sun.javafx.geom.Rectangle;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentNameDestinationDictionary;
import org.apache.pdfbox.text.PDFTextStripper;

import javax.swing.text.Document;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author xujie
 */
public class PDFUtil {

    public static void READPDF(String inputFile){
        //创建文档对象
        PDDocument doc =null;
        String content="";
        try {
            doc =PDDocument.load(new File(inputFile));
            PDFTextStripper textStripper =new PDFTextStripper();
            content=textStripper.getText(doc);
            System.out.println("内容:"+content);
            System.out.println("全部页数"+doc.getNumberOfPages());
            doc.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }


    public static void main(String[] args) {
        READPDF("C:\\Users\\huiyu\\Desktop/smn-sms_errorcode.pdf");
    }
}
