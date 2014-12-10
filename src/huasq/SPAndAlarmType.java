/*   1:    */ package huasq;
/*   2:    */ 
/*   3:    */ import java.io.Serializable;
/*   4:    */ import java.util.ArrayList;
/*   5:    */ import javax.xml.bind.annotation.XmlAccessType;
/*   6:    */ import javax.xml.bind.annotation.XmlAccessorType;
/*   7:    */ import javax.xml.bind.annotation.XmlAttribute;
/*   8:    */ import javax.xml.bind.annotation.XmlElement;
/*   9:    */ import javax.xml.bind.annotation.XmlRootElement;
/*  10:    */ 
/*  11:    */ @XmlRootElement
/*  12:    */ @XmlAccessorType(XmlAccessType.FIELD)
/*  13:    */ class SPAndAlarmType
/*  14:    */   implements Serializable
/*  15:    */ {
/*  16:    */   private static final long serialVersionUID = 1L;
/*  17:    */   @XmlElement
/*  18:    */   public ArrayList<SPType> sptypes;
/*  19:    */   @XmlElement
/*  20:    */   public ArrayList<AlarmTypes> alarmtypes;
/*  21:    */   @XmlAttribute
/*  22:611 */   public String map = "";
/*  23:    */   @XmlAttribute
/*  24:613 */   public String info = "";
/*  25:    */ }


/* Location:           D:\NARI\葛洲坝\test.jar
 * Qualified Name:     huasq.SPAndAlarmType
 * JD-Core Version:    0.7.0.1
 */