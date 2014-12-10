/*   1:    */ package com.nari.slsd.he.Calculate.strv;
/*   2:    */ 
/*   3:    */ public class MFullRegression
/*   4:    */   extends ModelCalculate
/*   5:    */ {
/*   6:    */   public MFullRegression(TplModelBase mBase, double[][] x_Original, double[][] y_Original, int progStart, int progEnd)
/*   7:    */   {
/*   8: 15 */     super(mBase, x_Original, y_Original, progStart, progEnd);
/*   9:    */   }
/*  10:    */   
/*  11:    */   public boolean Run()
/*  12:    */   {
/*  13: 25 */     Init_Data();
/*  14:    */     
/*  15:    */ 
/*  16: 28 */     double[][] x = new double[this.X_Num + 1][this.Sample_Num];
/*  17: 30 */     for (int i = 0; i < this.Sample_Num; i++)
/*  18:    */     {
/*  19: 32 */       x[0][i] = 1.0D;
/*  20: 33 */       for (int j = 0; j < this.X_Num; j++) {
/*  21: 34 */         x[(j + 1)][i] = (this.X_Mtx.GetData(j, i) - this.X_Mtx.GetData(j, 0));
/*  22:    */       }
/*  23:    */     }
/*  24: 38 */     MatrixMath xF_Mtx = new MatrixMath(x);
/*  25:    */     
/*  26:    */ 
/*  27: 41 */     MatrixMath g1_Mtx = xF_Mtx.TransposMultiply(false);
/*  28:    */     
/*  29:    */ 
/*  30: 44 */     MatrixMath g2_Mtx = g1_Mtx.MatInvert();
/*  31:    */     
/*  32:    */ 
/*  33: 47 */     xF_Mtx.set_Transpos(true);
/*  34: 48 */     MatrixMath g_Mtx = MatrixMath.Multiply(xF_Mtx, g2_Mtx);
/*  35:    */     
/*  36:    */ 
/*  37: 51 */     double[][] gy = new double[this.Y_Num][this.Sample_Num];
/*  38: 53 */     for (int i = 0; i < this.Y_Num; i++) {
/*  39: 55 */       for (int j = 0; j < this.Sample_Num; j++) {
/*  40: 56 */         gy[i][j] = (this.Y_Mtx.GetData(i, j) - this.Y_Mtx.GetData(i, 0));
/*  41:    */       }
/*  42:    */     }
/*  43: 59 */     MatrixMath gy_Mtx = new MatrixMath(gy);
/*  44:    */     
/*  45:    */ 
/*  46: 62 */     MatrixMath coeff_Mtx = MatrixMath.Multiply(gy_Mtx, g_Mtx);
/*  47:    */     
/*  48: 64 */     this.Coefficient = coeff_Mtx.get_Data();
/*  49: 67 */     for (int i = 0; i < this.Y_Num; i++)
/*  50:    */     {
/*  51: 69 */       this.Constant[i] = this.Coefficient[i][0];
/*  52: 70 */       this.Coefficient[i][0] += this.Y_Mtx.GetData(i, 0);
/*  53: 72 */       for (int j = 0; j < this.X_Num; j++) {
/*  54: 73 */         this.Coefficient[i][0] -= this.Coefficient[i][(j + 1)] * this.X_Mtx.GetData(j, 0);
/*  55:    */       }
/*  56:    */     }
/*  57: 77 */     for (int i = 0; i < this.Sample_Num; i++) {
/*  58: 79 */       for (int j = 0; j < this.X_Num; j++) {
/*  59: 80 */         x[(j + 1)][i] = this.X_Mtx.GetData(j, i);
/*  60:    */       }
/*  61:    */     }
/*  62: 85 */     xF_Mtx.set_Transpos(false);
/*  63: 86 */     coeff_Mtx.set_Transpos(false);
/*  64: 87 */     MatrixMath titted_Mtx = MatrixMath.Multiply(coeff_Mtx, xF_Mtx);
/*  65: 91 */     for (int i = 0; i < this.Y_Num; i++) {
/*  66: 93 */       this.Correlation[i] = MatrixMath.CorrelationOfRow(this.Y_Mtx, i, titted_Mtx, i);
/*  67:    */     }
/*  68: 99 */     titted_Mtx.set_K(-1.0D);
/*  69:    */     
/*  70:    */ 
/*  71:102 */     MatrixMath gg2F_Mtx = MatrixMath.Add(this.Y_Mtx, titted_Mtx);
/*  72:    */     
/*  73:104 */     MatrixMath gg3_Mtx = gg2F_Mtx.TransposMultiply(false);
/*  74:108 */     for (int j = 0; j < this.Y_Num; j++) {
/*  75:109 */       this.Residual[j] = (gg3_Mtx.GetData(j, j) / (this.Sample_Num - this.X_Num - 1));
/*  76:    */     }
/*  77:111 */     this.Fitted = titted_Mtx.get_Data();
/*  78:    */     
/*  79:    */ 
/*  80:114 */     FillModelCalResult();
/*  81:    */     
/*  82:116 */     return true;
/*  83:    */   }
/*  84:    */ }


/* Location:           D:\NARI\葛洲坝\test.jar
 * Qualified Name:     com.nari.slsd.he.Calculate.strv.MFullRegression
 * JD-Core Version:    0.7.0.1
 */