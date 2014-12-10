/*   1:    */ package huasq;
/*   2:    */ 
/*   3:    */ class CheckSum
/*   4:    */   extends CheckCode
/*   5:    */ {
/*   6:    */   public byte[] GetCheckCode(byte[] data, int srcIndex, int count)
/*   7:    */   {
/*   8:488 */     long sum = 0L;
/*   9:489 */     long s = 0L;
/*  10:492 */     while (count > 1)
/*  11:    */     {
/*  12:494 */       int temp1 = data[(srcIndex + 1)] < 0 ? data[(srcIndex + 1)] + 256 : data[(srcIndex + 1)];
/*  13:495 */       int temp2 = data[srcIndex] < 0 ? data[srcIndex] + 256 : data[srcIndex];
/*  14:496 */       s = (temp1 << 8) + temp2;
/*  15:497 */       sum += s;
/*  16:498 */       srcIndex += 2;
/*  17:499 */       count -= 2;
/*  18:    */     }
/*  19:502 */     if (count > 0)
/*  20:    */     {
/*  21:503 */       s = (short)data[srcIndex];
/*  22:504 */       sum += s;
/*  23:    */     }
/*  24:507 */     while (sum >> 16 > 0L) {
/*  25:508 */       sum = (sum & 0xFFFF) + (sum >> 16);
/*  26:    */     }
/*  27:509 */     s = (short)(int)(sum ^ 0xFFFFFFFF);
/*  28:510 */     byte[] code = {
/*  29:511 */       (byte)(int)(s & 0xFF), 
/*  30:512 */       (byte)(int)(s >> 8 & 0xFF) };
/*  31:513 */     return code;
/*  32:    */   }
/*  33:    */ }


/* Location:           D:\NARI\葛洲坝\test.jar
 * Qualified Name:     huasq.CheckSum
 * JD-Core Version:    0.7.0.1
 */