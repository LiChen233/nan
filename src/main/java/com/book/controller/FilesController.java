package com.book.controller;

import com.book.common.utils.FileUpload.FileUtil;
import com.book.common.utils.UuidUtil;
import com.book.entity.Result;
import com.book.entity.Files;
import com.book.service.FilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

@CrossOrigin
@RestController
public class FilesController {
    @Autowired
    FilesService filesService;
    private String id;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private String filename;

    /**
     * 上传文件
     * @param list
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("/uploadfile")
    public Map<String, Object> uploadfile(@RequestParam(value = "file") List<MultipartFile> list, HttpServletRequest request) throws Exception {

        //前端需要的状态码
        Integer errno = 0;
        //前端需要的图片地址，实际为uuid码
        List<String> urls = new ArrayList<>();
        Map<String, Object> map = new HashMap<>(2);
        //本地文件存储位置
        String path = "img/";

        //没文件就返回错误1
        if (list.size() == 0) {
            errno = 1;
        }else {
            for (MultipartFile file : list) {

                //截取文件名
                String[] split = file.getOriginalFilename().split("\\.");
                //获取文件名以及后缀
                String fileRealName = split[0];
                String suffix = "."+split[split.length - 1].toLowerCase();

                map.put("fileRealName",fileRealName);
                map.put("suffix",suffix);

                //生成uuid,使用uuid代替名字
                String uuid = UuidUtil.get32UUID();
                //设置路径

                //上传文件
                FileUtil.uploadFile(file.getBytes(),path,uuid);
                //文件上传后，添加进数据库
                Files f = Files.builder()
                        .uuid(uuid)
                        .suffix(suffix)
                        .filename(fileRealName)
                        .build();
                filesService.addImg(f);
                //返回uuid
                urls.add(uuid);
            }
        }
        String serverurl = filesService.getserverurl();
        map.put("errno", errno);
        map.put("data", urls);
        map.put("url",serverurl);

        return map;
    }

    /**
     * 下载文件，图片的话直接显示
     * @param request
     * @param response
     * @param model
     * @return
     */
    @GetMapping("/file/{filename}")
    public String createFolw(@PathVariable("filename")String filename, HttpServletRequest request,
                             HttpServletResponse response, Model model) {
        // response.setContentType("image/*");
        FileInputStream fis = null;
        OutputStream os = null;
        try {
            Files file = filesService.getImgByUUID(filename);
            fis = new FileInputStream("img/"+filename);
            //查询数据库中的文件后缀
            String suffix = file.getSuffix();
            String name = file.getFilename();
            String[] images = {".jpg",".png",".gif",".jpge",".webp"};
            boolean flag = true;
            for (String image : images) {
                if (suffix.equals(image)){
                    flag=false;
                }
            }
            //如果不是图片后缀，则使用附件的方式下载文件
            if (flag){
                response.setHeader("Content-Disposition","attachment;filename=\""+new String(name.getBytes("gb2312"),"iso8859-1")+suffix+ "\"");
            }

            os = response.getOutputStream();
            int count = 0;
            byte[] buffer = new byte[1024 * 8];
            while ((count = fis.read(buffer)) != -1) {
                os.write(buffer, 0, count);
                os.flush();
            }
        } catch (Exception e) {
            return "文件不存在！";
        }
        try {
            if (fis!=null){
                fis.close();
            }
            if (os!=null){
                os.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "ok";
    }

    @GetMapping("/delFile/{uuid}")
    public String delFile(@PathVariable("uuid") String uuid){
//        if (filesService.delFile(uuid)){
//            return "删除成功";
//        }
        return "删除失败";
    }

    @PostMapping("/getFileName")
    public Result getFileName(@RequestParam(value = "ids[]") String[] ids){
        Result res = Result.builder()
                .code(0)
                .data(filesService.getserverurl())
                .list(filesService.getFileName(ids))
                .build();
        return res;
    }

    @GetMapping("/getServerUrl")
    public String getServerUrl(){
        return filesService.getserverurl();
    }

}
