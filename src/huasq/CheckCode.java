/*   1:    */ package huasq;
/*   2:    */ 
/*   3:    */ abstract class CheckCode
/*   4:    */ {
/*   5:    */   public boolean Check(byte[] data, int srcIndex, int srcLen, byte[] vCode)
/*   6:    */   {
/*   7:534 */     byte[] chkCode = GetCheckCode(data, srcIndex, srcLen);
/*   8:535 */     if (chkCode.length != vCode.length) {
/*   9:536 */       return false;
/*  10:    */     }
/*  11:537 */     for (int i = 0; i < chkCode.length; i++) {
/*  12:538 */       if (chkCode[i] != vCode[i]) {
/*  13:539 */         return false;
/*  14:    */       }
/*  15:    */     }
/*  16:540 */     return true;
/*  17:    */   }
/*  18:    */   
/*  19:    */   public abstract byte[] GetCheckCode(byte[] paramArrayOfByte, int paramInt1, int paramInt2);
/*  20:    */ }


/* Location:           D:\NARI\葛洲坝\test.jar
 * Qualified Name:     huasq.CheckCode
 * JD-Core Version:    0.7.0.1
 */