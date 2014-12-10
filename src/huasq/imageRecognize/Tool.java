/*  1:   */ package huasq.imageRecognize;
/*  2:   */ 
/*  3:   */ import java.awt.image.BufferedImage;
/*  4:   */ import java.io.File;
/*  5:   */ import javax.imageio.ImageIO;
/*  6:   */ 
/*  7:   */ public class Tool
/*  8:   */ {
/*  9:   */   public static double[][] getGuassianFilter(int radio, double sigma)
/* 10:   */   {
/* 11:18 */     if (radio < 0) {
/* 12:19 */       return null;
/* 13:   */     }
/* 14:20 */     double[][] filters = new double[2 * radio + 1][];
/* 15:21 */     double sum = 0.0D;
/* 16:22 */     double sigma22 = 2.0D * sigma * sigma;double cov = sigma22 * 3.141592653589793D;
/* 17:23 */     for (int i = 0; i < 2 * radio + 1; i++)
/* 18:   */     {
/* 19:24 */       filters[i] = new double[2 * radio + 1];
/* 20:25 */       for (int j = 0; j < 2 * radio + 1; j++)
/* 21:   */       {
/* 22:26 */         filters[i][j] = (Math.exp(-((i - radio) * (i - radio) + (j - radio) * (j - radio)) / sigma22) / cov);
/* 23:27 */         sum += filters[i][j];
/* 24:   */       }
/* 25:   */     }
/* 26:30 */     for (int i = 0; i < 2 * radio + 1; i++) {
/* 27:31 */       for (int j = 0; j < 2 * radio + 1; j++) {
/* 28:32 */         filters[i][j] /= sum;
/* 29:   */       }
/* 30:   */     }
/* 31:35 */     return filters;
/* 32:   */   }
/* 33:   */   
/* 34:   */   public static double[][] getGrayImage(BufferedImage image)
/* 35:   */   {
/* 36:   */     try
/* 37:   */     {
/* 38:40 */       int width = image.getWidth();
/* 39:41 */       int height = image.getHeight();
/* 40:42 */       double[][] results = new double[width][];
/* 41:43 */       BufferedImage grayImage = new BufferedImage(width, height, 10);
/* 42:45 */       for (int i = 0; i < width; i++)
/* 43:   */       {
/* 44:46 */         results[i] = new double[height];
/* 45:47 */         for (int j = 0; j < height; j++)
/* 46:   */         {
/* 47:48 */           int rgb = image.getRGB(i, j);
/* 48:49 */           grayImage.setRGB(i, j, rgb);
/* 49:50 */           results[i][j] = (0.299D * (rgb >> 16 & 0xFF) + 0.587D * (rgb >> 8 & 0xFF) + 0.114D * (rgb & 0xFF));
/* 50:   */         }
/* 51:   */       }
/* 52:53 */       File newFile = new File("D:\\1.jpg");
/* 53:54 */       ImageIO.write(grayImage, "jpg", newFile);
/* 54:55 */       return results;
/* 55:   */     }
/* 56:   */     catch (Exception e)
/* 57:   */     {
/* 58:57 */       e.printStackTrace();
/* 59:   */     }
/* 60:59 */     return null;
/* 61:   */   }
/* 62:   */   
/* 63:   */   public static double[][] GuassianFilter(double[][] grays, double[][] filters)
/* 64:   */   {
/* 65:   */     try
/* 66:   */     {
/* 67:64 */       double[][] results = new double[grays.length][];
/* 68:66 */       for (int i = 0; i < grays.length; i++)
/* 69:   */       {
/* 70:67 */         results[i] = new double[grays[i].length];
/* 71:68 */         for (int j = 0; j < grays[i].length; j++)
/* 72:   */         {
/* 73:69 */           for (int k = -(filters.length - 1) / 2; k <= (filters.length - 1) / 2; k++)
/* 74:   */           {
/* 75:70 */             int indexH = j + k > grays[i].length - 1 ? grays[i].length - 1 : j + k < 0 ? 0 : j + k;
/* 76:71 */             for (int l = -(filters.length - 1) / 2; l <= (filters.length - 1) / 2; l++)
/* 77:   */             {
/* 78:72 */               int indexV = i + k > grays.length - 1 ? grays.length - 1 : i + k < 0 ? 0 : i + k;
/* 79:73 */               results[i][j] += grays[indexV][indexH] * filters[(l + (filters.length - 1) / 2)][(k + (filters.length - 1) / 2)];
/* 80:   */             }
/* 81:   */           }
/* 82:76 */           results[i][j] = (results[i][j] > 255.0D ? 255.0D : results[i][j] < 0.0D ? 0.0D : results[i][j]);
/* 83:   */         }
/* 84:   */       }
/* 85:79 */       return results;
/* 86:   */     }
/* 87:   */     catch (Exception e)
/* 88:   */     {
/* 89:81 */       e.printStackTrace();
/* 90:   */     }
/* 91:83 */     return null;
/* 92:   */   }
/* 93:   */ }


/* Location:           D:\NARI\葛洲坝\test.jar
 * Qualified Name:     huasq.imageRecognize.Tool
 * JD-Core Version:    0.7.0.1
 */