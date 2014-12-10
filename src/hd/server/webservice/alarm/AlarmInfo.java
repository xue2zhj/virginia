/*   1:    */ package hd.server.webservice.alarm;
/*   2:    */ 
/*   3:    */ import javax.xml.bind.annotation.XmlAccessType;
/*   4:    */ import javax.xml.bind.annotation.XmlAccessorType;
/*   5:    */ import javax.xml.bind.annotation.XmlElement;
/*   6:    */ import javax.xml.bind.annotation.XmlSchemaType;
/*   7:    */ import javax.xml.bind.annotation.XmlType;
/*   8:    */ import javax.xml.datatype.XMLGregorianCalendar;
/*   9:    */ 
/*  10:    */ @XmlAccessorType(XmlAccessType.FIELD)
/*  11:    */ @XmlType(name="alarmInfo", propOrder={"time", "type", "descr", "xInfo"})
/*  12:    */ public class AlarmInfo
/*  13:    */ {
/*  14:    */   @XmlElement(name="Time")
/*  15:    */   @XmlSchemaType(name="dateTime")
/*  16:    */   protected XMLGregorianCalendar time;
/*  17:    */   @XmlElement(name="Type")
/*  18:    */   protected int type;
/*  19:    */   @XmlElement(name="Descr")
/*  20:    */   protected String descr;
/*  21:    */   @XmlElement(name="XInfo")
/*  22:    */   protected String xInfo;
/*  23:    */   
/*  24:    */   public XMLGregorianCalendar getTime()
/*  25:    */   {
/*  26: 62 */     return this.time;
/*  27:    */   }
/*  28:    */   
/*  29:    */   public void setTime(XMLGregorianCalendar value)
/*  30:    */   {
/*  31: 74 */     this.time = value;
/*  32:    */   }
/*  33:    */   
/*  34:    */   public int getType()
/*  35:    */   {
/*  36: 82 */     return this.type;
/*  37:    */   }
/*  38:    */   
/*  39:    */   public void setType(int value)
/*  40:    */   {
/*  41: 90 */     this.type = value;
/*  42:    */   }
/*  43:    */   
/*  44:    */   public String getDescr()
/*  45:    */   {
/*  46:102 */     return this.descr;
/*  47:    */   }
/*  48:    */   
/*  49:    */   public void setDescr(String value)
/*  50:    */   {
/*  51:114 */     this.descr = value;
/*  52:    */   }
/*  53:    */   
/*  54:    */   public String getXInfo()
/*  55:    */   {
/*  56:126 */     return this.xInfo;
/*  57:    */   }
/*  58:    */   
/*  59:    */   public void setXInfo(String value)
/*  60:    */   {
/*  61:138 */     this.xInfo = value;
/*  62:    */   }
/*  63:    */ }


/* Location:           D:\NARI\葛洲坝\test.jar
 * Qualified Name:     hd.server.webservice.alarm.AlarmInfo
 * JD-Core Version:    0.7.0.1
 */