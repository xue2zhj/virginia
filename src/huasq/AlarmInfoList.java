package huasq;

import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
class AlarmInfoList
{
  @XmlAttribute
  public int Source;
  @XmlElement
  public List<AlarmInfo> AlarmInfo;
}


/* Location:           D:\NARI\葛洲坝\test.jar
 * Qualified Name:     huasq.AlarmInfoList
 * JD-Core Version:    0.7.0.1
 */