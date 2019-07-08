package com.jie.controller;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


import com.alibaba.fastjson.JSONObject;
@Controller
@RequestMapping("/file")
public class FileController {
    Logger logger=Logger.getLogger(FileController.class);
    @RequestMapping("/uploadMultipartFile")
    public ModelAndView uploadMultipartFile(MultipartFile file){
        ModelAndView mv=new ModelAndView();
        mv.setView(new MappingJackson2JsonView());
        String fileName=file.getOriginalFilename();
        file.getContentType();
        File dest=new File("d:/upload.jpg");
        try{
            file.transferTo(dest);
            mv.addObject("success",true);
            mv.addObject("msg","上传文件成功");
            mv.addObject("uri",dest.getAbsolutePath());
        }catch (IllegalStateException| IOException e){
            mv.addObject("success",false);
            mv.addObject("msg","上传文件失败");
            e.printStackTrace();
        }
        return mv;
    }

    @RequestMapping(value="/uploadFile.do" ,produces="text/html;charset=utf-8" )
    public @ResponseBody JSONObject importPicFile1(@RequestParam MultipartFile file1, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        JSONObject jsonObject=new JSONObject();
        if (file1.isEmpty()) {
            //map.put("result", "error");
           // map.put("msg", "上传文件不能为空");
            jsonObject.put("result", "error");
            jsonObject.put("msg", "上传文件不能为空");
            jsonObject.put("status",0);
        } else {
            String originalFilename = file1.getOriginalFilename();
            String fileBaseName = FilenameUtils.getBaseName(originalFilename);
            Date now = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String floderName = fileBaseName + "_" + df.format(now);
            try {
                //创建要上传的路径
                File fdir = new File("D:/newLab/users");
                if (!fdir.exists()) {
                    fdir.mkdirs();
                }
                //文件上传到路径下
                FileUtils.copyInputStreamToFile(file1.getInputStream(), new File(fdir, originalFilename));
                //coding
                jsonObject.put("result", "success");
            } catch (Exception e) {
                jsonObject.put("result", "error");
                jsonObject.put("msg", e.getMessage());
            }
        }
        return jsonObject;
    }

    @ResponseBody
    @RequestMapping(value = "/downloadFile",method = RequestMethod.POST)
    public void  getFile(String relativePath,HttpServletResponse response){
        try {    //relativePath='用户id/images/xxx.xx'

            String rootPath="D:/newLab/";//总目录
            String s[]=relativePath.split("/");
            String parentPath=null;
            logger.info(s[0]+s[1]+s[2]);
            if(s[1].equals("images"))
                parentPath =rootPath+s[0]+s[1];//根目录+自定义目录
            File dirFile = new File(parentPath);
            if(!dirFile.exists()){//创建目录
                dirFile.mkdirs();
            }
            File file = new File(parentPath+s[2]);//文件的地址
            if(file!=null) return;
            InputStream fis = new BufferedInputStream(new FileInputStream(file));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
           response.reset();
            // 设置response的Header
            response.addHeader("Content-Length", "" + file.length());
            //根据文件类型设置contentType
            String suffix=file.getName().substring(file.getName().lastIndexOf(".") + 1);
            if( suffix.equals("jpg"))
                 response.setContentType("image/jpg");
            else if(suffix.equals("png"))
                 response.setContentType("image/png");
           OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    @ResponseBody
    @RequestMapping(value = "/downloadImage",method = RequestMethod.GET)
    public void  getImage(String fileName,int userId,HttpServletResponse response) {
          String relativePath=userId+"/images/"+fileName;//图片存放的目录
          logger.info(userId+"传入的文件名"+fileName);
          getFile(relativePath,response);
    }

    @ResponseBody
    @RequestMapping(value = "/downloadUserImage.do",method = RequestMethod.GET,produces = MediaType.IMAGE_PNG_VALUE)
    public BufferedImage getUserImage(String fileName, int userId, HttpServletResponse response) throws IOException {
        //完整的路径D:/newLab/files/users/10001/images/userPortrait.png
        String relativePath="users/"+userId+"/images/"+fileName;//图片存放的目录
        logger.info(userId+"传入的文件名"+fileName);
        return ImageIO.read(new FileInputStream(new File("D:/newLab/files/users/"+userId+"/images/"+fileName)));
    }
}