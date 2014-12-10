/*   1:    */ package com.nari.slsd.he.Calculate.strv;
/*   2:    */ 
/*   3:    */ import java.io.PrintStream;
/*   4:    */ import java.text.DecimalFormat;
/*   5:    */ import java.util.ArrayList;
/*   6:    */ 
/*   7:    */ public abstract class ModelCalculate
/*   8:    */ {
/*   9:    */   protected MatrixMath X_Mtx;
/*  10:    */   protected MatrixMath Y_Mtx;
/*  11:    */   protected TplModelBase MBase;
/*  12:    */   protected int ProgEnd;
/*  13:    */   protected int Sample_Num;
/*  14:    */   protected int X_Num;
/*  15:    */   protected int Y_Num;
/*  16:    */   protected double[] Correlation;
/*  17:    */   protected double[] Residual;
/*  18:    */   public double[][] Coefficient;
/*  19:    */   protected double[] Constant;
/*  20:    */   protected double[][] Fitted;
/*  21:    */   protected int ProgStart;
/*  22:    */   
/*  23:    */   public abstract boolean Run();
/*  24:    */   
/*  25:    */   protected void Init_Data()
/*  26:    */   {
/*  27: 88 */     this.Fitted = new double[this.Y_Num][this.Sample_Num];
/*  28:    */     
/*  29: 90 */     this.Correlation = new double[this.Y_Num];
/*  30:    */     
/*  31: 92 */     this.Residual = new double[this.Y_Num];
/*  32:    */     
/*  33: 94 */     this.Constant = new double[this.Y_Num];
/*  34:    */     
/*  35: 96 */     this.Coefficient = new double[this.Y_Num][this.X_Num + 1];
/*  36:    */   }
/*  37:    */   
/*  38:    */   public ModelCalculate(TplModelBase mBase, double[][] x_Original, double[][] y_Original, int progStart, int progEnd)
/*  39:    */   {
/*  40:103 */     this.MBase = mBase;
/*  41:    */     
/*  42:105 */     this.X_Mtx = new MatrixMath();
/*  43:106 */     this.Y_Mtx = new MatrixMath();
/*  44:    */     
/*  45:108 */     this.X_Mtx.set_Data(x_Original);
/*  46:109 */     this.Y_Mtx.set_Data(y_Original);
/*  47:    */     
/*  48:111 */     this.ProgStart = progStart;
/*  49:112 */     this.ProgEnd = progEnd;
/*  50:    */     
/*  51:    */ 
/*  52:115 */     this.Sample_Num = this.X_Mtx.ColLen();
/*  53:116 */     this.X_Num = this.X_Mtx.RowLen();
/*  54:117 */     this.Y_Num = this.Y_Mtx.RowLen();
/*  55:    */   }
/*  56:    */   
/*  57:    */   protected void FillModelCalResult()
/*  58:    */   {
/*  59:126 */     ArrayList<TblTplModelFactor> factors = this.MBase.getModelFactor();
/*  60:    */     
/*  61:128 */     double valf = 0.0D;
/*  62:    */     
/*  63:    */ 
/*  64:131 */     int k = 0;
/*  65:133 */     for (int j = 0; j < this.Coefficient[0].length; j++)
/*  66:    */     {
/*  67:135 */       valf = this.Coefficient[k][j];
/*  68:136 */       if ((Double.isNaN(valf)) || (Double.isInfinite(valf))) {
/*  69:137 */         ((TblTplModelFactor)factors.get(j)).setK(0.0D);
/*  70:    */       } else {
/*  71:139 */         ((TblTplModelFactor)factors.get(j)).setK(valf);
/*  72:    */       }
/*  73:    */     }
/*  74:143 */     DecimalFormat fmt = new DecimalFormat("0.####");
/*  75:145 */     if ((this.Correlation != null) && (this.Correlation.length >= 0)) {
/*  76:148 */       this.MBase.getTplModel().setRelK(Double.valueOf(fmt.format(this.Correlation[0])).doubleValue());
/*  77:    */     }
/*  78:150 */     if ((this.Residual != null) && (this.Residual.length >= 0)) {
/*  79:153 */       this.MBase.getTplModel().setStdDiff(Double.valueOf(fmt.format(this.Residual[0])).doubleValue());
/*  80:    */     }
/*  81:    */   }
/*  82:    */   
/*  83:    */   public static void GetResiduals(double[][] x_Original, double[][] y_Original, ArrayList<Double> Correlation, ArrayList<Double> Residual)
/*  84:    */   {
/*  85:167 */     MatrixMath X_Mtx = new MatrixMath();
/*  86:168 */     MatrixMath Y_Mtx = new MatrixMath();
/*  87:    */     
/*  88:170 */     X_Mtx.set_Data(x_Original);
/*  89:171 */     Y_Mtx.set_Data(y_Original);
/*  90:    */     
/*  91:    */ 
/*  92:174 */     int Sample_Num = X_Mtx.ColLen();
/*  93:175 */     int X_Num = X_Mtx.RowLen();
/*  94:176 */     int Y_Num = Y_Mtx.RowLen();
/*  95:    */     
/*  96:    */ 
/*  97:179 */     double[][] x = new double[X_Num + 1][Sample_Num];
/*  98:181 */     for (int i = 0; i < Sample_Num; i++)
/*  99:    */     {
/* 100:183 */       x[0][i] = 1.0D;
/* 101:184 */       for (int j = 0; j < X_Num; j++) {
/* 102:185 */         x[(j + 1)][i] = (X_Mtx.GetData(j, i) - X_Mtx.GetData(j, 0));
/* 103:    */       }
/* 104:    */     }
/* 105:188 */     MatrixMath xF_Mtx = new MatrixMath(x);
/* 106:    */     
/* 107:    */ 
/* 108:191 */     MatrixMath g1_Mtx = xF_Mtx.TransposMultiply(false);
/* 109:    */     
/* 110:    */ 
/* 111:194 */     MatrixMath g2_Mtx = g1_Mtx.MatInvert();
/* 112:    */     
/* 113:    */ 
/* 114:197 */     xF_Mtx.set_Transpos(true);
/* 115:    */     
/* 116:199 */     MatrixMath g_Mtx = MatrixMath.Multiply(xF_Mtx, g2_Mtx);
/* 117:    */     
/* 118:201 */     double[][] gy = new double[Y_Num][Sample_Num];
/* 119:203 */     for (int i = 0; i < Y_Num; i++) {
/* 120:205 */       for (int j = 0; j < Sample_Num; j++) {
/* 121:206 */         gy[i][j] = (Y_Mtx.GetData(i, j) - Y_Mtx.GetData(i, 0));
/* 122:    */       }
/* 123:    */     }
/* 124:209 */     MatrixMath gy_Mtx = new MatrixMath(gy);
/* 125:    */     
/* 126:211 */     MatrixMath coeff_Mtx = MatrixMath.Multiply(gy_Mtx, g_Mtx);
/* 127:214 */     for (int i = 0; i < Sample_Num; i++) {
/* 128:216 */       for (int j = 0; j < X_Num; j++) {
/* 129:217 */         x[(j + 1)][i] = X_Mtx.GetData(j, i);
/* 130:    */       }
/* 131:    */     }
/* 132:221 */     xF_Mtx.set_Transpos(false);
/* 133:222 */     coeff_Mtx.set_Transpos(false);
/* 134:223 */     MatrixMath titted_Mtx = MatrixMath.Multiply(coeff_Mtx, xF_Mtx);
/* 135:228 */     for (int i = 0; i < Y_Num; i++)
/* 136:    */     {
/* 137:230 */       double aa = MatrixMath.CorrelationOfRow(Y_Mtx, i, titted_Mtx, i);
/* 138:231 */       Correlation.add(Double.valueOf(aa));
/* 139:232 */       System.out.println("Correlation[i]" + aa);
/* 140:    */     }
/* 141:236 */     titted_Mtx.set_K(-1.0D);
/* 142:    */     
/* 143:    */ 
/* 144:239 */     MatrixMath gg2F_Mtx = MatrixMath.Add(Y_Mtx, titted_Mtx);
/* 145:240 */     MatrixMath gg3_Mtx = gg2F_Mtx.TransposMultiply(false);
/* 146:243 */     for (int j = 0; j < Y_Num; j++)
/* 147:    */     {
/* 148:245 */       double aa = gg3_Mtx.GetData(j, j) / (Sample_Num - X_Num - 1);
/* 149:246 */       Residual.add(Double.valueOf(aa));
/* 150:247 */       System.out.println("Residual[j]" + aa);
/* 151:    */     }
/* 152:    */   }
/* 153:    */ }


/* Location:           D:\NARI\葛洲坝\test.jar
 * Qualified Name:     com.nari.slsd.he.Calculate.strv.ModelCalculate
 * JD-Core Version:    0.7.0.1
 */