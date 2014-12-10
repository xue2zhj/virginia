package hd.server.webservice.alarm;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.xml.bind.annotation.XmlSeeAlso;

@WebService(name="RecvAlarmService", targetNamespace="http://Alarm.WebService.Server.HD/")
@SOAPBinding(style=SOAPBinding.Style.RPC)
@XmlSeeAlso({ObjectFactory.class})
public abstract interface RecvAlarmService
{
  @WebMethod(operationName="ReciveAlarm")
  @WebResult(partName="return")
  public abstract int reciveAlarm(@WebParam(name="arg0", partName="arg0") AlarmInfoList paramAlarmInfoList);
}


/* Location:           D:\NARI\葛洲坝\test.jar
 * Qualified Name:     hd.server.webservice.alarm.RecvAlarmService
 * JD-Core Version:    0.7.0.1
 */