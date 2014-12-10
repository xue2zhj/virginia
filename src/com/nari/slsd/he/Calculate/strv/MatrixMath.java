/*    1:     */ package com.nari.slsd.he.Calculate.strv;
/*    2:     */ 
/*    3:     */ public class MatrixMath
/*    4:     */ {
/*    5:     */   private double[][] _Data;
/*    6:  16 */   private boolean _Transpos = false;
/*    7:  21 */   private double _K = 1.0D;
/*    8:  26 */   private int _ColLen = -1;
/*    9:  26 */   private int _RowLen = -1;
/*   10:     */   
/*   11:     */   public int RowLen()
/*   12:     */   {
/*   13:  34 */     if (this._Data.length == 0) {
/*   14:  35 */       return 0;
/*   15:     */     }
/*   16:  37 */     return this._Transpos ? this._Data[0].length : this._Data.length;
/*   17:     */   }
/*   18:     */   
/*   19:     */   public int ColLen()
/*   20:     */   {
/*   21:  45 */     if (this._Data.length == 0) {
/*   22:  46 */       return 0;
/*   23:     */     }
/*   24:  48 */     return this._Transpos ? this._Data.length : this._Data[0].length;
/*   25:     */   }
/*   26:     */   
/*   27:     */   public MatrixMath()
/*   28:     */   {
/*   29:  56 */     this._ColLen = (this._RowLen = -1);
/*   30:     */   }
/*   31:     */   
/*   32:     */   public double get_K()
/*   33:     */   {
/*   34:  60 */     return this._K;
/*   35:     */   }
/*   36:     */   
/*   37:     */   public void set_K(double _K)
/*   38:     */   {
/*   39:  64 */     this._K = _K;
/*   40:     */   }
/*   41:     */   
/*   42:     */   public double[][] get_Data()
/*   43:     */   {
/*   44:  68 */     return this._Data;
/*   45:     */   }
/*   46:     */   
/*   47:     */   public boolean is_Transpos()
/*   48:     */   {
/*   49:  72 */     return this._Transpos;
/*   50:     */   }
/*   51:     */   
/*   52:     */   public void set_Transpos(boolean _Transpos)
/*   53:     */   {
/*   54:  76 */     this._Transpos = _Transpos;
/*   55:  77 */     this._ColLen = ColLen();
/*   56:  78 */     this._RowLen = RowLen();
/*   57:     */   }
/*   58:     */   
/*   59:     */   public void set_Data(double[][] _Data)
/*   60:     */   {
/*   61:  84 */     this._Data = _Data;
/*   62:     */     
/*   63:  86 */     this._ColLen = ColLen();
/*   64:  87 */     this._RowLen = RowLen();
/*   65:     */   }
/*   66:     */   
/*   67:     */   public MatrixMath(double[][] data)
/*   68:     */   {
/*   69:  95 */     this._Data = data;
/*   70:     */     
/*   71:  97 */     this._ColLen = ColLen();
/*   72:  98 */     this._RowLen = RowLen();
/*   73:     */   }
/*   74:     */   
/*   75:     */   public double GetData(int row, int col)
/*   76:     */   {
/*   77: 107 */     double v = this._Transpos ? this._Data[col][row] : this._Data[row][col];
/*   78: 108 */     return this._K == 1.0D ? v : this._K * v;
/*   79:     */   }
/*   80:     */   
/*   81:     */   public void setData(int row, int col, double value)
/*   82:     */   {
/*   83: 114 */     if (this._K != 1.0D) {
/*   84: 115 */       value /= this._K;
/*   85:     */     }
/*   86: 117 */     if (this._Transpos) {
/*   87: 118 */       this._Data[col][row] = value;
/*   88:     */     } else {
/*   89: 120 */       this._Data[row][col] = value;
/*   90:     */     }
/*   91:     */   }
/*   92:     */   
/*   93:     */   public double[] GetRowData(int row)
/*   94:     */   {
/*   95: 128 */     double[] dataCol = new double[this._ColLen];
/*   96: 131 */     if (this._Transpos) {
/*   97: 133 */       for (int c = 0; c < this._ColLen; c++)
/*   98:     */       {
/*   99: 135 */         double v = this._Data[c][row];
/*  100: 136 */         dataCol[c] = (this._K == 1.0D ? v : this._K * v);
/*  101:     */       }
/*  102:     */     } else {
/*  103: 141 */       for (int c = 0; c < this._ColLen; c++)
/*  104:     */       {
/*  105: 143 */         double v = this._Data[row][c];
/*  106: 144 */         dataCol[c] = (this._K == 1.0D ? v : this._K * v);
/*  107:     */       }
/*  108:     */     }
/*  109: 147 */     return dataCol;
/*  110:     */   }
/*  111:     */   
/*  112:     */   public void SetRowData(int row, double[] value)
/*  113:     */   {
/*  114: 152 */     if (value.length != this._ColLen) {
/*  115: 153 */       return;
/*  116:     */     }
/*  117: 155 */     if (this._Transpos) {
/*  118: 157 */       for (int c = 0; c < this._ColLen; c++)
/*  119:     */       {
/*  120: 159 */         double v = value[c];
/*  121: 161 */         if (this._K != 1.0D) {
/*  122: 162 */           v /= this._K;
/*  123:     */         }
/*  124: 164 */         this._Data[c][row] = v;
/*  125:     */       }
/*  126:     */     } else {
/*  127: 169 */       for (int c = 0; c < this._ColLen; c++)
/*  128:     */       {
/*  129: 171 */         double v = value[c];
/*  130: 173 */         if (this._K != 1.0D) {
/*  131: 174 */           v /= this._K;
/*  132:     */         }
/*  133: 176 */         this._Data[row][c] = v;
/*  134:     */       }
/*  135:     */     }
/*  136:     */   }
/*  137:     */   
/*  138:     */   public double[][] DataCopy()
/*  139:     */   {
/*  140:     */     double[][] data;
/*  141: 190 */     if (!this._Transpos)
/*  142:     */     {
/*  143: 193 */       double[][] data = new double[this._Data.length][this._Data[0].length];
/*  144: 195 */       for (int r = 0; r < this._Data.length; r++) {
/*  145: 196 */         for (int c = 0; c < this._Data[0].length; c++) {
/*  146: 197 */           data[r][c] = this._Data[r][c];
/*  147:     */         }
/*  148:     */       }
/*  149: 200 */       if (this._K != 1.0D) {
/*  150: 202 */         for (int r = 0; r < this._RowLen; r++) {
/*  151: 203 */           for (int c = 0; c < this._ColLen; c++) {
/*  152: 204 */             data[r][c] *= this._K;
/*  153:     */           }
/*  154:     */         }
/*  155:     */       }
/*  156:     */     }
/*  157:     */     else
/*  158:     */     {
/*  159: 210 */       data = new double[this._RowLen][this._ColLen];
/*  160: 212 */       if (this._K == 1.0D) {
/*  161: 214 */         for (int r = 0; r < this._RowLen; r++) {
/*  162: 215 */           for (int c = 0; c < this._ColLen; c++) {
/*  163: 216 */             data[r][c] = this._Data[c][r];
/*  164:     */           }
/*  165:     */         }
/*  166:     */       } else {
/*  167: 220 */         for (int r = 0; r < this._RowLen; r++) {
/*  168: 221 */           for (int c = 0; c < this._ColLen; c++) {
/*  169: 222 */             data[r][c] = (this._Data[c][r] * this._K);
/*  170:     */           }
/*  171:     */         }
/*  172:     */       }
/*  173:     */     }
/*  174: 226 */     return data;
/*  175:     */   }
/*  176:     */   
/*  177:     */   public MatrixMath Clone()
/*  178:     */   {
/*  179: 235 */     MatrixMath newMtx = new MatrixMath();
/*  180: 236 */     newMtx.set_Data((double[][])get_Data().clone());
/*  181:     */     
/*  182: 238 */     newMtx.set_K(get_K());
/*  183: 239 */     newMtx._Transpos = this._Transpos;
/*  184: 240 */     newMtx._RowLen = this._RowLen;
/*  185: 241 */     newMtx._ColLen = this._ColLen;
/*  186:     */     
/*  187: 243 */     return newMtx;
/*  188:     */   }
/*  189:     */   
/*  190:     */   public static MatrixMath Add(MatrixMath mat1, MatrixMath mat2)
/*  191:     */   {
/*  192: 254 */     if ((mat1._RowLen != mat2._RowLen) || (mat1._ColLen != mat2._ColLen)) {
/*  193: 255 */       return null;
/*  194:     */     }
/*  195: 257 */     MatrixMath mat3 = new MatrixMath();
/*  196:     */     
/*  197: 259 */     mat3.set_Data(new double[mat1._RowLen][mat1._ColLen]);
/*  198: 262 */     if (mat1._Transpos)
/*  199:     */     {
/*  200: 264 */       if (mat2._Transpos) {
/*  201: 266 */         for (int r = 0; r < mat1._RowLen; r++) {
/*  202: 268 */           for (int c = 0; c < mat1._ColLen; c++)
/*  203:     */           {
/*  204: 271 */             double v1 = mat1._Data[c][r];
/*  205: 272 */             v1 = mat1._K == 1.0D ? v1 : mat1._K * v1;
/*  206:     */             
/*  207: 274 */             double v2 = mat2._Data[c][r];
/*  208: 275 */             v2 = mat2._K == 1.0D ? v2 : mat2._K * v2;
/*  209:     */             
/*  210: 277 */             mat3._Data[r][c] = (v1 + v2);
/*  211:     */           }
/*  212:     */         }
/*  213:     */       } else {
/*  214: 283 */         for (int r = 0; r < mat1._RowLen; r++) {
/*  215: 285 */           for (int c = 0; c < mat1._ColLen; c++)
/*  216:     */           {
/*  217: 288 */             double v1 = mat1._Data[c][r];
/*  218: 289 */             v1 = mat1._K == 1.0D ? v1 : mat1._K * v1;
/*  219:     */             
/*  220: 291 */             double v2 = mat2._Data[r][c];
/*  221: 292 */             v2 = mat2._K == 1.0D ? v2 : mat2._K * v2;
/*  222:     */             
/*  223: 294 */             mat3._Data[r][c] = (v1 + v2);
/*  224:     */           }
/*  225:     */         }
/*  226:     */       }
/*  227:     */     }
/*  228: 301 */     else if (mat2._Transpos) {
/*  229: 303 */       for (int r = 0; r < mat1._RowLen; r++) {
/*  230: 305 */         for (int c = 0; c < mat1._ColLen; c++)
/*  231:     */         {
/*  232: 308 */           double v1 = mat1._Data[r][c];
/*  233: 309 */           v1 = mat1._K == 1.0D ? v1 : mat1._K * v1;
/*  234:     */           
/*  235: 311 */           double v2 = mat2._Data[c][r];
/*  236: 312 */           v2 = mat2._K == 1.0D ? v2 : mat2._K * v2;
/*  237:     */           
/*  238: 314 */           mat3._Data[r][c] = (v1 + v2);
/*  239:     */         }
/*  240:     */       }
/*  241:     */     } else {
/*  242: 320 */       for (int r = 0; r < mat1._RowLen; r++) {
/*  243: 322 */         for (int c = 0; c < mat1._ColLen; c++)
/*  244:     */         {
/*  245: 325 */           double v1 = mat1._Data[r][c];
/*  246: 326 */           v1 = mat1._K == 1.0D ? v1 : mat1._K * v1;
/*  247:     */           
/*  248: 328 */           double v2 = mat2._Data[r][c];
/*  249: 329 */           v2 = mat2._K == 1.0D ? v2 : mat2._K * v2;
/*  250:     */           
/*  251: 331 */           mat3._Data[r][c] = (v1 + v2);
/*  252:     */         }
/*  253:     */       }
/*  254:     */     }
/*  255: 337 */     return mat3;
/*  256:     */   }
/*  257:     */   
/*  258:     */   public static MatrixMath Multiply(double k, MatrixMath mat)
/*  259:     */   {
/*  260: 347 */     MatrixMath rMat = new MatrixMath();
/*  261:     */     
/*  262: 349 */     rMat.set_Data((double[][])mat._Data.clone());
/*  263: 350 */     mat._K *= k;
/*  264: 351 */     rMat._Transpos = mat._Transpos;
/*  265:     */     
/*  266: 353 */     return rMat;
/*  267:     */   }
/*  268:     */   
/*  269:     */   public static MatrixMath Multiply(MatrixMath mat, double k)
/*  270:     */   {
/*  271: 364 */     MatrixMath rMat = new MatrixMath();
/*  272:     */     
/*  273: 366 */     rMat.set_Data((double[][])mat._Data.clone());
/*  274: 367 */     mat._K *= k;
/*  275: 368 */     rMat._Transpos = mat._Transpos;
/*  276:     */     
/*  277: 370 */     return rMat;
/*  278:     */   }
/*  279:     */   
/*  280:     */   public static MatrixMath Multiply(MatrixMath mat1, MatrixMath mat2)
/*  281:     */   {
/*  282: 381 */     if ((mat1 == null) || (mat2 == null)) {
/*  283: 382 */       return null;
/*  284:     */     }
/*  285: 385 */     if (mat1._ColLen != mat2._RowLen) {
/*  286: 386 */       return null;
/*  287:     */     }
/*  288: 388 */     MatrixMath mat3 = new MatrixMath();
/*  289: 389 */     int aRowLen = mat1._RowLen;
/*  290: 390 */     int bColLen = mat2._ColLen;
/*  291: 391 */     int degree = mat1._ColLen;
/*  292:     */     
/*  293:     */ 
/*  294:     */ 
/*  295: 395 */     mat3.set_Data(new double[aRowLen][bColLen]);
/*  296:     */     
/*  297:     */ 
/*  298: 398 */     mat3.set_K(mat1.get_K() * mat2.get_K());
/*  299: 400 */     if (mat1._Transpos)
/*  300:     */     {
/*  301: 402 */       if (mat2._Transpos) {
/*  302: 404 */         for (int r = 0; r < aRowLen; r++) {
/*  303: 405 */           for (int c = 0; c < bColLen; c++) {
/*  304: 406 */             for (int k = 0; k < degree; k++) {
/*  305: 407 */               mat3._Data[r][c] += mat1._Data[k][r] * mat2._Data[c][k];
/*  306:     */             }
/*  307:     */           }
/*  308:     */         }
/*  309:     */       } else {
/*  310: 412 */         for (int r = 0; r < aRowLen; r++) {
/*  311: 413 */           for (int c = 0; c < bColLen; c++) {
/*  312: 414 */             for (int k = 0; k < degree; k++) {
/*  313: 415 */               mat3._Data[r][c] += mat1._Data[k][r] * mat2._Data[k][c];
/*  314:     */             }
/*  315:     */           }
/*  316:     */         }
/*  317:     */       }
/*  318:     */     }
/*  319: 420 */     else if (mat2._Transpos) {
/*  320: 422 */       for (int r = 0; r < aRowLen; r++) {
/*  321: 423 */         for (int c = 0; c < bColLen; c++) {
/*  322: 424 */           for (int k = 0; k < degree; k++) {
/*  323: 426 */             mat3._Data[r][c] += mat1._Data[r][k] * mat2._Data[c][k];
/*  324:     */           }
/*  325:     */         }
/*  326:     */       }
/*  327:     */     } else {
/*  328: 432 */       for (int r = 0; r < aRowLen; r++) {
/*  329: 433 */         for (int c = 0; c < bColLen; c++) {
/*  330: 434 */           for (int k = 0; k < degree; k++) {
/*  331: 435 */             mat3._Data[r][c] += mat1._Data[r][k] * mat2._Data[k][c];
/*  332:     */           }
/*  333:     */         }
/*  334:     */       }
/*  335:     */     }
/*  336: 440 */     return mat3;
/*  337:     */   }
/*  338:     */   
/*  339:     */   public MatrixMath TransposMultiply(boolean transpos)
/*  340:     */   {
/*  341: 452 */     if (this._Data == null) {
/*  342: 453 */       return null;
/*  343:     */     }
/*  344: 457 */     boolean isTranspos = transpos ^ this._Transpos;
/*  345:     */     
/*  346: 459 */     MatrixMath mat3 = new MatrixMath();
/*  347:     */     int bColLen;
/*  348:     */     int aRowLen;
/*  349:     */     int bColLen;
/*  350: 463 */     if (isTranspos)
/*  351:     */     {
/*  352: 466 */       int aRowLen = this._Data[0].length;
/*  353: 467 */       bColLen = this._Data.length;
/*  354:     */     }
/*  355:     */     else
/*  356:     */     {
/*  357: 472 */       aRowLen = this._Data.length;
/*  358: 473 */       bColLen = this._Data[0].length;
/*  359:     */     }
/*  360: 476 */     mat3.set_Data(new double[aRowLen][aRowLen]);
/*  361:     */     
/*  362: 478 */     mat3.set_K(this._K * this._K);
/*  363: 480 */     if (isTranspos) {
/*  364: 482 */       for (int r = 0; r < aRowLen; r++) {
/*  365: 483 */         for (int c = 0; c < aRowLen; c++) {
/*  366: 484 */           for (int k = 0; k < bColLen; k++) {
/*  367: 485 */             mat3._Data[r][c] += this._Data[k][c] * this._Data[k][r];
/*  368:     */           }
/*  369:     */         }
/*  370:     */       }
/*  371:     */     } else {
/*  372: 489 */       for (int r = 0; r < aRowLen; r++) {
/*  373: 490 */         for (int c = 0; c < aRowLen; c++) {
/*  374: 491 */           for (int k = 0; k < bColLen; k++) {
/*  375: 492 */             mat3._Data[r][c] += this._Data[r][k] * this._Data[c][k];
/*  376:     */           }
/*  377:     */         }
/*  378:     */       }
/*  379:     */     }
/*  380: 495 */     return mat3;
/*  381:     */   }
/*  382:     */   
/*  383:     */   public double AvgOfRow(int rowIndex)
/*  384:     */   {
/*  385: 505 */     if (rowIndex >= this._RowLen) {
/*  386: 506 */       return (0.0D / 0.0D);
/*  387:     */     }
/*  388: 508 */     double result = 0.0D;
/*  389: 510 */     if (this._Transpos) {
/*  390: 514 */       for (int i = 0; i < this._ColLen; i++) {
/*  391: 515 */         result += this._Data[i][rowIndex];
/*  392:     */       }
/*  393:     */     } else {
/*  394: 520 */       for (int i = 0; i < this._ColLen; i++) {
/*  395: 521 */         result += this._Data[rowIndex][i];
/*  396:     */       }
/*  397:     */     }
/*  398: 524 */     result = this._K == 1.0D ? result : this._K * result;
/*  399:     */     
/*  400: 526 */     return result / this._ColLen;
/*  401:     */   }
/*  402:     */   
/*  403:     */   public double VarianceOfRow(int rowIndex)
/*  404:     */   {
/*  405: 536 */     if (rowIndex >= this._RowLen) {
/*  406: 537 */       return (0.0D / 0.0D);
/*  407:     */     }
/*  408: 540 */     if (this._ColLen <= 1) {
/*  409: 541 */       return (0.0D / 0.0D);
/*  410:     */     }
/*  411: 544 */     double result = 0.0D;
/*  412:     */     
/*  413: 546 */     double avg = AvgOfRow(rowIndex);
/*  414: 548 */     if (this._Transpos) {
/*  415: 550 */       for (int i = 0; i < this._ColLen; i++)
/*  416:     */       {
/*  417: 552 */         double v = this._Data[i][rowIndex];
/*  418: 553 */         v = this._K == 1.0D ? v : this._K * v;
/*  419: 554 */         v -= avg;
/*  420: 555 */         result += v * v;
/*  421:     */       }
/*  422:     */     } else {
/*  423: 560 */       for (int i = 0; i < this._ColLen; i++)
/*  424:     */       {
/*  425: 562 */         double v = this._Data[rowIndex][i];
/*  426: 563 */         v = this._K == 1.0D ? v : this._K * v;
/*  427: 564 */         v -= avg;
/*  428: 565 */         result += v * v;
/*  429:     */       }
/*  430:     */     }
/*  431: 569 */     return result / (this._ColLen - 1);
/*  432:     */   }
/*  433:     */   
/*  434:     */   public static double CovarianceOfRow(MatrixMath rMat1, int rowIndex1, MatrixMath rMat2, int rowIndex2)
/*  435:     */   {
/*  436: 584 */     if (rMat1._ColLen != rMat2._ColLen) {
/*  437: 585 */       return (0.0D / 0.0D);
/*  438:     */     }
/*  439: 588 */     if ((rowIndex1 >= rMat1._RowLen) || (rowIndex2 >= rMat2._RowLen)) {
/*  440: 589 */       return (0.0D / 0.0D);
/*  441:     */     }
/*  442: 592 */     int num = rMat1._ColLen;
/*  443:     */     
/*  444: 594 */     double result = 0.0D;
/*  445:     */     
/*  446: 596 */     double avg1 = rMat1.AvgOfRow(rowIndex1);
/*  447: 597 */     double avg2 = rMat2.AvgOfRow(rowIndex2);
/*  448: 601 */     if (rMat1._Transpos)
/*  449:     */     {
/*  450: 603 */       if (rMat2._Transpos) {
/*  451: 605 */         for (int i = 0; i < num; i++)
/*  452:     */         {
/*  453: 607 */           double v1 = rMat1._Data[i][rowIndex1];
/*  454: 608 */           v1 = rMat1._K == 1.0D ? v1 : rMat1._K * v1;
/*  455:     */           
/*  456: 610 */           double v2 = rMat2._Data[i][rowIndex2];
/*  457: 611 */           v2 = rMat2._K == 1.0D ? v2 : rMat2._K * v2;
/*  458:     */           
/*  459: 613 */           double v = (v1 - avg1) * (v2 - avg2);
/*  460: 614 */           result += v;
/*  461:     */         }
/*  462:     */       } else {
/*  463: 619 */         for (int i = 0; i < num; i++)
/*  464:     */         {
/*  465: 621 */           double v1 = rMat1._Data[i][rowIndex1];
/*  466: 622 */           v1 = rMat1._K == 1.0D ? v1 : rMat1._K * v1;
/*  467:     */           
/*  468: 624 */           double v2 = rMat2._Data[rowIndex2][i];
/*  469: 625 */           v2 = rMat2._K == 1.0D ? v2 : rMat2._K * v2;
/*  470:     */           
/*  471: 627 */           double v = (v1 - avg1) * (v2 - avg2);
/*  472: 628 */           result += v;
/*  473:     */         }
/*  474:     */       }
/*  475:     */     }
/*  476: 634 */     else if (rMat2._Transpos) {
/*  477: 636 */       for (int i = 0; i < num; i++)
/*  478:     */       {
/*  479: 638 */         double v1 = rMat1._Data[rowIndex1][i];
/*  480: 639 */         v1 = rMat1._K == 1.0D ? v1 : rMat1._K * v1;
/*  481:     */         
/*  482: 641 */         double v2 = rMat2._Data[i][rowIndex2];
/*  483: 642 */         v2 = rMat2._K == 1.0D ? v2 : rMat2._K * v2;
/*  484:     */         
/*  485: 644 */         double v = (v1 - avg1) * (v2 - avg2);
/*  486: 645 */         result += v;
/*  487:     */       }
/*  488:     */     } else {
/*  489: 650 */       for (int i = 0; i < num; i++)
/*  490:     */       {
/*  491: 652 */         double v1 = rMat1._Data[rowIndex1][i];
/*  492: 653 */         v1 = rMat1._K == 1.0D ? v1 : rMat1._K * v1;
/*  493:     */         
/*  494: 655 */         double v2 = rMat2._Data[rowIndex2][i];
/*  495: 656 */         v2 = rMat2._K == 1.0D ? v2 : rMat2._K * v2;
/*  496:     */         
/*  497: 658 */         double v = (v1 - avg1) * (v2 - avg2);
/*  498: 659 */         result += v;
/*  499:     */       }
/*  500:     */     }
/*  501: 664 */     result /= num;
/*  502: 665 */     return result;
/*  503:     */   }
/*  504:     */   
/*  505:     */   public static double CorrelationOfRow(MatrixMath rMat1, int rowIndex1, MatrixMath rMat2, int rowIndex2)
/*  506:     */   {
/*  507: 680 */     if (rMat1._ColLen != rMat2._ColLen) {
/*  508: 681 */       return (0.0D / 0.0D);
/*  509:     */     }
/*  510: 684 */     int num = rMat1._ColLen;
/*  511:     */     
/*  512: 686 */     double result = 0.0D;
/*  513:     */     
/*  514: 688 */     result = rMat1.VarianceOfRow(rowIndex1) * rMat2.VarianceOfRow(rowIndex2);
/*  515:     */     
/*  516:     */ 
/*  517: 691 */     result = result * (num - 1) * (num - 1) / (num * num);
/*  518:     */     
/*  519: 693 */     result = CovarianceOfRow(rMat1, rowIndex1, rMat2, rowIndex2) / Math.sqrt(result);
/*  520:     */     
/*  521: 695 */     return result;
/*  522:     */   }
/*  523:     */   
/*  524:     */   public double VectorDieLengthOfRow(int rowIndex)
/*  525:     */   {
/*  526: 706 */     if (rowIndex >= this._RowLen) {
/*  527: 707 */       return (0.0D / 0.0D);
/*  528:     */     }
/*  529: 710 */     int num = this._ColLen;
/*  530: 711 */     double result = 0.0D;
/*  531: 713 */     if (this._Transpos) {
/*  532: 715 */       for (int i = 0; i < num; i++)
/*  533:     */       {
/*  534: 717 */         double v = this._Data[i][rowIndex];
/*  535: 718 */         result += v * v;
/*  536:     */       }
/*  537:     */     } else {
/*  538: 723 */       for (int i = 0; i < num; i++)
/*  539:     */       {
/*  540: 725 */         double v = this._Data[rowIndex][i];
/*  541: 726 */         result += v * v;
/*  542:     */       }
/*  543:     */     }
/*  544: 730 */     result *= this._K * this._K;
/*  545:     */     
/*  546: 732 */     result = Math.sqrt(result);
/*  547:     */     
/*  548: 734 */     return result;
/*  549:     */   }
/*  550:     */   
/*  551:     */   public double MatDeter()
/*  552:     */   {
/*  553: 744 */     if ((this._RowLen != this._ColLen) || (this._RowLen < 1)) {
/*  554: 745 */       return (0.0D / 0.0D);
/*  555:     */     }
/*  556: 748 */     int num = this._RowLen;
/*  557:     */     
/*  558:     */ 
/*  559: 751 */     double[][] tempMat = DataCopy();
/*  560:     */     
/*  561:     */ 
/*  562:     */ 
/*  563: 755 */     int lx = 0;
/*  564: 756 */     int f = 1;
/*  565: 758 */     for (int i = 0; i < num; i++)
/*  566:     */     {
/*  567: 760 */       double bignum = 0.0D;
/*  568: 762 */       for (int k = i; k < num; k++)
/*  569:     */       {
/*  570: 765 */         double eval = Math.abs(tempMat[k][i]);
/*  571: 769 */         if (eval > bignum)
/*  572:     */         {
/*  573: 771 */           bignum = eval;
/*  574:     */           
/*  575: 773 */           lx = k;
/*  576:     */         }
/*  577:     */       }
/*  578: 778 */       if (i != lx) {
/*  579: 779 */         f = -f;
/*  580:     */       }
/*  581: 782 */       for (int j = 0; j < num; j++)
/*  582:     */       {
/*  583: 784 */         double temp1 = tempMat[i][j];
/*  584: 785 */         tempMat[i][j] = tempMat[lx][j];
/*  585: 786 */         tempMat[lx][j] = temp1;
/*  586:     */       }
/*  587: 789 */       double pivot = tempMat[i][i];
/*  588: 792 */       if (pivot == 0.0D) {
/*  589: 794 */         return 0.0D;
/*  590:     */       }
/*  591: 796 */       int nxt = i + 1;
/*  592: 797 */       for (int j = nxt; j < num; j++)
/*  593:     */       {
/*  594: 799 */         double rac = tempMat[j][i] / pivot;
/*  595: 801 */         for (int k = i; k < num; k++) {
/*  596: 802 */           tempMat[j][k] -= rac * tempMat[i][k];
/*  597:     */         }
/*  598:     */       }
/*  599:     */     }
/*  600: 806 */     double temp2 = 1.0D;
/*  601: 808 */     for (int i = 0; i < num; i++) {
/*  602: 809 */       temp2 *= tempMat[i][i];
/*  603:     */     }
/*  604: 811 */     return temp2 * f;
/*  605:     */   }
/*  606:     */   
/*  607:     */   public double MatSubDeter(int row, int col)
/*  608:     */   {
/*  609: 824 */     if ((this._RowLen != this._ColLen) || (this._RowLen < 1)) {
/*  610: 825 */       return (0.0D / 0.0D);
/*  611:     */     }
/*  612: 828 */     int num = this._RowLen - 1;
/*  613:     */     
/*  614:     */ 
/*  615: 831 */     MatrixMath tempMat = new MatrixMath();
/*  616:     */     
/*  617: 833 */     tempMat.set_Data(new double[num][num]);
/*  618: 835 */     if (this._Transpos) {
/*  619: 838 */       for (int r = 0; r < num; r++) {
/*  620: 839 */         for (int c = 0; c < num; c++) {
/*  621: 841 */           tempMat._Data[r][c] = this._Data[(c + 1)][(r + 1)];
/*  622:     */         }
/*  623:     */       }
/*  624:     */     } else {
/*  625: 847 */       for (int r = 0; r < num; r++) {
/*  626: 848 */         for (int c = 0; c < num; c++) {
/*  627: 849 */           tempMat._Data[r][c] = this._Data[(r + 1)][(c + 1)];
/*  628:     */         }
/*  629:     */       }
/*  630:     */     }
/*  631: 853 */     tempMat.set_K(this._K);
/*  632:     */     
/*  633: 855 */     return (row + col) % 2 > 0 ? -tempMat.MatDeter() : tempMat.MatDeter();
/*  634:     */   }
/*  635:     */   
/*  636:     */   public MatrixMath MatInvert()
/*  637:     */   {
/*  638: 866 */     if ((this._RowLen != this._ColLen) || (this._RowLen < 1)) {
/*  639: 867 */       return null;
/*  640:     */     }
/*  641: 870 */     int num = this._ColLen;
/*  642:     */     
/*  643: 872 */     MatrixMath tempMat = new MatrixMath();
/*  644:     */     
/*  645: 874 */     tempMat.set_Data(new double[num][num]);
/*  646:     */     
/*  647:     */ 
/*  648: 877 */     double hls = MatDeter();
/*  649: 879 */     if (Double.isNaN(hls)) {
/*  650: 880 */       return null;
/*  651:     */     }
/*  652: 883 */     if (hls == 0.0D) {
/*  653: 884 */       hls = 1.E-005D;
/*  654:     */     }
/*  655: 886 */     for (int i = 0; i < num; i++) {
/*  656: 887 */       for (int j = 0; j < num; j++) {
/*  657: 888 */         tempMat._Data[i][j] = (MatSubDeter(j, i) / hls);
/*  658:     */       }
/*  659:     */     }
/*  660: 890 */     return tempMat;
/*  661:     */   }
/*  662:     */   
/*  663:     */   private void JacobiRotation(double[][] chaVector, double[][] mtxData, int p, int q, double[] mp, double[] mq, int n)
/*  664:     */   {
/*  665: 908 */     double piOver4 = 0.7853981633974483D;
/*  666: 909 */     double piOver2 = 1.570796326794897D;
/*  667:     */     
/*  668: 911 */     double mpp = mp[p];
/*  669: 912 */     double mqq = mq[q];
/*  670: 913 */     double mtm = mp[q];
/*  671:     */     
/*  672:     */ 
/*  673:     */ 
/*  674: 917 */     double temp = mpp - mqq;
/*  675:     */     double angle;
/*  676: 918 */     if ((temp > 1.0E-008D) || (temp < -1.0E-008D))
/*  677:     */     {
/*  678: 921 */       double angle = Math.atan(2.0D * mtm / (mpp - mqq)) / 2.0D;
/*  679: 923 */       if (angle > piOver4) {
/*  680: 924 */         angle -= piOver2;
/*  681:     */       }
/*  682:     */     }
/*  683:     */     else
/*  684:     */     {
/*  685: 929 */       angle = mtm > 0.0D ? piOver4 : -piOver4;
/*  686:     */     }
/*  687: 933 */     double c = Math.cos(angle);
/*  688:     */     
/*  689: 935 */     double s = Math.sin(angle);
/*  690:     */     
/*  691: 937 */     double cs = c * s;
/*  692: 938 */     double ss = s * s;double cc = c * c;
/*  693:     */     
/*  694: 940 */     mp[p] = (mpp * cc + 2.0D * mtm * cs + mqq * ss);
/*  695: 941 */     mq[q] = (mpp * ss - 2.0D * mtm * cs + mqq * cc);
/*  696:     */     
/*  697: 943 */     mp[q] = 0.0D;
/*  698: 944 */     mq[p] = 0.0D;
/*  699: 946 */     for (int j = 0; j < n; j++) {
/*  700: 948 */       if ((j != p) && (j != q))
/*  701:     */       {
/*  702: 950 */         double mpj = mp[j] * c + mq[j] * s;
/*  703: 951 */         double mqj = mq[j] * c - mp[j] * s;
/*  704: 952 */         mp[j] = mpj;
/*  705: 953 */         mtxData[j][p] = mpj;
/*  706:     */         
/*  707: 955 */         mq[j] = mqj;
/*  708: 956 */         mtxData[j][q] = mqj;
/*  709:     */       }
/*  710:     */     }
/*  711: 960 */     for (j = 0; j < n; j++)
/*  712:     */     {
/*  713: 962 */       double g = chaVector[p][j];
/*  714: 963 */       double h = chaVector[q][j];
/*  715:     */       
/*  716: 965 */       chaVector[p][j] = (c * g + s * h);
/*  717: 966 */       chaVector[q][j] = (-s * g + c * h);
/*  718:     */     }
/*  719:     */   }
/*  720:     */   
/*  721:     */   public boolean CyclicJacobi(double[] chaValue, double[][] chaVector)
/*  722:     */   {
/*  723: 981 */     if ((this._RowLen != this._ColLen) || (this._RowLen < 1)) {
/*  724: 982 */       return false;
/*  725:     */     }
/*  726: 985 */     int num = this._RowLen;
/*  727:     */     
/*  728:     */ 
/*  729: 988 */     MatrixMath tempMrx = new MatrixMath();
/*  730: 989 */     tempMrx.set_Data(DataCopy());
/*  731: 992 */     for (int p = 0; p < num; p++) {
/*  732: 994 */       for (int q = 0; q < num; q++) {
/*  733: 995 */         if (Math.abs(tempMrx._Data[p][q] - tempMrx._Data[q][p]) > 0.0001D) {
/*  734: 996 */           return false;
/*  735:     */         }
/*  736:     */       }
/*  737:     */     }
/*  738:1000 */     int maxcount = this._RowLen;int count = 0;
/*  739:1002 */     if ((chaValue == null) || (chaValue.length != num)) {
/*  740:1005 */       chaValue = new double[num];
/*  741:     */     }
/*  742:1007 */     if ((chaVector == null) || (chaVector.length != num)) {
/*  743:1009 */       chaVector = new double[num][num];
/*  744:     */     }
/*  745:1011 */     boolean success = false;
/*  746:     */     
/*  747:1013 */     double[] atemp1 = new double[num];
/*  748:1014 */     double[] atemp2 = new double[num];
/*  749:1016 */     for (int p = 0; p < num; p++) {
/*  750:1019 */       chaVector[p][p] = 1.0D;
/*  751:     */     }
/*  752:1022 */     double sumsq = 0.0D;
/*  753:1025 */     for (int p = 0; p < num; p++)
/*  754:     */     {
/*  755:1028 */       double v = tempMrx._Data[p][p];
/*  756:1029 */       sumsq += v * v;
/*  757:     */     }
/*  758:     */     do
/*  759:     */     {
/*  760:1034 */       count++;
/*  761:1035 */       success = true;
/*  762:1037 */       for (int p = 0; p < num - 1; p++) {
/*  763:1039 */         for (int q = p + 1; q < num; q++)
/*  764:     */         {
/*  765:1043 */           double temp = tempMrx.get_Data()[q][p] / sumsq;
/*  766:1044 */           if ((temp > 1.0E-008D) || (temp < -1.0E-008D))
/*  767:     */           {
/*  768:1046 */             success = false;
/*  769:1047 */             atemp1 = tempMrx.GetRowData(p);
/*  770:1048 */             atemp2 = tempMrx.GetRowData(q);
/*  771:     */             
/*  772:1050 */             JacobiRotation(chaVector, tempMrx._Data, p, q, atemp1, atemp2, num);
/*  773:     */             
/*  774:1052 */             tempMrx.SetRowData(p, atemp1);
/*  775:1053 */             tempMrx.SetRowData(q, atemp2);
/*  776:     */           }
/*  777:     */         }
/*  778:     */       }
/*  779:1059 */     } while ((!success) || (count < maxcount));
/*  780:1061 */     if (success) {
/*  781:1063 */       for (int p = 0; p < num; p++) {
/*  782:1064 */         chaValue[p] = tempMrx._Data[p][p];
/*  783:     */       }
/*  784:     */     }
/*  785:1067 */     return success;
/*  786:     */   }
/*  787:     */ }


/* Location:           D:\NARI\葛洲坝\test.jar
 * Qualified Name:     com.nari.slsd.he.Calculate.strv.MatrixMath
 * JD-Core Version:    0.7.0.1
 */