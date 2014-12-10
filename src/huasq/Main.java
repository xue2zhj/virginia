/*   1:    */ package huasq;
/*   2:    */ 
/*   3:    */ import hd.server.webservice.alarm.AlarmInfo;
/*   4:    */ import hd.server.webservice.alarm.AlarmInfoList;
/*   5:    */ import hd.server.webservice.alarm.RecvAlarm;
/*   6:    */ import hd.server.webservice.alarm.RecvAlarmService;
/*   7:    */ import java.io.StringReader;
/*   8:    */ import java.io.StringWriter;
/*   9:    */ import java.util.GregorianCalendar;
/*  10:    */ import java.util.List;
/*  11:    */ import java.util.regex.Matcher;
/*  12:    */ import java.util.regex.Pattern;
/*  13:    */ import javax.xml.bind.JAXBContext;
/*  14:    */ import javax.xml.bind.JAXBException;
/*  15:    */ import javax.xml.bind.Marshaller;
/*  16:    */ import javax.xml.bind.Unmarshaller;
/*  17:    */ import javax.xml.datatype.DatatypeFactory;
/*  18:    */ import javax.xml.datatype.XMLGregorianCalendar;
/*  19:    */ import javax.xml.transform.stream.StreamSource;
/*  20:    */ 
/*  21:    */ public class Main
/*  22:    */ {
/*  23:    */   public static Object unmarshal(String xmlStr, Class[] cls)
/*  24:    */   {
/*  25:    */     try
/*  26:    */     {
/*  27: 65 */       JAXBContext jc = JAXBContext.newInstance(cls);
/*  28: 66 */       Unmarshaller u = jc.createUnmarshaller();
/*  29: 67 */       return u.unmarshal(new StreamSource(new StringReader(xmlStr)));
/*  30:    */     }
/*  31:    */     catch (JAXBException e)
/*  32:    */     {
/*  33: 70 */       e.printStackTrace();
/*  34:    */     }
/*  35:    */     catch (Exception localException) {}
/*  36: 73 */     return null;
/*  37:    */   }
/*  38:    */   
/*  39:    */   public static String getVByK(String input, String key)
/*  40:    */   {
/*  41: 77 */     if ((input == null) || (input.trim().length() < 2) || (key == null) || (key.trim().length() < 1)) {
/*  42: 78 */       return null;
/*  43:    */     }
/*  44: 80 */     Pattern pn = Pattern.compile("\\b" + key.trim() + "\\s*=([^;]+);");
/*  45:    */     try
/*  46:    */     {
/*  47: 82 */       Matcher ma = pn.matcher(input);
/*  48: 83 */       if (ma.find()) {
/*  49: 84 */         return ma.group(1);
/*  50:    */       }
/*  51:    */     }
/*  52:    */     catch (Exception localException) {}
/*  53: 88 */     return null;
/*  54:    */   }
/*  55:    */   
/*  56:    */   public static String setVByK(String input, String key, String value)
/*  57:    */   {
/*  58: 92 */     if ((key == null) || (key.trim().length() < 1)) {
/*  59: 93 */       return null;
/*  60:    */     }
/*  61: 96 */     if ((input == null) || (input.trim().length() < 1)) {
/*  62: 97 */       return key + "=" + value + ";";
/*  63:    */     }
/*  64:100 */     Pattern p = Pattern.compile("\\b" + key.trim() + "\\s*=([^;]+);");
/*  65:101 */     Matcher m = p.matcher(input);
/*  66:102 */     StringBuffer sb = new StringBuffer();
/*  67:103 */     if (m.find())
/*  68:    */     {
/*  69:104 */       m.appendReplacement(sb, key.trim() + "=" + value + ";");
/*  70:105 */       m.appendTail(sb);
/*  71:106 */       return sb.toString();
/*  72:    */     }
/*  73:108 */     input = input + key + "=" + value + ";";
/*  74:109 */     return input;
/*  75:    */   }
/*  76:    */   
/*  77:    */   public static void main(String[] args)
/*  78:    */   {
/*  79:168 */     int i = 1;
/*  80:    */     try
/*  81:    */     {
/*  82:320 */       RecvAlarm alarmSender = new RecvAlarm();
/*  83:321 */       AlarmInfoList test = new AlarmInfoList();
/*  84:322 */       AlarmInfo info1 = new AlarmInfo();
/*  85:323 */       info1.setDescr("三板溪老屯2雨量测试报警");
/*  86:324 */       info1.setType(1);
/*  87:325 */       info1.setXInfo("PName=10010101784;Level=2;");
/*  88:326 */       test.getAlarmInfo().add(info1);
/*  89:    */       
/*  90:328 */       AlarmInfo info2 = new AlarmInfo();
/*  91:329 */       info2.setDescr("洪江芷江雨量测试报警");
/*  92:330 */       info2.setType(2);
/*  93:331 */       info2.setXInfo("PName=10054100184;Level=3;");
/*  94:332 */       test.getAlarmInfo().add(info2);
/*  95:    */       for (;;)
/*  96:    */       {
/*  97:    */         try
/*  98:    */         {
/*  99:336 */           XMLGregorianCalendar c = DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar());
/* 100:337 */           info1.setTime(c);
/* 101:338 */           info2.setTime(c);
/* 102:339 */           alarmSender.getRecvAlarmServicePort().reciveAlarm(test);
/* 103:    */         }
/* 104:    */         catch (Exception e)
/* 105:    */         {
/* 106:341 */           e.printStackTrace();
/* 107:    */         }
/* 108:343 */         Thread.sleep(5000L);
/* 109:    */       }
/* 110:    */     }
/* 111:    */     catch (Exception e)
/* 112:    */     {
/* 113:346 */       e.printStackTrace();
/* 114:    */     }
/* 115:    */   }
/* 116:    */   
/* 117:    */   public static byte[][] getByte(Object msgs)
/* 118:    */   {
/* 119:    */     try
/* 120:    */     {
/* 121:356 */       String xml = marshal(msgs, "utf-8");
/* 122:357 */       byte[] data = xml.getBytes("utf-8");
/* 123:358 */       return composePkg(data);
/* 124:    */     }
/* 125:    */     catch (Exception e) {}
/* 126:362 */     return null;
/* 127:    */   }
/* 128:    */   
/* 129:    */   public static String marshal(Object obj, String encoding)
/* 130:    */   {
/* 131:    */     try
/* 132:    */     {
/* 133:368 */       JAXBContext jc = JAXBContext.newInstance(new Class[] { obj.getClass() });
/* 134:369 */       Marshaller m = jc.createMarshaller();
/* 135:370 */       m.setProperty("jaxb.formatted.output", Boolean.valueOf(true));
/* 136:371 */       m.setProperty("jaxb.encoding", encoding);
/* 137:372 */       StringWriter sr = new StringWriter();
/* 138:373 */       m.marshal(obj, sr);
/* 139:374 */       return sr.toString();
/* 140:    */     }
/* 141:    */     catch (JAXBException e) {}
/* 142:376 */     return null;
/* 143:    */   }
/* 144:    */   
/* 145:    */   public static int getVersion(String input)
/* 146:    */   {
/* 147:    */     try
/* 148:    */     {
/* 149:382 */       Pattern p = Pattern.compile("\\b/\\s*\\*\\s*Version\\s*=\\s*(\\d+)\\s*\\*\\s*/", 2);
/* 150:383 */       Matcher m = p.matcher(input);
/* 151:384 */       if (m.find()) {
/* 152:385 */         return Integer.valueOf(m.group(1)).intValue();
/* 153:    */       }
/* 154:    */     }
/* 155:    */     catch (Exception localException) {}
/* 156:388 */     return -1;
/* 157:    */   }
/* 158:    */   
/* 159:    */   public static byte[][] composePkg(byte[] data)
/* 160:    */   {
/* 161:393 */     CheckXOR chkXOR = new CheckXOR();
/* 162:394 */     CheckSum chkSum = new CheckSum();
/* 163:395 */     byte[] pkgData = new byte[data.length + 10]; byte 
/* 164:396 */       tmp37_36 = (pkgData[2] = pkgData[3] = -1);pkgData[1] = tmp37_36;pkgData[0] = tmp37_36;
/* 165:    */     
/* 166:398 */     long l = data.length + 2;
/* 167:399 */     pkgData[4] = ((byte)(int)(l & 0xFF));
/* 168:400 */     pkgData[5] = ((byte)(int)(l >> 8));
/* 169:    */     
/* 170:402 */     pkgData[6] = -128;
/* 171:    */     
/* 172:404 */     byte[] cc = chkXOR.GetCheckCode(pkgData, 4, 3);
/* 173:405 */     pkgData[7] = cc[0];
/* 174:407 */     for (int i = 0; i < data.length; i++) {
/* 175:408 */       pkgData[(8 + i)] = data[i];
/* 176:    */     }
/* 177:411 */     cc = chkSum.GetCheckCode(pkgData, 4, data.length + 4);
/* 178:412 */     pkgData[(data.length + 8)] = cc[0];
/* 179:413 */     pkgData[(data.length + 9)] = cc[1];
/* 180:    */     
/* 181:415 */     return new byte[][] { pkgData };
/* 182:    */   }
/* 183:    */ }


/* Location:           D:\NARI\葛洲坝\test.jar
 * Qualified Name:     huasq.Main
 * JD-Core Version:    0.7.0.1
 */