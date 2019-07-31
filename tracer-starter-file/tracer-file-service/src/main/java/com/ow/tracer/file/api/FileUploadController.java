package com.ow.tracer.file.api;

import cn.hutool.Hutool;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileReader;
import  cn.hutool.core.io.file.FileWriter;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.IdcardUtil;
import com.ow.tracer.common.base.BaseController;
import com.ow.tracer.common.base.FileResult;
import com.ow.tracer.common.base.Result;
import com.ow.tracer.common.util.Results;
import io.lettuce.core.ScriptOutputType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * @author easy
 * @auther easy
 * @date 18-11-19 17:38
 * @description
 */
@RestController
@RequestMapping("/file")
public class FileUploadController  extends BaseController {
    /**
     * 文件上传接口
     * 有第三方传入，根据项目名-模块名-文件类型-日期进行分类
     */
    @PostMapping(value="/fileUplod")
    public Result fileUplod(MultipartFile file,String projectName,String modelName) throws  Exception{
        if(file.isEmpty()){
            return Results.failure("文件为空，请核查后在进行上传");
        }
        if(projectName.isEmpty()){
            return Results.failure("所属项目名不允许为空，请核查后重试") ;
        }

        if(modelName.isEmpty()){
            return Results.failure("所属模块名不允许为空，请核查后重试");
        }

        int size  = (int)file.getSize();
        if(size>1024*1024*1024){
            return Results.failure("文件超过1G，请缩小后上传") ;
        }
        //指定文件名称
        String fileName =IdUtil.fastSimpleUUID();
        // 获取文件后缀
        String prefix=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        //获取当前时间
        String today= DateUtil.today();

        //获得当前项目路径
        String filePath =  FileUploadController.class.getProtectionDomain()
                .getCodeSource().getLocation().getFile();
        //创建文件
        File  dest = new File(projectName+"/"+modelName+"/"+today+"/"+fileName+prefix);
        String url=projectName+"/"+modelName+"/"+today+"/"+fileName+prefix;
        //将MultipartFile转为File
        FileUtil.writeFromStream(file.getInputStream(),dest);
        //创建文件
        FileUtil.touch(dest);
        url="http://xingfudna.com:15809/"+url;

        //用于上传文件用的返回类，集成了基础返回类，传值为图片名称（自定义），和编辑好的图片路径（同本方法内的url）
        return  new FileResult(fileName, url);
    }
    /**
     * 文件上传接口
     * 有第三方传入，根据项目名-模块名-文件类型-日期进行分类
     */
    @GetMapping(value="/downloadFile")
    public void downloadFile(String projectName, String modelName, String fileName, HttpServletResponse resp) throws  Exception {
        //获得当前项目路径

        String orginFilePath ="/home/gaoxiang/FILE"+"/"+projectName + "/" + modelName + "/" + fileName;

        System.out.println(orginFilePath);
        File file =FileUtil.file(orginFilePath);
        BufferedOutputStream os = null;
        String type=FileTypeUtil.getType(file);
        if (file.exists()) {
            resp.reset();
            os = new BufferedOutputStream(resp.getOutputStream());
            resp.addHeader("Content-Disposition","attachment;filename=" + URLEncoder.encode(file.getName(), "UTF-8"));
            resp.setContentType("application/octet-stream");
            System.out.println( FileUtil.readString(file,"UTF-8"));
            FileReader fileReader = new FileReader(file,"UTF-8");
            System.out.println(fileReader.readString());
            os.write(fileReader.readBytes());
            os.flush();
            os.close();
  } else {
            resp.reset();
            try {
                resp.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("文件不存在", "UTF-8"));
                resp.setContentType("application/octet-stream");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

    }

    public static String codeString(String fileName) throws Exception {
        BufferedInputStream bin = new BufferedInputStream(new FileInputStream(fileName));
        int p = (bin.read() << 8) + bin.read();
        String code = null;
        // 其中的 0xefbb、0xfffe、0xfeff、0x5c75这些都是这个文件的前面两个字节的16进制数
        switch (p) {
            case 0xefbb:
                code = "UTF-8";
                break;
            case 0xfffe:
                code = "Unicode";
                break;
            case 0xfeff:
                code = "UTF-16BE";
                break;
            case 0x5c75:
                code = "ANSI|ASCII";
                break;
            default:
                code = "GBK";
        }
        return code;
    }

}
