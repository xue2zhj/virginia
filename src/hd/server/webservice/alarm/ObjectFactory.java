/*  1:   */ package hd.server.webservice.alarm;
/*  2:   */ 
/*  3:   */ import javax.xml.bind.annotation.XmlRegistry;
/*  4:   */ 
/*  5:   */ @XmlRegistry
/*  6:   */ public class ObjectFactory
/*  7:   */ {
/*  8:   */   public AlarmInfoList createAlarmInfoList()
/*  9:   */   {
/* 10:37 */     return new AlarmInfoList();
/* 11:   */   }
/* 12:   */   
/* 13:   */   public AlarmInfo createAlarmInfo()
/* 14:   */   {
/* 15:45 */     return new AlarmInfo();
/* 16:   */   }
/* 17:   */ }


/* Location:           D:\NARI\葛洲坝\test.jar
 * Qualified Name:     hd.server.webservice.alarm.ObjectFactory
 * JD-Core Version:    0.7.0.1
 */