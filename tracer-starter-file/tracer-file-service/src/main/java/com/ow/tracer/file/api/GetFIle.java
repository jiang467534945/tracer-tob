package com.ow.tracer.file.api;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.util.ZipUtil;
import cn.hutool.dfa.WordTree;
import com.ow.tracer.common.gencode.util.WordUtil;

import java.io.*;
import java.net.URLEncoder;

/**
 * @auther: Easy
 * @date: 19-4-26 09:35
 * @description:
 */
public class GetFIle {
    public static void main(String[] args) throws  Exception {
        //获得当前项目路径

        String orginFilePath ="/run/media/easy/娱乐/IdeaProjects/Tracer/Tracer/ShopApply/155418091567232/商城系统技术白皮书.doc";

        ZipUtil.zip(orginFilePath);

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
