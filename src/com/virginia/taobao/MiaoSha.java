package com.virginia.taobao;

import java.net.Socket;
import java.util.regex.Pattern;

import javax.swing.JPanel;

import org.eclipse.swt.widgets.DateTime;




//import System.Collections;
//import System.Configuration;
//import System.Data;
//import System.Web;
//import System.Web.Security;
//import System.Web.UI;
//import System.Web.UI.HtmlControls;
//import System.Web.UI.WebControls;
//import System.Net;
//import System.Net.Sockets;
//import System.Text;
//import System.Threading;
//import System.IO;
//import System.Text.RegularExpressions;

public class MiaoSha extends JPanel
{
    String strServer ="";
    String strPath ="";

    protected void MiaoSha()
    {
       
    }

    public static String Recv(Socket sock, Encoding encode)
    {
        Byte[] buffer = new Byte[8192];
        StringBuilder sb = new StringBuilder();

        Thread.sleep(50);//根据页面响应时间进行微调
        int len = sock.recv(buffer);
        sb.Append(encode.getString(buffer, 0, len));

        while (sock.Available > 0)
        {
            Thread.sleep(300);//也可以视情况微调
            Array.clear(buffer, 0, buffer.length);
            len = sock.Receive(buffer);
            sb.Append(encode.GetString(buffer, 0, len));
            String ss = encode.GetString(buffer, 0, len);
        }
        sock.close();
        return sb.toString();
    }

    /// <summary>
    /// Socket获取页面HTML同时返回头信息
    /// </summary>
    /// <param name="server">服务器地址或主机名</param>
    /// <param name="url">请求的页面</param>
    /// <param name="method">post or get</param>
    /// <param name="data">提交的数据</param>
    /// <param name="Cookies">Cookies</param>
    /// <returns>返回页面的HTML</returns>
    public String GetHtml(String server, String url, String method, String data, String Cookies)
    {
        String _method = method.toUpperCase();
        String _url ="";
        if (url == "")
        {
            _url = "/";
        }
        else if (url.substring(0, 1) != "/")
        {
            _url = "/" + url;
        }
        else
        {
            _url = url;
        }
        String formatString ="";
        String sendString ="";
        Encoding ASCII = Encoding.Default;

        //以下是拼接的HTTP头信息
        if (_method == "GET")
        {
            formatString = "";
            formatString += "{0} {1} HTTP/1.1\r\n";
            formatString += "Host: {2}\r\n";
            formatString += "User-Agent:Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.9.1.7) Gecko/20091221 Firefox/3.5.7\r\n";
            formatString += "Accept: text/html\r\n";
            formatString += "Keep-Alive: 300\r\n";
            formatString += "Cookies:{3}\r\n";
            formatString += "Connection: keep-alive\r\n\r\n";
            sendString = String.Format(formatString, _method, _url, server, Cookies);
        }
        else
        {
            formatString = "";
            formatString += "{0} {1} HTTP/1.1\r\n";
            formatString += "Host: {2}\r\n";
            formatString += "User-Agent:Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.9.1.7) Gecko/20091221 Firefox/3.5.7\r\n";
            formatString += "Accept:text/html\r\n";
            formatString += "Content-Type:application/x-www-form-urlencoded\r\n";
            formatString += "Content-length:{3}\r\n";
            formatString += "Referer:http://buy.taobao.com/auction/buy_now.jhtml";
            formatString += "Keep-Alive:300\r\n";
            formatString += "Cookies:{4}\r\n";
            formatString += "Connection: keep-alive\r\n\r\n";
            formatString += "{5}\r\n";
            sendString = String.Format(formatString, _method, _url, server, Encoding.Default.GetByteCount(data), Cookies, data);
        }

        Byte[] ByteGet = ASCII.GetBytes(sendString);
        Byte[] RecvBytes = new Byte[1024];
        String strRetPage = null;
        IPAddress hostadd = Dns.Resolve(server).AddressList[0];
        IPEndPoint EPhost = new IPEndPoint(hostadd, 80);
        Socket s = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);
        s.Connect(EPhost);
        if (!s.Connected)
        {
            strRetPage = "链接主机失败";
            return strRetPage;
        }
        s.Send(ByteGet, ByteGet.length, SocketFlags.None);

        strRetPage = Recv(s, ASCII);

        return strRetPage;
    }

    protected void btnLogin_Click(object sender, EventArgs e)
    {
        String u = this.txtUserName.Text.Trim();
        String p = this.txtPwd.Text.Trim();
        DateTime st = DateTime.Now;

        //淘宝登录需要post的数据串
        String sendData = "TPL_username=" + u + "&TPL_password=" + Server.UrlEncode(p) + "&actionForStable=enable_post_user_action&action=Authenticator&mi_uid=&mcheck=&TPL_redirect_url=http%3A%2F%2Fitem.taobao.com%2Fauction%2Fitem_detail-0db1-3036113cf5455bd74047f1a581ba4be7.htm&_oooo_=http%3A%2F%2Fitem.taobao.com%2Fauction%2Fitem_detail-0db1-3036113cf5455bd74047f1a581ba4be7.htm&event_submit_do_login=anything&abtest=&pstrong=3&from=&yparam=&done=&loginType=3&tid=&support=000001&CtrlVersion=1%2C0%2C0%2C7";

        String s = GetHtml("login.taobao.com", "/member/login.jhtml", "post", sendData, "");
        Session["Cookies"] = GetCookies(s); //从返回的源码中提取cookies，抓取登录后的页面需要附上该cookies 

    }
    protected void btnBuy_Click(object sender, EventArgs e)
    {
        String strURL = this.txtURL.Text.Trim();
        getServerAndPath(strURL);

        String s = GetHtml(strServer, strPath, "get", "", Session["Cookies"].toString());
        //Response.Write(s);
        if (s.indexOf("立即购买") > 0)
        {
            String item_id = strURL.Split('-')[2].Split('.')[0].toString();
            String x_id = strURL.Split('-')[1].toString();

            s = GetHtml("buy.taobao.com", "/auction/buy.htm?from=itemDetail&item_id=" + item_id + "&x_id=" + x_id, "get", "", Session["Cookies"].toString());
            //Response.Write(s);
            import (StreamWriter sw = new StreamWriter(Server.MapPath("debug1.html")))
            {
                sw.Write(s);
            }

            if (s.indexOf("确认提交订单") > 0)
            {
                Session["Cookies"] = GetCookies(s);
                String postData = getPostData(s);
                String r = GetHtml("buy.taobao.com", "/auction/buy_now.htm", "post", postData, Session["Cookies"].toString());
                if (r.indexOf("302") > 0)
                {
                    import (StreamWriter sw = new StreamWriter(Server.MapPath("debug2.html")))
                    {
                        sw.Write(r);
                    }
                }
                else
                {
                    ////
                }
                import (StreamWriter sw = new StreamWriter(Server.MapPath("debug2.html")))
                {
                    sw.Write(r);
                }
            }
        }
        else if (s.indexOf("btn-wait") > 0)//该宝贝还处于定时上架的状态
        { 
            
        }

    }


    /// <summary>
    /// 从返回的源代码中提取cookies
    /// </summary>
    /// <param name="s"></param>
    /// <returns></returns>
    private String GetCookies(String s)
    {
        StringBuilder sbCookies = new StringBuilder();

        String[] arr = s.Split(new String[] { "\r\n" }, StringSplitOptions.RemoveEmptyEntries);
        for (String str : arr)
        {
            if (str.startsWith("Set-Cookie: "))
            {
                int intStart = str.indexOf(";");
                String strCookie = str.substring(12, intStart - 11);
                sbCookies.append(strCookie);
            }
        }
        return sbCookies.toString();
    }

    private String GetLocationURL(String s)
    {

        String RtnString ="";
        StringBuilder sbCookies = new StringBuilder();

        String[] arr = s.Split(new String[] { "\r\n" }, StringSplitOptions.RemoveEmptyEntries);
        for (String str : arr)
        {
            if (str.startsWith("Location: "))
            {
                RtnString = str.substring(11, str.length() - 11);
            }
        }
        return RtnString;
    }



    private void getServerAndPath(String strURL)
    {
        if (strURL != "" && strURL.indexOf("/") > 0)
        {
            int SlashPos = strURL.substring(7).indexOf("/");
            strServer = strURL.substring(7, SlashPos);
            strPath = strURL.substring(SlashPos + 7);
        }
        else
            return;
    }



    /// <summary>
    /// 从最后确认购买页面的源代码中提取表单数据的数据
    /// </summary>
    /// <param name="html"></param>
    /// <returns></returns>
    private String getPostData(String html)
    {
        String postStr = "";
        String pat = "<input .*?name.{0,1}=.{0,1}\"(.*?)\".*? value.{0,1}=\"(.*?)\".*?>";
        Pattern regex = Pattern.compile(pat);
       java.util.regex.Matcher matcher = regex.matcher(html);
//        /Regex regex = new Regex(pat, RegexOptions.Multiline | RegexOptions.IgnoreCase);
       // MatchCollection mcollection = regex.Matches(html);
       if( matcher.find() ){
    	   String m = matcher.group();
    	   matcher.g
    	   if (m.indexOf("_fma.b._0.s") > 0) { continue; }
           if (m.indexOf("_fma.b._0.c") > 0) { continue; }
           if (m.indexOf("isCheckCode") > 0 && gcollection[2].Value.ToLower() == "true")
           {
               //isCheckCode = true;
           }
           postStr += gcollection[1].Value; postStr += "=";
           postStr += Server.UrlEncode(gcollection[2].Value);
           postStr += "&";
       }
       
        foreach (Match m in mcollection)
        {
            GroupCollection gcollection = m.Groups;
            if (m.toString().indexOf("_fma.b._0.s") > 0) { continue; }
            if (m.toString().indexOf("_fma.b._0.c") > 0) { continue; }
            if (m.toString().indexOf("isCheckCode") > 0 && gcollection[2].Value.ToLower() == "true")
            {
                //isCheckCode = true;
            }
            postStr += gcollection[1].Value; postStr += "=";
            postStr += Server.UrlEncode(gcollection[2].Value);
            postStr += "&";
        }
        postStr += "n_prov=370000&n_city=370500&n_area=370522&_fma.b._0.w=quicky&_fma.b._0.ac=250&consignment=10&_fma.b._0.au=5&_fma.b._0.c=8888";
        postStr = postStr.Replace("quantity=0", "quantity=1").Replace("_fma.b._0.d=您不必重复省-市-区信息；至少5个字", "_fma.b._0.d=" + Server.UrlEncode("收货人的具体地址")).Replace("_fma.b._0.po=", "_fma.b._0.po=230031").Replace("_fma.b._0.de=", "_fma.b._0.de="+Server.UrlEncode("啊峰")).Replace("_fma.b._0.u=", "_fma.b._0.u=0").Replace("_fma.b._0.di=1", "_fma.b._0.di=370522").Replace("_fma.b._0.deli=", "_fma.b._0.deli=13888888888");
        postStr += "&_fma.b._0.s=2";
        //postStr = Server.UrlEncode(postStr);

        return postStr;
    }
}