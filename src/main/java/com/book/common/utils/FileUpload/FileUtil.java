package com.book.common.utils.FileUpload;

import sun.misc.BASE64Decoder;

import java.io.*;

public class FileUtil{

    //文件上传工具类服务方法

    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception{

        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath+fileName);
        out.write(file);
        out.flush();
        out.close();
    }

    public static void uploadFileBase64(String filePath, String fileName, String suffix, String base64) throws Exception{
        // 判断格式是否为base64格式
        if(base64.contains("data:")){
            base64 = base64.substring(base64.indexOf(",")+1);
        }else {
            return;
        }
        base64 = base64.replace("\r\n", "");
        File file;
        //创建文件目录
        File  dir =new File(filePath);
        if (!dir.exists() && !dir.isDirectory()) {
            dir.mkdirs();
        }

        BASE64Decoder decoder = new BASE64Decoder();
        byte[] bytes =  decoder.decodeBuffer(base64);

        file = new File(filePath + fileName + suffix);
        FileOutputStream fos = new FileOutputStream(file);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        bos.write(bytes);

        bos.flush();
        bos.close();
        fos.flush();
        fos.close();

    }


    public static void flow(InputStream inputStream, OutputStream outputStream) throws IOException {

        if (inputStream != null && outputStream != null){
            byte[] bytes = new byte[1024];
            int length = 0;

            while ((length = inputStream.read(bytes)) != -1){
                outputStream.write(bytes, 0, length);
            }

            inputStream.close();
            outputStream.flush();
            outputStream.close();

        }
    }
}