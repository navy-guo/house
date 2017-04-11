package com.navy.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by CNW-5roo on 2017/1/17.
 */
@Controller
public class UploadController {

    @Value("${upload.store.dir}")
    private String UPLOAD_STORE_PATH;

    @Value("${upload.tmp.dir}")
    private String UPLOAD_TMP_PATH;

    //获取分隔符（不同系统不同）
    String sep = System.getProperty("file.separator");

    /**
     * plupload 图片上传
     * @param file
     * @return
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value="/upload", method= RequestMethod.POST)
    public void handleFileUpload(HttpServletRequest request, HttpServletResponse response, @RequestParam(value="file",required = false) MultipartFile file) throws IOException {
            try {
//                String uploadPath = "/files/image/";
                String realPath = request.getServletContext().getRealPath(UPLOAD_STORE_PATH);
//                String realPath = (System.getProperty("user.dir") + UPLOAD_STORE_PATH);
//                String showPath = (System.getProperty("user.dir") + UPLOAD_TMP_PATH);
                // 提取文件拓展名
                String fileNameExtension = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."), file.getOriginalFilename().length());
                // 生成实际存储的真实文件名
                String realName = UUID.randomUUID().toString() + fileNameExtension;
                //生成显示文件
                File showFile=new File(realPath + realName);
                file.transferTo(showFile);				//写入文件
                Map<String, Object> m = new HashMap<String, Object>();
                m.put( "status", true );
                m.put( "fileUrl", "/files/" + realName);
                response.getWriter().write( JSON.toJSONString( m ) );
            } catch (Exception e) {
                e.printStackTrace();
            }
    }


    /**
     * wangEdit 图片上传
     * ghj - 2017/2/9
     */
    @RequestMapping("/uploadImg")
    public void uploadImage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("textml;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        Part part = request.getPart("myFileName");// myFileName是文件域的name属性值
        // 文件类型限制
        String[] allowedType = { "image/bmp", "image/gif", "image/jpeg", "image/png" };
        boolean allowed = Arrays.asList(allowedType).contains(part.getContentType());
        if (!allowed) {
            response.getWriter().write("error|不支持的类型");
            return;
        }
        // 图片大小限制
        if (part.getSize() > 5 * 1024 * 1024) {
            response.getWriter().write("error|图片大小不能超过5M");
            return;
        }
        // 包含原始文件名的字符串
        String fi = part.getHeader("content-disposition");
        // 提取文件拓展名
        String fileNameExtension = fi.substring(fi.indexOf("."), fi.length() - 1);
        // 生成实际存储的真实文件名
        String realName = UUID.randomUUID().toString() + fileNameExtension;

        // 图片存放的真实路径
//        String path= File.separator+"files"+File.separator+"image";
//        String uploadPath = "/files/image/";
        String realPath = request.getServletContext().getRealPath(UPLOAD_STORE_PATH) + realName;
        File file = new File(request.getServletContext().getRealPath(UPLOAD_STORE_PATH));
        if (!file.exists())
            file.mkdirs();
        // 将文件写入指定路径下
        part.write(realPath);

        // 返回图片的URL地址
        response.getWriter().write(request.getContextPath() + UPLOAD_STORE_PATH + realName);

    }

}
