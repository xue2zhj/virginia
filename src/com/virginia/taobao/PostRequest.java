package com.virginia.taobao;

import java.io.BufferedReader;  
import java.io.IOException;  
import java.io.InputStream;  
import java.io.InputStreamReader;  
import java.io.OutputStreamWriter;  
import java.net.URL;  
import java.net.URLConnection;  

public class PostRequest {
	 public static void TestPost() throws IOException {  
         
	        URL url = new URL("http://h5.m.taobao.com/awp/mtb/mtb.htm?spm=0.0.0.0#!/awp/mtb/mtb.htm");  
	        URLConnection connection = url.openConnection();  
	        connection.setDoOutput(true);  
	        OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "8859_1");  
	        out.write("username=kevin&password=12345"); // ��ҳ�洫�����ݡ�post�Ĺؼ����ڣ�  
	        out.flush();  
	        out.close();  
	        // һ�����ͳɹ��������·����Ϳ��Եõ��������Ļ�Ӧ��  
	        String sCurrentLine;  
	        String sTotalString;  
	        sCurrentLine = "";  
	        sTotalString = "";  
	        InputStream l_urlStream;  
	        l_urlStream = connection.getInputStream();  
	        // ��˵�е������װ����  
	        BufferedReader l_reader = new BufferedReader(new InputStreamReader(  
	                l_urlStream));  
	        while ((sCurrentLine = l_reader.readLine()) != null) {  
	            sTotalString += sCurrentLine + "\r\n";  
	  
	        }  
	        System.out.println(sTotalString);  
	          
	    }  
	  
	    public static void main(String[] args) throws IOException {  
	        TestPost();  
	    }  
}
