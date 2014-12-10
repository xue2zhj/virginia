package com.virginia.taobao;

import java.io.IOException;
import java.net.URL;
import javax.swing.*;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class HtmlBrowser extends JFrame {
	JPanel con;
	BorderLayout borderall = new BorderLayout();
	JLabel lbpro = new JLabel();
	JPanel pmain = new JPanel();
	BorderLayout bordermain = new BorderLayout();
	JTextField txtURL = new JTextField();
	JEditorPane edpane = new JEditorPane();

	public HtmlBrowser() {
		try {
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void jbInit() throws Exception {
		con = (JPanel) getContentPane();
		con.setLayout(borderall);
		pmain.setLayout(bordermain);
		lbpro.setText("������������URL");
		txtURL.setText("");
		txtURL.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtURL_actionPerformed(e);
			}
		});
		edpane.setEditable(false);
		edpane.addHyperlinkListener(new javax.swing.event.HyperlinkListener() {
			public void hyperlinkUpdate(HyperlinkEvent e) {
				edpane_hyperlinkUpdate(e);
			}
		});
		JScrollPane sp = new JScrollPane();
		sp.getViewport().add(edpane);
		pmain.add(txtURL, "North");
		pmain.add(sp, "Center");
		con.add(lbpro, "North");
		con.add(pmain, "Center");
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
		this.setSize(new Dimension(600, 500));
		this.setTitle("��ҳ�����");
		this.setVisible(true);
	}

	void txtURL_actionPerformed(ActionEvent e) {
		try {
			edpane.setPage(txtURL.getText());
		} catch (IOException ex) {
			JOptionPane msg = new JOptionPane();
			JOptionPane.showMessageDialog(this, "����ȷ��URL��ַ" + txtURL.getText(),
					"����ȷ������", 0);
		}
	}

	void edpane_hyperlinkUpdate(HyperlinkEvent e) {
		if (e.getEventType() == javax.swing.event.HyperlinkEvent.EventType.ACTIVATED) {
			try {

				URL url = e.getURL();
				edpane.setPage(url);
				txtURL.setText(url.toString());
			} catch (IOException io) {
				JOptionPane msg = new JOptionPane();
				JOptionPane.showMessageDialog(this, "���ܴ�����", "����ȷ������", 0);
			}
		}
	}

	protected void processWindowEvent(WindowEvent e) {
		super.processWindowEvent(e);
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			System.exit(0);
		}
	}

	public static void main(String[] args) {
		new HtmlBrowser();
	}

}
