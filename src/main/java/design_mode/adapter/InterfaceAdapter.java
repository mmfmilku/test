package design_mode.adapter;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class InterfaceAdapter {

    public static void main(String[] args) {
        try {
            createPdf("F:\\dev\\project\\test\\src\\main\\resources\\test.pdf");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createPdf(String path) throws Exception {
        // 创建文件流
        OutputStream os = new FileOutputStream(new File(path));
        // 创建A4大小的PDF文档
        Document document = new Document(PageSize.A4);
        PdfWriter writer = PdfWriter.getInstance(document, os);
        // 设置自定义回调事件
        writer.setPageEvent(new MyPdfEvent());
        // 打开
        document.open();
        // 添加100行
        int lines = 0;
        while (lines < 100) {
            document.add(new Paragraph("lines:"  + (lines++ + 1)));
        }
        // 操作完成后关闭
        document.close();
        writer.close();
    }

}

// 自定义事件继承PdfPageEventHelper类
class MyPdfEvent extends PdfPageEventHelper {

    @Override
    public void onStartPage(PdfWriter writer, Document document) {
        // 每页被创建时的回调事件，PDF每次新增一个页面时会调用此事件
        try {
            // 添加段落，显示当前页数
            String text = "new page, this is page:" + writer.getPageNumber();
            document.add(new Paragraph(text));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
