/*   1:    */ package com.nari.slsd.he.Calculate.strv;
/*   2:    */ 
/*   3:    */ import java.security.Timestamp;
/*   4:    */ 
/*   5:    */ public class TblTplModel
/*   6:    */ {
/*   7:  7 */   private int Version = 1;
/*   8:    */   private short OId;
/*   9:    */   private int PointId;
/*  10:    */   private String OName;
/*  11:    */   private short Modtype;
/*  12:    */   private short ModUse;
/*  13:    */   private Timestamp BuildTime;
/*  14:    */   private double RelK;
/*  15:    */   private double StdDiff;
/*  16:    */   private Timestamp StartTime;
/*  17:    */   private Timestamp EndTime;
/*  18:    */   
/*  19:    */   public int getPointId()
/*  20:    */   {
/*  21: 49 */     return this.PointId;
/*  22:    */   }
/*  23:    */   
/*  24:    */   public void setPointId(int pointId)
/*  25:    */   {
/*  26: 53 */     this.PointId = pointId;
/*  27:    */   }
/*  28:    */   
/*  29:    */   public short getOId()
/*  30:    */   {
/*  31: 57 */     return this.OId;
/*  32:    */   }
/*  33:    */   
/*  34:    */   public void setOId(short oId)
/*  35:    */   {
/*  36: 61 */     this.OId = oId;
/*  37:    */   }
/*  38:    */   
/*  39:    */   public String getOName()
/*  40:    */   {
/*  41: 65 */     return this.OName;
/*  42:    */   }
/*  43:    */   
/*  44:    */   public void setOName(String oName)
/*  45:    */   {
/*  46: 69 */     this.OName = oName;
/*  47:    */   }
/*  48:    */   
/*  49:    */   public short getModtype()
/*  50:    */   {
/*  51: 73 */     return this.Modtype;
/*  52:    */   }
/*  53:    */   
/*  54:    */   public void setModtype(short modtype)
/*  55:    */   {
/*  56: 77 */     this.Modtype = modtype;
/*  57:    */   }
/*  58:    */   
/*  59:    */   public short getModUse()
/*  60:    */   {
/*  61: 81 */     return this.ModUse;
/*  62:    */   }
/*  63:    */   
/*  64:    */   public void setModUse(short modUse)
/*  65:    */   {
/*  66: 85 */     this.ModUse = modUse;
/*  67:    */   }
/*  68:    */   
/*  69:    */   public Timestamp getBuildTime()
/*  70:    */   {
/*  71: 89 */     return this.BuildTime;
/*  72:    */   }
/*  73:    */   
/*  74:    */   public void setBuildTime(Timestamp buildTime)
/*  75:    */   {
/*  76: 93 */     this.BuildTime = buildTime;
/*  77:    */   }
/*  78:    */   
/*  79:    */   public double getRelK()
/*  80:    */   {
/*  81: 97 */     return this.RelK;
/*  82:    */   }
/*  83:    */   
/*  84:    */   public void setRelK(double relK)
/*  85:    */   {
/*  86:101 */     this.RelK = relK;
/*  87:    */   }
/*  88:    */   
/*  89:    */   public double getStdDiff()
/*  90:    */   {
/*  91:105 */     return this.StdDiff;
/*  92:    */   }
/*  93:    */   
/*  94:    */   public void setStdDiff(double stdDiff)
/*  95:    */   {
/*  96:109 */     this.StdDiff = stdDiff;
/*  97:    */   }
/*  98:    */   
/*  99:    */   public Timestamp getStartTime()
/* 100:    */   {
/* 101:113 */     return this.StartTime;
/* 102:    */   }
/* 103:    */   
/* 104:    */   public void setStartTime(Timestamp startTime)
/* 105:    */   {
/* 106:117 */     this.StartTime = startTime;
/* 107:    */   }
/* 108:    */   
/* 109:    */   public Timestamp getEndTime()
/* 110:    */   {
/* 111:121 */     return this.EndTime;
/* 112:    */   }
/* 113:    */   
/* 114:    */   public void setEndTime(Timestamp endTime)
/* 115:    */   {
/* 116:125 */     this.EndTime = endTime;
/* 117:    */   }
/* 118:    */ }


/* Location:           D:\NARI\葛洲坝\test.jar
 * Qualified Name:     com.nari.slsd.he.Calculate.strv.TblTplModel
 * JD-Core Version:    0.7.0.1
 */