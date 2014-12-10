/*  1:   */ package com.nari.slsd.he.Calculate.strv;
/*  2:   */ 
/*  3:   */ public class ChartData
/*  4:   */ {
/*  5:   */   private double imitationValue;
/*  6:   */   private double realValue;
/*  7:   */   private double xValue;
/*  8:   */   
/*  9:   */   public ChartData(double imitationValue, double realValue, double xValue)
/* 10:   */   {
/* 11: 9 */     this.imitationValue = imitationValue;
/* 12:10 */     this.realValue = realValue;
/* 13:11 */     this.xValue = xValue;
/* 14:   */   }
/* 15:   */   
/* 16:   */   public double getImitationValue()
/* 17:   */   {
/* 18:15 */     return this.imitationValue;
/* 19:   */   }
/* 20:   */   
/* 21:   */   public void setImitationValue(double imitationValue)
/* 22:   */   {
/* 23:19 */     this.imitationValue = imitationValue;
/* 24:   */   }
/* 25:   */   
/* 26:   */   public double getRealValue()
/* 27:   */   {
/* 28:23 */     return this.realValue;
/* 29:   */   }
/* 30:   */   
/* 31:   */   public void setRealValue(double realValue)
/* 32:   */   {
/* 33:27 */     this.realValue = realValue;
/* 34:   */   }
/* 35:   */   
/* 36:   */   public Double getxValue()
/* 37:   */   {
/* 38:31 */     return Double.valueOf(this.xValue);
/* 39:   */   }
/* 40:   */   
/* 41:   */   public void setxValue(double xValue)
/* 42:   */   {
/* 43:35 */     this.xValue = xValue;
/* 44:   */   }
/* 45:   */ }


/* Location:           D:\NARI\葛洲坝\test.jar
 * Qualified Name:     com.nari.slsd.he.Calculate.strv.ChartData
 * JD-Core Version:    0.7.0.1
 */