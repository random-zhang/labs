package com.jie.controller;

import com.jie.service.UserService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;


import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/file")
public class FileController {
    Logger logger = Logger.getLogger(FileController.class);
 @Autowired
    UserService userService=null;
    @RequestMapping(value = "/uploadMultipartFile.json", method = RequestMethod.POST)
    public ModelAndView uploadMultipartFile(MultipartFile file) {
        ModelAndView mv = new ModelAndView();
        mv.setView(new MappingJackson2JsonView());
        String fileName = file.getOriginalFilename();
        file.getContentType();
        File dest = new File("d:/upload.jpg");
        try {
            file.transferTo(dest);
            mv.addObject("success", true);
            mv.addObject("msg", "上传文件成功");
            mv.addObject("uri", dest.getAbsolutePath());
        } catch (IllegalStateException | IOException e) {
            mv.addObject("success", false);
            mv.addObject("msg", "上传文件失败");
            e.printStackTrace();
        }
        return mv;
    }

    @RequestMapping(value = "/uploadFile.do", produces = "text/html;charset=utf-8")
    public @ResponseBody
    JSONObject importPicFile1(@RequestParam MultipartFile file1, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        JSONObject jsonObject = new JSONObject();
        if (file1.isEmpty()) {
            //map.put("result", "error");
            // map.put("msg", "上传文件不能为空");
            jsonObject.put("result", "error");
            jsonObject.put("msg", "上传文件不能为空");
            jsonObject.put("status", 0);
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
    @RequestMapping(value = "/downloadFile", method = RequestMethod.POST)
    public void getFile(String relativePath, HttpServletResponse response) {
        try {    //relativePath='用户id/images/xxx.xx'

            String rootPath = "D:/newLab/";//总目录
            String s[] = relativePath.split("/");
            String parentPath = null;
            logger.info(s[0] + s[1] + s[2]);
            if (s[1].equals("images"))
                parentPath = rootPath + s[0] + s[1];//根目录+自定义目录
            File dirFile = new File(parentPath);
            if (!dirFile.exists()) {//创建目录
                dirFile.mkdirs();
            }
            File file = new File(parentPath + s[2]);//文件的地址
            if (file != null) return;
            InputStream fis = new BufferedInputStream(new FileInputStream(file));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Length", "" + file.length());
            //根据文件类型设置contentType
            String suffix = file.getName().substring(file.getName().lastIndexOf(".") + 1);
            if (suffix.equals("jpg"))
                response.setContentType("image/jpg");
            else if (suffix.equals("png"))
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
    @RequestMapping(value = "/downloadImage", method = RequestMethod.GET)
    public void getImage(String fileName, int userId, HttpServletResponse response) {
        String relativePath = userId + "/images/" + fileName;//图片存放的目录
        logger.info(userId + "传入的文件名" + fileName);
        getFile(relativePath, response);
    }

    @ResponseBody
    @RequestMapping(value = "/downloadUserPortrait.json",  method =RequestMethod.POST)
    public boolean getUserImage(@RequestBody Map map, HttpServletResponse response) throws IOException {
        //完整的路径D:/newLab/userData/userId/userInfo/portrait.png
        int userId=(int)map.get("userId");
        String relativePath = "userData/" + userId + "/userInfo/portrait.png" ;//图片存放的目录
        //将图片输出给浏览器

        try{
            BufferedImage image =ImageIO.read(new FileInputStream(new File("D:/newLab/"+relativePath)));
            response.setContentType("image/png");
            OutputStream os = response.getOutputStream();
            ImageIO.write(image, "png", os);
        }catch (Exception e){
            return false;
        }
        return true;

    }
    //头像保存在 newLab/userData/userId/userInfo/portrait.png
    @ResponseBody
    @RequestMapping(value = "/updateUserPortrait", method = {RequestMethod.POST, RequestMethod.GET})
    public boolean updateUserPortrait(HttpServletRequest request, @RequestParam(value = "pictures", required = false) List<MultipartFile> listFile) {
        System.out.println(request.getParameter("userId"));
        String url=request.getParameter("userId")+"/userInfo/portrait.png";
        ArrayList<String> absolutePaths=updateUserFile(url,listFile);//只有一个文件  故
        String absolutePath=null;
        if (absolutePaths!=null){
            if(absolutePaths.size()>0){
                absolutePath=absolutePaths.get(0);
            }
        }
        if (absolutePath!=null&&!absolutePath.equals("")){
            //上传到数据库

        }
    return true;
    }
    public ArrayList<String> updateUserFile(String url,@RequestParam(value = "pictures", required = false) List<MultipartFile> listFile){
        String urlNode="userData/";
        url=urlNode+url;
        return  updateFile(url,listFile);
    }
    @RequestMapping(value = "/updateFile.json", method = {RequestMethod.POST, RequestMethod.GET})
    public ArrayList<String> updateFile(String url, @RequestParam(value = "pictures", required = false) List<MultipartFile> listFile) {
       String urlRoot="D:newLab/";
       url=urlRoot+url;
       ArrayList<String> absolutePath=new ArrayList<>();
        if (listFile.size() > 0) {
            for (int i = 0; i < listFile.size(); i++) {
                if (!listFile.get(i).isEmpty()) {
                    // 文件保存路径
                    File file=new File(url);
                    if (!file.getParentFile().exists()) {
                        file.getParentFile().mkdirs();
                    }
                    // 转存文件
                    try {
                     listFile.get(i).transferTo(file);
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                        return null;
                    } catch (IOException e) {
                        e.printStackTrace();
                        return null;
                    }
                     absolutePath.add(file.getAbsolutePath());
                }
            }
        }
        return  absolutePath;
    }
}
