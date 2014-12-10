package com.virginia.taobao;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SWTMain extends JPanel{

	public SWTMain(){
		jbInit();
	}
	
	public void jbInit(){
		this.setLayout(new BorderLayout());
	    SWTPane jbtn_Sel = new SWTPane();
        add(jbtn_Sel, BorderLayout.CENTER);
        jbtn_Sel.setBounds(1, 1, 600, 600);
        
        add(new JButton("see me"), BorderLayout.NORTH);
	}
	
	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		frame.add(new SWTMain());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800,600);
		frame.setVisible(true);
	}
}