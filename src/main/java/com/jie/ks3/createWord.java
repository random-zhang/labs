package com.jie.ks3;

import com.lowagie.text.*;
import com.lowagie.text.rtf.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class createWord {
    public static void exportDocT(String imagePath,float a1, float a2, float a3, float a4,long time1,long time2,long time3,long time4) throws DocumentException, IOException {

        // 1.1创建 Document对象
        Document document = new Document(PageSize.A4);
        Date date=new Date();
        SimpleDateFormat  f=new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
        String str=f.format(date);
        File file = new File("D:\\ks\\"+str+".doc");
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        //1.2读入文件到内存
        RtfWriter2.getInstance(document, new FileOutputStream(file));
        document.open();
        // 标题字体风格
        Font font = new Font();
        //2 段落
        Paragraph title = new Paragraph("详细数据文档");
        // 设置标题格式对齐方式
        title.setAlignment(Element.ALIGN_CENTER);
        title.setFont(font);
        //写入
        document.add(title);
        Image img1 = Image.getInstance(imagePath);//图片--路径方式写入
        document.add(img1);
        String contextString ="10000次下各算法效率\n"+
                "初始道号 1\n"+
                "终止道号 300\n"+
                "每次请求数10\n"+
                "请求号随机\n"+
                "先来先服务算法的平均寻道数为: "+a1+"       用时   "+time1+"毫秒\n"+
                "最短寻道时间算法的平均寻道数为: "+a2+"       用时   "+time2+"毫秒\n"+
                "电梯调度算法的平均寻道数为: "+a3+"       用时   "+time3+"毫秒\n"+
                "单向扫描算法的平均寻道数为: "+a4+"       用时   "+time4+"毫秒\n"
                ;

        Paragraph context = new Paragraph(contextString);
        // 正文格式左对齐
        context.setAlignment(Element.ALIGN_LEFT);
        // 离上一段落（标题）空的行数
        context.setSpacingBefore(5);
        // 设置第一行空的列数
        context.setFirstLineIndent(20);
        document.add(context);
        //关闭
        document.close();
    }
}
