package hd.server.webservice.validuser;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService(name="UserValidService", targetNamespace="http://ValidUser.WebService.Server.HD/")
@SOAPBinding(style=SOAPBinding.Style.RPC)
public abstract interface UserValidService
{
  @WebMethod
  @WebResult(partName="return")
  public abstract int loginValid(@WebParam(name="arg0", partName="arg0") String paramString1, @WebParam(name="arg1", partName="arg1") String paramString2);
}


/* Location:           D:\NARI\葛洲坝\test.jar
 * Qualified Name:     hd.server.webservice.validuser.UserValidService
 * JD-Core Version:    0.7.0.1
 */