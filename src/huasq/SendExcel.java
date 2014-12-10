/*   1:    */ package huasq;
/*   2:    */ 
/*   3:    */ import java.io.FileInputStream;
/*   4:    */ import java.io.InputStream;
/*   5:    */ import java.io.OutputStream;
/*   6:    */ import java.io.PrintStream;
/*   7:    */ import java.net.Socket;
/*   8:    */ import java.util.Arrays;
/*   9:    */ 
/*  10:    */ class SendExcel
/*  11:    */   extends Thread
/*  12:    */ {
/*  13:    */   Socket socket;
/*  14:    */   
/*  15:    */   SendExcel(Socket s)
/*  16:    */   {
/*  17:448 */     this.socket = s;
/*  18:    */   }
/*  19:    */   
/*  20:    */   public void run()
/*  21:    */   {
/*  22:    */     try
/*  23:    */     {
/*  24:454 */       InputStream is = this.socket.getInputStream();
/*  25:455 */       byte[] b = new byte[8388608];
/*  26:456 */       int l = is.read(b);
/*  27:457 */       System.out.println(new String(Arrays.copyOf(b, l), "utf-8"));
/*  28:    */       
/*  29:459 */       FileInputStream f = new FileInputStream("D:\\tmp001.xls");
/*  30:460 */       l = f.read(b);
/*  31:461 */       f.close();
/*  32:    */       
/*  33:463 */       OutputStream os = this.socket.getOutputStream();
/*  34:464 */       os.write(Arrays.copyOf(b, l));
/*  35:465 */       this.socket.close();
/*  36:    */     }
/*  37:    */     catch (Exception localException) {}
/*  38:    */   }
/*  39:    */ }


/* Location:           D:\NARI\葛洲坝\test.jar
 * Qualified Name:     huasq.SendExcel
 * JD-Core Version:    0.7.0.1
 */