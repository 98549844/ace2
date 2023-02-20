package com.restController;

//import com.heeexy.example.util.CommonUtil;

import com.constant.AceEnvironment;
import com.service.FilesService;
import com.util.NullUtil;
import io.swagger.annotations.Api;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;


//https://blog.csdn.net/qq_35416214/article/details/106231487
//https://blog.csdn.net/qq_38762237/article/details/81282444?utm_medium=distribute.pc_relevant.none-task-blog-baidujs-1
//https://blog.csdn.net/qq_38762237/article/details/81282444
@RestController
@RequestMapping("/rest/gallery")
@Api(tags = "gallery")
public class GalleryRestController {
    private static final Logger log = LogManager.getLogger(ApiRestController.class.getName());

    private static final HashMap<String, String> errorInfo = new HashMap<String, String>();

    static {
        HashMap<String, String> tmp = errorInfo;
        // 默认成功
        tmp.put("SUCCESS", "SUCCESS");
        tmp.put("NOFILE", "msg.not_include_file");
        tmp.put("TYPE", "msg.file_not_allow");
        tmp.put("SIZE", "msg.file_size_limit");
        tmp.put("REQUEST", "msg.upload_request_error");
        tmp.put("IO", "msg.io_error");
        tmp.put("DIR", "msg.create_directory_error");
        tmp.put("UNKNOWN", "msg.unknown_error");
    }

    private final FilesService filesService;
    private final String imagesPath;

    @Autowired
    public GalleryRestController(FilesService filesService) {
        this.filesService = filesService;
        this.imagesPath = AceEnvironment.getImagesPath();
    }


    @GetMapping("/download")
    public String downloadFile(String filePath, HttpServletRequest request, HttpServletResponse response) {
        if (NullUtil.isNotNull(filePath)) {
            //设置文件路径
            File file = new File(filePath);
            //File file = new File(realPath , fileName);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition", "attachment;fileName=" + filePath);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    return "下载成功";
                } catch (Exception e) {
                    e.printStackTrace();
                } finally { // 做关闭操作
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return "下载失败";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(@RequestParam("file") MultipartFile file) {    //注意参数
        String result = filesService.upload(imagesPath, file);
        return result;
    }


    @RequestMapping(value = "/uploads", method = RequestMethod.POST, headers = "content-type=multipart/form-data")
    public List uploads(@RequestParam(value = "files") MultipartFile[] files) {
        List list = filesService.uploads(files, null, imagesPath);
        return list;
    }

//    // 多个文件一起上传
//    @PostMapping("/batch")
//    public String handleFileUploadsssssssssss(HttpServletRequest request) {   //注意参数
//        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
//        MultipartFile file = null;
//        BufferedOutputStream stream = null;
//        for (int i = 0; i < files.size(); ++i) {
//            file = files.get(i);
//        //    String filePath = "./FILE/KING/";            // 文件路径
//            File dest = new File(filePath);
//            // 检测是否存在目录
//            if (!dest.exists()) {
//                dest.mkdirs();// 新建文件夹
//            }
//            if (!file.isEmpty()) {
//                try {
//                    byte[] bytes = file.getBytes();
//                    stream = new BufferedOutputStream(new FileOutputStream(new File(filePath + file.getOriginalFilename())));//设置文件路径及名字
//                    stream.write(bytes);// 写入
//                    stream.close();
//                } catch (Exception e) {
//                    stream = null;
//                    return "第 " + i + " 个文件上传失败 ==> " + e.getMessage();
//                }
//            } else {
//                return "第 " + i + " 个文件上传失败因为文件为空";
//            }
//        }
//        return "上传成功";
//    }
//
//
//    //多文件上传
//    @PostMapping("/batchUpload")
//    public JSONObject handleFileUpload(HttpServletRequest request) throws Exception{
//        //定义返回客户端json对象
//        JSONObject returnData = new JSONObject();
//        //定义处理流对象,处理文件上传
//        BufferedOutputStream stream = null;
//        //定义map存储返回结果集
//        Map<String,String> returnfileMap = new HashMap<String, String>();
//
//        //获取前端上传的文件列表
//        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
//        MultipartFile file = null;
//
//        //将request对象转成JSONObject对象
//        JSONObject jsonObject = CommonUtil.request2Json(request);
//        //验证必填字段,用户id,设备id,上传文件类型
//        CommonUtil.hasAllRequired(jsonObject,"user_id,equi_id,upload_type");
//
//        //获取当前用户id
//        String user_id = jsonObject.getString("user_id");
//        //获取当前设备id
//        String equi_id = jsonObject.getString("equi_id");
//        //获取上传文件的类型 1:巡检 2:维保
//        String upload_type = jsonObject.getString("upload_type");
//
//        //判断上传文件类型并设置前置路径
//        File uploadPath = null;
//        String basePath = "/root/img"; //基础文件上传路径
//        String inspection = "/inspection"; //巡检文件夹路径
//        String maintenance = "/maintenance"; //维保文件夹路径
//
//        switch (upload_type){
//            case "1":
//                uploadPath = new File(basePath+inspection);
//                break;
//            case "2":
//                uploadPath = new File(basePath+maintenance);
//                break;
//            default:
//                uploadPath = new File(basePath);
//        }
//        //判断服务器上传文件夹是否存在
//        if(!uploadPath.exists()){
//            uploadPath.mkdirs();
//        }
//
//        //遍历客户端上传文件列表
//        for (int i = 0; i < files.size(); ++i) {
//            //获取到每个文件
//            file = files.get(i);
//            try {
//                //获取上传文件后缀
//                String houzhui = file.getOriginalFilename().split("\\.")[1];
//                //拼接上传文件保存在服务器的路径(当前用户id+设备id+时间戳.后缀名)
//                File fil = new File(uploadPath+"/"+user_id+equi_id+new Date().getTime()+"."+houzhui);
//                //将上传文件保存到服务器上传文件夹目录下
//                byte[] bytes = file.getBytes();
//                stream = new BufferedOutputStream(new FileOutputStream(fil));
//                stream.write(bytes);
//                stream.close();
//                //每成功上传一个文件,将上传文件名作为key,服务器保存路径作为value存入returnfileMap中
//                switch (upload_type){
//                    case "1":
//                        returnfileMap.put(file.getOriginalFilename(),inspection+"/"+fil.getName());
//                        break;
//                    case "2":
//                        returnfileMap.put(file.getOriginalFilename(),maintenance+"/"+fil.getName());
//                        break;
//                }
//            } catch (Exception e) {
//                stream = null;
//                //保存上传失败的文件信息,将上传文件名作为key,value值为"fail",存入returnfileMap中
//                returnfileMap.put(file.getOriginalFilename(),"fail");
//            }finally {
//                //关闭处理流
//                if(stream!=null){stream.close();}
//            }
//        }
//        //返回returnfileMap集合到客户端
//        returnData.put("message",returnfileMap);
//        return CommonUtil.successJson(returnData);
//    }


}
