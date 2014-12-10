/*   1:    */ package huasq;
/*   2:    */ 
/*   3:    */ import java.io.Serializable;
/*   4:    */ 
/*   5:    */ class TblDefSurveypoints
/*   6:    */   implements Serializable
/*   7:    */ {
/*   8:    */   private static final long serialVersionUID = 1L;
/*   9:578 */   public short oId = -1;
/*  10:    */   public short SourceId;
/*  11:580 */   public String ThirdPName = "";
/*  12:581 */   public String oName = "";
/*  13:    */   public byte VType;
/*  14:    */   public byte DecNum;
/*  15:584 */   public String Unit = "";
/*  16:585 */   public String Description = "";
/*  17:586 */   public String DeptGUID = "";
/*  18:    */   
/*  19:    */   public TblDefSurveypoints clone()
/*  20:    */   {
/*  21:589 */     TblDefSurveypoints clone = new TblDefSurveypoints();
/*  22:590 */     clone.SourceId = this.SourceId;
/*  23:591 */     clone.DecNum = this.DecNum;
/*  24:592 */     clone.DeptGUID = this.DeptGUID;
/*  25:593 */     clone.Description = this.Description;
/*  26:594 */     clone.oId = this.oId;
/*  27:595 */     clone.oName = this.oName;
/*  28:596 */     clone.ThirdPName = this.ThirdPName;
/*  29:597 */     clone.Unit = this.Unit;
/*  30:598 */     clone.VType = this.VType;
/*  31:599 */     return clone;
/*  32:    */   }
/*  33:    */ }


/* Location:           D:\NARI\葛洲坝\test.jar
 * Qualified Name:     huasq.TblDefSurveypoints
 * JD-Core Version:    0.7.0.1
 */