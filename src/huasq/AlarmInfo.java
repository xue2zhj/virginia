package huasq;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
class AlarmInfo
  implements Serializable
{
  @XmlAttribute
  public Date Time;
  @XmlAttribute
  public int Type;
  @XmlAttribute
  public String Descr;
  @XmlElement
  public String XInfo;
}


/* Location:           D:\NARI\葛洲坝\test.jar
 * Qualified Name:     huasq.AlarmInfo
 * JD-Core Version:    0.7.0.1
 */