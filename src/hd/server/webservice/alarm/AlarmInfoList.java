/*  1:   */ package hd.server.webservice.alarm;
/*  2:   */ 
/*  3:   */ import java.util.ArrayList;
/*  4:   */ import java.util.List;
/*  5:   */ import javax.xml.bind.annotation.XmlAccessType;
/*  6:   */ import javax.xml.bind.annotation.XmlAccessorType;
/*  7:   */ import javax.xml.bind.annotation.XmlElement;
/*  8:   */ import javax.xml.bind.annotation.XmlType;
/*  9:   */ 
/* 10:   */ @XmlAccessorType(XmlAccessType.FIELD)
/* 11:   */ @XmlType(name="alarmInfoList", propOrder={"source", "alarmInfo"})
/* 12:   */ public class AlarmInfoList
/* 13:   */ {
/* 14:   */   @XmlElement(name="Source")
/* 15:   */   protected int source;
/* 16:   */   @XmlElement(name="AlarmInfo", nillable=true)
/* 17:   */   protected List<AlarmInfo> alarmInfo;
/* 18:   */   
/* 19:   */   public int getSource()
/* 20:   */   {
/* 21:49 */     return this.source;
/* 22:   */   }
/* 23:   */   
/* 24:   */   public void setSource(int value)
/* 25:   */   {
/* 26:57 */     this.source = value;
/* 27:   */   }
/* 28:   */   
/* 29:   */   public List<AlarmInfo> getAlarmInfo()
/* 30:   */   {
/* 31:83 */     if (this.alarmInfo == null) {
/* 32:84 */       this.alarmInfo = new ArrayList();
/* 33:   */     }
/* 34:86 */     return this.alarmInfo;
/* 35:   */   }
/* 36:   */ }


/* Location:           D:\NARI\葛洲坝\test.jar
 * Qualified Name:     hd.server.webservice.alarm.AlarmInfoList
 * JD-Core Version:    0.7.0.1
 */