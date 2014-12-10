package com.virginia.taobao;

import org.eclipse.swt.widgets.Display;

/**
 *
 * @author liul
 */
public class DisplayThread extends Thread {
 
    private Display display;
    Object sem = new Object();
 
    public void run() {
        synchronized (sem) {
            display = Display.getDefault();
            sem.notifyAll();
        }
        swtEventLoop();
    }
 
    private void swtEventLoop() {
        while (true) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
    }
 
    public Display getDisplay() {
        try {
            synchronized (sem) {
                while (display == null) {
                    sem.wait();
                }
                return display;
            }
        } catch (Exception e) {
            return null;
        }
    }
}