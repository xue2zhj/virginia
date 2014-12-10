/*   1:    */ package com.nari.slsd.he.Calculate.strv;
/*   2:    */ 
/*   3:    */ import java.util.ArrayList;
/*   4:    */ 
/*   5:    */ public class TplModelBase
/*   6:    */ {
/*   7:  9 */   private int Version = 1;
/*   8:    */   private TblTplModel TplModel;
/*   9:    */   private ArrayList<TblTplModelFactor> ModelFactor;
/*  10:    */   
/*  11:    */   public TblTplModel getTplModel()
/*  12:    */   {
/*  13: 19 */     return this.TplModel;
/*  14:    */   }
/*  15:    */   
/*  16:    */   public void setTplModel(TblTplModel tplModel)
/*  17:    */   {
/*  18: 23 */     this.TplModel = tplModel;
/*  19: 24 */     if ((this.ModelFactor != null) && (this.ModelFactor.size() > 0)) {
/*  20: 26 */       for (int i = 0; i < this.ModelFactor.size(); i++) {
/*  21: 28 */         ((TblTplModelFactor)this.ModelFactor.get(i)).setModelId(this.TplModel.getOId());
/*  22:    */       }
/*  23:    */     }
/*  24:    */   }
/*  25:    */   
/*  26:    */   public ArrayList<TblTplModelFactor> getModelFactor()
/*  27:    */   {
/*  28: 34 */     return this.ModelFactor;
/*  29:    */   }
/*  30:    */   
/*  31:    */   public void setModelFactor(ArrayList<TblTplModelFactor> modelFactor)
/*  32:    */   {
/*  33: 38 */     this.ModelFactor = modelFactor;
/*  34:    */   }
/*  35:    */   
/*  36:    */   public TplModelBase()
/*  37:    */   {
/*  38: 52 */     this.TplModel = new TblTplModel();
/*  39: 53 */     this.ModelFactor = new ArrayList();
/*  40:    */   }
/*  41:    */   
/*  42:    */   public void LoadItems(TblTplModelFactor[] items)
/*  43:    */   {
/*  44: 61 */     if (this.ModelFactor == null) {
/*  45: 63 */       this.ModelFactor = new ArrayList();
/*  46:    */     }
/*  47: 65 */     for (int i = 0; i < items.length; i++)
/*  48:    */     {
/*  49: 67 */       if (this.TplModel != null) {
/*  50: 69 */         items[i].setModelId(this.TplModel.getOId());
/*  51:    */       }
/*  52: 72 */       this.ModelFactor.add(items[i]);
/*  53:    */     }
/*  54:    */   }
/*  55:    */   
/*  56:    */   public void LoadItem(TblTplModelFactor item)
/*  57:    */   {
/*  58: 78 */     if (this.ModelFactor == null) {
/*  59: 80 */       this.ModelFactor = new ArrayList();
/*  60:    */     }
/*  61: 83 */     if (this.TplModel != null) {
/*  62: 85 */       item.setModelId(this.TplModel.getOId());
/*  63:    */     }
/*  64: 88 */     this.ModelFactor.add(item);
/*  65:    */   }
/*  66:    */   
/*  67:    */   public int Size()
/*  68:    */   {
/*  69: 94 */     return this.ModelFactor.size() - 1;
/*  70:    */   }
/*  71:    */   
/*  72:    */   public int[] GetMathTypes()
/*  73:    */   {
/*  74: 99 */     int size = Size();
/*  75:101 */     if (size == 0) {
/*  76:102 */       return null;
/*  77:    */     }
/*  78:104 */     int[] mats = new int[size];
/*  79:106 */     for (int i = 0; i < size; i++) {
/*  80:108 */       mats[i] = ((TblTplModelFactor)this.ModelFactor.get(i + 1)).getMTypeId();
/*  81:    */     }
/*  82:110 */     return mats;
/*  83:    */   }
/*  84:    */   
/*  85:    */   public double[] ModelFitted(double[][] x_Original, double maxVal, double minVal, boolean getMaxMin)
/*  86:    */   {
/*  87:115 */     maxVal = 4.9E-324D;
/*  88:116 */     minVal = 1.7976931348623157E+308D;
/*  89:118 */     if (x_Original == null) {
/*  90:119 */       return null;
/*  91:    */     }
/*  92:121 */     int length = x_Original[0].length;
/*  93:    */     
/*  94:123 */     double[] fits = new double[length];
/*  95:127 */     for (int i = 0; i < length; i++)
/*  96:    */     {
/*  97:129 */       for (int w = 0; w < this.ModelFactor.size(); w++)
/*  98:    */       {
/*  99:131 */         TblTplModelFactor factor = (TblTplModelFactor)this.ModelFactor.get(w);
/* 100:132 */         double val = 0.0D;
/* 101:135 */         if (factor.getSF() == 0) {
/* 102:136 */           val = factor.getK();
/* 103:    */         } else {
/* 104:141 */           val = factor.getK() * x_Original[(w - 1)][i];
/* 105:    */         }
/* 106:145 */         if ((val != (0.0D / 0.0D)) && (!Double.isInfinite(val))) {
/* 107:146 */           fits[i] += val;
/* 108:    */         }
/* 109:    */       }
/* 110:149 */       if (getMaxMin)
/* 111:    */       {
/* 112:152 */         if (maxVal < fits[i]) {
/* 113:153 */           maxVal = fits[i];
/* 114:    */         }
/* 115:155 */         if (minVal > fits[i]) {
/* 116:156 */           minVal = fits[i];
/* 117:    */         }
/* 118:    */       }
/* 119:    */     }
/* 120:161 */     return fits;
/* 121:    */   }
/* 122:    */   
/* 123:    */   public static TplModelBase GetModelBase(int[] maths)
/* 124:    */   {
/* 125:167 */     TplModelBase mBase = new TplModelBase();
/* 126:    */     
/* 127:169 */     TblTplModelFactor factor = null;
/* 128:170 */     if ((maths == null) || (maths.length == 0)) {
/* 129:172 */       return null;
/* 130:    */     }
/* 131:208 */     factor = new TblTplModelFactor();
/* 132:209 */     factor.setMTypeId(0);
/* 133:210 */     factor.setSF(0);
/* 134:211 */     mBase.LoadItem(factor);
/* 135:212 */     int[] arrayOfInt = maths;int j = maths.length;
/* 136:212 */     for (int i = 0; i < j; i++)
/* 137:    */     {
/* 138:212 */       int i = arrayOfInt[i];
/* 139:    */       
/* 140:214 */       factor = new TblTplModelFactor();
/* 141:215 */       factor.setMTypeId(i);
/* 142:216 */       factor.setSF(1);
/* 143:217 */       mBase.LoadItem(factor);
/* 144:    */     }
/* 145:221 */     return mBase;
/* 146:    */   }
/* 147:    */ }


/* Location:           D:\NARI\葛洲坝\test.jar
 * Qualified Name:     com.nari.slsd.he.Calculate.strv.TplModelBase
 * JD-Core Version:    0.7.0.1
 */