package huasq;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MailAttachment
{
  @XmlAttribute
  public String name;
  @XmlAttribute
  public String mimetype;
  @XmlElement
  public byte[] content;
}


/* Location:           D:\NARI\葛洲坝\test.jar
 * Qualified Name:     huasq.MailAttachment
 * JD-Core Version:    0.7.0.1
 */