/*   1:    */ package com.nari.slsd.he.Calculate.strv;
/*   2:    */ 
/*   3:    */ public class MStepwiseRegression
/*   4:    */   extends ModelCalculate
/*   5:    */ {
/*   6:    */   public MStepwiseRegression(TplModelBase mBase, double[][] x_Original, double[][] y_Original, int progStart, int progEnd)
/*   7:    */   {
/*   8:  7 */     super(mBase, x_Original, y_Original, progStart, progEnd);
/*   9:    */   }
/*  10:    */   
/*  11:    */   public boolean Run()
/*  12:    */   {
/*  13: 16 */     double f1 = 1.0D;
/*  14: 17 */     double f2 = 1.0D;
/*  15:    */     
/*  16:    */ 
/*  17: 20 */     Init_Data();
/*  18: 23 */     for (int i = 0; i < this.Y_Num; i++) {
/*  19: 28 */       Stepwise_Regression(i, f1, f2);
/*  20:    */     }
/*  21: 32 */     FillModelCalResult();
/*  22: 33 */     return true;
/*  23:    */   }
/*  24:    */   
/*  25:    */   private void Stepwise_Regression(int yNo, double f1, double f2)
/*  26:    */   {
/*  27: 49 */     int[] yzkg = new int[this.X_Num];
/*  28:    */     
/*  29: 51 */     double[][] r = new double[this.X_Num + 1][this.X_Num + 1];
/*  30: 53 */     for (int i = 0; i < this.X_Num; i++)
/*  31:    */     {
/*  32: 56 */       for (int j = 0; j < this.X_Num; j++) {
/*  33: 57 */         r[i][j] = MatrixMath.CorrelationOfRow(this.X_Mtx, i, this.X_Mtx, j);
/*  34:    */       }
/*  35: 60 */       r[i][this.X_Num] = MatrixMath.CorrelationOfRow(this.X_Mtx, i, this.Y_Mtx, yNo);
/*  36: 61 */       r[this.X_Num][i] = r[i][this.X_Num];
/*  37:    */     }
/*  38: 72 */     r[this.X_Num][this.X_Num] = 1.0D;
/*  39:    */     
/*  40: 74 */     int facnum = 0;int step = 0;
/*  41:    */     int cont;
/*  42:    */     do
/*  43:    */     {
/*  44: 77 */       double vmax = 4.9E-324D;
/*  45: 78 */       double vmin = 1.7976931348623157E+308D;
/*  46: 79 */       step++;cont = 0;double tol = -99999.0D;
/*  47: 81 */       if (step > 1) {
/*  48: 82 */         tol = 0.0001D;
/*  49:    */       }
/*  50: 84 */       if (step > 2)
/*  51:    */       {
/*  52: 86 */         int km = -1;
/*  53: 88 */         for (int i = 0; i < this.X_Num; i++) {
/*  54: 90 */           if ((yzkg[i] != 0) && (r[i][i] > tol))
/*  55:    */           {
/*  56: 92 */             double v = Math.abs(r[this.X_Num][i] * r[i][this.X_Num] / r[i][i]);
/*  57: 94 */             if (v < vmin)
/*  58:    */             {
/*  59: 96 */               vmin = v;
/*  60: 97 */               km = i;
/*  61:    */             }
/*  62:    */           }
/*  63:    */         }
/*  64:101 */         if (km >= 0)
/*  65:    */         {
/*  66:103 */           vmin *= (this.Sample_Num - facnum - 1) / r[this.X_Num][this.X_Num];
/*  67:106 */           if (vmin <= f2)
/*  68:    */           {
/*  69:108 */             yzkg[km] = 0;
/*  70:109 */             facnum--;
/*  71:110 */             cont = 1;
/*  72:111 */             UpdateR(km, r);
/*  73:    */           }
/*  74:    */         }
/*  75:    */       }
/*  76:116 */       int km = -1;
/*  77:117 */       for (int i = 0; i < this.X_Num; i++) {
/*  78:119 */         if ((yzkg[i] == 0) && (r[i][i] > tol))
/*  79:    */         {
/*  80:121 */           double v = r[this.X_Num][i] * r[i][this.X_Num] / r[i][i];
/*  81:122 */           if (v > vmax)
/*  82:    */           {
/*  83:124 */             vmax = v;
/*  84:125 */             km = i;
/*  85:    */           }
/*  86:    */         }
/*  87:    */       }
/*  88:130 */       if (km >= 0)
/*  89:    */       {
/*  90:132 */         vmax *= (this.Sample_Num - facnum - 2) / (r[this.X_Num][this.X_Num] - vmax);
/*  91:134 */         if ((vmax > f1) || (step <= 2))
/*  92:    */         {
/*  93:136 */           yzkg[km] = 1;
/*  94:137 */           facnum++;
/*  95:138 */           cont = 1;
/*  96:139 */           UpdateR(km, r);
/*  97:    */         }
/*  98:    */       }
/*  99:144 */     } while (cont == 1);
/* 100:156 */     this.Correlation[yNo] = Math.sqrt(1.0D - r[this.X_Num][this.X_Num]);
/* 101:    */     
/* 102:158 */     double yMtx_Variance = this.Y_Mtx.VarianceOfRow(yNo);
/* 103:    */     
/* 104:160 */     this.Residual[yNo] = Math.sqrt(yMtx_Variance * r[this.X_Num][this.X_Num]);
/* 105:164 */     for (int i = 0; i < this.X_Num; i++) {
/* 106:165 */       if (yzkg[i] != 0) {
/* 107:166 */         this.Coefficient[yNo][(i + 1)] = (r[i][this.X_Num] * Math.sqrt(yMtx_Variance / this.X_Mtx.VarianceOfRow(i)));
/* 108:    */       }
/* 109:    */     }
/* 110:168 */     this.Coefficient[yNo][0] = (this.Y_Mtx.AvgOfRow(yNo) - this.Y_Mtx.get_Data()[yNo][0]);
/* 111:170 */     for (int i = 0; i < this.X_Num; i++) {
/* 112:171 */       this.Coefficient[yNo][0] -= this.Coefficient[yNo][(i + 1)] * (this.X_Mtx.AvgOfRow(i) - this.X_Mtx.get_Data()[i][0]);
/* 113:    */     }
/* 114:173 */     this.Constant[yNo] = this.Coefficient[yNo][0];
/* 115:174 */     this.Coefficient[yNo][0] += this.Y_Mtx.get_Data()[yNo][0];
/* 116:176 */     for (int i = 0; i < this.X_Num; i++) {
/* 117:177 */       this.Coefficient[yNo][0] -= this.Coefficient[yNo][(i + 1)] * this.X_Mtx.get_Data()[i][0];
/* 118:    */     }
/* 119:180 */     for (int i = 0; i < this.Sample_Num; i++)
/* 120:    */     {
/* 121:182 */       this.Fitted[yNo][i] = this.Coefficient[yNo][0];
/* 122:183 */       for (int j = 0; j < this.X_Num; j++) {
/* 123:184 */         this.Fitted[yNo][i] += this.Coefficient[yNo][(j + 1)] * this.X_Mtx.get_Data()[j][i];
/* 124:    */       }
/* 125:    */     }
/* 126:    */   }
/* 127:    */   
/* 128:    */   private void UpdateR(int km, double[][] r)
/* 129:    */   {
/* 130:196 */     double[] rtoj = new double[this.X_Num + 1];
/* 131:    */     
/* 132:198 */     double rmm = r[km][km];
/* 133:200 */     for (int j = 0; j < this.X_Num + 1; j++) {
/* 134:201 */       rtoj[j] = r[km][j];
/* 135:    */     }
/* 136:203 */     for (int i = 0; i < this.X_Num + 1; i++)
/* 137:    */     {
/* 138:205 */       double rim = r[i][km];
/* 139:206 */       for (int j = 0; j < this.X_Num + 1; j++)
/* 140:    */       {
/* 141:208 */         double rmj = rtoj[j];
/* 142:210 */         if ((i != km) && (j != km)) {
/* 143:211 */           r[i][j] -= rim * rmj / rmm;
/* 144:    */         }
/* 145:213 */         if (j != km) {
/* 146:214 */           r[km][j] = (rmj / rmm);
/* 147:    */         }
/* 148:    */       }
/* 149:217 */       if (i != km) {
/* 150:218 */         r[i][km] = (-rim / rmm);
/* 151:    */       }
/* 152:    */     }
/* 153:220 */     r[km][km] = (1.0D / rmm);
/* 154:    */   }
/* 155:    */ }


/* Location:           D:\NARI\葛洲坝\test.jar
 * Qualified Name:     com.nari.slsd.he.Calculate.strv.MStepwiseRegression
 * JD-Core Version:    0.7.0.1
 */