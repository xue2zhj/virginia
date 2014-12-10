/*   1:    */ package huasq;
/*   2:    */ 
/*   3:    */ class CheckXOR
/*   4:    */   extends CheckCode
/*   5:    */ {
/*   6:    */   public byte[] GetCheckCode(byte[] data, int srcIndex, int srcLen)
/*   7:    */   {
/*   8:475 */     byte[] chkCode = new byte[1];
/*   9:476 */     for (int i = srcIndex; i < srcIndex + srcLen; i++)
/*  10:    */     {
/*  11:477 */       int tmp14_13 = 0; byte[] tmp14_11 = chkCode;tmp14_11[tmp14_13] = ((byte)(tmp14_11[tmp14_13] ^ data[i]));
/*  12:    */     }
/*  13:479 */     return chkCode;
/*  14:    */   }
/*  15:    */ }


/* Location:           D:\NARI\葛洲坝\test.jar
 * Qualified Name:     huasq.CheckXOR
 * JD-Core Version:    0.7.0.1
 */