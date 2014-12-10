/*   1:    */ package com.nari.slsd.he.Calculate.strv;
/*   2:    */ 
/*   3:    */ public class MPartialRegression
/*   4:    */   extends ModelCalculate
/*   5:    */ {
/*   6:    */   public MPartialRegression(TplModelBase mBase, double[][] x_Original, double[][] y_Original, int progStart, int progEnd)
/*   7:    */   {
/*   8:  8 */     super(mBase, x_Original, y_Original, progStart, progEnd);
/*   9:    */   }
/*  10:    */   
/*  11:    */   public boolean Run()
/*  12:    */   {
/*  13: 19 */     Init_Data();
/*  14:    */     
/*  15: 21 */     MatrixMath xx_Mtx = new MatrixMath();
/*  16: 22 */     MatrixMath yy_Mtx = new MatrixMath();
/*  17:    */     
/*  18:    */ 
/*  19:    */ 
/*  20:    */ 
/*  21: 27 */     double jCYXX = 0.0975D;
/*  22:    */     
/*  23:    */ 
/*  24: 30 */     double[][] e = new double[this.X_Num][this.Sample_Num];
/*  25:    */     
/*  26:    */ 
/*  27: 33 */     double[][] ii = new double[this.X_Num][this.X_Num];
/*  28: 36 */     for (int i = 0; i < this.X_Num; i++) {
/*  29: 38 */       for (int j = 0; j < this.X_Num; j++) {
/*  30: 40 */         if (i == j) {
/*  31: 41 */           ii[i][j] = 1.0D;
/*  32:    */         }
/*  33:    */       }
/*  34:    */     }
/*  35: 45 */     MatrixMath ii_Mtx = new MatrixMath(ii);
/*  36:    */     
/*  37:    */ 
/*  38: 48 */     double[][] f0 = new double[this.Y_Num][this.Sample_Num];
/*  39:    */     
/*  40: 50 */     int h = 0;
/*  41:    */     
/*  42:    */ 
/*  43: 53 */     boolean flag = true;
/*  44: 57 */     for (int i = 0; i < this.X_Num; i++)
/*  45:    */     {
/*  46: 59 */       double av = this.X_Mtx.AvgOfRow(i);
/*  47: 60 */       double sv = Math.sqrt(this.X_Mtx.VarianceOfRow(i));
/*  48: 61 */       for (int j = 0; j < this.Sample_Num; j++) {
/*  49: 62 */         e[i][j] = ((this.X_Mtx.GetData(i, j) - av) / sv);
/*  50:    */       }
/*  51:    */     }
/*  52: 65 */     for (int i = 0; i < this.Y_Num; i++)
/*  53:    */     {
/*  54: 67 */       double av = this.Y_Mtx.AvgOfRow(i);
/*  55: 68 */       double sv = Math.sqrt(this.Y_Mtx.VarianceOfRow(i));
/*  56: 69 */       for (int j = 0; j < this.Sample_Num; j++) {
/*  57: 70 */         f0[i][j] = ((this.Y_Mtx.GetData(i, j) - av) / sv);
/*  58:    */       }
/*  59:    */     }
/*  60: 73 */     MatrixMath e_Mtx = new MatrixMath(e);
/*  61:    */     
/*  62: 75 */     MatrixMath f0_Mtx = new MatrixMath(f0);
/*  63:    */     
/*  64:    */ 
/*  65: 78 */     double[][] wx = new double[this.X_Num][this.X_Num];
/*  66: 79 */     MatrixMath wx_Mtx = new MatrixMath(wx);
/*  67:    */     
/*  68:    */ 
/*  69: 82 */     double[][] t = new double[this.X_Num][this.Sample_Num];
/*  70:    */     
/*  71: 84 */     MatrixMath t_Mtx = new MatrixMath(t);
/*  72:    */     
/*  73:    */ 
/*  74:    */ 
/*  75: 88 */     double[][] w = new double[this.X_Num][this.X_Num];
/*  76:    */     
/*  77: 90 */     MatrixMath w_Mtx = new MatrixMath(w);
/*  78:    */     
/*  79: 92 */     MatrixMath z_Mtx = new MatrixMath(ii);
/*  80:    */     
/*  81: 94 */     double v = 0.0D;
/*  82:    */     do
/*  83:    */     {
/*  84:102 */       e_Mtx.set_Transpos(true);
/*  85:103 */       f0_Mtx.set_Transpos(false);
/*  86:    */       
/*  87:    */ 
/*  88:106 */       MatrixMath g1_Mtx = MatrixMath.Multiply(f0_Mtx, e_Mtx);
/*  89:    */       
/*  90:    */ 
/*  91:109 */       f0_Mtx.set_Transpos(true);
/*  92:    */       
/*  93:111 */       MatrixMath g2_Mtx = MatrixMath.Multiply(f0_Mtx, g1_Mtx);
/*  94:    */       
/*  95:    */ 
/*  96:114 */       e_Mtx.set_Transpos(false);
/*  97:115 */       MatrixMath g_Mtx = MatrixMath.Multiply(e_Mtx, g2_Mtx);
/*  98:    */       
/*  99:    */ 
/* 100:    */ 
/* 101:    */ 
/* 102:    */ 
/* 103:121 */       int num = g_Mtx.RowLen();
/* 104:    */       
/* 105:123 */       double[] val = new double[num];
/* 106:124 */       double[][] vec = new double[num][num];
/* 107:    */       
/* 108:    */ 
/* 109:127 */       boolean success = g_Mtx.CyclicJacobi(val, vec);
/* 110:129 */       if (!success) {
/* 111:132 */         return success;
/* 112:    */       }
/* 113:136 */       e_Mtx.set_Transpos(false);
/* 114:137 */       MatrixMath vec_Mtx = new MatrixMath(vec);
/* 115:    */       
/* 116:139 */       g_Mtx = MatrixMath.Multiply(vec_Mtx, e_Mtx);
/* 117:    */       
/* 118:141 */       int no = 0;
/* 119:    */       
/* 120:143 */       double dat = val[0];
/* 121:145 */       for (int i = 0; i < this.X_Num - 1; i++) {
/* 122:147 */         if (val[(i + 1)] > dat)
/* 123:    */         {
/* 124:149 */           dat = val[(i + 1)];
/* 125:150 */           no = i + 1;
/* 126:    */         }
/* 127:    */       }
/* 128:155 */       t_Mtx.SetRowData(h, g_Mtx.GetRowData(no));
/* 129:    */       
/* 130:    */ 
/* 131:158 */       w_Mtx.SetRowData(h, vec_Mtx.GetRowData(no));
/* 132:    */       
/* 133:    */ 
/* 134:161 */       double[][] g1 = new double[1][this.X_Num];
/* 135:162 */       g1_Mtx = new MatrixMath(g1);
/* 136:    */       
/* 137:    */ 
/* 138:165 */       g1_Mtx.SetRowData(0, w_Mtx.GetRowData(h));
/* 139:    */       
/* 140:    */ 
/* 141:168 */       g2_Mtx = MatrixMath.Multiply(g1_Mtx, z_Mtx);
/* 142:    */       
/* 143:    */ 
/* 144:171 */       wx_Mtx.SetRowData(h, g2_Mtx.GetRowData(0));
/* 145:    */       
/* 146:173 */       h++;
/* 147:180 */       if (h > 1)
/* 148:    */       {
/* 149:185 */         boolean doflag = true;
/* 150:186 */         double ahk = 0.0D;
/* 151:187 */         int k = 0;
/* 152:    */         do
/* 153:    */         {
/* 154:193 */           double pRESShk = 0.0D;
/* 155:    */           
/* 156:195 */           xx_Mtx.set_Data(new double[h][this.Sample_Num - 1]);
/* 157:196 */           yy_Mtx.set_Data(new double[1][this.Sample_Num - 1]);
/* 158:199 */           for (int id = 0; id < this.Sample_Num; id++)
/* 159:    */           {
/* 160:201 */             for (int i = 0; i < this.Sample_Num - 1; i++)
/* 161:    */             {
/* 162:204 */               for (int j = 0; j < h; j++)
/* 163:    */               {
/* 164:206 */                 xx_Mtx.setData(j, i, t[j][i]);
/* 165:208 */                 if (i >= id) {
/* 166:211 */                   xx_Mtx.setData(j, i, t[j][(i + 1)]);
/* 167:    */                 }
/* 168:    */               }
/* 169:215 */               yy_Mtx.setData(0, i, this.Y_Mtx.GetData(k, i));
/* 170:216 */               if (i >= id) {
/* 171:217 */                 yy_Mtx.setData(0, i, this.Y_Mtx.GetData(k, i + 1));
/* 172:    */               }
/* 173:    */             }
/* 174:221 */             FullRegression(xx_Mtx, yy_Mtx, h, 1, this.Sample_Num - 1);
/* 175:    */             
/* 176:223 */             double yid = this.Coefficient[0][0] + yy_Mtx.get_Data()[0][0];
/* 177:225 */             for (int i = 0; i < h; i++) {
/* 178:226 */               yid += this.Coefficient[0][(i + 1)] * (t[i][id] - xx_Mtx.GetData(i, 0));
/* 179:    */             }
/* 180:229 */             v = yid - this.Y_Mtx.GetData(k, id);
/* 181:230 */             pRESShk += v * v;
/* 182:    */           }
/* 183:236 */           if (h - 1 >= 0)
/* 184:    */           {
/* 185:239 */             xx_Mtx.set_Data(new double[h - 1][this.Sample_Num]);
/* 186:240 */             yy_Mtx.set_Data(new double[1][this.Sample_Num]);
/* 187:241 */             for (int i = 0; i < this.Sample_Num; i++)
/* 188:    */             {
/* 189:243 */               for (int j = 0; j < h - 1; j++) {
/* 190:244 */                 xx_Mtx.setData(j, i, t[j][i]);
/* 191:    */               }
/* 192:247 */               yy_Mtx.setData(0, i, this.Y_Mtx.GetData(k, i));
/* 193:    */             }
/* 194:250 */             FullRegression(xx_Mtx, yy_Mtx, h - 1, 1, this.Sample_Num);
/* 195:    */             
/* 196:252 */             double sSh_1k = 0.0D;
/* 197:254 */             for (int id = 0; id < this.Sample_Num; id++)
/* 198:    */             {
/* 199:257 */               v = yy_Mtx.GetData(0, id) - this.Fitted[0][id];
/* 200:258 */               sSh_1k += v * v;
/* 201:    */             }
/* 202:262 */             ahk = 1.0D - pRESShk / sSh_1k;
/* 203:264 */             if (ahk >= jCYXX) {
/* 204:265 */               doflag = false;
/* 205:    */             }
/* 206:267 */             k++;
/* 207:268 */             if (k == this.Y_Num) {
/* 208:269 */               doflag = false;
/* 209:    */             }
/* 210:    */           }
/* 211:188 */         } while (
/* 212:    */         
/* 213:    */ 
/* 214:    */ 
/* 215:    */ 
/* 216:    */ 
/* 217:    */ 
/* 218:    */ 
/* 219:    */ 
/* 220:    */ 
/* 221:    */ 
/* 222:    */ 
/* 223:    */ 
/* 224:    */ 
/* 225:    */ 
/* 226:    */ 
/* 227:    */ 
/* 228:    */ 
/* 229:    */ 
/* 230:    */ 
/* 231:    */ 
/* 232:    */ 
/* 233:    */ 
/* 234:    */ 
/* 235:    */ 
/* 236:    */ 
/* 237:    */ 
/* 238:    */ 
/* 239:    */ 
/* 240:    */ 
/* 241:    */ 
/* 242:    */ 
/* 243:    */ 
/* 244:    */ 
/* 245:    */ 
/* 246:    */ 
/* 247:    */ 
/* 248:    */ 
/* 249:    */ 
/* 250:    */ 
/* 251:    */ 
/* 252:    */ 
/* 253:    */ 
/* 254:    */ 
/* 255:    */ 
/* 256:    */ 
/* 257:    */ 
/* 258:    */ 
/* 259:    */ 
/* 260:    */ 
/* 261:    */ 
/* 262:    */ 
/* 263:    */ 
/* 264:    */ 
/* 265:    */ 
/* 266:    */ 
/* 267:    */ 
/* 268:    */ 
/* 269:    */ 
/* 270:    */ 
/* 271:    */ 
/* 272:    */ 
/* 273:    */ 
/* 274:    */ 
/* 275:    */ 
/* 276:    */ 
/* 277:    */ 
/* 278:    */ 
/* 279:    */ 
/* 280:    */ 
/* 281:    */ 
/* 282:    */ 
/* 283:    */ 
/* 284:    */ 
/* 285:    */ 
/* 286:    */ 
/* 287:    */ 
/* 288:    */ 
/* 289:    */ 
/* 290:    */ 
/* 291:    */ 
/* 292:    */ 
/* 293:    */ 
/* 294:    */ 
/* 295:272 */           doflag);
/* 296:274 */         if (ahk < jCYXX)
/* 297:    */         {
/* 298:276 */           h--;
/* 299:277 */           flag = false;
/* 300:    */         }
/* 301:    */       }
/* 302:285 */       if (h == this.X_Num) {
/* 303:286 */         flag = false;
/* 304:    */       }
/* 305:288 */       if (flag)
/* 306:    */       {
/* 307:293 */         double[][] tt = new double[1][this.Sample_Num];
/* 308:    */         
/* 309:295 */         MatrixMath tt_Mtx = new MatrixMath(tt);
/* 310:    */         
/* 311:    */ 
/* 312:298 */         tt_Mtx.SetRowData(0, t_Mtx.GetRowData(h - 1));
/* 313:    */         
/* 314:    */ 
/* 315:301 */         e_Mtx.set_Transpos(true);
/* 316:    */         
/* 317:303 */         g1_Mtx = MatrixMath.Multiply(tt_Mtx, e_Mtx);
/* 318:    */         
/* 319:    */ 
/* 320:    */ 
/* 321:    */ 
/* 322:308 */         v = t_Mtx.VectorDieLengthOfRow(h - 1);
/* 323:309 */         double VecLen = 1.0D / (v * v);
/* 324:    */         
/* 325:    */ 
/* 326:312 */         MatrixMath pp_Mtx = MatrixMath.Multiply(g1_Mtx, VecLen);
/* 327:    */         
/* 328:    */ 
/* 329:315 */         pp_Mtx.set_Transpos(true);
/* 330:316 */         tt_Mtx.set_Transpos(false);
/* 331:    */         
/* 332:318 */         MatrixMath g3_Mtx = MatrixMath.Multiply(pp_Mtx, tt_Mtx);
/* 333:319 */         g3_Mtx.set_K(g3_Mtx.get_K() * -1.0D);
/* 334:    */         
/* 335:    */ 
/* 336:322 */         e_Mtx.set_Transpos(false);
/* 337:    */         
/* 338:    */ 
/* 339:325 */         e_Mtx = MatrixMath.Add(e_Mtx, g3_Mtx);
/* 340:    */         
/* 341:    */ 
/* 342:    */ 
/* 343:329 */         g1 = new double[1][this.X_Num];
/* 344:330 */         g1_Mtx = new MatrixMath(g1);
/* 345:    */         
/* 346:    */ 
/* 347:    */ 
/* 348:334 */         g1_Mtx.SetRowData(0, w_Mtx.GetRowData(h - 1));
/* 349:    */         
/* 350:336 */         pp_Mtx.set_Transpos(true);
/* 351:    */         
/* 352:338 */         g2_Mtx = MatrixMath.Multiply(pp_Mtx, g1_Mtx);
/* 353:    */         
/* 354:    */ 
/* 355:    */ 
/* 356:342 */         g2_Mtx.set_K(g2_Mtx.get_K() * -1.0D);
/* 357:    */         
/* 358:    */ 
/* 359:345 */         g2_Mtx = MatrixMath.Add(ii_Mtx, g2_Mtx);
/* 360:    */         
/* 361:    */ 
/* 362:348 */         z_Mtx = MatrixMath.Multiply(g2_Mtx, z_Mtx);
/* 363:    */       }
/* 364: 99 */     } while (
/* 365:    */     
/* 366:    */ 
/* 367:    */ 
/* 368:    */ 
/* 369:    */ 
/* 370:    */ 
/* 371:    */ 
/* 372:    */ 
/* 373:    */ 
/* 374:    */ 
/* 375:    */ 
/* 376:    */ 
/* 377:    */ 
/* 378:    */ 
/* 379:    */ 
/* 380:    */ 
/* 381:    */ 
/* 382:    */ 
/* 383:    */ 
/* 384:    */ 
/* 385:    */ 
/* 386:    */ 
/* 387:    */ 
/* 388:    */ 
/* 389:    */ 
/* 390:    */ 
/* 391:    */ 
/* 392:    */ 
/* 393:    */ 
/* 394:    */ 
/* 395:    */ 
/* 396:    */ 
/* 397:    */ 
/* 398:    */ 
/* 399:    */ 
/* 400:    */ 
/* 401:    */ 
/* 402:    */ 
/* 403:    */ 
/* 404:    */ 
/* 405:    */ 
/* 406:    */ 
/* 407:    */ 
/* 408:    */ 
/* 409:    */ 
/* 410:    */ 
/* 411:    */ 
/* 412:    */ 
/* 413:    */ 
/* 414:    */ 
/* 415:    */ 
/* 416:    */ 
/* 417:    */ 
/* 418:    */ 
/* 419:    */ 
/* 420:    */ 
/* 421:    */ 
/* 422:    */ 
/* 423:    */ 
/* 424:    */ 
/* 425:    */ 
/* 426:    */ 
/* 427:    */ 
/* 428:    */ 
/* 429:    */ 
/* 430:    */ 
/* 431:    */ 
/* 432:    */ 
/* 433:    */ 
/* 434:    */ 
/* 435:    */ 
/* 436:    */ 
/* 437:    */ 
/* 438:    */ 
/* 439:    */ 
/* 440:    */ 
/* 441:    */ 
/* 442:    */ 
/* 443:    */ 
/* 444:    */ 
/* 445:    */ 
/* 446:    */ 
/* 447:    */ 
/* 448:    */ 
/* 449:    */ 
/* 450:    */ 
/* 451:    */ 
/* 452:    */ 
/* 453:    */ 
/* 454:    */ 
/* 455:    */ 
/* 456:    */ 
/* 457:    */ 
/* 458:    */ 
/* 459:    */ 
/* 460:    */ 
/* 461:    */ 
/* 462:    */ 
/* 463:    */ 
/* 464:    */ 
/* 465:    */ 
/* 466:    */ 
/* 467:    */ 
/* 468:    */ 
/* 469:    */ 
/* 470:    */ 
/* 471:    */ 
/* 472:    */ 
/* 473:    */ 
/* 474:    */ 
/* 475:    */ 
/* 476:    */ 
/* 477:    */ 
/* 478:    */ 
/* 479:    */ 
/* 480:    */ 
/* 481:    */ 
/* 482:    */ 
/* 483:    */ 
/* 484:    */ 
/* 485:    */ 
/* 486:    */ 
/* 487:    */ 
/* 488:    */ 
/* 489:    */ 
/* 490:    */ 
/* 491:    */ 
/* 492:    */ 
/* 493:    */ 
/* 494:    */ 
/* 495:    */ 
/* 496:    */ 
/* 497:    */ 
/* 498:    */ 
/* 499:    */ 
/* 500:    */ 
/* 501:    */ 
/* 502:    */ 
/* 503:    */ 
/* 504:    */ 
/* 505:    */ 
/* 506:    */ 
/* 507:    */ 
/* 508:    */ 
/* 509:    */ 
/* 510:    */ 
/* 511:    */ 
/* 512:    */ 
/* 513:    */ 
/* 514:    */ 
/* 515:    */ 
/* 516:    */ 
/* 517:    */ 
/* 518:    */ 
/* 519:    */ 
/* 520:    */ 
/* 521:    */ 
/* 522:    */ 
/* 523:    */ 
/* 524:    */ 
/* 525:    */ 
/* 526:    */ 
/* 527:    */ 
/* 528:    */ 
/* 529:    */ 
/* 530:    */ 
/* 531:    */ 
/* 532:    */ 
/* 533:    */ 
/* 534:    */ 
/* 535:    */ 
/* 536:    */ 
/* 537:    */ 
/* 538:    */ 
/* 539:    */ 
/* 540:    */ 
/* 541:    */ 
/* 542:    */ 
/* 543:    */ 
/* 544:    */ 
/* 545:    */ 
/* 546:    */ 
/* 547:    */ 
/* 548:    */ 
/* 549:    */ 
/* 550:    */ 
/* 551:    */ 
/* 552:    */ 
/* 553:    */ 
/* 554:    */ 
/* 555:    */ 
/* 556:    */ 
/* 557:    */ 
/* 558:    */ 
/* 559:    */ 
/* 560:    */ 
/* 561:    */ 
/* 562:    */ 
/* 563:    */ 
/* 564:    */ 
/* 565:    */ 
/* 566:    */ 
/* 567:    */ 
/* 568:    */ 
/* 569:    */ 
/* 570:    */ 
/* 571:    */ 
/* 572:    */ 
/* 573:    */ 
/* 574:    */ 
/* 575:    */ 
/* 576:    */ 
/* 577:    */ 
/* 578:    */ 
/* 579:    */ 
/* 580:    */ 
/* 581:    */ 
/* 582:    */ 
/* 583:    */ 
/* 584:    */ 
/* 585:    */ 
/* 586:    */ 
/* 587:    */ 
/* 588:    */ 
/* 589:    */ 
/* 590:    */ 
/* 591:    */ 
/* 592:    */ 
/* 593:    */ 
/* 594:    */ 
/* 595:    */ 
/* 596:    */ 
/* 597:    */ 
/* 598:    */ 
/* 599:    */ 
/* 600:    */ 
/* 601:    */ 
/* 602:    */ 
/* 603:    */ 
/* 604:    */ 
/* 605:    */ 
/* 606:    */ 
/* 607:    */ 
/* 608:    */ 
/* 609:    */ 
/* 610:    */ 
/* 611:    */ 
/* 612:    */ 
/* 613:    */ 
/* 614:    */ 
/* 615:    */ 
/* 616:    */ 
/* 617:352 */       flag);
/* 618:357 */     if (this.X_Num > 1) {
/* 619:358 */       h = 2;
/* 620:    */     } else {
/* 621:360 */       h = 1;
/* 622:    */     }
/* 623:363 */     xx_Mtx.set_Data(new double[h][this.Sample_Num]);
/* 624:    */     
/* 625:365 */     yy_Mtx.set_Data(new double[this.Y_Num][this.Sample_Num]);
/* 626:367 */     for (int i = 0; i < h; i++) {
/* 627:369 */       for (int j = 0; j < this.Sample_Num; j++) {
/* 628:372 */         xx_Mtx.setData(i, j, t[i][j]);
/* 629:    */       }
/* 630:    */     }
/* 631:376 */     for (int i = 0; i < this.Y_Num; i++) {
/* 632:377 */       for (int j = 0; j < this.Sample_Num; j++) {
/* 633:380 */         yy_Mtx.setData(i, j, this.Y_Mtx.GetData(i, j));
/* 634:    */       }
/* 635:    */     }
/* 636:384 */     FullRegression(xx_Mtx, yy_Mtx, h, this.Y_Num, this.Sample_Num);
/* 637:    */     
/* 638:    */ 
/* 639:387 */     double[][] bb = new double[this.Y_Num][h];
/* 640:388 */     double[] bbY = new double[this.Y_Num];
/* 641:389 */     for (int i = 0; i < this.Y_Num; i++)
/* 642:    */     {
/* 643:391 */       for (int j = 0; j < h; j++) {
/* 644:392 */         bb[i][j] = this.Coefficient[i][(j + 1)];
/* 645:    */       }
/* 646:394 */       bbY[i] = this.Coefficient[i][0];
/* 647:    */     }
/* 648:397 */     MatrixMath bb_Mtx = new MatrixMath(bb);
/* 649:    */     
/* 650:    */ 
/* 651:    */ 
/* 652:    */ 
/* 653:402 */     double[][] wxx = new double[h][wx_Mtx.ColLen()];
/* 654:403 */     for (int i = 0; i < h; i++) {
/* 655:405 */       for (int j = 0; j < wx_Mtx.ColLen(); j++) {
/* 656:406 */         wxx[i][j] = wx_Mtx.GetData(i, j);
/* 657:    */       }
/* 658:    */     }
/* 659:409 */     MatrixMath wxx_Mtx = new MatrixMath(wxx);
/* 660:    */     
/* 661:    */ 
/* 662:412 */     MatrixMath gg_Mtx = MatrixMath.Multiply(bb_Mtx, wxx_Mtx);
/* 663:    */     
/* 664:414 */     this.Coefficient = new double[this.Y_Num][this.X_Num + 1];
/* 665:416 */     for (int i = 0; i < this.Y_Num; i++)
/* 666:    */     {
/* 667:418 */       this.Coefficient[i][0] = bbY[i];
/* 668:419 */       for (int j = 1; j < this.X_Num + 1; j++) {
/* 669:421 */         this.Coefficient[i][j] = gg_Mtx.GetData(i, j - 1);
/* 670:    */       }
/* 671:    */     }
/* 672:428 */     for (int j = 0; j < this.X_Num; j++)
/* 673:    */     {
/* 674:430 */       double x_MtxAvg = this.X_Mtx.AvgOfRow(j);
/* 675:431 */       double x_MtxVariance = Math.sqrt(this.X_Mtx.VarianceOfRow(j));
/* 676:433 */       for (int i = 0; i < this.Y_Num; i++)
/* 677:    */       {
/* 678:435 */         this.Coefficient[i][0] -= this.Coefficient[i][(j + 1)] * x_MtxAvg / x_MtxVariance;
/* 679:436 */         this.Coefficient[i][(j + 1)] /= x_MtxVariance;
/* 680:    */       }
/* 681:    */     }
/* 682:441 */     double[][] thisx = new double[this.X_Num + 1][this.Sample_Num];
/* 683:442 */     for (int j = 0; j < this.Sample_Num; j++)
/* 684:    */     {
/* 685:444 */       thisx[0][j] = 1.0D;
/* 686:445 */       for (int i = 1; i < this.X_Num + 1; i++) {
/* 687:448 */         thisx[i][j] = this.X_Mtx.GetData(i - 1, j);
/* 688:    */       }
/* 689:    */     }
/* 690:452 */     MatrixMath thisx_Mtx = new MatrixMath(thisx);
/* 691:    */     
/* 692:454 */     MatrixMath coefficient_Mtx = new MatrixMath(this.Coefficient);
/* 693:    */     
/* 694:456 */     MatrixMath fitted_Mtx = MatrixMath.Multiply(coefficient_Mtx, thisx_Mtx);
/* 695:457 */     this.Fitted = fitted_Mtx.get_Data();
/* 696:    */     
/* 697:    */ 
/* 698:    */ 
/* 699:    */ 
/* 700:462 */     FillModelCalResult();
/* 701:    */     
/* 702:464 */     return true;
/* 703:    */   }
/* 704:    */   
/* 705:    */   private void FullRegression(MatrixMath x_Mtx, MatrixMath y_Mtx, int x_Num, int y_Num, int sample_Num)
/* 706:    */   {
/* 707:479 */     double[][] x = new double[x_Num + 1][sample_Num];
/* 708:481 */     for (int i = 0; i < sample_Num; i++)
/* 709:    */     {
/* 710:483 */       x[0][i] = 1.0D;
/* 711:484 */       for (int j = 0; j < x_Num; j++) {
/* 712:487 */         x[(j + 1)][i] = (x_Mtx.GetData(j, i) - x_Mtx.GetData(j, 0));
/* 713:    */       }
/* 714:    */     }
/* 715:491 */     MatrixMath xF_Mtx = new MatrixMath(x);
/* 716:    */     
/* 717:    */ 
/* 718:494 */     MatrixMath g1_Mtx = xF_Mtx.TransposMultiply(false);
/* 719:    */     
/* 720:    */ 
/* 721:497 */     MatrixMath g2_Mtx = g1_Mtx.MatInvert();
/* 722:    */     
/* 723:    */ 
/* 724:500 */     xF_Mtx.set_Transpos(true);
/* 725:501 */     MatrixMath g_Mtx = MatrixMath.Multiply(xF_Mtx, g2_Mtx);
/* 726:    */     
/* 727:    */ 
/* 728:504 */     double[][] gy = new double[y_Num][sample_Num];
/* 729:506 */     for (int i = 0; i < y_Num; i++) {
/* 730:508 */       for (int j = 0; j < sample_Num; j++) {
/* 731:510 */         gy[i][j] = (y_Mtx.GetData(i, j) - y_Mtx.GetData(i, 0));
/* 732:    */       }
/* 733:    */     }
/* 734:514 */     MatrixMath gy_Mtx = new MatrixMath(gy);
/* 735:    */     
/* 736:    */ 
/* 737:517 */     MatrixMath coeff_Mtx = MatrixMath.Multiply(gy_Mtx, g_Mtx);
/* 738:    */     
/* 739:519 */     this.Coefficient = coeff_Mtx.get_Data();
/* 740:522 */     for (int i = 0; i < y_Num; i++)
/* 741:    */     {
/* 742:524 */       this.Constant[i] = this.Coefficient[i][0];
/* 743:525 */       this.Coefficient[i][0] += y_Mtx.GetData(i, 0);
/* 744:527 */       for (int j = 0; j < x_Num; j++) {
/* 745:528 */         this.Coefficient[i][0] -= this.Coefficient[i][(j + 1)] * x_Mtx.GetData(j, 0);
/* 746:    */       }
/* 747:    */     }
/* 748:532 */     for (int i = 0; i < sample_Num; i++) {
/* 749:534 */       for (int j = 0; j < x_Num; j++) {
/* 750:535 */         x[(j + 1)][i] = x_Mtx.GetData(j, i);
/* 751:    */       }
/* 752:    */     }
/* 753:539 */     xF_Mtx.set_Transpos(false);
/* 754:540 */     MatrixMath titted_Mtx = MatrixMath.Multiply(coeff_Mtx, xF_Mtx);
/* 755:543 */     for (int i = 0; i < y_Num; i++) {
/* 756:545 */       this.Correlation[i] = MatrixMath.CorrelationOfRow(y_Mtx, i, titted_Mtx, i);
/* 757:    */     }
/* 758:551 */     titted_Mtx.set_K(-1.0D);
/* 759:    */     
/* 760:    */ 
/* 761:554 */     MatrixMath gg2F_Mtx = MatrixMath.Add(y_Mtx, titted_Mtx);
/* 762:    */     
/* 763:556 */     MatrixMath gg3_Mtx = gg2F_Mtx.TransposMultiply(false);
/* 764:559 */     for (int j = 0; j < y_Num; j++) {
/* 765:560 */       this.Residual[j] = (gg3_Mtx.GetData(j, j) / (sample_Num - x_Num - 1));
/* 766:    */     }
/* 767:562 */     this.Fitted = titted_Mtx.get_Data();
/* 768:    */   }
/* 769:    */ }


/* Location:           D:\NARI\葛洲坝\test.jar
 * Qualified Name:     com.nari.slsd.he.Calculate.strv.MPartialRegression
 * JD-Core Version:    0.7.0.1
 */