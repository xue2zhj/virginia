/**
 * @Title: Miao.java
 * @Package com.virginia.taobao
 * @Description: TODO(用一句话描述该文件做�?��)
 * @author Virginia
 * @date 2014�?�?7�?上午11:03:26
 * @version V1.0
 */
package com.virginia.taobao;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HtmlRequest
{
	public static void main(String[] args) throws IOException {  
         URL url = new URL("http://www.163.com/");  
         HttpURLConnection conn = (HttpURLConnection)url.openConnection();  
         InputStream inputStream = conn.getInputStream();   //通过输入流获得网站数�? 
         byte[] getData = readInputStream(inputStream);     //获得网站的二进制数据  
         String data = new String(getData, "gb2312");  
         System.out.println(data);  
           
     }  

     public static  byte[] readInputStream(InputStream inputStream) throws IOException {  
         byte[] buffer = new byte[1024];  
         int len = 0;  
         ByteArrayOutputStream bos = new ByteArrayOutputStream();  
         while((len = inputStream.read(buffer)) != -1) {  
             bos.write(buffer, 0, len);  
         }  
           
         bos.close();  
         return bos.toByteArray();  
     }  
       
}
