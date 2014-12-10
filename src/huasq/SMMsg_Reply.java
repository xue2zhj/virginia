package huasq;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="SMMsg_Reply")
class SMMsg_Reply
{
  private static final long serialVersionUID = 1L;
  @XmlAttribute
  public String ConfirmResult;
  @XmlAttribute
  public String TaskID;
  @XmlElement
  public String Address;
}


/* Location:           D:\NARI\葛洲坝\test.jar
 * Qualified Name:     huasq.SMMsg_Reply
 * JD-Core Version:    0.7.0.1
 */