package jspStudy.test6;

import jspStudy.Book;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Logger;
@WebServlet("/BookAddServlet")
public class  BookAddServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ServletConfig config;
    Logger logger=Logger.getLogger("tom");
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.config = config;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
       /* SmartUpload upload = new SmartUpload();
        upload.initialize(config, request, response);
        upload.setCharset("utf-8");
        upload.setAllowedFilesList("jpg,jpeg,bmp,png");
        try {
            upload.upload();
            upload.save("images");
        } catch (SmartUploadException e) {
            System.err.println(e.getMessage());
        }
        String bookNo = upload.getRequest().getParameter("bookNo");
        String bookName = upload.getRequest().getParameter("bookName");
        String author = upload.getRequest().getParameter("author");
        String press = upload.getRequest().getParameter("press");
        String pressDate = upload.getRequest().getParameter("pressDate");
        String bookNum = upload.getRequest().getParameter("bookNum");
        String orgPrice = upload.getRequest().getParameter("orgPrice");
        String nowPrice = upload.getRequest().getParameter("nowPrice");
        File file = upload.getFiles().getFile(0);
        */
        if (!ServletFileUpload.isMultipartContent(request)) {
            throw new RuntimeException("不是文件上传表单！");
        }
        FileItemFactory factory=new DiskFileItemFactory();
        ServletFileUpload upload=new ServletFileUpload(factory);
        upload.setHeaderEncoding("UTF-8");
        Book book = new Book();
        try {
            List<FileItem> fileItems=upload.parseRequest(request);
            for (FileItem fileItem:fileItems){
                if(fileItem.isFormField()){//普通表单
                  String name=fileItem.getFieldName();
                  String value = fileItem.getString("UTF-8");
                  switch (name){
                      case "bookNo":{
                          book.setBookNo(value);
                          break;
                      }
                      case "bookName":{
                          book.setBookName(value);
                          break;
                      }
                      case "author":{
                          book.setAuthor(value);
                          logger.info(value);
                          System.out.println(value);
                          break;
                      }
                      case "pressDate":{
                          book.setPressDate(value);
                          break;
                      }
                      case "press":{
                          book.setPress(value);
                          break;
                      }
                      case "bookNum":{
                          int num;
                          if (value== null || value.trim().equals(""))
                              num = 0;
                          else
                              num = Integer.parseInt(value);
                          book.setBookNum(num);
                          break;
                      }
                      case "nowPrice":{
                          float nPrice;
                          if (value == null ||value.trim().equals(""))
                              nPrice = 0;
                          else
                              nPrice = Float.parseFloat(value);
                          book.setNowPrice(nPrice);
                          break;
                      }
                      case "orgPrice":{
                          float oPrice;
                          book.setBookName(value);
                          if (value == null ||value.trim().equals(""))
                              oPrice = 0;
                          else
                              oPrice = Float.parseFloat(value);
                          book.setOrgPrice(oPrice);
                          break;
                      }
                  }
                }else{
                     String fileName = fileItem.getName();
                    fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
                    book.setBookCover(fileName);
                    InputStream is = fileItem.getInputStream();    //获得输入流，
                    String parentDir = this.getServletContext().getRealPath("jspStudy/images");
                    File file = new File(parentDir,fileName);
                    if(! file.getParentFile().exists()){  //父目录不存在
                        file.getParentFile().mkdirs();        //mkdirs():创建文件夹，如果上级目录没有的话，也一并创建出来。
                    }
                    FileOutputStream out = new FileOutputStream(file);
                    byte[] buf = new byte[1024];
                    int len = -1;
                    while( (len = is.read(buf)) != -1){
                         out.write(buf, 0, len);
                                     }
                    out.close();
                    is.close();
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        BookDB bookDb = new BookDB();
        bookDb.addBook(book);
        bookDb.close();
        response.sendRedirect("BookDisplayServlet");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        doGet(request, response);
    }
}