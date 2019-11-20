<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, javax.mail.*, javax.mail.internet.*" %>
<%@ page import="org.springframework.mail.javamail.MimeMessageHelper" %>
<%@ page import="com.jspsmart.upload.*" %>
<%@ page import="javax.activation.DataSource" %>
<%@ page import="javax.activation.FileDataSource" %>
<%@ page import="javax.activation.DataHandler" %>
<%
    try{
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        SmartUpload upload = new SmartUpload();
        upload.initialize(pageContext);
        upload.setCharset("utf-8");
        //upload.setAllowedFilesList("jpg,jpeg,bmp,png");
        upload.upload();
        upload.save("jspStudy/images");
        String from=upload.getRequest().getParameter("from");
        String to=upload.getRequest().getParameter("to");
        String subject=upload.getRequest().getParameter("subject");
        String messageText=upload.getRequest().getParameter("content");
        String password=upload.getRequest().getParameter("password");
        File file =upload.getFiles().getFile(0);//获取上传的附件
        //生成smtp主机名称
        int n =from.indexOf('@');
        int m=from.length() ;
        String mailserver ="smtp."+from.substring(n+1,m);
        //建立邮件对话
        Properties pro=new Properties();
        pro.put("mail.smtp.host",mailserver);
        pro.put("mail.smtp.auth","true");
        Session sess=Session.getInstance(pro);
        sess.setDebug(true);
        //ᯠ新建一个消息对象
        MimeMessage message=new MimeMessage(sess);
        //设置发件人
        InternetAddress from_mail=new InternetAddress(from);
        message.setFrom(from_mail);
        //设置收件人
        InternetAddress to_mail=new InternetAddress(to);
        message.setRecipient(Message.RecipientType.TO ,to_mail);
        //设置主题
        message.setSubject(subject);
        //设置内容
        //创建消息部分
        BodyPart messageBodyPart = new MimeBodyPart();
        //填充消息
        messageBodyPart.setText(messageText);
        //创建多媒体消息
        Multipart multipart = new MimeMultipart();
        //设置文本消息部分
        multipart.addBodyPart(messageBodyPart);
        //message.setText(messageText);
        //设置发送时间
        message.setSentDate(new Date());
        //发送附件
        //message.add
        messageBodyPart = new MimeBodyPart();
        //String filename =“file.txt”;
        //DataSource source = new FileDataSource("../images/"+file.getFileName());
        DataSource source = new FileDataSource("D:/google download/ssm/lab/target/lab/jspStudy/images/"+file.getFileName());
        out.print(file.getFileName());
        //out.print(file.getFileExt());
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(file.getFileName());
        multipart.addBodyPart(messageBodyPart);
        //MimeMessageHelper helper = new MimeMessageHelper(message,true,"utf-8");
        //helper.addAttachment(file.getFileName(),new java.io.File("../../../images/"+"aa.png"));
        //out.print(file.getFileName());
        //发送邮件
        //发送完整消息
        message.setContent(multipart);
        message.saveChanges(); //保证报头域和会话内容一致
        Transport transport =sess.getTransport("smtp");
        transport.connect(mailserver,from,password);
        transport.sendMessage(message,message.getAllRecipients());
        transport.close();
       // out.println("<script language='javascript'> alert('邮件已发送'); window.location.href='../mail.html';</script>");
        out.println("<script language='javascript'> alert('邮件已发送'); </script>");
    }catch(Exception e){
        e.printStackTrace();
        System.out.println("发送邮件产生的错误"+e.getMessage());
        //out.println("<script language='javascript'>alert('邮件发送失败');history.go(-1); </script>");
        out.println("<script language='javascript'> alert('失败'); </script>");
    }
%>