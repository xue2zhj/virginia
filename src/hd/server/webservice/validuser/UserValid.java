/*  1:   */ package hd.server.webservice.validuser;
/*  2:   */ 
/*  3:   */ import java.net.MalformedURLException;
/*  4:   */ import java.net.URL;
/*  5:   */ import java.util.logging.Logger;
/*  6:   */ import javax.xml.namespace.QName;
/*  7:   */ import javax.xml.ws.Service;
/*  8:   */ import javax.xml.ws.WebEndpoint;
/*  9:   */ import javax.xml.ws.WebServiceClient;
/* 10:   */ import javax.xml.ws.WebServiceFeature;
/* 11:   */ 
/* 12:   */ @WebServiceClient(name="UserValid", targetNamespace="http://ValidUser.WebService.Server.HD/", wsdlLocation="http://192.9.206.234:8088/loginService?wsdl")
/* 13:   */ public class UserValid
/* 14:   */   extends Service
/* 15:   */ {
/* 16:   */   private static final URL USERVALID_WSDL_LOCATION;
/* 17:26 */   private static final Logger logger = Logger.getLogger(UserValid.class.getName());
/* 18:   */   
/* 19:   */   static
/* 20:   */   {
/* 21:29 */     URL url = null;
/* 22:   */     try
/* 23:   */     {
/* 24:32 */       URL baseUrl = UserValid.class.getResource(".");
/* 25:33 */       url = new URL(baseUrl, "http://192.9.206.234:8088/loginService?wsdl");
/* 26:   */     }
/* 27:   */     catch (MalformedURLException e)
/* 28:   */     {
/* 29:35 */       logger.warning("Failed to create URL for the wsdl Location: 'http://192.9.206.234:8088/loginService?wsdl', retrying as a local file");
/* 30:36 */       logger.warning(e.getMessage());
/* 31:   */     }
/* 32:38 */     USERVALID_WSDL_LOCATION = url;
/* 33:   */   }
/* 34:   */   
/* 35:   */   public UserValid(URL wsdlLocation, QName serviceName)
/* 36:   */   {
/* 37:42 */     super(wsdlLocation, serviceName);
/* 38:   */   }
/* 39:   */   
/* 40:   */   public UserValid()
/* 41:   */   {
/* 42:46 */     super(USERVALID_WSDL_LOCATION, new QName("http://ValidUser.WebService.Server.HD/", "UserValid"));
/* 43:   */   }
/* 44:   */   
/* 45:   */   @WebEndpoint(name="UserValidServicePort")
/* 46:   */   public UserValidService getUserValidServicePort()
/* 47:   */   {
/* 48:56 */     return (UserValidService)super.getPort(new QName("http://ValidUser.WebService.Server.HD/", "UserValidServicePort"), UserValidService.class);
/* 49:   */   }
/* 50:   */   
/* 51:   */   @WebEndpoint(name="UserValidServicePort")
/* 52:   */   public UserValidService getUserValidServicePort(WebServiceFeature... features)
/* 53:   */   {
/* 54:68 */     return (UserValidService)super.getPort(new QName("http://ValidUser.WebService.Server.HD/", "UserValidServicePort"), UserValidService.class, features);
/* 55:   */   }
/* 56:   */ }


/* Location:           D:\NARI\葛洲坝\test.jar
 * Qualified Name:     hd.server.webservice.validuser.UserValid
 * JD-Core Version:    0.7.0.1
 */