package cn.tx.controller;

import com.alibaba.fastjson.JSONObject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/upload")
public class UploadController {

    @ResponseBody
    @RequestMapping("/uploadFile")
    //文件名字、文件类型、文件具体文件、请求对象、响应对象
//    public void uploadFile(HttpServletRequest request, HttpServletResponse response, MultipartFile picFile，String lastImg, String fileType) throws IOException {
    //1.处理文件，拿到名字并处理保证名称唯一
    //2.获取该文件Byte字节流
    //3.获取上传位置http：localhost:8084/pic/xxx.jpg
    //4.利用客户端工具和图片工具进行上传
    //5.处理出来后半段命令pic/xxx.jpg
//
//
//
//        //获得文件的原始文件名
//        String originalFilename = multipartFile.getOriginalFilename();
//        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
//        String fileName = UUID.randomUUID().toString();
//        fileName = fileName + suffix;
//
//        //创建jersy的客户端
//        Client client = Client.create();
//
//        //删除老图片
//        if(StringUtils.isNotBlank(lastImage)){
//            WebResource resource1 = client.resource(lastImage);
//            resource1.delete();
//        }
//
//        //resource的参数文件服务器上的文件的绝对路径
//        WebResource resource = client.resource(PropReader.read("filePath")+"/"+type+"/"+fileName);
//        resource.put(bytes);
//
//        JSONObject jo = new JSONObject();
//        jo.put("realPath", PropReader.read("filePath")+"/"+type+"/"+fileName);
//        jo.put("relativePath", "/"+type+"/"+fileName);
//
//        response.getWriter().write(jo.toString());
//    }
    public void upLoadFile(HttpServletRequest request, HttpServletResponse response, MultipartFile picfile,String lastImg, String fileType) throws IOException {
        //1.处理文件，拿到名字并处理保证名称唯一
        //2.获取该文件Byte字节流
        //3.获取上传位置http：localhost:8084/pic/xxx.jpg
        //4.利用客户端工具和图片工具进行上传
        //5.处理出来后半段命令pic/xxx.jpg
        //把Request做强制转换
//        MultipartHttpServletRequest mr = (MultipartHttpServletRequest) request;
//        Map<String, MultipartFile> fileMap = mr.getFileMap();
//        Set<String> keySet = fileMap.keySet();
//        Iterator<String> iterator = keySet.iterator();
//        String key = iterator.next();
//        //获得到上传的文件
//        MultipartFile multipartFile = fileMap.get(key);
        //获取文件字节数组方便上传
        byte[] bytes = picfile.getBytes();
        //获取文件名称
        String filename = picfile.getOriginalFilename();
        String suffix = filename.substring(filename.lastIndexOf("."));
        String fileName = UUID.randomUUID().toString();
        fileName = fileName + suffix;
        String filePath = "http://localhost:8085";
        String realPath = filePath+"/"+fileType+"/" + fileName;
        String relativePath ="/"+fileType+"/" + fileName;

        //创建jersy的客户端
        Client client = Client.create();

        //删除老图片
        if(lastImg != null && !"".equals(lastImg)){
            WebResource resource1 = client.resource(lastImg);
            resource1.delete();
        }

        //resource的参数文件服务器上的文件的绝对路径
        WebResource resource = client.resource(realPath);
        resource.put(bytes);

        JSONObject jo = new JSONObject();
        jo.put("realPath", realPath);
        jo.put("relativePath", relativePath);

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(jo.toString());
    }


    @RequestMapping("/uploadFileMp3")
    public void uploadFileMP3(MultipartFile mp3file,String lastMp3,String fileType,HttpServletRequest request,HttpServletResponse response){
        try {
            String originalFilename = mp3file.getOriginalFilename();
            String fileName = UUID.randomUUID().toString();
            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            fileName=fileName+suffix;
            String filePath="http://localhost:8085";
            String realPath=filePath+"/"+fileType+"/"+fileName;
            String relativePath="/"+fileType+"/"+fileName;

            byte[] bytes = mp3file.getBytes();
            Client client=Client.create();

            if (lastMp3 != null && !"".equals(lastMp3)){
                WebResource resource1 = client.resource(lastMp3);
                resource1.delete();
            }

            WebResource resource = client.resource(realPath);
            resource.put(bytes);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("realPath",realPath);
            jsonObject.put("relativePath", relativePath);

            response.getWriter().write(jsonObject.toString());

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
