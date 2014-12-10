package huasq;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
class SPType
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  @XmlAttribute
  public short type;
  @XmlAttribute
  public String name;
  @XmlAttribute
  public String info;
}


/* Location:           D:\NARI\葛洲坝\test.jar
 * Qualified Name:     huasq.SPType
 * JD-Core Version:    0.7.0.1
 */