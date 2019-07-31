package com.ow.tracer.mantuoluo.member;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ow.tracer.common.vo.NumberValue;


import javax.activation.MimetypesFileTypeMap;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @auther: Easy
 * @date: 19-6-28 11:46
 * @description:
 */
public class TestCvas {
//
    public static String ImageTo (java.util.List<NumberValue> kvDatas) throws  Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();//io流
        BufferedImage bi = new BufferedImage(241,241,BufferedImage.TYPE_INT_RGB);

//得到它的绘制环境(这张图片的笔)
        Graphics2D g2 =bi.createGraphics();

// 增加下面代码使得背景透明
        bi = g2.getDeviceConfiguration().createCompatibleImage(241, 241, Transparency.TRANSLUCENT);
        g2.dispose();
        g2 = bi.createGraphics();
        g2.drawImage(bi.getScaledInstance(241,
                241, Image.SCALE_SMOOTH),
               0, 0, null);

//        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(255,248,238));
        g2.fillRect(0,0,241,241);
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("微软雅黑",Font.BOLD,18)); //设置字体:字体、字号、大小
        int x = 80;
        int y = 80;
        int lx = 0;
        int ly = 0;
        int fx = 55;
        int fy = 50;
        int jy = 30;
        int yy = 0;
        java.util.List <MapData> list = new ArrayList();
        for (int i = 1; i < 4; i++) {
            g2.drawRect(lx, ly, x, y);
            g2.setPaint(new Color(0, 0, 0, 64));//阴影颜色
            g2.drawString(""+(yy + 1), lx + 30, fy+5);//先绘制阴影
            g2.setPaint(Color.BLACK);//正文颜色
            g2.drawString(""+(yy + 1), lx + 30, fy+5);
            g2.drawRect(lx+80, ly , x, y);
            g2.setPaint(new Color(0, 0, 0, 64));//阴影颜色
            g2.drawString(""+(yy + 1 + 1), lx + x + 30, fy + 5);//先绘制阴影
            g2.setPaint(Color.BLACK);//正文颜色
            g2.drawString(""+(yy + 1 + 1), lx + x + 30, fy + 5);
            g2.drawRect(lx+80 +80 , ly , x, y);
            g2.setPaint(new Color(0, 0, 0, 64));//阴影颜色
            g2.drawString(""+(yy + 1 + 1 + 1), (lx + x * 2) + 30, fy + 5);//先绘制阴影
            g2.setPaint(Color.BLACK);//正文颜色
            g2.drawString(""+(yy + 1 + 1 + 1), (lx + x * 2) + 30, fy + 5);
            ly =ly+ 80;
            fy = fy + y;
            yy = yy + 1 + 1 + 1;
            jy = jy + y;
        }
        MapData mapData = new MapData();
        mapData.setYy(1+"");
        mapData.setXy(60+"");
        mapData.setJy(30+"");
        list.add(mapData);
        mapData = new MapData();
        mapData.setYy(2+"");
        mapData.setXy(140+"");
        mapData.setJy(30+"");
        list.add(mapData);
        mapData = new MapData();
        mapData.setYy(3+"");
        mapData.setXy(220+"");
        mapData.setJy(30+"");
        list.add(mapData);
        mapData = new MapData();
        mapData.setYy(4+"");
        mapData.setXy(60+"");
        mapData.setJy(110+"");
        list.add(mapData);
        mapData = new MapData();
        mapData.setYy(5+"");
        mapData.setXy(140+"");
        mapData.setJy(110+"");
        list.add(mapData);
        mapData = new MapData();
        mapData.setYy(6+"");
        mapData.setXy(220+"");
        mapData.setJy(110+"");
        list.add(mapData);
        mapData = new MapData();
        mapData.setYy(7+"");
        mapData.setXy(60+"");
        mapData.setJy(190+"");
        list.add(mapData);
        mapData = new MapData();
        mapData.setYy(8+"");
        mapData.setXy(140+"");
        mapData.setJy(190+"");
        list.add(mapData);
        mapData = new MapData();
        mapData.setYy(9+"");
        mapData.setXy(220+"");
        mapData.setJy(190+"");
        list.add(mapData);
        for(NumberValue k:kvDatas){
        for(MapData m : list){
            if(k.getNum()==Integer.parseInt(m.getYy())){
                if(k.getSum()!=0) {
                    g2.drawString(k.getSum() + "", Integer.parseInt(m.getXy()), Integer.parseInt(m.getJy()));
                    if (Integer.parseInt(m.getYy()) < 4) {
                        g2.drawRoundRect(Integer.parseInt(m.getXy()) - 45, 30, 40, 40, 40, 40);
                    } else if (Integer.parseInt(m.getYy()) >= 4 && Integer.parseInt(m.getYy()) <= 6) {
                        g2.drawRoundRect(Integer.parseInt(m.getXy()) - 45, 108, 40, 40, 40, 40);
                    } else {
                        g2.drawRoundRect(Integer.parseInt(m.getXy()) - 45, 190, 40, 40, 40, 40);
                    }
                }
////                g2.drawRoundRect(10,150,40,40,40,40);
////        if (Number(zb[j].yy) < 4) {
////                    ctx.arc(zb[j].xy - 25, 75, 25, 0, 360, false);
////                    ctx.stroke()
////                    ctx.draw(true)
////                    console.log(zb[j].xy);
////
////                } else if (Number(zb[j].yy) >= 4 && Number(zb[j].yy)<=6){
////                    ctx.arc(zb[j].xy - 25, 155, 25, 0, 360, false);
////                    ctx.stroke()
////                    ctx.draw(true)
////                }
////                else {
////                    ctx.arc(zb[j].xy - 25, 235, 25, 0, 360, false);
////                    ctx.stroke()
////                    ctx.draw(true)
////                }

            }
        }
    }

        g2.dispose();
        ImageIO.write(bi,"png",new FileOutputStream("/home/easy/runtime/a.png"));//保存图片 JPEG表示保存格式
//        File file = new File("/home/easy/runtime/a.png");
//        FileInputStream fileInputStream = new FileInputStream(file);
//        uploadImg(fileInputStream);
        //文件：设置file的name，路径
        String url = "http://xingfudna.com:8566/file/fileUplod?projectName=Tracer&modelName=ShopApply";
        Map<String, String> textMap = new HashMap<String, String>();
        //普通参数：可以设置多个input的name，value
        textMap.put("name", "file");
        Map<String, String> fileMap = new HashMap<String, String>();
        fileMap.put("upfile", "/home/easy/runtime/a.png");
        String contentType = "";//image/png
        String  res =    formUpload(url, textMap, fileMap,contentType);
        JSONObject jsonObject = JSON.parseObject(res);//创建jsonObjec对象
        System.out.println(jsonObject.get("url").toString());
        return  jsonObject.get("url").toString();
    }
//
//    public static void main(String[] args) throws Exception{
//        ImageTo();
//    }
    /**
     * @author qimh
     * @description 模拟form表单，上传图片
     * @return 接口返回的json数据
     * 原理：模拟form表单提交：把请求头部信息和和img 信息 写入到输出流中，
     * 通过流把img写入到服务器临时目录里，然后服务器再把img移到指定的位置
     * 最后通过写入流来获取post的响应信息。
     *
     */
    public static void uploadImg(FileInputStream fileInputStream) {
        try {

            // 换行符
            final String newLine = "\r\n";
            final String boundaryPrefix = "--";
            // 定义数据分隔线
            String BOUNDARY = "========7d4a6d158c9";
            // 服务器的域名
            URL url = new URL("http://xingfudna.com:8566/file/fileUplod?projectName=Tracer&modelName=ShopApply");//"www.myhost.com",ECSheng=http://192.168.1.59/ecstore/index.php/openapi/syncnovo/image_upload ，即为form的action值
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // 设置为POST情
            conn.setRequestMethod("POST");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            // 设置请求头参数
            conn.setRequestProperty("Charsert", "UTF-8");
            conn.setRequestProperty("Content-Type", "multipart/form-data; ");

            OutputStream out = new DataOutputStream(conn.getOutputStream());

            // 上传文件
            StringBuilder sb = new StringBuilder();
            sb.append(boundaryPrefix);
            sb.append(BOUNDARY);
            sb.append(newLine);
            // 文件参数,photo参数名可以随意修改
            sb.append("Content-Disposition: form-data; name=\""
                    + "file" + "\"; filename=\"" + "file"
                    + "\"\r\n");
            sb.append("Content-Type:image/jpeg");
            // 参数头设置完以后需要两个换行，然后才是参数内容
            sb.append(newLine);
            sb.append(newLine);

            // 将参数头的数据写入到输出流中
            out.write(sb.toString().getBytes());

            // 数据输入流,用于读取文件数据
            DataInputStream in = new DataInputStream(fileInputStream);
            byte[] bufferOut = new byte[1024];
            int bytes = 0;
            // 每次读1KB数据,并且将文件数据写入到输出流中
            while ((bytes = in.read(bufferOut)) != -1) {
                out.write(bufferOut, 0, bytes);
            }
            // 最后添加换行
            out.write(newLine.getBytes());
            in.close();

            // 定义最后数据分隔线，即--加上BOUNDARY再加上--。
            byte[] end_data = (newLine + boundaryPrefix + BOUNDARY + boundaryPrefix + newLine)
                    .getBytes();
            // 写上结尾标识
            out.write(end_data);
            out.flush();
            out.close();




            // 定义BufferedReader输入流来读取URL的响应 ----读取返回的结果
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
//                System.out.println(line);
                JSONObject jsonObject = JSON.parseObject(line);//创建jsonObjec对象

                String json = jsonObject.toString();//josn格式的字符串
                System.out.println(json);
            }

        } catch (Exception e) {
            System.out.println("发送POST请求出现异常！" + e);
            e.printStackTrace();
        }
    }
    public static InputStream getImageStream(BufferedImage bimage){
        InputStream is = null;
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        ImageOutputStream imOut;
        try {
            imOut = ImageIO.createImageOutputStream(bs);
            ImageIO.write(bimage, "png",imOut);
            is= new ByteArrayInputStream(bs.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return is;
    }
    /**
     * 上传图片
     * @param urlStr
     * @param textMap
     * @param fileMap
     * @param contentType 没有传入文件类型默认采用application/octet-stream
     * contentType非空采用filename匹配默认的图片类型
     * @return 返回response数据
     */
    @SuppressWarnings("rawtypes")
    public static String formUpload(String urlStr, Map<String, String> textMap,
                                    Map<String, String> fileMap,String contentType) {
        String res = "";
        HttpURLConnection conn = null;
        // boundary就是request头和上传文件内容的分隔符
        String BOUNDARY = "---------------------------123821742118716";
        try {
            URL url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(30000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("User-Agent",
                    "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");
            conn.setRequestProperty("Content-Type",
                    "multipart/form-data; boundary=" + BOUNDARY);
            OutputStream out = new DataOutputStream(conn.getOutputStream());

            // file
            if (fileMap != null) {
                Iterator iter = fileMap.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry entry = (Map.Entry) iter.next();
                    String inputName = (String) entry.getKey();
                    String inputValue = (String) entry.getValue();
                    if (inputValue == null) {
                        continue;
                    }
                    File file = new File(inputValue);
                    String filename = file.getName();

                    //没有传入文件类型，同时根据文件获取不到类型，默认采用application/octet-stream
                    contentType = new MimetypesFileTypeMap().getContentType(file);
                    //contentType非空采用filename匹配默认的图片类型
                    if(!"".equals(contentType)){
                        if (filename.endsWith(".png")) {
                            contentType = "image/png";
                        }else if (filename.endsWith(".jpg") || filename.endsWith(".jpeg") || filename.endsWith(".jpe")) {
                            contentType = "image/jpeg";
                        }else if (filename.endsWith(".gif")) {
                            contentType = "image/gif";
                        }else if (filename.endsWith(".ico")) {
                            contentType = "image/image/x-icon";
                        }
                    }
                    if (contentType == null || "".equals(contentType)) {
                        contentType = "multipart/form-data";
                    }
                    StringBuffer strBuf = new StringBuffer();
                    strBuf.append("\r\n").append("--").append(BOUNDARY)
                            .append("\r\n");
                    strBuf.append("Content-Disposition: form-data; name=\""
                            + "file" + "\"; filename=\"" + filename
                            + "\"\r\n");
                    System.out.println(inputName+","+filename);

                    strBuf.append("Content-Type:" + contentType + "\r\n\r\n");
                    out.write(strBuf.toString().getBytes());
                    DataInputStream in = new DataInputStream(
                            new FileInputStream(file));
                    int bytes = 0;
                    byte[] bufferOut = new byte[1024];
                    while ((bytes = in.read(bufferOut)) != -1) {
                        out.write(bufferOut, 0, bytes);
                    }
                    in.close();
                }
            }
            byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();
            out.write(endData);
            out.flush();
            out.close();
            // 读取返回数据
            StringBuffer strBuf = new StringBuffer();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                strBuf.append(line).append("\n");
            }
            res = strBuf.toString();
            reader.close();
            reader = null;
        } catch (Exception e) {
            System.out.println("发送POST请求出错。" + urlStr);
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
                conn = null;
            }
        }
        return res;
    }


}
