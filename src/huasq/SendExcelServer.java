/*   1:    */ package huasq;
/*   2:    */ 
/*   3:    */ import java.net.InetAddress;
/*   4:    */ import java.net.ServerSocket;
/*   5:    */ import java.net.Socket;
/*   6:    */ 
/*   7:    */ class SendExcelServer
/*   8:    */   extends Thread
/*   9:    */ {
/*  10:    */   ServerSocket server;
/*  11:    */   
/*  12:    */   SendExcelServer()
/*  13:    */   {
/*  14:    */     try
/*  15:    */     {
/*  16:423 */       this.server = new ServerSocket(7766, 10, InetAddress.getByName("10.144.118.80"));
/*  17:    */     }
/*  18:    */     catch (Exception e)
/*  19:    */     {
/*  20:425 */       e.printStackTrace();
/*  21:    */     }
/*  22:    */   }
/*  23:    */   
/*  24:    */   public void run()
/*  25:    */   {
/*  26:    */     try
/*  27:    */     {
/*  28:    */       for (;;)
/*  29:    */       {
/*  30:434 */         Socket socket = this.server.accept();
/*  31:435 */         SendExcel excel = new SendExcel(socket);
/*  32:436 */         excel.start();
/*  33:    */       }
/*  34:    */     }
/*  35:    */     catch (Exception e)
/*  36:    */     {
/*  37:438 */       e.printStackTrace();
/*  38:    */     }
/*  39:    */   }
/*  40:    */ }


/* Location:           D:\NARI\葛洲坝\test.jar
 * Qualified Name:     huasq.SendExcelServer
 * JD-Core Version:    0.7.0.1
 */