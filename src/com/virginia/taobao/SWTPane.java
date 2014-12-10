package com.virginia.taobao;

import java.awt.BorderLayout;  
import java.awt.Canvas;  
import java.awt.Panel;  
import org.eclipse.swt.SWT;  
import org.eclipse.swt.awt.SWT_AWT;  
import org.eclipse.swt.browser.Browser;  
import org.eclipse.swt.layout.FillLayout;  
   
import org.eclipse.swt.widgets.Display;  
import org.eclipse.swt.widgets.Shell;  
   
/** 
 * 
 * @author liujl 
 */ 
public class SWTPane extends Panel {  
   
    DisplayThread displayThread;  
    private Canvas canvas;  
   
    public SWTPane() {  
        displayThread = new DisplayThread();  
        displayThread.start();  
        canvas = new Canvas();  
        setLayout(new BorderLayout());  
        add(canvas, BorderLayout.CENTER);  
    }  
 public static void main(String args[]) throws Exception {  
   
     java.awt.EventQueue.invokeLater(new Runnable() {  
   
            public void run() {  
                new SWTPane().setVisible(true);  
            }  
        });  
        
 }  
    public void addNotify() {  
        super.addNotify();  
        Display dis = displayThread.getDisplay();  
        dis.syncExec(new Runnable() {  
   
            public void run() {  
                Shell shell = SWT_AWT.new_Shell(displayThread.getDisplay(), canvas);  
                shell.setLayout(new FillLayout());  
                final Browser browser = new Browser(shell, SWT.NONE);  
                browser.setLayoutData(BorderLayout.CENTER);  
                browser.setUrl("http://www.baidu.com");  
            }  
        });  
    }  
}  