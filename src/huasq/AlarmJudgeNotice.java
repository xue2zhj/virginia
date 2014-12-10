package huasq;

import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
class AlarmJudgeNotice
{
  @XmlAttribute
  public String alarmConclusion;
  @XmlElement
  public List<AlarmInfo> AlarmInfo;
}


/* Location:           D:\NARI\葛洲坝\test.jar
 * Qualified Name:     huasq.AlarmJudgeNotice
 * JD-Core Version:    0.7.0.1
 */