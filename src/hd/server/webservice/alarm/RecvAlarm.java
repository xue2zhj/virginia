package hd.server.webservice.alarm;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;

@WebServiceClient(name="RecvAlarm", targetNamespace="http://Alarm.WebService.Server.HD/", wsdlLocation="http://10.10.10.13:8088/RecvAlarmService?wsdl")
public class RecvAlarm
  extends Service
{
  private static final URL RECVALARM_WSDL_LOCATION;
  private static final Logger logger = Logger.getLogger(RecvAlarm.class.getName());
  
  static
  {
    URL url = null;
    try
    {
      URL baseUrl = RecvAlarm.class.getResource(".");
      url = new URL(baseUrl, "http://10.10.10.13:8088/RecvAlarmService?wsdl");
    }
    catch (MalformedURLException e)
    {
      logger.warning("Failed to create URL for the wsdl Location: 'http://10.10.10.13:8088/RecvAlarmService?wsdl', retrying as a local file");
      logger.warning(e.getMessage());
    }
    RECVALARM_WSDL_LOCATION = url;
  }
  
  public RecvAlarm(URL wsdlLocation, QName serviceName)
  {
    super(wsdlLocation, serviceName);
  }
  
  public RecvAlarm()
  {
    super(RECVALARM_WSDL_LOCATION, new QName("http://Alarm.WebService.Server.HD/", "RecvAlarm"));
  }
  
  @WebEndpoint(name="RecvAlarmServicePort")
  public RecvAlarmService getRecvAlarmServicePort()
  {
    return (RecvAlarmService)super.getPort(new QName("http://Alarm.WebService.Server.HD/", "RecvAlarmServicePort"), RecvAlarmService.class);
  }
  
  @WebEndpoint(name="RecvAlarmServicePort")
  public RecvAlarmService getRecvAlarmServicePort(WebServiceFeature... features)
  {
    return (RecvAlarmService)super.getPort(new QName("http://Alarm.WebService.Server.HD/", "RecvAlarmServicePort"), RecvAlarmService.class, features);
  }
}
